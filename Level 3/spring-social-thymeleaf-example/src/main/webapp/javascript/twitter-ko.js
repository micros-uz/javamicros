function twitter(){
	
	
	var self = this; 
	self.friendProfiles = ko.observable() ;
	$.get("getFriends", {},self.friendProfiles); 

	
};

function twitterSphere(ids){
	
	var self = this;  
	self.tweetUpdates = ko.observable(); 
	var feedPoller = FeedPoller();
	$.get("tweetUpdates", {friendIds:ids.join(',')},self.friendProfiles); 
	feedPoller.startFeed("tweetUpdates",{friendIds:ids.join(',')},20000, self.tweetUpdates); 
	
}


function twitterKo(){
	
	ko.applyBindings(new twitter());
}

function twitterKoSphere(ids){
	
	ko.applyBindings(new twitterSphere(ids)); 
}