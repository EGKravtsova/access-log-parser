package org.example;

public class AccessLogParser {
    // Собственное исключение для слишком длинных строк
    public static class LineTooLongException extends RuntimeException {
        public LineTooLongException(String message) {
            super(message);
        }
    }
}
