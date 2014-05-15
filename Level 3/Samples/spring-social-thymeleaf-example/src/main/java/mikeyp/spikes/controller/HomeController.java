package mikeyp.spikes.controller;

import java.security.Principal;

import javax.inject.Inject;
import javax.inject.Provider;

import mikeyp.spikes.account.Account;
import mikeyp.spikes.account.AccountRepository;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;

@Controller
public class HomeController {

	private final Provider<ConnectionRepository> connectionRepositoryProvider;

	private final AccountRepository accountRepository;

	@Inject
	public HomeController(Provider<ConnectionRepository> connectionRepositoryProvider, AccountRepository accountRepository) {
		this.connectionRepositoryProvider = connectionRepositoryProvider;
		this.accountRepository = accountRepository;
	}

	@RequestMapping("/")
	public String home(Principal currentUser, Model model) {
		model.addAttribute("connectionsToProviders", getConnectionRepository().findAllConnections());
		
		if(currentUser != null)
		{
			Account account = accountRepository.findAccountByUsername(currentUser.getName());
			if (account != null) {
				model.addAttribute("account", account);
				return ThymeleafViewResolver.REDIRECT_URL_PREFIX+"/user/twitterLanding";
			}
		}
	
		return "anonhome";
	}

	private ConnectionRepository getConnectionRepository() {
		return connectionRepositoryProvider.get();
	}
}
