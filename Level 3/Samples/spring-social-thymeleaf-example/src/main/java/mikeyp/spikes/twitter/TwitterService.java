package mikeyp.spikes.twitter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Component;

@Component
public class TwitterService {

	
	public Set<Tweet> getFeedUpdates(TimelineOperations timelineOperations, String user,Collection<Long> friendIds) {

		Set<Tweet> updates = new HashSet<Tweet>();
		for(Long friendId : friendIds){
			List<Tweet> tweets = timelineOperations.getUserTimeline(friendId); 
			updates.addAll(tweets); 
		}
		return updates; 
	}




	public CursoredList<TwitterProfile> getFriends(Twitter twitter) {
		return twitter.friendOperations().getFriends(); 
	}

}
