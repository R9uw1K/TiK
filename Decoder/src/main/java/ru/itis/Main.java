package ru.itis;

import ru.itis.util.FileUtil;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String PATH_FILE = "C:\\Users\\raush\\Desktop\\Raushan\\Decoder\\src\\main\\resources\\input.txt";
    public static void main(String[] args) throws IOException {
        System.out.printf("Decoded text: %s", Fano.decodeFano( FileUtil.downloadFromFile(PATH_FILE)));
    }
}
