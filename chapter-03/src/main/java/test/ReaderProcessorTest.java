package test;

import interfaces.BufferedReaderProcessor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReaderProcessorTest {
    private static String processFile(BufferedReaderProcessor bufferedReaderProcessor) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("chapter-03/src/main/resources/test.txt"))) {
            return bufferedReaderProcessor.process(bufferedReader);
        }
    }

    public static void main(String[] args) throws IOException {
        String oneLine = processFile(BufferedReader::readLine);
        String doubleLine = processFile((BufferedReader bufferedReader) -> {
            return bufferedReader.readLine() + bufferedReader.readLine();
        });
        System.out.println(oneLine);
        System.out.println(doubleLine);
    }
}
