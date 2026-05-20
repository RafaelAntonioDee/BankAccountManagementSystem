/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppService;
import Objects.UserAccount;
import java.util.ArrayList;
/**
 *
 * @author rafra
 */
public class AccountFunctions {
    
    private static ArrayList <UserAccount> registeredUser = new ArrayList<>();
    
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        return email.endsWith("@gmail.com") || email.contains("@");
    }
    
    public static boolean validatePhoneNumber(String phone) {
        if (phone == null || phone.isEmpty()) return false;
        return phone.matches("\\d+") && phone.length() <= 11;
    }

    public static void registerUser(String fName, String lName, String email, String addr, String phone, String pass, String bday) {
    UserAccount newUser = new UserAccount(fName, lName, email, addr, phone, pass, bday);
    registeredUser.add(newUser);
    }

    public static UserAccount getAuthenticatedUser(String email, String password) {
    for (UserAccount user : registeredUser) {
        if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
            return user; 
        }
    }
    return null; 
    }
        
    }
   

