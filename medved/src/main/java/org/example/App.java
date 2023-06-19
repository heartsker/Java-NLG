package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static Dataset dataset;
    private static NGramManager nGramManager;
    private final static Integer defaultMaxNGramDepth = 5;
    private final static String defaultDatasetPath = "datasets/war_peace.txt";

    private final static Integer defaultMaxLength = 100;

    public static void main(String[] args) throws IOException {

        // Setup default dataset

        String datasetPath = System.getProperty("dataset");

        if (datasetPath == null) {
            datasetPath = defaultDatasetPath;
        }

        String depthString = System.getProperty("depth");

        Integer depth = defaultMaxNGramDepth;

        if (depthString != null) {
            depth = Integer.parseInt(depthString);
        }

        String lenghtString = System.getProperty("lenght");

        Integer lenght = defaultMaxNGramDepth;

        if (lenghtString != null) {
            lenght = Integer.parseInt(lenghtString);
        }

        String prompt = String.join(" ", args);

        setupDataset(datasetPath, depth);

        handlePrompt(prompt, lenght);
    }

    private static void setupDataset(String filename, Integer depth) throws IOException {
        System.out.println("Setting up dataset from \"" + filename + "\". Max depth is set to " + depth);

        // Setup dataset

        dataset = new Dataset(filename);
        dataset.setup();

        List<Integer> tokenized = dataset.tokenizer.tokenize(dataset.rawData);

        // Setup NGramManager
        nGramManager = new NGramManager(tokenized);

        nGramManager.buildNGrams(depth);


        System.out.println("Successfully set up dataset from " + filename);
    }

    private static void handlePrompt(String prompt, Integer length) {
        List<Integer> tokens = dataset.tokenizer.tokenize(prompt);

        Generator generator = new Generator(tokens, nGramManager);

        List<Integer> generatedTokens = generator.generate(length);
        String generatedText = dataset.tokenizer.detokenize(generatedTokens);

        System.out.println("==== Generated text from your prompt ====");
        System.out.println(generatedText);
        System.out.println("=========================================");
    }
}
