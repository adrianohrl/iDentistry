package tech.adrianohrl.identistry.model.payment;

/**
 *
 * @author adrianohrl Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public enum Types {
    
    PAYMENT_SLIP("Payment Slip"),
    CREDIT_CARD("Credit Card"),
    CHEQUE("Cheque"),
    DEBIT_CARD("Debit Card"),
    CASH("Cash");
    
    private final String name;

    private Types(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public boolean isPaymentSlip() {
        return this == PAYMENT_SLIP;
    }
    
    public boolean isCreditCard() {
        return this == CREDIT_CARD;
    }
    
    public boolean isCheque() {
        return this == CHEQUE;
    }
    
    public boolean isDebitCard() {
        return this == DEBIT_CARD;
    }
    
    public boolean isCash() {
        return this == CASH;
    }
    
}
