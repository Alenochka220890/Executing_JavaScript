package factory.settings;

public final class UserSettings {

    private static final String USERNAME = System.getProperty("username", "default");
    private static final String EMAIL = System.getProperty("useremail", "default@email.com");
    private static final String PASSWORD = System.getProperty("userpassword", "default");

    // Геттеры
    public static String getUsername() {
        return USERNAME;
    }

    public static String getEmail() {
        return EMAIL;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    // Запрещаем создание экземпляров класса
    private UserSettings() {
    }
}

