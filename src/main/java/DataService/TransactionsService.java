/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataService;

import Objects.AccountTransactHistory;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author rafra
 */
public class TransactionsService {

    private static ArrayList<AccountTransactHistory> TransactionsHistory = new ArrayList<>();

    public static ArrayList getTransactions(String email) {
        ArrayList<AccountTransactHistory> userHistory = new ArrayList<>();

        for (AccountTransactHistory transaction : TransactionsHistory) {
            if (transaction.getEmail().equalsIgnoreCase(email)) {
                userHistory.add(transaction);
            }
        }
        return userHistory;
    }

    public static void addTransaction(String email, String type, LocalDate date, String balancechange) {
        AccountTransactHistory transaction = new AccountTransactHistory();
        transaction.setTransactionID(String.format("T%04d", TransactionsHistory.size() + 1));
        transaction.setEmail(email);
        transaction.setTransaction(type);
        transaction.setDate(date);
        transaction.setBalanceChange(balancechange);

        TransactionsHistory.add(transaction);
    }
}
