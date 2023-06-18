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

    private static Integer maxLength = 100;

    public static void main(String[] args) throws IOException {

        // Setup default dataset

        String datasetPath = defaultDatasetPath;

        if (args.length > 1) {
            datasetPath = args[1];
        }

        setupDatasetWithDefaultDepth(datasetPath);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            String command = reader.readLine();

            // Setup new dataset
            if (command.split(" ")[0].equals("setup")) {
                datasetPath = command.split(" ")[1];

                setupDatasetWithDefaultDepth(datasetPath);
                continue;
            }

            // Setup new maxDepth
            if (command.split(" ")[0].equals("depth")) {
                int depth = Integer.parseInt(command.split(" ")[1]);

                setupDataset(datasetPath, depth);
                System.out.println("Set depth to " + depth);
                continue;
            }

            // Setup max length
            if (command.split(" ")[0].equals("length")) {
                maxLength = Integer.parseInt(command.split(" ")[1]);
                System.out.println("Set depth to " + maxLength);
                continue;
            }

            // Exit
            if (command.equals("exit") || command.equals("quit")) {
                System.out.println("Quiting the application");
                break;
            }

            // New prompt

            handlePrompt(command);
        }
    }

    private static void setupDatasetWithDefaultDepth(String filename) throws IOException {
        setupDataset(filename, defaultMaxNGramDepth);
    }

    private static void setupDataset(String filename, Integer depth) throws IOException {
        System.out.println("Setting up dataset from " + filename);

        // Setup dataset

        dataset = new Dataset(filename);
        dataset.setup();

        List<Integer> tokenized = dataset.tokenizer.tokenize(dataset.rawData);

        // Setup NGramManager
        nGramManager = new NGramManager(tokenized);

        nGramManager.buildNGrams(depth);


        System.out.println("Successfully set up dataset from " + filename);
    }

    private static void handlePrompt(String prompt) {
        List<Integer> tokens = dataset.tokenizer.tokenize(prompt);

        Generator generator = new Generator(tokens, nGramManager);

        List<Integer> generatedTokens = generator.generate(maxLength);
        String generatedText = dataset.tokenizer.detokenize(generatedTokens);

        System.out.println("==== Generated text from your prompt ====");
        System.out.println(generatedText);
        System.out.println("=========================================");
    }
}
