package FeaturesPanelsUI;

import Objects.AccountTransactHistory;
import AppService.BalanceFunctions;
import Objects.Account;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TransactionsPanel extends JPanel implements ActionListener {

    private JLabel lblSearch, lblDate, lblType, lblTransactions;
    private JTextField txtSearch;
    private JButton btnSearch;
    private JComboBox cmbDateRange, cmbTransactionType;
    private JTable TransactionsTable;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private JPanel pnlProcess;
    private Account currentUser;

    public TransactionsPanel(Account user) {
        this.currentUser = user;
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        lblSearch = new JLabel("Search");
        lblSearch.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSearch.setForeground(Color.GRAY);
        lblSearch.setBounds(25, 25, 250, 35);
        add(lblSearch);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/search.png"));

        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(16, 16, Image.SCALE_SMOOTH);

        ImageIcon searchIcon = new ImageIcon(resizedImg);

        btnSearch = new JButton(searchIcon);
        btnSearch.setBounds(25, 60, 40, 35);
        btnSearch.setFocusPainted(false);
        btnSearch.addActionListener(this);
        add(btnSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(70, 60, 360, 35);
        add(txtSearch);

        lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDate.setForeground(Color.GRAY);
        lblDate.setBounds(485, 25, 100, 25);
        add(lblDate);

        cmbDateRange = new JComboBox();
        cmbDateRange.addItem("All");
        cmbDateRange.addItem("Past Day");
        cmbDateRange.addItem("Past Week");
        cmbDateRange.addItem("Past Month");
        cmbDateRange.addItem("Past 3 Months");
        cmbDateRange.setBounds(485, 60, 150, 35);
        add(cmbDateRange);

        lblType = new JLabel("Type");
        lblType.setFont(new Font("Arial", Font.PLAIN, 18));
        lblType.setForeground(Color.GRAY);
        lblType.setBounds(665, 25, 100, 25);
        add(lblType);

        cmbTransactionType = new JComboBox();
        cmbTransactionType.addItem("All");
        cmbTransactionType.addItem("Deposit");
        cmbTransactionType.addItem("Withdraw");
        cmbTransactionType.addItem("Transfer");
        cmbTransactionType.addItem("Payment");
        cmbTransactionType.setBounds(665, 60, 147, 35);
        add(cmbTransactionType);

        //------------------------------- Transactions List -------------------------------
        lblTransactions = new JLabel("Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setForeground(Color.GRAY);
        lblTransactions.setBounds(25, 110, 737, 35);
        add(lblTransactions);

        String[] columns = {"Transaction ID", "Transaction Type", "Date", "Balance"};
        model = new DefaultTableModel(columns, 0);

        TransactionsTable = new JTable(model);
        TransactionsTable.getTableHeader().setReorderingAllowed(false);
        TransactionsTable.setRowHeight(28);
        TransactionsTable.setIntercellSpacing(new Dimension(10, 6));
        TransactionsTable.getTableHeader().setResizingAllowed(false);
        TransactionsTable.setRowSelectionAllowed(false);

        JTableHeader header = TransactionsTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(new Color(82, 124, 174));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        scroll = new JScrollPane(TransactionsTable);
        scroll.setBounds(25, 145, 787, 390);
        add(scroll);

        showTransactions();
    }

    public void showTransactions() {

        ArrayList<AccountTransactHistory> userHistory = BalanceFunctions.getTransactions(currentUser.getEmail());
        model.setRowCount(0);

        String search = txtSearch.getText().trim().toLowerCase();
        String selectedDate = cmbDateRange.getSelectedItem().toString();
        String selectedType = cmbTransactionType.getSelectedItem().toString();

        LocalDate today = LocalDate.now();

        for (AccountTransactHistory transaction : userHistory) {

            String id = transaction.getTransactionID();
            String type = transaction.getTransaction();
            LocalDate date = transaction.getDate();
            String dueDateFormatted = date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
            String balance = String.valueOf(transaction.getBalanceChange());

            boolean searchOk = search.equals("") || id.toLowerCase().contains(search) || type.toLowerCase().contains(search);

            boolean typeOk = selectedType.equals("All") || type.equals(selectedType);

            boolean dateOk = true;
            LocalDate transDate = date;

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
                model.addRow(new Object[]{id, type, dueDateFormatted, balance});
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showTransactions();
    }
}
