function FeedPoller(){
	
	var self = this; 
	
	var obj = {
			
			startFeed : function(url ,feedParams, interval, callback){
				
				var defaultedFeedParams = feedParams || {}; 
				self[url] = self[url] || {} ; 
				self[url].intervalHandle = window.setInterval(function(){$.get(url, feedParams,callback)},interval); 
			},
			stopFeed : function(url){
				if(self[url]  && self[url].intervalHandle != null)
				{
					clearInterval(self[url].intervalHandle) ;
				}
			}
	}
	
	return obj; 
}