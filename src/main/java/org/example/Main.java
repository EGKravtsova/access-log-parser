package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int correctFilesCount = 0; // Счетчик корректных файлов

        while (true) {
            System.out.print("Введите путь к файлу: ");
            String filePath = scanner.nextLine();

            File file = new File(filePath);
            boolean fileExists = file.exists(); // Проверяем существование файла
            boolean isFile = file.isFile();     // Проверяем, является ли путь файлом

            if (!fileExists) {
                System.out.println("Файл не существует!");
                continue;
            }

            if (!isFile) {
                System.out.println("Указан путь к папке, а не к файлу!");
                continue;
            }

            // Если дошли до этого места, значит файл существует и это действительно файл
            correctFilesCount++;
            System.out.println("Путь указан верно");
            System.out.println("Это файл номер " + correctFilesCount);
        }
    }
}
