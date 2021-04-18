package wordCounter;

import wordCounter.counter.Counter;
import wordCounter.counter.WordCounter;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCounter implements Counter {
    @Override
    public int wordCount(String words) {
        Stream<Character> characterStream = IntStream.range(0, words.length()).mapToObj(words::charAt);
        WordCounter reduce = characterStream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return reduce.getCount();
    }

}
