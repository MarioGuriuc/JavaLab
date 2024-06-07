package app;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    public void execute(ResourceBundle messages) {
        Locale[] locales = Locale.getAvailableLocales();
        System.out.println(messages.getString("locales"));
        for (Locale locale : locales) {
            System.out.println(locale.toString() + " " + locale.getDisplayName());
        }
    }
}
