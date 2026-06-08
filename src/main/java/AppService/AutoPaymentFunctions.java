package AppService;

import DataService.AutoPaymentService;
import Objects.AutoPayment;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AutoPaymentFunctions {

    public static void createAutoPayment(String email, String payee, double amount, String frequency, LocalDate date) {

        AutoPayment payment = new AutoPayment();
        payment.setEmail(email);
        payment.setPayee(payee);
        payment.setAmount(amount);
        payment.setFrequency(frequency);
        payment.setPaymentDate(date);

        String newId = generateNextAutoPayID();
        payment.setAutoPayID(newId);

        AutoPaymentService.addAutoPayment(payment);
    }

    private static String generateNextAutoPayID() {

        int max = 0;

        for (AutoPayment p : AutoPaymentService.getAllAutoPayments()) {

            String id = p.getAutoPayID();

            if (id != null && id.startsWith("AP")) {
                try {
                    int num = Integer.parseInt(id.substring(2));

                    if (num > max) {
                        max = num;
                    }

                } catch (NumberFormatException ignored) {
                }
            }
        }

        int next = max + 1;
        return String.format("AP%04d", next);
    }

    public static ArrayList<AutoPayment> getAllUserPayments(String email) {
        return AutoPaymentService.getAllUserAutoPay(email);
    }

    public static void removeAutoPay(String id) {
        AutoPaymentService.removeAutoPayment(id);
    }

    public static AutoPayment getFirstAutoPay(String email) {
        ArrayList<AutoPayment> list = getAllUserPayments(email);

        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }
}
