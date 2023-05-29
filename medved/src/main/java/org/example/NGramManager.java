package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NGramManager {

    final private List<Integer> source;
    final private Map<List<Integer>, Map<Integer, Integer>> ngrams = new HashMap<>();
    private Integer maxNGramSize = 0;

    public NGramManager(List<Integer> source) {
        this.source = source;
    }

    public List<Integer> getMostFrequentNextTokens(List<Integer> ngram) {
        List<Integer> mostFrequentNextTokens = null;

        for (int windowSize = Integer.min(ngram.size(), maxNGramSize); windowSize > 0; windowSize--) {
            List<Integer> subList = ngram.subList(ngram.size() - windowSize, ngram.size());
            if (ngrams.containsKey(subList)) {
                Map<Integer, Integer> nextTokens = ngrams.get(subList);
                Integer maxCount = 0;
                List<Integer> bestNextTokens = new ArrayList<>();
                for (Map.Entry<Integer, Integer> entry : nextTokens.entrySet()) {
                    if (entry.getValue().equals(maxCount)) {
                        bestNextTokens.add(entry.getKey());
                    }
                    if (entry.getValue() > maxCount) {
                        maxCount = entry.getValue();
                        bestNextTokens.clear();
                        bestNextTokens.add(entry.getKey());
                    }
                }
                mostFrequentNextTokens = bestNextTokens;
                break;
            }
        }

        return mostFrequentNextTokens;
    }

    public void buildNGrams(Integer upTo) {
        maxNGramSize = upTo;
        for (int windowSize = 1; windowSize <= upTo; windowSize++) {
            for (int i = 0; i < source.size() - windowSize; i++) {
                List<Integer> ngram = source.subList(i, i + windowSize);

                Integer nextToken = source.get(i + windowSize);

                Integer nextTokenCount = ngrams.getOrDefault(ngram, new HashMap<>()).getOrDefault(nextToken, 0);

                Map<Integer, Integer> newValue = ngrams.getOrDefault(ngram, new HashMap<>());

                newValue.put(nextToken, nextTokenCount + 1);

                ngrams.put(ngram, newValue);
            }
        }
    }

    public void logNGrams() {
        System.out.println("NGrams:");
        for (Map.Entry<List<Integer>, Map<Integer, Integer>> entry : ngrams.entrySet()) {
            System.out.println("\t" + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
