package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NGramManager {

    final private List<Integer> source;
    final private Map<List<Integer>, Map<Integer, Integer>> ngrams = new HashMap<>();

    public NGramManager(List<Integer> source) {
        this.source = source;
    }

    public List<Integer> getMostFrequentNextTokens(List<Integer> ngram) {
        Map<Integer, Integer> nextTokens = ngrams.get(ngram);
        if (nextTokens == null) {
            return null;
        }
        List<Integer> mostFrequentNextTokens = null;
        Integer mostFrequentNextTokenCount = 0;
        for (Map.Entry<Integer, Integer> nextToken : nextTokens.entrySet()) {
            if (nextToken.getValue() > mostFrequentNextTokenCount) {
                mostFrequentNextTokens = new ArrayList<>();
                mostFrequentNextTokens.add(nextToken.getKey());

                mostFrequentNextTokenCount = nextToken.getValue();
            } else if (nextToken.getValue().equals(mostFrequentNextTokenCount)) {
                if (mostFrequentNextTokens == null) {
                    mostFrequentNextTokens = new ArrayList<>();
                }
                mostFrequentNextTokens.add(nextToken.getKey());
            }
        }
        return mostFrequentNextTokens;
    }

    public void buildNGrams(Integer upTo) {
        for (int windowSize = 1; windowSize <= upTo; windowSize++) {
            for (int i = 0; i < source.size() - windowSize; i++) {
                List<Integer> ngram = source.subList(i, i + windowSize);

                Integer nextToken = source.get(i + windowSize);

                Integer nextTokenCount = ngrams.getOrDefault(ngram, new HashMap<>()).getOrDefault(nextToken, 0);
                Map<Integer, Integer> newValue = new HashMap<>();
                newValue.put(nextToken, nextTokenCount + 1);

                ngrams.put(ngram, ngrams.getOrDefault(ngram, newValue));
            }
        }
    }
}
