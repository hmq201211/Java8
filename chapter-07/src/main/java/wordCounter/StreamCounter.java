package wordCounter;

import wordCounter.counter.Counter;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCounter implements Counter {
    @Override
    public int wordCount(String words) {
        Stream<Character> characterStream = IntStream.range(0, words.length()).mapToObj(words::charAt);
        WordCounter reduce = characterStream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        return reduce.getCount();
    }

    private static class WordCounter {
        private final int count;
        private final boolean lastIsSpace;

        private WordCounter(int count, boolean lastIsSpace) {
            this.count = count;
            this.lastIsSpace = lastIsSpace;
        }

        public WordCounter accumulate(Character character) {
            if (Character.isWhitespace(character)) {
                return lastIsSpace ? this : new WordCounter(count, true);
            } else {
                return lastIsSpace ? new WordCounter(count + 1, false) : this;
            }
        }

        public WordCounter combine(WordCounter wordCounter) {
            return new WordCounter(count + wordCounter.count, wordCounter.lastIsSpace);
        }

        public int getCount() {
            return count;
        }
    }
}
