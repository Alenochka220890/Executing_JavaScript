package factory.settings;

import java.util.Random;

public class LanguageSettings {

    public static String generateLevellanguage() {

        String[] languageLevels = {"beginner", "intermediate", "advanced", "native"};
        Random random = new Random();
        String randomLevel = languageLevels[random.nextInt(languageLevels.length)];

        return randomLevel;
    }
}
