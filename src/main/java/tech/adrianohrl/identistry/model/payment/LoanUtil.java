package tech.adrianohrl.identistry.model.payment;

/**
 *
 * @author adrianohrl Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class LoanUtil {
    
    /**
     * This method helps to calculate the Loan Installment.
     * 
     * @param q the loan amount.
     * @param m the number of days.
     * @param n the number of months.
     * @param i the daily interest rate [%].
     * @return the 
     */
    public static double getInstallment(double q, int m, int n, double i) {
        if (m > 30) {
            n += m / 30;
            m %= 30;
        }
        if (i <= 0.0) {
            return q / (n + (m != 0 ? 1 : 0));
        }
        double j = getDailyInterest(i);
        return q * (m * j + n * i) / (m + n) / (1 - Math.pow(1 + j, -m) * Math.pow(1 + i, -n));
    }
    
    /**
     * This method helps to calculate the daily interest based on the monthly one.
     * 
     * @param monthly the monthly interest rate.
     * @return the daily interest rate.
     */
    public static double getDailyInterest(double monthly) {
        return monthly / 30;
    }
    
    /**
     * This method helps to calculate the monthly interest based on the annually one.
     * 
     * @param annually the annually interest rate.
     * @return the monthly interest rate. 
     */
    public static double getMonthlyInterest(double annually) {
        return annually / 360;
    }
    
}
