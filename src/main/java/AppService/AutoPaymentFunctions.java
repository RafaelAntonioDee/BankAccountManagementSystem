package AppService;

import DataService.AutoPaymentService;
import Objects.AutoPayments;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AutoPaymentFunctions {

    public static void createAutoPayment(
            String email,
            String payee,
            double amount,
            String frequency,
            String date
    ) {

        AutoPayments payment = new AutoPayments();
        payment.setPayee(payee);
        payment.setAmount(amount);
        payment.setFrequency(frequency);
        payment.setActive(true);

        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime newDate = LocalDateTime.parse(date, dateFormat);
            payment.setPaymentDate(newDate);
        } catch (Exception e) {
            payment.setPaymentDate(LocalDateTime.now());
        }

        AutoPaymentService.addAutoPayment(email, payment);
    }

    public static ArrayList<AutoPayments> getAllPayments(String email) {
        return AutoPaymentService.getAllAutoPayments(email);
    }
}