import org.example.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public static void main(String[] args) {
    // Укажите путь к файлу access.log
    String path = "C:/JavaProg/access.log";

    try {
        // 1. Проверяем, что файл существует и это именно файл (не директория)
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Файл не найден: " + path);
        }
        if (!Files.isRegularFile(filePath)) {
            throw new IOException("Указанный путь не является файлом: " + path);
        }

        // 2. Читаем файл построчно
        FileReader fileReader = new FileReader(path);
        BufferedReader reader = new BufferedReader(fileReader);

        int lineCount = 0;
        int maxLength = Integer.MIN_VALUE; // для самой длинной строки
        int minLength = Integer.MAX_VALUE; // для самой короткой строки

        String line;
        while ((line = reader.readLine()) != null) {
            int length = line.length();

            // 3. Проверяем длину строки
            if (length > 1024) {
                throw new AccessLogParser.LineTooLongException(
                        "Найденная строка превышает допустимую длину (1024 символа). " +
                                "Длина текущей строки: " + length + ", номер строки: " + (lineCount + 1)
                );
            }

            // Обновляем счётчики
            lineCount++;
            if (length > maxLength) maxLength = length;
            if (length < minLength) minLength = length;
        }

        // 4. Закрываем ресурсы
        reader.close();
        fileReader.close();

        // 5. Выводим результаты
        System.out.println("Общее количество строк в файле: " + lineCount);
        System.out.println("Длина самой длинной строки: " + maxLength);
        System.out.println("Длина самой короткой строки: " + (minLength == Integer.MAX_VALUE ? 0 : minLength));

    } catch (FileNotFoundException e) {
        System.err.println("Ошибка: Файл не найден.");
        e.printStackTrace();
    } catch (IOException e) {
        System.err.println("Ошибка ввода-вывода при работе с файлом.");
        e.printStackTrace();
    } catch (AccessLogParser.LineTooLongException e) {
        System.err.println("Ошибка: Обнаружена слишком длинная строка.");
        e.printStackTrace();
    } catch (Exception e) {
        System.err.println("Неожиданная ошибка.");
        e.printStackTrace();
    }
}

