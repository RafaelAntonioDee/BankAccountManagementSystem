/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author rafra
 */
public class AutoPayment {
    private String AutoPayID, Payee, Frequency, Email;
    private LocalDate Date;
    private double Amount;
        
    public String getAutoPayID(){
        return AutoPayID;
    }
    public void setAutoPayID(String newAutoPayID){
        AutoPayID = newAutoPayID;
    }
    
    public String getEmail(){
        return Email;
    }
    public void setEmail(String newEmail){
        Email = newEmail;
    }
    
    public String getPayee(){
        return Payee;
    }
    public void setPayee(String newPayee){
        Payee = newPayee;
    }
    
    public String getFrequency(){
        return Frequency;
    }
    public void setFrequency(String newFrequency){
        Frequency = newFrequency;
    }
    
    public LocalDate getDate(){
        return Date;
    }
    public void setPaymentDate(LocalDate newPaymentDate){
        Date = newPaymentDate;
    }
    
    public double getAmount(){
        return Amount;
    }
    public void setAmount(double newAmount){
        Amount = newAmount;
    }
    

}
