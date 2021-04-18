package wordCounter.test;

import wordCounter.IterativeCounter;
import wordCounter.ParallelStreamCounter;
import wordCounter.StreamCounter;

public class Test {
    public static void main(String[] args) {
        final String words = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
        System.out.println("new IterativeCounter().wordCount(words) = " + new IterativeCounter().wordCount(words));
        System.out.println("new StreamCounter().wordCount(words) = " + new StreamCounter().wordCount(words));
        System.out.println("new ParallelStreamCounter().wordCount(words) = " + new ParallelStreamCounter().wordCount(words));
    }
}
