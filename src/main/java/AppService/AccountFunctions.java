/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppService;

import Objects.Account;
import DataService.AccountService;
import Objects.AccountPersonalInformation;

/**
 *
 * @author rafra
 */
public class AccountFunctions {
    
    // CHECK FIRST NAME FORMAT (bawal number)
    public static boolean validateFirstName(String fName) {
        return AccountService.validateFirstName(fName);
    }
    
    // CHECK LAST NAME FORMAT (bawal number)
    public static boolean validateLastName(String lName) {
        return AccountService.validateLastName(lName);
    }
    
    // CHECK IF EMAIL EXISTS
    public static boolean emailExists(String email) {
        return AccountService.getUser(email) != null;
    }
    public static boolean validateExistingEmail(String email) {
        return emailExists(email);
    }
    
    // CHECK EMAIL FORMAT
    public static boolean validateEmail(String email) {
        return AccountService.validateEmail(email);
    }
    
    // CHECK PHONE NUMBER FORMAT
    public static boolean validatePhoneNumber(String phone) {
        return AccountService.validatePhoneNumber(phone);
    }
    
    public static void registerUser(String fName, String lName, String email, String addr, String phone, String pass, String bday) {
        Account newUser = new Account();
        newUser.setEmail(email);
        newUser.setPassword(pass);
        newUser.setRole("User");
        newUser.setBalance(0.00);

        AccountPersonalInformation newUserInfo = new AccountPersonalInformation();
        newUserInfo.setEmail(email);
        newUserInfo.setFirstName(fName);
        newUserInfo.setLastName(lName);
        newUserInfo.setAddress(addr);
        newUserInfo.setPhoneNum(phone);
        newUserInfo.setBirthdate(bday);

        AccountService.registerUser(newUser, newUserInfo);
    }

    public static Account getAuthenticatedUser(String email, String password) {
        return AccountService.getAuthenticatedUser(email, password);
    }

    public static String getFullName(AccountPersonalInformation acc) {
        return acc.getFirstName() + " " + acc.getLastName();
    }

    public static AccountPersonalInformation getUserInfo(String email) {
        return AccountService.getUserInfo(email);
    }

    public static Account getUser(String email) {
        return AccountService.getUser(email);
    }

    public static void ChangeTheme(String email, String theme){
        AccountService.ChangeTheme(email, theme);
    }
   
}
