package wordCounter.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    private final String words;
    private int currentPos = 0;
    private static final int THRESHOLD = 5;

    public WordCounterSpliterator(String words) {
        this.words = words;
    }


    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(words.charAt(currentPos++));
        return currentPos < words.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = words.length() - currentPos;
        if (currentSize < THRESHOLD) {
            return null;
        }
        for (int trySplitPos = currentPos + currentSize / 2; trySplitPos < words.length(); trySplitPos++) {
            if (Character.isWhitespace(words.charAt(trySplitPos))) {
                WordCounterSpliterator wordCounterSpliterator = new WordCounterSpliterator(words.substring(currentPos, trySplitPos));
                currentPos = trySplitPos;
                return wordCounterSpliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return words.length() - currentPos;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
