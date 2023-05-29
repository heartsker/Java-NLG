package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NGramManagerTest
{
    @Test
    public void testNGramManager()
    {
        List<Integer> source = new ArrayList<Integer>();
        List<Integer> input = new ArrayList<Integer>();
        List<Integer> got = new ArrayList<Integer>();

        for (int i = 0; i < 10; ++i) {
            source.add(i);
            if (i < 5) {
                input.add(i);
                got.add(i);
            }
        }

        NGramManager nGramManager = new NGramManager(source);
        nGramManager.buildNGrams(10);

        for (int i = 5; i < 10; ++i) {
            Integer nextToken = nGramManager.getMostFrequentNextTokens(input).get(0);
            got.add(nextToken);
            input.add(nextToken);
        }
        Assertions.assertEquals(source, got);
    }
}
