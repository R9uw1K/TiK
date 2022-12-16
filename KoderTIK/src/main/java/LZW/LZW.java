package LZW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZW {
    public static String getLZW(String text, String alph) {

        Map<String, Integer> combinations = new HashMap<>();
        StringBuilder modAlph = new StringBuilder();
        int sizeAlph = alph.length();
        int sizeText = text.length();

        List<Integer> result = new ArrayList<>();
        int ind = -1;

        //убираем пробелы в строке алфавита
        for (int i = 0; i < alph.length(); i++) {
            if (i % 2 == 0) {
                modAlph.append(alph.charAt(i));
            }
        }

        sizeAlph = modAlph.length();
        int numberCode = sizeAlph;

        //создаем комбинации и их коды из алфавита
        for (int i = 0; i < sizeAlph; i++) {
            combinations.put(String.valueOf(modAlph.charAt(i)), i);
        }

        for (int j = 0; j < sizeText; j++) {
            String st = String.valueOf(text.charAt(0));
            StringBuilder words = new StringBuilder();

            //ищем наибольшее слово
            for (int i = j; i < sizeText; i++) {
                words.append(text.charAt(i));
                for (String s : combinations.keySet()) {
                    if (words.toString().equals(s)) {
                        st = words.toString();
                        ind = i;
                    }
                }
            }

            //кладем код
            for (Map.Entry<String, Integer> r : combinations.entrySet()) {
                if (st.equals(r.getKey())) {
                    result.add(r.getValue());
                }
            }

            if ((ind + 1) < sizeText) {
                //ищем новое слово для комбинаций
                StringBuilder newWord = new StringBuilder();
                newWord.append(st);
                newWord.append(text.charAt(ind+1));

                //добавляем новое слово в комбинации
                combinations.put(newWord.toString(), numberCode);
                numberCode++;


            }

            j += st.length() - 1;
        }

        //двоичное представление
        List<String> strResult = new ArrayList<>();
        for (Integer i : result) {
            strResult.add(Integer.toBinaryString(i));
        }

        StringBuilder res = new StringBuilder();
        for (String i : strResult) {
            if (i.length() < 8) {
                StringBuilder r = new StringBuilder();
                int t = 8 - i.length();
                for (int j = 0; j < t; j++) {
                    r.append("0");
                }
                r.append(i);
                res.append(r);
            }
        }

        return res.toString();
    }
}
