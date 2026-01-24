import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public static void main(String[] args) {
    String path = "C:/JavaProg/access.log";

    try {
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Файл не найден: " + path);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            int totalRequests = 0;
            int googlebotCount = 0;
            int yandexbotCount = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                totalRequests++;

                // Ищем User-Agent в конце строки
                int lastQuoteIndex = line.lastIndexOf('"');
                if (lastQuoteIndex == -1) continue;

                String userAgent = line.substring(lastQuoteIndex + 1).trim();
                if (userAgent.isEmpty() || userAgent.equals("-")) continue;

                // Ищем первую пару скобок
                int openBracket = userAgent.indexOf('(');
                int closeBracket = userAgent.indexOf(')');
                if (openBracket == -1 || closeBracket == -1) continue;

                // Извлекаем текст в скобках
                String firstBrackets = userAgent.substring(openBracket + 1, closeBracket);

                // Разделяем по точке с запятой
                String[] parts = firstBrackets.split(";");
                if (parts.length < 2) continue;

                // Очищаем и берем второй фрагмент
                String fragment = parts[1].trim();

                // Ищем слэш
                int slashIndex = fragment.indexOf('/');
                if (slashIndex == -1) continue;

                // Получаем программу до слэша
                String programName = fragment.substring(0, slashIndex).trim();

                // Сравниваем с ботами
                if ("Googlebot".equals(programName)) {
                    googlebotCount++;
                    System.out.println("Найден Googlebot: " + programName);
                } else if ("YandexBot".equals(programName)) {
                    yandexbotCount++;
                    System.out.println("Найден YandexBot: " + programName);
                }
            }

            if (totalRequests > 0) {
                double googlebotRatio = (googlebotCount / (double) totalRequests) * 100;
                double yandexbotRatio = (yandexbotCount / (double) totalRequests) * 100;

                System.out.printf("\nДоля запросов от Googlebot: %.2f%%%n", googlebotRatio);
                System.out.printf("Доля запросов от YandexBot: %.2f%%%n", yandexbotRatio);
            } else {
                System.out.println("Файл не содержит запросов.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла.");
            e.printStackTrace();
        }
    } catch (Exception e) {
        System.err.println("Критическая ошибка: " + e.getMessage());
        e.printStackTrace();
    }
}



