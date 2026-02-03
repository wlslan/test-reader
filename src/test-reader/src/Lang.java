import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lang {
    enum Language {
        LATVIAN,
    }
    private static Map<Language,String> langFiles = new HashMap<>();
    static {
        langFiles.put(Language.LATVIAN,"Latvian");
    }
    private static Map<String,String> lang;
    public static void setLanguage(Language language) {
        if (!langFiles.containsKey(language)) {
            throw new IllegalStateException("Unexpected value: " + language);
        }
        Scanner scanner = new Scanner(new File(langFiles.get(language)+".csv"));
        while (scanner.hasNextLine()) {

        }
    }
    public static String Get(String key) {
        return lang.get(key);
    }
}
