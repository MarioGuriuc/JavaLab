package app;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    private Locale currentLocale;

    public void execute(String languageTag, ResourceBundle messages) {
        currentLocale = Locale.forLanguageTag(languageTag);
        System.out.println(messages.getString("locale.set").replace("{0}", currentLocale.toString()));
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
}
