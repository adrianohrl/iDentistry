package tech.adrianohrl.identistry.util;

import java.net.URL;

/**
 *
 * @author Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class PropertyUtil {
    
    private PropertyUtil() {
    }
    
    public static String getDefaultProfilePicturePath() {
        String path = tech.adrianohrl.util.PropertyUtil.getProperty("images.default.profile.picture");
        ClassLoader classLoader = PropertyUtil.class.getClassLoader();
        URL url = classLoader.getResource(path);
        return url != null ? url.getFile() : "";
    }
    
    public static double getLowPasswordEntropyThreshold() {
        String threshold = tech.adrianohrl.util.PropertyUtil.getProperty("general.config.password.entropy.threshold.low");
        return Double.parseDouble(threshold);
    }
    
    public static double getHighPasswordEntropyThreshold() {
        String threshold = tech.adrianohrl.util.PropertyUtil.getProperty("general.config.password.entropy.threshold.high");
        return Double.parseDouble(threshold);
    }
    
    public static double getDefaultPaymentInstallmentPenaltyRate() {
        String rate = tech.adrianohrl.util.PropertyUtil.getProperty("general.config.payment.installment.penalty.rate");
        return Double.parseDouble(rate);
    }
    
    public static double getDefaultPaymentInstallmentPenaltyPerDay() {
        String penaltyPerDay = tech.adrianohrl.util.PropertyUtil.getProperty("general.config.payment.installment.penalty.per-day");
        return Double.parseDouble(penaltyPerDay);
    }
    
}
