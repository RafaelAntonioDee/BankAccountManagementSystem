/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AppService;

import Objects.Account;
import DataService.AccountService;
import DataService.TransactionsService;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author rafra
 */
public class BalanceFunctions {

    public static double deposit(String email, double amount) {
        addTransaction(email, "Deposit", LocalDate.now(), "+ " + amount);

        return AccountService.deposit(email, amount);
    }

    public static double withdraw(String email, double amount) {
        addTransaction(email, "Withdraw", LocalDate.now(), "- " + amount);

        return AccountService.withdraw(email, amount);
    }

    public static void transfer(Account user, Account receiver, double amount) {
        addTransaction(user.getEmail(), "Transferred", LocalDate.now(), "- " + amount);
        addTransaction(receiver.getEmail(), "Received", LocalDate.now(), "+ " + amount);

        AccountService.withdraw(user.getEmail(), amount);
        AccountService.deposit(receiver.getEmail(), amount);
    }

    public static ArrayList getTransactions(String email) {
        return TransactionsService.getTransactions(email);
    }

    public static void addTransaction(String email, String type, LocalDate date, String balancechange) {
        TransactionsService.addTransaction(email, type, date, balancechange);
    }
}
