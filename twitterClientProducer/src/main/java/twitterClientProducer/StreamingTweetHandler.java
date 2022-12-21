package twitterClientProducer;

import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.ConnectionExceptionProblem;
import com.twitter.clientlib.model.OperationalDisconnectProblem;
import com.twitter.clientlib.model.Problem;
import com.twitter.clientlib.model.StreamingTweetResponse;


public abstract class StreamingTweetHandler extends StreamingHandler<StreamingTweetResponse> {
  public StreamingTweetHandler(TwitterApi apiInstance) {
    super(apiInstance);
  }

  @Override
  public StreamingTweetResponse getStreamingObject(String tweetString) throws Exception {
    return StreamingTweetResponse.fromJson(tweetString);
  }

  @Override
  public boolean hasReconnectErrors(StreamingTweetResponse streamingTweet) {
    boolean needToReconnect = false;
    if (streamingTweet.getErrors() != null) {
      for (Problem problem : streamingTweet.getErrors()) {
        if (problem instanceof OperationalDisconnectProblem || problem instanceof ConnectionExceptionProblem) {
          System.err.println("Re-connecting to the stream due to: " + problem);
          needToReconnect = true;
          break;
        }
      }
    }
    return needToReconnect;
  }
}