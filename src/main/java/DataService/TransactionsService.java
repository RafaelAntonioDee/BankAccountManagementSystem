/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataService;

import Objects.AccountTransactHistory;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rafra
 */
public class TransactionsService {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_bankaccountmanagement",
                "root",
                ""
        );
    }

    // Get All User Transactions
    public static ArrayList<AccountTransactHistory> getTransactions(String email) {

        ArrayList<AccountTransactHistory> list = new ArrayList<>();

        String sql = "SELECT * FROM transactions WHERE Email = ? ORDER BY Date DESC";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                AccountTransactHistory t = new AccountTransactHistory();
                t.setTransactionID(rs.getString("TransactionID"));
                t.setEmail(rs.getString("Email"));
                t.setTransaction(rs.getString("TransactionType"));
                t.setDate(rs.getDate("Date").toLocalDate());
                t.setBalanceChange(rs.getString("BalanceChange"));

                list.add(t);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // Add Transaction
    public static void addTransaction(String email, String type, LocalDate date, String balanceChange, String newId) {

        String sql = "INSERT INTO transactions "
                + "(TransactionID, Email, TransactionType, Date, BalanceChange) "
                + "VALUES (?,?,?,?,?)";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, newId);
            st.setString(2, email);
            st.setString(3, type);
            st.setDate(4, java.sql.Date.valueOf(date));
            st.setString(5, balanceChange);

            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Generate ID
    public static String generateNextTransactionID() {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();

        while (true) {

            StringBuilder id = new StringBuilder("RN");

            for (int i = 0; i < 8; i++) {
                id.append(chars.charAt(random.nextInt(chars.length())));
            }

            String transactionID = id.toString();

            if (!transactionIDExists(transactionID)) {
                return transactionID;
            }
        }
    }

    private static boolean transactionIDExists(String id) {

        String sql = "SELECT 1 FROM transactions WHERE TransactionID = ?";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, id);

            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return true;
    }
}
