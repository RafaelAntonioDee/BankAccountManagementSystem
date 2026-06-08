/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataService;

import java.util.ArrayList;
import Objects.Account;
import Objects.AccountPersonalInformation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import javax.swing.JOptionPane;

/**
 *
 * @author rafra
 */
public class AccountService {

    private Connection connection;

    public AccountService() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_bankaccountmanagement",
                    "root",
                    ""
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Get Connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_bankaccountmanagement",
                "root",
                ""
        );
    }

    //------------------------------- Get Account Functions -------------------------------
    // Get All Accounts
    public static ArrayList<Account> getAllAccounts() {
        ArrayList<Account> list = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement("SELECT * FROM accounts"); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Account acc = new Account();
                acc.setEmail(rs.getString("Email"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getString("Role"));
                acc.setBalance(rs.getDouble("Balance"));
                acc.setSystemTheme(rs.getString("SystemTheme"));

                list.add(acc);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // Get All Accounts Personal Information
    public static ArrayList<AccountPersonalInformation> getAllPersonalInfo() {
        ArrayList<AccountPersonalInformation> list = new ArrayList<>();

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement("SELECT * FROM accountspersonalinfo"); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                AccountPersonalInformation info = new AccountPersonalInformation();
                info.setEmail(rs.getString("Email"));
                info.setFirstName(rs.getString("FirstName"));
                info.setLastName(rs.getString("LastName"));
                info.setAddress(rs.getString("Address"));
                info.setPhoneNum(rs.getString("PhoneNum"));
                info.setBirthdate(rs.getString("Birthdate"));

                list.add(info);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // Get User
    public static Account getUser(String email) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM accounts WHERE Email=?")) {

            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Account acc = new Account();
                acc.setEmail(rs.getString("Email"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getString("Role"));
                acc.setBalance(rs.getDouble("Balance"));
                acc.setSystemTheme(rs.getString("SystemTheme"));
                return acc;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    // Get UserInfo
    public static AccountPersonalInformation getUserInfo(String email) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM accountspersonalinfo WHERE Email=?")) {

            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                AccountPersonalInformation info = new AccountPersonalInformation();
                info.setEmail(rs.getString("Email"));
                info.setFirstName(rs.getString("FirstName"));
                info.setLastName(rs.getString("LastName"));
                info.setAddress(rs.getString("Address"));
                info.setPhoneNum(rs.getString("PhoneNum"));
                info.setBirthdate(rs.getString("Birthdate"));
                return info;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    //------------------------------- Validation Functions -------------------------------
    // Authenticate Login
    public static Account getAuthenticatedUser(String email, String password) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM accounts WHERE Email=? AND Password=?")) {

            st.setString(1, email);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Account acc = new Account();
                acc.setEmail(rs.getString("Email"));
                acc.setPassword(rs.getString("Password"));
                acc.setRole(rs.getString("Role"));
                acc.setBalance(rs.getDouble("Balance"));
                acc.setSystemTheme(rs.getString("SystemTheme"));
                return acc;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static boolean validateFirstName(String fName) {

        return fName.matches("[a-zA-Z ]+");
    }

    public static boolean validateLastName(String lName) {
        return lName.matches("[a-zA-Z ]+");
    }

    public static boolean validateEmail(String email) {

        return email.endsWith("@gmail.com") || email.contains("@");
    }

    public static boolean emailExist(String email, ArrayList<Account> accounts) {
        for (Account acc : accounts) {
            if (acc.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePhoneNumber(String phone) {
        return phone.matches("\\d+") && phone.length() == 11 && phone.startsWith("09");
    }

    public static String getFullName(AccountPersonalInformation acc) {
        return acc.getFirstName() + " " + acc.getLastName();
    }

    //------------------------------- Accounts Functions -------------------------------
    // Register
    public static void registerUser(Account acc, AccountPersonalInformation info) {

        try (Connection conn = getConnection()) {

            PreparedStatement st1 = conn.prepareStatement(
                    "INSERT INTO accounts (Email, Password, Role, Balance, SystemTheme) VALUES (?,?,?,?,?)"
            );

            st1.setString(1, acc.getEmail());
            st1.setString(2, acc.getPassword());
            st1.setString(3, acc.getRole());
            st1.setDouble(4, acc.getBalance());
            st1.setString(5, acc.getSystemTheme());
            st1.executeUpdate();

            PreparedStatement st2 = conn.prepareStatement(
                    "INSERT INTO accountspersonalinfo (Email, FirstName, LastName, Address, PhoneNum, Birthdate) VALUES (?,?,?,?,?,?)"
            );

            st2.setString(1, info.getEmail());
            st2.setString(2, info.getFirstName());
            st2.setString(3, info.getLastName());
            st2.setString(4, info.getAddress());
            st2.setString(5, info.getPhoneNum());
            st2.setString(6, info.getBirthdate());
            st2.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Update Balance Functions
    public static void updateBalance(String email, double newBalance) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(
                "UPDATE accounts SET Balance=? WHERE Email=?")) {

            st.setDouble(1, newBalance);
            st.setString(2, email);
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static double deposit(String email, double amount) {
        Account acc = getUser(email);
        if (acc != null) {
            updateBalance(email, acc.getBalance() + amount);
        }
        return acc.getBalance() + amount;
    }

    public static double withdraw(String email, double amount) {
        Account acc = getUser(email);
        if (acc != null) {
            updateBalance(email, acc.getBalance() - amount);
        }
        return acc.getBalance() - amount;
    }

    //------------------------------- Update Settings Functions -------------------------------
    public static void updateEmail(String oldEmail, String newEmail) {

        try (Connection conn = getConnection()) {

            PreparedStatement st1 = conn.prepareStatement(
                    "UPDATE accounts SET Email=? WHERE Email=?"
            );
            st1.setString(1, newEmail);
            st1.setString(2, oldEmail);
            st1.executeUpdate();

            PreparedStatement st2 = conn.prepareStatement(
                    "UPDATE accountspersonalinfo SET Email=? WHERE Email=?"
            );
            st2.setString(1, newEmail);
            st2.setString(2, oldEmail);
            st2.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updatePassword(String email, String newPassword) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(
                "UPDATE accounts SET Password=? WHERE Email=?")) {

            st.setString(1, newPassword);
            st.setString(2, email);
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateFirstName(String email, String value) {
        updateInfo(email, "FirstName", value);
    }

    public static void updateLastName(String email, String value) {
        updateInfo(email, "LastName", value);
    }

    public static void updatePhone(String email, String value) {
        updateInfo(email, "PhoneNum", value);
    }

    public static void updateAddress(String email, String value) {
        updateInfo(email, "Address", value);
    }

    public static void updateBirthday(String email, String value) {
        updateInfo(email, "Birthdate", value);
    }

    private static void updateInfo(String email, String column, String value) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(
                "UPDATE accountspersonalinfo SET " + column + "=? WHERE Email=?")) {

            st.setString(1, value);
            st.setString(2, email);
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void changeTheme(String email, String theme) {
        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(
                "UPDATE accounts SET SystemTheme=? WHERE Email=?")) {

            st.setString(1, theme);
            st.setString(2, email);
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
