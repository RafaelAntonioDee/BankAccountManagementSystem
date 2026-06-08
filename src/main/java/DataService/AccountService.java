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

        // DUMMY ACCOUNT #1
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

        // DUMMY ACCOUNT #2
        Account acc2 = new Account();
        acc2.setEmail("@@");
        acc2.setPassword("dsa");
        acc2.setRole("admin");
        acc2.setBalance(1000.00);

        AccountPersonalInformation newUserInfo2 = new AccountPersonalInformation();
        newUserInfo2.setEmail("@@");
        newUserInfo2.setFirstName("dog");
        newUserInfo2.setLastName("hot");
        newUserInfo2.setAddress("Malaban, San Pedro, Laguna");
        newUserInfo2.setPhoneNum("123123123");
        newUserInfo2.setBirthdate("May 20, 2026");

        AccountsPersonalInfo.add(newUserInfo2);
        Accounts.add(acc2);
    }

    // Account Functions
    public static boolean validateFirstName(String fName) {

        return fName.matches("[a-zA-Z ]+");
    }

    public static boolean validateLastName(String lName) {
        return lName.matches("[a-zA-Z ]+");
    }

    public static boolean validateEmail(String email) {

        return email.endsWith("@gmail.com") || email.contains("@");
    }

//    public static boolean emailExist(String email, ArrayList<Account> accounts) {
//        for (Account acc : accounts) {
//            if (acc.getEmail().equalsIgnoreCase(email)) {
//                return true;
//            }
//        }
//        return false;
//    }
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

    public static ArrayList<Account> getAccounts() {
        return Accounts;
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

    public static void ChangeTheme(String email, String theme) {
        Account user = getUser(email);
        user.setSystemTheme(theme);
    }

}
