/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataService;

import java.util.ArrayList;
import Objects.Account;
import Objects.AccountPersonalInformation;

/**
 *
 * @author rafra
 */
public class AccountService {

    private static ArrayList<Account> Accounts = new ArrayList<>();
    private static ArrayList<AccountPersonalInformation> AccountsPersonalInfo = new ArrayList<>();

    public AccountService() {
        Account acc1 = new Account();
        acc1.setEmail("test@gmail.com");
        acc1.setPassword("test123");
        acc1.setRole("User");
        acc1.setBalance(0.00);

        AccountPersonalInformation newUserInfo = new AccountPersonalInformation();
        newUserInfo.setEmail("test@gmail.com");
        newUserInfo.setFirstName("hot");
        newUserInfo.setLastName("dog");
        newUserInfo.setAddress("San Antonio, San Pedro, Laguna");
        newUserInfo.setPhoneNum("123123123");
        newUserInfo.setBirthdate("May 20, 2026");

        AccountsPersonalInfo.add(newUserInfo);
        Accounts.add(acc1);
    }

    //Account Functions
    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        return email.endsWith("@gmail.com") || email.contains("@");
    }

    public static boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d+") && phone.length() <= 11;
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


}
