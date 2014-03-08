/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mikeyp.spikes.controller.signup;


import javax.inject.Inject;
import javax.validation.Valid;

import mikeyp.spikes.account.Account;
import mikeyp.spikes.account.AccountRepository;
import mikeyp.spikes.account.UsernameAlreadyInUseException;
import mikeyp.spikes.controller.message.Message;
import mikeyp.spikes.controller.message.MessageType;
import mikeyp.spikes.spring.social.auth.AuthenticationService;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;


@Controller
public class SignupController {

	private final AccountRepository accountRepository;
	private final AuthenticationService authService; 
	@Inject
	public SignupController(AccountRepository accountRepository, AuthenticationService authService) {
		this.accountRepository = accountRepository;
		this.authService = authService; 
	}

	
	@ModelAttribute("signupForm")
	public SignupForm createForm(WebRequest request){
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		SignupForm form = null; 
		if (connection != null) {
			request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			form=  SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			form= new SignupForm();
		}
		return form; 
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signupForm(WebRequest request) {
		return "signup";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		Account account = createAccount(form, formBinding);
		if (account != null) {
			authService.signin(account.getUsername());
			ProviderSignInUtils.handlePostSignUp(account.getUsername(), request);
			return "redirect:/";
		}
		return null;
	}

	
	private Account createAccount(SignupForm form, BindingResult formBinding) {
		try {
			Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName());
			accountRepository.createAccount(account);
			return account;
		} catch (UsernameAlreadyInUseException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	}

}
