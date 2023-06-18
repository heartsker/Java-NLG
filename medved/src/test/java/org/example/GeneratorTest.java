package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GeneratorTest
{
    private NGramManager nGramManager;
    private List<Integer> source;
    @BeforeEach
    void Init() {
        source = new ArrayList<Integer>();
        for (int i = 0; i < 10; ++i) {
            source.add(i);
        }

        this.nGramManager = new NGramManager(source);
        this.nGramManager.buildNGrams(10);
    }
    @Test
    public void testGenerator()
    {

        List<Integer> input = new ArrayList<Integer>();
        for (int i = 0; i < 5; ++i) {
            input.add(i);
        }
        Generator generator = new Generator(input, this.nGramManager);

        List<Integer> got = generator.generate(10);

        Assertions.assertEquals(source, got);
    }
}