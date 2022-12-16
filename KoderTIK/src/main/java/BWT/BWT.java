package BWT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BWT {
    public static List<String> getAlgBW(String text) {
        List<String> shiftList = new ArrayList<>();
        int size = text.length();
        List<String> sortedStrings;
        int n = -1;
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String shift = text.substring(i, size) + text.substring(0, i);
            shiftList.add(shift);
        }

        sortedStrings = shiftList.stream().sorted().collect(Collectors.toList());

        for (int i = 0; i < size; i++) {
            sb.append(sortedStrings.get(i).charAt(size - 1));
            if (sortedStrings.get(i).equals(text)) {
                n = i;
            }
        }

        result.add(sb.toString());
        result.add(String.valueOf(n+1));

        return result;
    }
}
