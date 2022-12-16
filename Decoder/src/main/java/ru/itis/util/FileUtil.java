package ru.itis.util;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileUtil {
    public static Map<String,Double> downloadFromFile(String filePath) throws IOException {
        Map<String, Double> map = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String firstLine = reader.readLine();
        String secondLine = reader.readLine();
        String[] letters = firstLine.split(" ");
        String[] probabilities = secondLine.split(" ");
        for(int i = 0 ; i < letters.length; i++) {
            map.put(letters[i], Double.valueOf(probabilities[i]));
        }
        reader.close();
        return map;
    }
}
