/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataService;

import Objects.AccountTransactHistory;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public static void addTransaction(String email, String type, LocalDate date, String balanceChange) {

        String newId = generateNextTransactionID();

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
    private static String generateNextTransactionID() {

        String sql = "SELECT TransactionID FROM transactions ORDER BY TransactionID DESC LIMIT 1";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                String lastId = rs.getString("TransactionID");

                int num = Integer.parseInt(lastId.substring(1));
                return String.format("T%04d", num + 1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "T0001";
    }
}
