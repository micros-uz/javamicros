package mikeyp.spikes.configuration;

import javax.inject.Inject;

import mikeyp.spikes.spring.social.auth.AuthenticationService;
import mikeyp.spikes.spring.social.auth.ProviderSignInAdapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.config.xml.SpringSecurityAuthenticationNameUserIdSource;
import org.springframework.social.config.xml.UserIdSource;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.twitter.config.annotation.EnableTwitter;

@Configuration
@EnableJdbcConnectionRepository
@EnableTwitter(appId="${twitter.consumerKey}", appSecret="${twitter.consumerSecret}")
public class SocialConfiguration {

	
	@Inject
	private ConnectionFactoryLocator connectionFactoryLocator;
	
	@Inject 
	private ConnectionRepository connectionRepository;

	@Inject 
	private UsersConnectionRepository usersConnectionRepository;

	@Bean
	public ConnectController connectController() {
		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		return connectController;
	}

	@Bean
	public AuthenticationService authService(){
		return new AuthenticationService(); 
	}
	@Bean
	public ProviderSignInController providerSignInController(AuthenticationService authService,RequestCache requestCache) {
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new ProviderSignInAdapter(authService,requestCache));
	}
	
	
	@Bean
	public UserIdSource userIdSource() {
		return new SpringSecurityAuthenticationNameUserIdSource();
	}

}
