package wordCounter;

import wordCounter.counter.Counter;
import wordCounter.counter.WordCounter;
import wordCounter.spliterator.WordCounterSpliterator;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ParallelStreamCounter implements Counter {
    @Override
    public int wordCount(String words) {
        return StreamSupport.stream(new WordCounterSpliterator(words), true).reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine).getCount();
    }
}
