package DataService;

import Objects.Account;
import Objects.AutoPayment;
import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;

public class AutoPaymentService {

    private static ArrayList<AutoPayment> AutoPayments = new ArrayList<>();

    public static void addAutoPayment(AutoPayment payment) {
        AutoPayments.add(payment);
    }

    public static ArrayList<AutoPayment> getAllUserAutoPay(String email) {

        ArrayList<AutoPayment> userAutoPay = new ArrayList<>();

        for (AutoPayment payment : AutoPayments) {
            if (payment.getEmail() != null && payment.getEmail().equals(email)) {
                userAutoPay.add(payment);
            }
        }

        userAutoPay.sort(Comparator.comparing(AutoPayment::getDate));

        return userAutoPay;
    }

    public static ArrayList<AutoPayment> getAllAutoPayments() {
        return AutoPayments;
    }

    public static void removeAutoPayment(String id) {
        AutoPayments.removeIf(payment -> payment.getAutoPayID().equals(id));
    }

    
}
