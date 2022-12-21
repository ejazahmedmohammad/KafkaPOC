package twitterClientProducer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.JSON;
import com.twitter.clientlib.model.AddOrDeleteRulesRequest;
import com.twitter.clientlib.model.AddRulesRequest;
import com.twitter.clientlib.model.Get2TweetsIdResponse;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;
import com.twitter.clientlib.model.RuleNoId;

public class TweetReader {
	
	public static void main(String[] args) throws ApiException, FileNotFoundException, IOException {
		
		var apiInstance = ApiConfig.getApiConfig();
		
		Set<String> tweetFields = new HashSet<>();
	    tweetFields.add("author_id");
	    tweetFields.add("id");
	    tweetFields.add("created_at");
	    RuleNoId rule = new RuleNoId();
	    rule.setTag("telangana");
		AddRulesRequest y = new AddRulesRequest().addAddItem(rule);
		AddOrDeleteRulesRequest x = new AddOrDeleteRulesRequest(y);
		apiInstance.tweets().addOrDeleteRules(x).execute();
		
		TweetsStreamListenersExecutor tsle = new TweetsStreamListenersExecutor();
	      tsle.stream().streamingHandler(new StreamingTweetHandlerImpl(apiInstance)).executeListeners(); 
		
		}
}
