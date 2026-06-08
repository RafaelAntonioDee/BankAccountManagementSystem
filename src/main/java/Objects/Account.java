/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author rafra
 */
public class Account {

    private String Email;
    private String Password;
    private String Role;
    private double Balance;
    private String SystemTheme = "Light";

    public String getSystemTheme() {
        return SystemTheme;
    }

    public void setSystemTheme(String newSystemTheme) {
        SystemTheme = newSystemTheme;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String newEmail) {
        Email = newEmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String newPassword) {
        Password = newPassword;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String newRole) {
        Role = newRole;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double newBalance) {
        Balance = newBalance;
    }


}
