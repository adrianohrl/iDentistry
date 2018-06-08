package tech.adrianohrl.util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class CurrencyUtil {
    
    private static final Locale LOCALE = PropertyUtil.getDefaultLocale();
    private static final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(LOCALE);
    
    private CurrencyUtil() {    
    }
    
    public static String format(double amount) {
        return FORMATTER.format(amount);
    }
    
}
