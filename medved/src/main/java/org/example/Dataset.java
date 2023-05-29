package org.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dataset {
    public Tokenizer tokenizer;
    public String fileName;
    public String rawData;
    public Map<Integer, String> token2string;
    public Map<String, Integer> string2token;

    public Dataset(String fileName) {
        this.fileName = fileName;
        token2string = new HashMap<>();
        tokenizer = new Tokenizer(this);
        string2token = new HashMap<>();
    }

    private void load() throws IOException {
        rawData = Loader.load(fileName);
    }

    public void setup() throws IOException {
        load();
        tokenizer.setup(rawData);
    }
}
