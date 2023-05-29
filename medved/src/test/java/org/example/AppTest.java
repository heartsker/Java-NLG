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
import java.util.List;

public class AppTest
{
    private Dataset dataset;
    @BeforeEach
    void Init() {

        dataset = new Dataset("file.txt");
        try {
            dataset.setup();
        } catch (IOException e) {
            e.printStackTrace();
            Assertions.assertTrue( false );
        }
    }
    @Test
    public void testDetokenizeReverts()
    {
        List<Integer> tokenized = dataset.tokenizer.tokenize(dataset.rawData);
        String detokenized = dataset.tokenizer.detokenize(tokenized);
        Assertions.assertEquals(dataset.rawData, detokenized);
    }
    @Disabled
    public void testTokenizeReverts()
    {
        List<Integer> tokensMock = Arrays.asList(new Integer[]{2, 4, 1, 3, 1, 2, 0, 1});
        String detokenized = dataset.tokenizer.detokenize(tokensMock);
        List<Integer> tokenized = dataset.tokenizer.tokenize(detokenized);
        detokenized = dataset.tokenizer.detokenize(tokenized);
        Assertions.assertEquals(tokensMock, tokenized);
    }
}
