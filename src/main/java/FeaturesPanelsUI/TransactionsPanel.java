package FeaturesPanelsUI;

import Objects.AccountTransactHistory;
import AppService.BalanceFunctions;
import DashboardUIDefault.Colors;
import DashboardUIDefault.Icons;
import static DashboardUIDefault.MainDashboard.theme;
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
    private String[] dateRange = {"Past Day", "Past Week", "Past Month", "Past 3 Months"},
            transacType = {"All", "Deposit", "Withdraw", "Transfer", "Payment"};
    public static Colors theme = Colors.LIGHT();
    public static Icons icons = Icons.LIGHT();

    public TransactionsPanel(Account user) {
        this.currentUser = AppService.AccountFunctions.getUser(user.getEmail());

        if (currentUser.getSystemTheme().equals("Light") || currentUser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
            icons = Icons.LIGHT();
        } else {
            theme = Colors.DARK();
            icons = Icons.DARK();
        }
        setBounds(0, 0, 837, 560);
        setBackground(theme.BACKGROUND);
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setLayout(null);

        lblSearch = new JLabel("Search");
        lblSearch.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSearch.setForeground(theme.TEXT_GRAY);
        lblSearch.setBounds(25, 25, 250, 35);
        add(lblSearch);

        btnSearch = new JButton(icons.SearchIcon);
        btnSearch.setBounds(25, 60, 40, 35);
        btnSearch.setFocusPainted(false);
        btnSearch.setBackground(theme.BORDER_GRAY);
        btnSearch.addActionListener(this);
        add(btnSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(70, 60, 360, 35);
        txtSearch.setForeground(theme.TEXT_BLACK);
        txtSearch.setBackground(theme.PANELS_BACKGROUND);
        add(txtSearch);

        lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDate.setForeground(theme.TEXT_GRAY);
        lblDate.setBounds(485, 25, 100, 25);
        add(lblDate);

        cmbDateRange = new JComboBox(dateRange);
        cmbDateRange.setBounds(485, 60, 150, 35);
        cmbDateRange.setFocusable(false);
        cmbDateRange.setForeground(theme.TEXT_BLACK);
        cmbDateRange.setBackground(theme.PANELS_BACKGROUND);
        add(cmbDateRange);

        cmbDateRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactions();
            }
        });

        cmbDateRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactions();
            }
        });

        lblType = new JLabel("Type");
        lblType.setFont(new Font("Arial", Font.PLAIN, 18));
        lblType.setForeground(theme.TEXT_GRAY);
        lblType.setBounds(665, 25, 100, 25);
        add(lblType);

        cmbTransactionType = new JComboBox(transacType);
        cmbTransactionType.setBounds(665, 60, 147, 35);
        cmbTransactionType.setFocusable(false);
        cmbTransactionType.setForeground(theme.TEXT_BLACK);
        cmbTransactionType.setBackground(theme.PANELS_BACKGROUND);
        add(cmbTransactionType);

        cmbTransactionType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactions();
            }
        });

        cmbTransactionType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactions();
            }
        });

        //------------------------------- Transactions List -------------------------------
        lblTransactions = new JLabel("Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setForeground(theme.TEXT_GRAY);
        lblTransactions.setBounds(25, 110, 737, 35);
        add(lblTransactions);

        String[] columns = {"Reference ID", "Transaction Type", "Date", "Balance"};
        model = new DefaultTableModel(columns, 0);

        TransactionsTable = new JTable(model);
        TransactionsTable.getTableHeader().setReorderingAllowed(false);
        TransactionsTable.setRowHeight(28);
        TransactionsTable.setIntercellSpacing(new Dimension(10, 6));
        TransactionsTable.getTableHeader().setResizingAllowed(false);
        TransactionsTable.setRowSelectionAllowed(false);
        TransactionsTable.setBackground(theme.BACKGROUND);
        TransactionsTable.setForeground(theme.TEXT_BLACK);
        TransactionsTable.setBorder(new LineBorder(theme.BORDER_GRAY));

        JTableHeader header = TransactionsTable.getTableHeader();
        header.setReorderingAllowed(false);
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setBackground(theme.PRIMARY_BLUE);
        header.setForeground(theme.TEXT_WHITE);
        header.setBorder(new LineBorder(theme.BORDER_GRAY));
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        scroll = new JScrollPane(TransactionsTable);
        scroll.setBounds(25, 145, 787, 390);
        scroll.setBackground(theme.BACKGROUND);
        scroll.setBorder(new LineBorder(theme.BORDER_GRAY));
        scroll.getViewport().setBackground(theme.BACKGROUND);
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
