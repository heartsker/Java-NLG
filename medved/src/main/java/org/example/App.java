package org.example;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Dataset dataset = new Dataset("test.txt");
        dataset.setup();
        List<Integer> tokenized = dataset.tokenizer.tokenize(dataset.rawData);
        System.out.println(dataset.tokenizer.detokenize(tokenized));
    }
}
