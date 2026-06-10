package DataService;

import AppService.BalanceFunctions;
import Objects.Account;
import Objects.AutoPayment;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.*;
import java.util.HashMap;

public class AutoPaymentService {

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/db_bankaccountmanagement",
                "root",
                ""
        );
    }

    // ADD
    public static void addAutoPayment(AutoPayment payment) {

        String sql = "INSERT INTO autopayments "
                + "(AutoPayID, Email, Payee, Frequency, DueDate, Amount, isPaid) "
                + "VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, payment.getAutoPayID());
            st.setString(2, payment.getEmail());
            st.setString(3, payment.getPayee());
            st.setString(4, payment.getFrequency());
            st.setDate(5, java.sql.Date.valueOf(payment.getDate()));
            st.setDouble(6, payment.getAmount());
            st.setBoolean(7, payment.isPaid());

            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //Get User Auto Pay Data
    public static ArrayList<AutoPayment> getAllUserAutoPay(String email) {

        ArrayList<AutoPayment> list = new ArrayList<>();

        String sql = "SELECT * FROM autopayments WHERE Email = ?";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                AutoPayment p = new AutoPayment();
                p.setAutoPayID(rs.getString("AutoPayID"));
                p.setEmail(rs.getString("Email"));
                p.setPayee(rs.getString("Payee"));
                p.setFrequency(rs.getString("Frequency"));
                p.setPaymentDate(rs.getDate("DueDate").toLocalDate());
                p.setAmount(rs.getDouble("Amount"));
                p.setPaid(rs.getBoolean("IsPaid"));

                list.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // sort soonest due date first
        list.sort(Comparator.comparing(AutoPayment::getDate));

        return list;
    }

    // Get All Auto Pay Data
    public static ArrayList<AutoPayment> getAllAutoPayments() {

        ArrayList<AutoPayment> list = new ArrayList<>();

        String sql = "SELECT * FROM autopayments";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {

                AutoPayment p = new AutoPayment();
                p.setAutoPayID(rs.getString("AutoPayID"));
                p.setEmail(rs.getString("Email"));
                p.setPayee(rs.getString("Payee"));
                p.setFrequency(rs.getString("Frequency"));
                p.setPaymentDate(rs.getDate("DueDate").toLocalDate());
                p.setAmount(rs.getDouble("Amount"));
                p.setPaid(rs.getBoolean("IsPaid"));

                list.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // Remove Auto Payment
    public static void removeAutoPayment(String id) {

        String sql = "DELETE FROM autopayments WHERE AutoPayID = ?";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, id);
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Date Format
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    public static LocalDate getDateNow() {
        return LocalDate.now();
    }

    public static String getFormattedDateNow() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    // Check if Auto Payment Due date is today na
    public static void processDuePayments() {

        ArrayList<AutoPayment> payments = AutoPaymentService.getAllAutoPayments();

        LocalDate today = LocalDate.now();

        for (AutoPayment p : payments) {

            if (p.isPaid()) {
                continue;
            }

            if (!today.isBefore(p.getDate())) {

                double amount = p.getAmount();
                String email = p.getEmail();

                boolean success = AccountService.deductBalance(email, amount);
                String sequentialTxnId = BalanceFunctions.getNextTransactionID();

                    TransactionsService.addTransaction(email, "Auto Payment", today, "-" + amount, sequentialTxnId);
                    AutoPaymentService.markAsPaid(p.getAutoPayID());

                    // Changes the Auto Payment Status back to Unpaid
                    LocalDate nextDate = calculateNextDueDate(p.getDate(), p.getFrequency());
                    AutoPaymentService.updatePaymentForNextCycle(p.getAutoPayID(), nextDate);
                
            }
        }
    }

    // Mark if Paid na 'yung AutoPayment
    public static void markAsPaid(String autoPayID) {

        String sql = "UPDATE autopayments SET IsPaid = 1 WHERE AutoPayID = ?";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, autoPayID);
            st.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static LocalDate calculateNextDueDate(LocalDate currentDueDate, String frequency) {
        if (frequency == null) {
            return null;
        }

        switch (frequency.toLowerCase().trim()) {
            case "daily":
                return currentDueDate.plusDays(1);
            case "monthly":
                return currentDueDate.plusMonths(1);
            case "quarterly":
                return currentDueDate.plusMonths(3);
            case "semi-annually":
                return currentDueDate.plusMonths(6);
            case "annually":
                return currentDueDate.plusYears(1);
            default:
                return currentDueDate.plusMonths(1);
        }
    }

    // Update the Payment Date after Succesfully Auto-Paying
    public static void updatePaymentForNextCycle(String autoPayID, LocalDate nextDate) {
        String sql = "UPDATE autopayments SET DueDate = ?, IsPaid = 0 WHERE AutoPayID = ?";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setDate(1, java.sql.Date.valueOf(nextDate));
            st.setString(2, autoPayID);
            st.executeUpdate();

        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    // Check if you're eligible to unsubscribe (Can't if you have unpaid subscription)
    public static boolean canUnsubscribe(String id) {

        String sql = "SELECT IsPaid, DueDate FROM autopayments WHERE AutoPayID = ?";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                boolean isPaid = rs.getBoolean("IsPaid");
                LocalDate dueDate = rs.getDate("DueDate").toLocalDate();

                if (!isPaid && LocalDate.now().isBefore(dueDate)) {
                    return true;
                }

                return isPaid;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
