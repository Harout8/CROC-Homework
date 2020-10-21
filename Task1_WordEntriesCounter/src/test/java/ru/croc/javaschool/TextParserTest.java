package ru.croc.javaschool;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


public class TextParserTest {

    @Test
    public void testCountWordsEntries() {
        String text = "We will we will rock you, We will we will rock you !";
        TextParser textParser = new TextParser();

        Map<String, Integer> wordsAndCount = new HashMap<String, Integer>();
        wordsAndCount.put("We", 2);
        wordsAndCount.put("will", 4);
        wordsAndCount.put("we", 2);
        wordsAndCount.put("rock", 2);
        wordsAndCount.put("you", 2);

        Assertions.assertEquals(wordsAndCount, textParser.countWordsEntries(text));
    }
}
