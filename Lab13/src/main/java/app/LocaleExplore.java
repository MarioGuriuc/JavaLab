package app;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SetLocale setLocale = new SetLocale();
        DisplayLocales displayLocales = new DisplayLocales();
        Info info = new Info();
        ResourceBundle messages = ResourceBundle.getBundle("app.Messages", Locale.getDefault());

        while (true) {
            System.out.print(messages.getString("prompt"));
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("locales")) {
                displayLocales.execute(messages);
            }
            else if (command.startsWith("setlocale")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    setLocale.execute(parts[1], messages);
                    messages = ResourceBundle.getBundle("app.Messages", setLocale.getCurrentLocale());
                }
                else {
                    System.out.println(messages.getString("invalid"));
                }
            }
            else if (command.equalsIgnoreCase("info")) {
                info.execute(setLocale.getCurrentLocale(), messages);
            }
            else {
                System.out.println(messages.getString("invalid"));
            }
        }
    }
}
