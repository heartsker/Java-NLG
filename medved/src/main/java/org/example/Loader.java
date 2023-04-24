package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader {
    public static String load(String fileName) throws IOException {
        return Files.readString(Paths.get(fileName));
    }
}
