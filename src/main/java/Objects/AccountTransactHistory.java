/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;
import java.time.LocalDate;

/**
 *
 * @author rafra
 */
public class AccountTransactHistory {
    private String TransactionID;
    private String Email;
    private String Transaction;
    private LocalDate Date;
    private String BalanceChange;
    
    public String getTransactionID(){
        return TransactionID;
    }
    public void setTransactionID(String newTransactionID){
        TransactionID = newTransactionID;
    }
    
    public String getEmail(){
        return Email;
    }
    public void setEmail(String newEmail){
        Email = newEmail;
    }
    
    public String getTransaction(){
        return Transaction;
    }
    public void setTransaction(String newTransaction){
        Transaction = newTransaction;
    }
    
    public LocalDate getDate(){
        return Date;
    }
    public void setDate(LocalDate newDate){
        Date = newDate;
    }
    
    public String getBalanceChange(){
        return BalanceChange;
    }
    public void setBalanceChange(String newBalanceChange){
        BalanceChange = newBalanceChange;
    }
}
