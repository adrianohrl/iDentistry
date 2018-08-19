package tech.adrianohrl.identistry.model.payment;

import tech.adrianohrl.util.CurrencyUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import tech.adrianohrl.identistry.exceptions.iDentistryException;
import tech.adrianohrl.util.CalendarFormat;
import tech.adrianohrl.util.CalendarUtil;
import tech.adrianohrl.util.PropertyUtil;

/**
 *
 * @author adrianohrl Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class PaymentSlip implements Iterable<Payment> {
    
    private long code;
    private Calendar contractDate = CalendarUtil.now();
    private Payment input;
    private List<Payment> payments = new ArrayList<>();
    private double amount;
    private double interestRate = 0.0;

    public PaymentSlip() {
    }
    
    public PaymentSlip(double amount, Payment input, int numberOfInstallments, double interestRate, Calendar firstPayday) {
        this.amount = amount;
        this.input = input;
        if (input != null) {
            input.setObs("Input payment of a " + CurrencyUtil.format(amount) + " payment slip.");
        }
        this.interestRate = interestRate;        
        generatePayments(numberOfInstallments, firstPayday);
    }
    
    private void generatePayments(int numberOfInstallments, Calendar firstPayday) {
        if (numberOfInstallments <= 0) {
            throw new iDentistryException("Invalid number of installment.");
        }
        Calendar now = new GregorianCalendar(PropertyUtil.getDefaultLocale());
        if (now.after(firstPayday)) {
            throw new iDentistryException("Invalid payment slip payday.");
        }
        double installmentAmount = getInstallmentAmount(numberOfInstallments, firstPayday);
        Calendar dueDate = firstPayday != null ? (Calendar) firstPayday.clone() : null;
        for (int i = 0; i < numberOfInstallments; i++) {
            String obs = "Installment payment of a " + CurrencyUtil.format(amount) + " payment slip (" + i + 1 + " of " + numberOfInstallments + ")";
            Calendar date = null;
            if (dueDate != null) {
                date = (Calendar) dueDate.clone();
                dueDate.add(Calendar.MONTH, 1);
            }
            payments.add(new Payment(Types.PAYMENT_SLIP, null, date, installmentAmount, 0.0, obs));
        }
    }
    
    private double getInstallmentAmount(int numberOfInstallments, Calendar firstPayday) {
        int numberOfDays = firstPayday != null ? CalendarUtil.getDaysBetween(contractDate, firstPayday) : 0;
        int numberOfMonths = numberOfInstallments - (input != null ? 1 : 0);
        double remainingAmount = amount - (input != null ? input.getAmount() : 0.0);
        return LoanUtil.getInstallment(remainingAmount, numberOfDays, numberOfMonths, 100 * interestRate);
    }
    
    public double getInstallmentAmount() {
        int numberOfDays = payments.get(0).getDueDate() != null ? CalendarUtil.getDaysBetween(contractDate, payments.get(0).getDueDate()) : 0;
        int numberOfMonths = payments.size() - (input != null ? 1 : 0);
        double remainingAmount = amount - (input != null ? input.getAmount() : 0.0);
        return LoanUtil.getInstallment(remainingAmount, numberOfDays, numberOfMonths, 100 * interestRate);
    }
    
    public boolean isPaid() {
        for (Payment payment : this) {
            if (!payment.isPaid()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isExpired() {
        for (Payment payment : this) {
            if (payment.isExpired()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Payment> iterator() {
        List<Payment> allPayments = new ArrayList();
        allPayments.add(input);
        allPayments.addAll(payments);
        return allPayments.iterator();
    }

    @Override
    public String toString() {
        return CalendarFormat.format(contractDate) + ": " + input + " + " + payments.size() + " x " + CurrencyUtil.format(getInstallmentAmount());
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Payment getInput() {
        return input;
    }

    public void setInput(Payment input) {
        this.input = input;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Calendar getContractDate() {
        return contractDate;
    }

    public void setContractDate(Calendar contractDate) {
        this.contractDate = contractDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
}
