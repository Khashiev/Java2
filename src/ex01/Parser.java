package src.ex01;

import java.io.*;
import java.util.*;

public class Parser {
    private final Set<String> dictionary = new HashSet<>();
    private final Vector<String> file1Words = new Vector<>();
    private final Vector<String> file2Words = new Vector<>();

    public Parser(String file1, String file2) {
        fillDictionary(file1, file1Words);
        fillDictionary(file2, file2Words);
    }

    private void fillDictionary(String fileName, Vector<String> fileWords) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                Collections.addAll(this.dictionary, words);
                Collections.addAll(fileWords, words);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeDictionary() {
        String outputFileName = "src/ex01/dictionary.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (String word : dictionary) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public double getSimilarity() {
        int dictionarySize = dictionary.size();
        String[] dictionaryArray = dictionary.toArray(new String[dictionarySize]);
        double numerator = 0;
        double denominator1 = 0;
        double denominator2 = 0;

        for (int i = 0; i < dictionarySize; ++i) {
            int count1 = countOfOccurrences(file1Words, dictionaryArray[i]);
            int count2 = countOfOccurrences(file2Words, dictionaryArray[i]);

            numerator += count1 * count2;
            denominator1 += count1 * count1;
            denominator2 += count2 * count2;
        }

        double denominator = Math.sqrt(denominator1) * Math.sqrt(denominator2);
        return numerator / denominator;
    }

    private int countOfOccurrences(Vector<String> file, String word) {
        int count = 0;
        for (String string : file) {
            if (string.equals(word)) {
                ++count;
            }
        }
        return count;
    }
}
