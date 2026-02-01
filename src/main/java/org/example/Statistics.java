package org.example;
import java.util.*;

public class Statistics {
    // Хранит уникальные страницы с кодом ответа 200
    private final Set<String> pages = new HashSet<>();

    // Хранит количество упоминаний каждой ОС
    private final Map<String, Integer> osCount = new HashMap<>();

    // Общее количество записей (для расчёта долей)
    private int totalEntries = 0;

    /**
     * Добавляет запись в статистику.
     * @param page URL страницы
     * @param responseCode код ответа (например, 200)
     * @param os операционная система пользователя
     */
    public void addEntry(String page, int responseCode, String os) {
        // Добавляем страницу, если код ответа 200
        if (responseCode == 200) {
            pages.add(page);
        }

        // Обновляем счётчик ОС
        osCount.put(os, osCount.getOrDefault(os, 0) + 1);
        totalEntries++;
    }

    /**
     * Возвращает список всех существующих страниц сайта (с кодом 200).
     * @return Set<String> набор URL страниц
     */
    public Set<String> getAllPages() {
        return new HashSet<>(pages); // Возвращаем копию для потокобезопасности
    }

    /**
     * Возвращает статистику операционных систем в виде долей (от 0 до 1).
     * @return Map<String, Double> где ключ — ОС, значение — доля её упоминаний
     */
    public Map<String, Double> getOsStatistics() {
        Map<String, Double> osShares = new HashMap<>();

        for (Map.Entry<String, Integer> entry : osCount.entrySet()) {
            String os = entry.getKey();
            int count = entry.getValue();
            double share = (double) count / totalEntries; // Доля ОС
            osShares.put(os, share);
        }

        return osShares;
    }
}

