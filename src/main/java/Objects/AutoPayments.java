/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.time.LocalDateTime;

/**
 *
 * @author rafra
 */
public class AutoPayments {
    private String Payee, Frequency;
    private LocalDateTime Date;
    private double Amount;
    private boolean isActive;
        
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
    
    public LocalDateTime getDate(){
        return Date;
    }
    public void setPaymentDate(LocalDateTime newPaymentDate){
        Date = newPaymentDate;
    }
    
    public double getAmount(){
        return Amount;
    }
    public void setAmount(double newAmount){
        Amount = newAmount;
    }
    
    public boolean getActive() {
        return isActive;
    }
    
    public void setActive(boolean newIsActive) {
        isActive = newIsActive;
    }
}
