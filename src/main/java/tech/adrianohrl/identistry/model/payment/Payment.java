package tech.adrianohrl.identistry.model.payment;

import tech.adrianohrl.util.CurrencyUtil;
import java.util.Calendar;
import java.util.GregorianCalendar;
import tech.adrianohrl.util.CalendarFormat;

/**
 *
 * @author adrianohrl Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class Payment {
    
    private long code;
    private Types type;
    private Calendar paymentDate;
    private Calendar dueDate;
    private double amount;
    private double tendered;
    private String obs;

    public Payment() {
    }
    
    public Payment(Types type, Calendar paymentDate, Calendar dueDate, double amount, double tendered, String obs) {
        this.type = type;
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
        this.amount = amount;
        this.tendered = tendered;
        this.obs = obs;
    }

    @Override
    public String toString() {
        return type + ": " + CurrencyUtil.format(amount) + " (" + (isPaid() ? "paid" : "unpaid, due " + CalendarFormat.formatDate(dueDate)) + ")";
    }
    
    public boolean isExpired() {
        Calendar now = new GregorianCalendar();
        return paymentDate == null && dueDate != null && now.after(dueDate);
    }
    
    public boolean isPaid() {
        return paymentDate != null;
    }
    
    public double calculateChange() {
        return tendered - amount;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Calendar getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Calendar paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTendered() {
        return tendered;
    }

    public void setTendered(double tendered) {
        this.tendered = tendered;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
}
