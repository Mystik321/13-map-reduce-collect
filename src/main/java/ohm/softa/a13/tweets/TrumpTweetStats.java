package ohm.softa.a13.tweets;

import ohm.softa.a13.model.Tweet;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrumpTweetStats {

    public static Map<String, Long> calculateSourceAppStats(Stream<Tweet> tweetStream) {
        /* TODO group the tweets by the `sourceApp` they were created with and count how many it were per `sourceApp` */
        return tweetStream.collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.counting()));
    }

    public static Map<String, Set<Tweet>> calculateTweetsBySourceApp(Stream<Tweet> tweetStream) {
        /* TODO group the tweets by the `sourceApp`
         * collect the tweets in `Set`s for each source app */
        return tweetStream.collect(Collectors.groupingBy(Tweet::getSourceApp, Collectors.toSet()));
    }

    public static Map<String, Integer> calculateWordCount(Stream<Tweet> tweetStream, List<String> stopWords) {
        /* Remark: implement this method at last */
        /* TODO split the tweets, lower them, trim them, remove all words that are in the `stopWords`,
         * reduce the result to a Map<String, Integer> to count how often each word were in the tweets
         * optionally you could filter for all words that were used more than 10 times */
        return tweetStream
			.map(Tweet::getText)
			.map(x -> x.split(" "))
			.flatMap(Arrays::stream)
			.map(String::toLowerCase)
			.map(String::trim)
			.filter(x -> !stopWords.contains(x))
			.reduce(new HashMap<String, Integer>(), new BiFunction<HashMap<String, Integer>, String, HashMap<String, Integer>>() {
				@Override
				public HashMap<String, Integer> apply(HashMap<String, Integer> stringIntegerHashMap, String s) {
					if (!stringIntegerHashMap.containsKey(s))
						stringIntegerHashMap.put(s, 1);
					else
						stringIntegerHashMap.put(s ,stringIntegerHashMap.get(s) + 1);

					return stringIntegerHashMap;
				}
			}, (m1, m2) -> m1);
    }
}
