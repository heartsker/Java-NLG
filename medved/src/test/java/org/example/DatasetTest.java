package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DatasetTest
{
    private String filename = "datasets/test.txt";
    @Test
    public void testLoader()
    {
        String loaded = "";
        try {
            loaded = Loader.load(filename);
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.assertTrue( false );
        }
        Assertions.assertEquals("hello world! hello, world?\r\n" +
                "test words: coffee, milk, tea;", loaded);
    }
    @Test
    public void testDatasetSetup()
    {
        try {
            Dataset dataset = new Dataset(filename);
            dataset.setup();
            dataset.rawData = "hi i am test hi";
            Map<Integer, String> expectedTokens2Str = new HashMap<>();
            expectedTokens2Str.put(0, "hello");
            expectedTokens2Str.put(1, " ");
            expectedTokens2Str.put(2, "world");
            expectedTokens2Str.put(3, "!");
            expectedTokens2Str.put(4, ",");
            expectedTokens2Str.put(5, "?");
            expectedTokens2Str.put(6, "\r");
            expectedTokens2Str.put(7, "\n");
            expectedTokens2Str.put(8, "test");
            expectedTokens2Str.put(9, "words");
            expectedTokens2Str.put(10, ":");
            expectedTokens2Str.put(11, "coffee");
            expectedTokens2Str.put(12, "milk");
            expectedTokens2Str.put(13, "tea");
            expectedTokens2Str.put(14, ";");
            Map<String, Integer> expectedStr2Tokens = new HashMap<>();
            expectedStr2Tokens.put("hello", 0);
            expectedStr2Tokens.put(" ", 1);
            expectedStr2Tokens.put("world", 2);
            expectedStr2Tokens.put("!", 3);
            expectedStr2Tokens.put(",", 4);
            expectedStr2Tokens.put("?", 5);
            expectedStr2Tokens.put("\r", 6);
            expectedStr2Tokens.put("\n", 7);
            expectedStr2Tokens.put("test", 8);
            expectedStr2Tokens.put("words", 9);
            expectedStr2Tokens.put(":", 10);
            expectedStr2Tokens.put("coffee", 11);
            expectedStr2Tokens.put("milk", 12);
            expectedStr2Tokens.put("tea", 13);
            expectedStr2Tokens.put(";", 14);
            Assertions.assertEquals(expectedTokens2Str, dataset.token2string);
            Assertions.assertEquals(expectedStr2Tokens, dataset.string2token);
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.assertTrue( false );
        }
    }
}
