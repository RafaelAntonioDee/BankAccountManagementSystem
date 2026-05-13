/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataService;

import java.util.ArrayList;
import Objects.Account;

/**
 *
 * @author rafra
 */
public class AccountService {

    private ArrayList<Account> Accounts = new ArrayList<>();

    public AccountService() {
        //sample data lang to
        Account acc1 = new Account();
        acc1.setEmail("dee@gmail.com");
        acc1.setPassword("dee123");
        acc1.setRole("User");
        acc1.setBalance(5000);

        Accounts.add(acc1);
    }

    public void UpdateBalance(String email, double newBalance) {
        Account acc = GetAccount(email);
        acc.setBalance(newBalance);
    }

    public double GetBalance(String email) {
        Account acc = GetAccount(email);
        return acc.getBalance();
    }

    public Account GetAccount(String email) {
        for (Account acc : Accounts) {
            if (acc.getEmail().equals(email)) {
                return acc;
            }
        }
        return null;
    }
}
