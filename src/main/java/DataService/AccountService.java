/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataService;

import java.util.ArrayList;
import Objects.Account;
import Objects.AccountPersonalInformation;
import java.util.HashSet;

/**
 *
 * @author rafra
 */
public class AccountService {

    private static ArrayList<Account> Accounts = new ArrayList<>();
    private static ArrayList<AccountPersonalInformation> AccountsPersonalInfo = new ArrayList<>();

    public AccountService() {
        Account acc1 = new Account();
        acc1.setEmail("@");
        acc1.setPassword("dsa");
        acc1.setRole("admin");
        acc1.setBalance(0.00);

        AccountPersonalInformation newUserInfo = new AccountPersonalInformation();
        newUserInfo.setEmail("@");
        newUserInfo.setFirstName("hot");
        newUserInfo.setLastName("dog");
        newUserInfo.setAddress("San Antonio, San Pedro, Laguna");
        newUserInfo.setPhoneNum("123123123");
        newUserInfo.setBirthdate("May 20, 2026");

        AccountsPersonalInfo.add(newUserInfo);
        Accounts.add(acc1);
    }

    //Account Functions
    public static boolean validateFirstName(String fName) {
//        if (fName == null || fName.isEmpty()) {
//            return false;
//        }
        return fName.matches("[a-zA-Z ]+");
    }
    
    public static boolean validateLastName(String lName) {
//        if (lName == null || lName.isEmpty()) {
//            return false;
//        }
        return lName.matches("[a-zA-Z ]+");
    }

    public static boolean validateEmail(String email) {
//        if (email == null || email.isEmpty()) {
//            return false;
//        }
        return email.endsWith("@gmail.com") || email.contains("@");
    }

    public static boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d+") && phone.length() == 11 && phone.startsWith("09");
    }

    public static void registerUser(Account newUser, AccountPersonalInformation newUserInfo) {
        AccountsPersonalInfo.add(newUserInfo);
        Accounts.add(newUser);
    }

    public static Account getAuthenticatedUser(String email, String password) {
        for (Account user : Accounts) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static String getFullName(AccountPersonalInformation acc) {
        return acc.getFirstName() + " " + acc.getLastName();
    }

    public static AccountPersonalInformation getUserInfo(String email) {
        for (AccountPersonalInformation userinfo : AccountsPersonalInfo) {
            if (userinfo.getEmail().equalsIgnoreCase(email)) {
                return userinfo;
            }
        }
        return null;
    }

    public static Account getUser(String email) {
        for (Account user : Accounts) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

    //Balance Functions
    public static double deposit(String email, double amount) {
        Account user = getUser(email);
        user.setBalance(user.getBalance() + amount);
        return user.getBalance();
    }

    public static double withdraw(String email, double amount) {
        Account user = getUser(email);
        user.setBalance(user.getBalance() - amount);
        return user.getBalance();
    }

    // Settings Functions
    public static void updateEmail(String email, String newEmail) {
        Account user = getUser(email);
        user.setEmail(newEmail);

        AccountPersonalInformation userinfo = getUserInfo(email);
        userinfo.setEmail(newEmail);
    }

    public static void updatePassword(String email, String newPassword) {
        Account user = getUser(email);
        user.setPassword(newPassword);

//        AccountPersonalInformation userinfo = getUserInfo(email);
//        userinfo.setPassword(newPassword);
    }

    public static void updateFirstName(String email, String newFirstName) {
        AccountPersonalInformation userinfo = getUserInfo(email);
        userinfo.setFirstName(newFirstName);
    }

    public static void updateLastName(String email, String newLastName) {
        AccountPersonalInformation userinfo = getUserInfo(email);
        userinfo.setLastName(newLastName);
    }

    public static void updatePhone(String email, String newPhone) {
        AccountPersonalInformation userinfo = getUserInfo(email);
        userinfo.setPhoneNum(newPhone);
    }

    public static void updateAddress(String email, String newAdress) {
        AccountPersonalInformation userinfo = getUserInfo(email);
        userinfo.setAddress(newAdress);
    }

    public static void updateBirthday(String email, String newBirthday) {
        AccountPersonalInformation userinfo = getUserInfo(email);
        userinfo.setBirthdate(newBirthday);
    }

}
