package DataService;

import Objects.AutoPayments;
import java.util.ArrayList;
import java.util.HashMap;

public class AutoPaymentService {

    private static HashMap<String, ArrayList<AutoPayments>> userPayments = new HashMap<>();

    private static ArrayList<AutoPayments> getUser(String email) {
        if (!userPayments.containsKey(email)) {
            userPayments.put(email, new ArrayList<>());
        }
        return userPayments.get(email);
    }

    public static void addAutoPayment(String email, AutoPayments payment) {
        getUser(email).add(payment);
    }

    public static ArrayList<AutoPayments> getAllAutoPayments(String email) {
        return getUser(email);
    }

    public static void removeAutoPayment(String email, int index) {
        ArrayList<AutoPayments> list = getUser(email);
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }
    
// ONLY USED TO CHECK IF THE ACCOUNT FOR AUTOPAYMENT
    
//    public static void debugPrint(String email) {
//    System.out.println("ez debug");
//
//    ArrayList<AutoPayments> list = userPayments.get(email);
//
//    if (list == null) {
//        System.out.println("No list for" + email);
//        return;
//    }
//
//    System.out.println("User: " + email);
//    System.out.println("Size: " + list.size());
//
//    for (AutoPayments p : list) {
//        System.out.println(p.getPayee() + " | " + p.getAmount());
//    }
//}
    
}


