import java.util.*;

public class Statistics {
    // Существующие страницы (код 200)
    private final Set<String> existingPages = new HashSet<>();

    // Несуществующие страницы (код 404)
    private final Set<String> nonExistingPages = new HashSet<>();

    // Счётчик операционных систем (из задания #1)
    private final Map<String, Integer> osCount = new HashMap<>();
    private int totalOsEntries = 0;

    // Счётчик браузеров (новое требование)
    private final Map<String, Integer> browserCount = new HashMap<>();
    private int totalBrowserEntries = 0;

    /**
     * Добавляет запись в статистику.
     * @param page URL страницы
     * @param responseCode код ответа (например, 200, 404)
     * @param os операционная система
     * @param browser браузер пользователя
     */
    public void addEntry(String page, int responseCode, String os, String browser) {
        // 1. Существующие страницы (200)
        if (responseCode == 200) {
            existingPages.add(page);
        }

        // 2. Несуществующие страницы (404)
        if (responseCode == 404) {
            nonExistingPages.add(page);
        }

        // 3. Статистика ОС (как в задании #1)
        osCount.put(os, osCount.getOrDefault(os, 0) + 1);
        totalOsEntries++;

        // 4. Статистика браузеров (новое)
        browserCount.put(browser, browserCount.getOrDefault(browser, 0) + 1);
        totalBrowserEntries++;
    }

    /**
     * Возвращает список всех несуществующих страниц (код 404).
     * @return Set<String> набор URL несуществующих страниц
     */
    public Set<String> getNonExistingPages() {
        return new HashSet<>(nonExistingPages); // Копия для безопасности
    }

    /**
     * Возвращает статистику браузеров в виде долей (от 0 до 1).
     * @return Map<String, Double> где ключ — браузер, значение — его доля
     */
    public Map<String, Double> getBrowserStatistics() {
        Map<String, Double> browserShares = new HashMap<>();

        for (Map.Entry<String, Integer> entry : browserCount.entrySet()) {
            String browser = entry.getKey();
            int count = entry.getValue();
            double share = (double) count / totalBrowserEntries; // Доля браузера
            browserShares.put(browser, share);
        }

        return browserShares;
    }

    // Методы из задания #1 (для полноты)
    public Set<String> getExistingPages() {
        return new HashSet<>(existingPages);
    }

    public Map<String, Double> getOsStatistics() {
        Map<String, Double> osShares = new HashMap<>();
        for (Map.Entry<String, Integer> entry : osCount.entrySet()) {
            String os = entry.getKey();
            int count = entry.getValue();
            double share = (double) count / totalOsEntries;
            osShares.put(os, share);
        }
        return osShares;
    }
}
