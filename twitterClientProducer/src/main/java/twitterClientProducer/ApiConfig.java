package twitterClientProducer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.twitter.clientlib.TwitterCredentialsOAuth2;
import com.twitter.clientlib.api.TwitterApi;

public class ApiConfig {
	
	public static TwitterApi getApiConfig(
			
			) throws FileNotFoundException, IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try (InputStream resourceStream = loader.getResourceAsStream("application.properties")) {
		    properties.load(resourceStream);
		} catch (IOException e) {
		    e.printStackTrace();
		}return new TwitterApi(new TwitterCredentialsOAuth2(
				properties.getProperty("TWITTER_OAUTH2_CLIENT_ID"),
				properties.getProperty("TWITTER_OAUTH2_CLIENT_SECRET"),
				properties.getProperty("TWITTER_OAUTH2_ACCESS_TOKEN"),
				properties.getProperty("TWITTER_OAUTH2_REFRESH_TOKEN")));
	}

}
