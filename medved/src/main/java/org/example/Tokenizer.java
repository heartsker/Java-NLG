package org.example;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private final Dataset storage;

    public Tokenizer(Dataset storage) {
        this.storage = storage;
    }

    public void setup(String rawData) {
        String[] words = split(rawData);
        for (String word : words) {
            if (storage.string2token.containsKey(word)) {
                continue;
            }
            int count = storage.string2token.size();
            storage.string2token.put(word, count);
            storage.token2string.put(count, word);
        }
    }

    public List<Integer> tokenize(String string) {
        String[] words = split(string);
        List<Integer> tokens = new ArrayList<>();
        for (String word : words) {
            if (storage.string2token.containsKey(word)) {
                tokens.add(storage.string2token.get(word));
            }
        }
        return tokens;
    }

    public String detokenize(List<Integer> tokens) {
        List<String> words = new ArrayList<>();
        for (Integer token : tokens) {
            if (storage.token2string.containsKey(token)) {
                words.add(storage.token2string.get(token));
            }
        }
        return String.join("", words);
    }

    private String[] split(String string) {
        return string.toLowerCase().split("(?<=\\W)|(?=\\W)");
    }
}
