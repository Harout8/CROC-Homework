package ru.croc.javaschool;


import java.util.HashMap;
import java.util.Map;


public class TextParser {

    public Map countWordsEntries(String text) {
        char[] chars = text.toCharArray();
        String word = "";
        int count = 0;
        Map<String, Integer> wordsAndCount = new HashMap<String, Integer>();

        for (int i = 0; i < chars.length; i++) {
           if (isLetter(chars[i])) {
               word += chars[i];
           } else {
               addWord(wordsAndCount, word, count);
               word = "";
           }
        }

        addWord(wordsAndCount, word, count);

        return wordsAndCount;
    }

    private boolean isLetter(char letter) {
        return (((letter >= 65) && (letter <= 90))
                | ((letter >= 97) && (letter <= 122)));
    }

    private void addWord(Map<String, Integer> wordsAndCount, String word, int count) {
        if (!word.equals("")) {
            if (wordsAndCount.containsKey(word)) {
                count = wordsAndCount.get(word) + 1;
                wordsAndCount.put(word, count);
            } else {
                wordsAndCount.put(word, 1);
            }
        }
    }
}
