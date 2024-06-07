package app;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public void execute(Locale locale, ResourceBundle messages) {
        ResourceBundle bundle = ResourceBundle.getBundle("app.Messages", locale);
        System.out.println(bundle.getString("info").replace("{0}", locale.toString()));

        Currency currency = Currency.getInstance(locale);
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, locale);

        System.out.println("Country: " + locale.getDisplayCountry() + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println("Language: " + locale.getDisplayLanguage() + " (" + locale.getDisplayLanguage(locale) + ")");
        System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName(locale) + ")");
        System.out.println("Week Days: " + String.join(", ", dfs.getWeekdays()));
        System.out.println("Months: " + String.join(", ", dfs.getMonths()));
        System.out.println("Today: " + df.format(System.currentTimeMillis()));
    }
}
