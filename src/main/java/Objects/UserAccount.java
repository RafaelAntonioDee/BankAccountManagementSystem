/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;
/**
 *
 * @author rafra
 */
public class UserAccount {
    private String firstName, lastName, email, address, phone, password, birthday;

    public UserAccount(String fName, String lName, String email, String address, String phone, String password, String birthday) {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.birthday = birthday;
    }

    // Getters for Login and Dashboard display
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getPhone() { return phone; }
    public String getBirthday() {return birthday;}
    public String getAddress() { return address; }
    }
