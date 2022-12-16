package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static BWT.BWT.getAlgBW;
import static LZW.LZW.getLZW;

public class Main {
    public static void main(String[] args) {
        String text;
        Scanner sc = new Scanner(System.in);
        text = sc.nextLine();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\raush\\Desktop\\KoderTIK\\src\\main\\resources\\LZW.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        assert reader != null;
        String alph = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        List<String> algBW = getAlgBW(text);
        String word = algBW.get(0);
        Integer indBW = Integer.valueOf(algBW.get(1));
        String result = getLZW(word, alph);
        System.out.println("Answer:" +  result);
        System.out.println("BWT " + indBW);
    }
}
