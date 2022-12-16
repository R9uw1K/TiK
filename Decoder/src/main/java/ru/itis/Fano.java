package ru.itis;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Fano {
    public static String decodeFano(Map<String, Double> map){
        Map<String, String> codeMap = getCodeMap(map, "").entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        StringBuilder str = new StringBuilder("");
        for (Map.Entry<String, String> entry:
             codeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.print("Input text: ");
        String input = new Scanner(System.in).nextLine();
        while(input.length() > 0) {
            String prefix = getFirstPrefix(codeMap, input);
            str.append(codeMap.get(prefix));
           input = input.substring(prefix.length());
        }
        return str.toString();
    }

    private static Map<String, String> getCodeMap(Map<String, Double> map, String code) {
        if(map.isEmpty()) throw new IllegalArgumentException("Dictionary could not be empty!");
        if(map.size() == 1) return Map.of(map.keySet().stream().findFirst().get(), code);
        Map<String, Double> firstMap = new HashMap<>();
        double sumProbability = 0.0;
        double averageProbability = map.values().stream().reduce(Double::sum).get() / 2;
        while(sumProbability < averageProbability) {
            Map.Entry<String, Double> entry = Collections.max(map.entrySet(), Comparator.comparingDouble(Map.Entry::getValue));
            Double max = entry.getValue();
            sumProbability+=max;
            if(sumProbability>averageProbability && !firstMap.isEmpty()) break;
            map.remove(entry.getKey());
            firstMap.put(entry.getKey(), entry.getValue());
        }

        return Stream.of(getCodeMap(firstMap, String.format("%s0",code)), getCodeMap(map, String.format("%s1",code)))
                .flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

    }



    private static String getFirstPrefix(Map<String, String> map, String input) {
        for(int i = 1; i<=input.length(); i++) {
            if(map.containsKey(input.substring(0, i))) return input.substring(0, i);
        }
        throw new IllegalArgumentException("Message can not be decoded");
    }
}
