package org.example;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Dataset dataset = new Dataset("file.txt");
        dataset.setup();
        System.out.println("============RAW==DATA============");
        System.out.println(dataset.rawData);
        List<Integer> tokenized = dataset.tokenizer.tokenize(dataset.rawData);
        System.out.println("============TOKENIZED============");
        System.out.println(tokenized);
        System.out.println("===========DETOKENIZED===========");
        String detokenized = dataset.tokenizer.detokenize(tokenized);
        System.out.println(detokenized);
    }
}
