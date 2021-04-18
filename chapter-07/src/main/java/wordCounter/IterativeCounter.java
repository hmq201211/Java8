package wordCounter;

import wordCounter.counter.Counter;

public class IterativeCounter implements Counter {

    @Override
    public int wordCount(String words) {
        int count = 0;
        boolean lastIsSpace = true;
        for (Character c : words.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastIsSpace = true;
            } else {
                if (lastIsSpace) count++;
                lastIsSpace = false;
            }
        }
        return count;
    }
}
