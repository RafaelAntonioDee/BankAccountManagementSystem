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
public class BalanceFunctions {

    public static double deposit(String email, double amount) {
        return AccountService.deposit(email, amount);
    }

    public static double withdraw(String email, double amount) {
        return AccountService.withdraw(email, amount);
    }
}
