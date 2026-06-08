package DataService;

import Objects.Account;
import Objects.AutoPayment;
import java.sql.*;
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
                + "(AutoPayID, Email, Payee, Frequency, DueDate, Amount) "
                + "VALUES (?,?,?,?,?,?)";

        try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, payment.getAutoPayID());
            st.setString(2, payment.getEmail());
            st.setString(3, payment.getPayee());
            st.setString(4, payment.getFrequency());
            st.setDate(5, java.sql.Date.valueOf(payment.getDate()));
            st.setDouble(6, payment.getAmount());

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

}
