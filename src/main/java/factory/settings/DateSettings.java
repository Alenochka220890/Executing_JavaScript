package factory.settings;

import java.time.LocalDate;
import java.util.Random;

public class DateSettings {

    public static String formatForInput(int day, int month, int year) {
        return String.format("%02d.%02d.%d", day, month, year);
    }

    public static String formatForOutput(int day, int month, int year) {
        return String.format("%d-%02d-%02d", year, month, day);
    }

    public static String[] generateRandomDate() {
        Random random = new Random();

        // Генерируем случайный год
        int year = (int) (Math.random() * 56) + 1950;
        // Генерируем случайный месяц
        int month = 1 + random.nextInt(12);

        // Генерируем случайный день с учетом количества дней в месяце
        LocalDate date = LocalDate.of(year, month, 1);
        int daysInMonth = date.lengthOfMonth();
        int day = 1 + random.nextInt(daysInMonth);

        String inputDate = DateSettings.formatForInput(day, month, year);
        String expectedOutput = DateSettings.formatForOutput(day, month, year);
        return new String[]{inputDate, expectedOutput};
    }


}
