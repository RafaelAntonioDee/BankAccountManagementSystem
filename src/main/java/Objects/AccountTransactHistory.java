/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author rafra
 */
public class AccountTransactHistory {
    public int TransactionID;
    public String Email;
    public String Transaction;
    public String Date;
    public String BalanceChange;
    
    public int getTransactionID(){
        return TransactionID;
    }
    public void setTransactionID(int newTransactionID){
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
    
    public String getDate(){
        return Date;
    }
    public void setDate(String newDate){
        Date = newDate;
    }
    
    public String getBalanceChange(){
        return BalanceChange;
    }
    public void setBalanceChange(String newBalanceChange){
        BalanceChange = newBalanceChange;
    }
}
