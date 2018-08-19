package tech.adrianohrl.identistry.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import tech.adrianohrl.identistry.model.individuals.Clinic;
import tech.adrianohrl.identistry.model.individuals.Dentist;
import tech.adrianohrl.identistry.model.individuals.Patient;
import tech.adrianohrl.identistry.model.payment.PaymentSlip;

/**
 *
 * @author Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class ReportUtil {
    
    private static final Logger LOGGER = Logger.getLogger(ReportUtil.class);
    
    private ReportUtil () {
    }
    
    public static void generatePaymentSlip(Clinic clinic, Dentist dentist, Patient patient, String state, PaymentSlip paymentSlip, String service, String path) {
        Map parameters = new HashMap();
        parameters.put("CLINIC", clinic);
        parameters.put("DENTIST", dentist);
        parameters.put("PATIENT", patient);
        parameters.put("STATE", state);
        parameters.put("TOTAL", paymentSlip.getPayments().size());
        parameters.put("PAYABLE", "Pagável apenas em nossa clínica.");
        parameters.put("SERVICE", service);
        int penalty = 2;
        double penaltyPerDay = 0.35;
        parameters.put("PENAULTY_RATE", PropertyUtil.getDefaultPaymentInstallmentPenaltyRate());
        parameters.put("PENAULTY_PER_DAY", PropertyUtil.getDefaultPaymentInstallmentPenaltyPerDay());
        parameters.put("INPUT_AMOUNT", paymentSlip.getInput() != null ? paymentSlip.getInput().getAmount() : 0.0);
        parameters.put("INSTALLMENT_AMOUNT", paymentSlip.getInstallmentAmount());
        parameters.put("TOTAL_AMOUNT", paymentSlip.getAmount());
        String sourceFilename = "PaymentSlip.jasper";
        tech.adrianohrl.util.ReportUtil.generateReport(sourceFilename, paymentSlip.getPayments(), parameters, path);
    }
    
}
