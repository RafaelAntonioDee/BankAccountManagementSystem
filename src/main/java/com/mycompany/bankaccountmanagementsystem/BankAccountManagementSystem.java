/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankaccountmanagementsystem;

import LoginUI.LoginPage;
import DataService.AccountService;

/**
 *
 * @author rafra
 */
public class BankAccountManagementSystem {

    public static void main(String[] args) {
        LoginPage page = new LoginPage();
        page.setVisible(true);
        
        AccountService service = new AccountService();
    }
}
