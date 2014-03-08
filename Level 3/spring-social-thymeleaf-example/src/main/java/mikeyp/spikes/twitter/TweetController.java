package mikeyp.spikes.twitter;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.sort;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;


@Controller
@RequestMapping("/user")
public class TweetController {


	@Autowired
	private TwitterService twitterService; 
	
	@Autowired
	private Twitter twitter ;
	
	@RequestMapping("/twitterLanding")
	public String landingPageTwitter(){
		
		return "twitterLanding"; 
	}
	
	
	@RequestMapping("/getFriends")
	@ResponseBody
	public List<TwitterProfile> nonAsyncGetFriends(WebRequest request)
	{
		return twitterService.getFriends(twitter); 
			
	}
	
	@RequestMapping("/tweetUpdates")
	@ResponseBody
	public Callable<List<Tweet>> tweetUpdates(Principal user, final WebRequest request ,final @RequestParam Collection<Long> friendIds){
		
		final TimelineOperations timeline = twitter.timelineOperations(); 
		final String username = user.getName() ; 
		return new Callable<List<Tweet>>(){

			public List<Tweet> call() throws Exception {
				List<Tweet> tweets = sort(twitterService.getFeedUpdates(timeline,username,friendIds), on(Tweet.class).getCreatedAt(),Collections.reverseOrder());
				return tweets; 
			}
			
		};
	}
	
	@RequestMapping(value= "/sphere",method=RequestMethod.POST)
	public String buildSphere(WebRequest request,ModelMap model) 
	{
		Iterator<String> params = request.getParameterNames(); 
		Set<Long> ids = new HashSet<Long> (); 
		while(params.hasNext())
		{
			String idRaw = params.next(); 
			ids.add(Long.parseLong(idRaw)); 
		}
		
		model.put("idsInSphere",ids); 
		model.addAttribute("idsInSphere",ids); 
		
		return "twitterSphere"; 
	}
	
}
