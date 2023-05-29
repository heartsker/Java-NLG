package org.example;

import java.io.IOException;
import java.util.ArrayList;
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

        System.out.println("============NGRAMS============");

//        String text = "The quick brown fox jumps over the lazy dog";
//        List<Integer> tokenizedText = dataset.tokenizer.tokenize(text);
//
//        System.out.println("Text: " + text);
//        System.out.println("Tokenized text: " + tokenizedText);

        NGramManager nGramManager = new NGramManager(tokenized);

        nGramManager.buildNGrams(5);
        nGramManager.logNGrams();

        String testText = "The quick brown ";
        List<Integer> tokens = dataset.tokenizer.tokenize(testText);

        System.out.println("Test text: " + testText);
        System.out.println("Tokenized test text: " + tokens);

        List<Integer> nextTokens = nGramManager.getMostFrequentNextTokens(tokens);

        List<String> nextWords = dataset.tokenizer.detokenizeSeparate(nextTokens);

        System.out.println("For text [" + testText + "] the most frequent next tokens are: " + nextWords);

        System.out.println("============GENERATING============");

        Generator generator = new Generator(tokens, nGramManager);

        List<Integer> generatedTokens = generator.generate(20);
        List<String> generatedText = dataset.tokenizer.detokenizeSeparate(generatedTokens);

        System.out.println("Prompt: [" + testText + "]");
        System.out.println("Generated text: " + generatedText);
    }
}
