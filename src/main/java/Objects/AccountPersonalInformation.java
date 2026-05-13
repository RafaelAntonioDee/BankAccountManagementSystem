/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author rafra
 */
public class AccountPersonalInformation {
    private String Email;
    private String FirstName;
    private String LastName;
    private String Address;
    private String PhoneNum;
    private String Birthdate;
    
    public String getEmail(){
        return Email;
    }
    public void setEmail(String newEmail){
        Email = newEmail;
    }
    
    public String getFirstName(){
        return FirstName;
    }
    public void setFirstName(String newFirstName){
        FirstName = newFirstName;
    }
    
    public String getLastName(){
        return LastName;
    }
    public void setLastName(String newLastName){
        LastName = newLastName;
    }
    
    public String getAddress(){
        return Address;
    }
    public void setAddress(String newAddress){
        Address = newAddress;
    }
    
    public String getPhoneNum(){
        return PhoneNum;
    }
    public void setPhoneNum(String newPhoneNum){
        PhoneNum = newPhoneNum;
    }
    
    public String getBirthdate(){
        return Birthdate;
    }
    public void setBirthdate(String newBirthdate){
        Birthdate = newBirthdate;
    }
}
