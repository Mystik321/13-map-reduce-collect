package ohm.softa.a13;

import ohm.softa.a13.tweets.TrumpTweetStats;
import ohm.softa.a13.tweets.TweetStreamGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Peter Kurfer
 * Created on 1/11/18.
 */
public class App {
	public static void main(String[] args) {
		List<String> stopwords = new ArrayList<>(List.of("is", "are", "I", "have", "has"));
		TweetStreamGenerator gen = TweetStreamGenerator.fromJson("/trump_tweets.json");
		Map<String, Long> m = TrumpTweetStats.calculateSourceAppStats(gen.getTweetStream());
		Map<String, Integer> wordcount = TrumpTweetStats.calculateWordCount(gen.getTweetStream(), stopwords);
		wordcount.forEach((x,y) -> System.out.println(x + ": " + y));
	}
}
