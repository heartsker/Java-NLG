package org.example;

import java.util.List;
import java.util.Random;

public class Generator {

    final private List<Integer> inputTokens;
    private NGramManager nGramManager;

    public Generator (List<Integer> inputTokens, NGramManager nGramManager) {
        this.inputTokens = inputTokens;
        this.nGramManager = nGramManager;
    }

    public List<Integer> generate(Integer maxLength) {
        List<Integer> generatedTokens = inputTokens;

        while (generatedTokens.size() < maxLength) {
            List<Integer> nextTokens = this.nGramManager.getMostFrequentNextTokens(generatedTokens);
            if (nextTokens == null || nextTokens.size() == 0) {
                break;
            }
            Random randomizer = new Random();
            Integer nextToken = nextTokens.get(randomizer.nextInt(nextTokens.size()));
            generatedTokens.add(nextToken);
        }

        return generatedTokens;
    }
}
