package wordCounter.counter;

public class WordCounter {
        private final int count;
        private final boolean lastIsSpace;

        public WordCounter(int count, boolean lastIsSpace) {
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