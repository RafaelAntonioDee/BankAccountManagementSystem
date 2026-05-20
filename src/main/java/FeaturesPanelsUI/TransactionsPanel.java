package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class TransactionsPanel extends JPanel implements ActionListener {

    private JLabel lblSearch, lblDate, lblType, lblTransactions;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JComboBox cmbDateRange, cmbTransactionType;
    private JTable TransactionsTable;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JPanel pnlProcess;

    private String[][] transactions = {
        {"T001", "user1@gmail.com", "Deposit", "Cash Deposit", "2026-05-06", "5000.0"},
        {"T002", "user2@gmail.com", "Withdraw", "ATM Withdraw", "2026-05-01", "4200.0"},
        {"T003", "user1@gmail.com", "Transfer", "Bank Transfer", "2026-04-22", "3000.0"},
        {"T004", "user3@gmail.com", "Payment", "Bills Payment", "2026-03-15", "2500.0"}
    };

    public TransactionsPanel() {
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(25, 25, 787, 510);
        pnlProcess.setBackground(new Color(243, 243, 243));
        pnlProcess.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblSearch = new JLabel("Search");
        lblSearch.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSearch.setBounds(25, 20, 200, 25);
        pnlProcess.add(lblSearch);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/search.png"));

        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        ImageIcon searchIcon = new ImageIcon(resizedImg);

        btnSearch = new JButton(searchIcon);
        btnSearch.setBounds(25, 50, 40, 35);

        btnSearch.setFocusPainted(false);

        btnSearch.addActionListener(this);

        pnlProcess.add(btnSearch);

        btnSearch = new JButton(searchIcon);
        btnSearch.setBounds(25, 50, 40, 35);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);
        pnlProcess.add(btnSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(70, 50, 360, 35);
        pnlProcess.add(txtSearch);

        lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Arial", Font.PLAIN, 16));
        lblDate.setBounds(485, 20, 100, 25);
        pnlProcess.add(lblDate);

        cmbDateRange = new JComboBox();
        cmbDateRange.addItem("All");
        cmbDateRange.addItem("Past Day");
        cmbDateRange.addItem("Past Week");
        cmbDateRange.addItem("Past Month");
        cmbDateRange.addItem("Past 3 Months");
        cmbDateRange.setBounds(485, 50, 150, 35);
        pnlProcess.add(cmbDateRange);

        lblType = new JLabel("Type");
        lblType.setFont(new Font("Arial", Font.PLAIN, 16));
        lblType.setBounds(665, 20, 100, 25);
        pnlProcess.add(lblType);

        cmbTransactionType = new JComboBox();
        cmbTransactionType.addItem("All");
        cmbTransactionType.addItem("Deposit");
        cmbTransactionType.addItem("Withdraw");
        cmbTransactionType.addItem("Transfer");
        cmbTransactionType.addItem("Payment");
        cmbTransactionType.setBounds(665, 50, 100, 35);
        pnlProcess.add(cmbTransactionType);

        lblTransactions = new JLabel("Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setBounds(25, 120, 737, 35);
        pnlProcess.add(lblTransactions);

        String[] columns = {"Transaction ID", "Email", "Transaction Type", "Transaction", "Date", "Balance"};
        model = new DefaultTableModel(columns, 0);

        TransactionsTable = new JTable(model);
        TransactionsTable.getTableHeader().setReorderingAllowed(false);

        scroll = new JScrollPane(TransactionsTable);
        scroll.setBounds(25, 160, 737, 320);
        pnlProcess.add(scroll);

        showTransactions();
    }

    public void showTransactions() {
        model.setRowCount(0);

        String search = txtSearch.getText().trim().toLowerCase();
        String selectedDate = cmbDateRange.getSelectedItem().toString();
        String selectedType = cmbTransactionType.getSelectedItem().toString();

        LocalDate today = LocalDate.now();

        for (int i = 0; i < transactions.length; i++) {
            String id = transactions[i][0];
            String email = transactions[i][1];
            String type = transactions[i][2];
            String transaction = transactions[i][3];
            String date = transactions[i][4];
            String balance = transactions[i][5];

            boolean searchOk = search.equals("")
                || id.toLowerCase().contains(search)
                || email.equalsIgnoreCase(search)
                || transaction.toLowerCase().contains(search);

            boolean typeOk = selectedType.equals("All") || type.equals(selectedType);

            boolean dateOk = true;
            LocalDate transDate = LocalDate.parse(date);

            if (selectedDate.equals("Past Day")) {
                dateOk = !transDate.isBefore(today.minusDays(1));
            } else if (selectedDate.equals("Past Week")) {
                dateOk = !transDate.isBefore(today.minusWeeks(1));
            } else if (selectedDate.equals("Past Month")) {
                dateOk = !transDate.isBefore(today.minusMonths(1));
            } else if (selectedDate.equals("Past 3 Months")) {
                dateOk = !transDate.isBefore(today.minusMonths(3));
            }

            if (searchOk && typeOk && dateOk) {
                model.addRow(new Object[]{id, email, type, transaction, date, balance});
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showTransactions();
    }
}


/*

package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class TransactionsPanel extends JPanel implements ActionListener {

    private JLabel lblDateRange, lblTransactions;
    private JPanel pnlProcess;
    private JComboBox cmbDateRange;
    private JTable TransactionsTable;
    private JScrollPane scroll;

    public TransactionsPanel() {
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(25, 25, 787, 510);
        pnlProcess.setBackground(new Color(243, 243, 243));
        pnlProcess.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblDateRange = new JLabel("Date Range");
        lblDateRange.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDateRange.setBounds(25, 25, 737, 35);
        pnlProcess.add(lblDateRange);

        cmbDateRange = new JComboBox();
        cmbDateRange.setBounds(25, 65, 300, 35);
        cmbDateRange.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbDateRange.setOpaque(false);
        cmbDateRange.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlProcess.add(cmbDateRange);

        lblTransactions = new JLabel("Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setBounds(25, 125, 737, 35);
        pnlProcess.add(lblTransactions);

        String[] columns = {"Transaction ID", "Transaction", "Date", "Balance"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        TransactionsTable = new JTable(model);
        TransactionsTable.getTableHeader().setReorderingAllowed(false);
        scroll = new JScrollPane(TransactionsTable);
        scroll.setBounds(25, 165, 737, 320);
        pnlProcess.add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
*/