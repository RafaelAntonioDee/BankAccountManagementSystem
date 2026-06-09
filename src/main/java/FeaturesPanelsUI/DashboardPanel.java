/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.AccountFunctions;
import AppService.BalanceFunctions;
import DashboardUIDefault.Colors;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import DashboardUIDefault.MainDashboard;
import Objects.Account;
import Objects.AccountTransactHistory;
import Objects.AutoPayment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author rafra
 */
public class DashboardPanel extends JPanel implements ActionListener {

    private JLabel lblAutoAmount, lblRecipient, lblFrequency, lblDate, lblBalance, lblAmount, lblTransactions, lblQuickTransfer, lblTo, lblTransferAmount, lblScheduledPayments, lblTemporaryScheduled;
    private JPanel pnlBalance, pnlTransactions, pnlScheduledPay, pnlQuickTransfer, pnlAutoPay;
    private JButton btnDeposit, btnWithdraw, btnQuickTransfer, btnManagePayments;
    private JTable TransactionsTable;
    private JScrollPane scroll;
    private JTextField txtAmount, txtEmail;
    private MainDashboard dashboard;
    private Account currentUser;
    private DefaultTableModel model;
    public static Colors theme = Colors.LIGHT();

    public DashboardPanel(MainDashboard dashboard, Account user) {

        this.currentUser = AppService.AccountFunctions.getUser(user.getEmail());
        this.dashboard = dashboard;

        if (currentUser.getSystemTheme().equals("Light") || currentUser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }

        setBounds(0, 0, 837, 560);
        setBackground(theme.BACKGROUND);
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setLayout(null);

        // Main Checking Account
        pnlBalance = new JPanel();
        pnlBalance.setBounds(25, 25, 425, 200);
        pnlBalance.setBackground(theme.PANELS_BACKGROUND);
        pnlBalance.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlBalance.setLayout(null);
        add(pnlBalance);

        lblBalance = new JLabel("Balance");
        lblBalance.setForeground(theme.TEXT_BLACK);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBalance.setBounds(25, 20, 250, 35);
        pnlBalance.add(lblBalance);

        lblAmount = new JLabel("    ₱" + String.format("%.2f", currentUser.getBalance()));
        lblAmount.setForeground(theme.TEXT_WHITE);
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        lblAmount.setBounds(25, 60, 250, 50);
        lblAmount.setOpaque(true);
        lblAmount.setBackground(theme.PRIMARY_BLUE);
        pnlBalance.add(lblAmount);

        btnDeposit = new JButton("Deposit");
        btnDeposit.setHorizontalAlignment(JButton.CENTER);
        btnDeposit.setBounds(25, 130, 90, 35);
        btnDeposit.setBackground(theme.PRIMARY_BLUE);
        btnDeposit.setForeground(theme.TEXT_WHITE);
        btnDeposit.setFocusPainted(false);
        btnDeposit.addActionListener(this);
        pnlBalance.add(btnDeposit);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setHorizontalAlignment(JButton.CENTER);
        btnWithdraw.setBounds(135, 130, 90, 35);
        btnWithdraw.setBackground(theme.SUCCESS_GREEN);
        btnWithdraw.setForeground(theme.TEXT_WHITE);
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.addActionListener(this);
        pnlBalance.add(btnWithdraw);

        // Recent Transactions
        pnlTransactions = new JPanel();
        pnlTransactions.setBounds(25, 250, 425, 285);
        pnlTransactions.setBackground(theme.PANELS_BACKGROUND);
        pnlTransactions.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlTransactions.setLayout(null);
        add(pnlTransactions);

        lblTransactions = new JLabel("Recent Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setBounds(25, 10, 250, 35);
        lblTransactions.setForeground(theme.TEXT_BLACK);
        pnlTransactions.add(lblTransactions);

        String[] columns = {"Reference ID", "Type", "Date", "Balance"};
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
        scroll.setBounds(25, 45, 375, 220);
        scroll.setBackground(theme.BACKGROUND);
        scroll.setBorder(new LineBorder(theme.BORDER_GRAY));
        scroll.getViewport().setBackground(theme.BACKGROUND);
        pnlTransactions.add(scroll);

        // Scheduled Payments
        pnlScheduledPay = new JPanel();
        pnlScheduledPay.setBounds(475, 25, 330, 200);
        pnlScheduledPay.setBackground(theme.PANELS_BACKGROUND);
        pnlScheduledPay.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlScheduledPay.setLayout(null);
        add(pnlScheduledPay);

        lblScheduledPayments = new JLabel("Scheduled Payments");
        lblScheduledPayments.setFont(new Font("Arial", Font.PLAIN, 18));
        lblScheduledPayments.setBounds(25, 10, 250, 35);
        lblScheduledPayments.setForeground(theme.TEXT_BLACK);
        pnlScheduledPay.add(lblScheduledPayments);

        btnManagePayments = new JButton("Manage Payments");
        btnManagePayments.setHorizontalAlignment(JButton.CENTER);
        btnManagePayments.setBounds(25, 140, 280, 35);
        btnManagePayments.setBackground(theme.PRIMARY_BLUE);
        btnManagePayments.setForeground(theme.TEXT_WHITE);
        btnManagePayments.setFocusPainted(false);
        btnManagePayments.addActionListener(this);
        pnlScheduledPay.add(btnManagePayments);

        AutoPayment autopaydata = AppService.AutoPaymentFunctions.getFirstAutoPay(currentUser.getEmail());

        if (autopaydata != null) {
            pnlAutoPay = new JPanel();
            pnlAutoPay.setBounds(25, 45, 280, 85);
            pnlAutoPay.setBackground(theme.BACKGROUND);
            pnlAutoPay.setBorder(new LineBorder(theme.PRIMARY_BLUE));
            pnlAutoPay.setLayout(null);
            pnlScheduledPay.add(pnlAutoPay);

            lblRecipient = new JLabel();
            lblRecipient.setFont(new Font("Arial", Font.PLAIN, 16));
            lblRecipient.setBounds(10, 5, 337, 25);
            lblRecipient.setText(autopaydata.getPayee());
            lblRecipient.setForeground(theme.TEXT_BLACK);
            pnlAutoPay.add(lblRecipient);

            lblAutoAmount = new JLabel();
            lblAutoAmount.setBounds(10, 30, 337, 25);
            lblAutoAmount.setText("Amount: " + String.format("%.2f", autopaydata.getAmount()));
            lblAutoAmount.setForeground(theme.TEXT_BLACK);
            pnlAutoPay.add(lblAutoAmount);

            lblFrequency = new JLabel();
            lblFrequency.setBounds(10, 55, 337, 25);
            lblFrequency.setText("Frequency: " + autopaydata.getFrequency());
            lblFrequency.setForeground(theme.TEXT_BLACK);
            pnlAutoPay.add(lblFrequency);

            String dueDateFormatted = autopaydata.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

            lblDate = new JLabel();
            lblDate.setBounds(135, 30, 337, 25);
            lblDate.setText("Due Date: " + dueDateFormatted);
            lblDate.setForeground(theme.TEXT_BLACK);
            pnlAutoPay.add(lblDate);
        }

        // QUICK TRANSFER
        pnlQuickTransfer = new JPanel();
        pnlQuickTransfer.setBounds(475, 250, 330, 280);
        pnlQuickTransfer.setBackground(theme.PANELS_BACKGROUND);
        pnlQuickTransfer.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlQuickTransfer.setLayout(null);
        add(pnlQuickTransfer);

        lblQuickTransfer = new JLabel("Quick Transfer");
        lblQuickTransfer.setFont(new Font("Arial", Font.PLAIN, 18));
        lblQuickTransfer.setBounds(25, 10, 250, 35);
        lblQuickTransfer.setForeground(theme.TEXT_BLACK);
        pnlQuickTransfer.add(lblQuickTransfer);

        lblTo = new JLabel("To (email): ");
        lblTo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTo.setBounds(25, 85, 100, 35);
        lblTo.setForeground(theme.TEXT_BLACK);
        pnlQuickTransfer.add(lblTo);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 85, 185, 35);
        txtEmail.setBackground(theme.BACKGROUND);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtEmail.setForeground(theme.TEXT_BLACK);
        pnlQuickTransfer.add(txtEmail);

        lblTransferAmount = new JLabel("Amount: ");
        lblTransferAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransferAmount.setBounds(25, 145, 100, 35);
        lblTransferAmount.setForeground(theme.TEXT_BLACK);
        pnlQuickTransfer.add(lblTransferAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(120, 145, 185, 35);
        txtAmount.setBackground(theme.BACKGROUND);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtAmount.setForeground(theme.TEXT_BLACK);
        pnlQuickTransfer.add(txtAmount);

        btnQuickTransfer = new JButton("Transfer");
        btnQuickTransfer.setHorizontalAlignment(JButton.CENTER);
        btnQuickTransfer.setBounds(215, 220, 90, 35);
        btnQuickTransfer.setBackground(theme.PRIMARY_BLUE);
        btnQuickTransfer.setForeground(theme.TEXT_WHITE);
        btnQuickTransfer.setFocusPainted(false);
        btnQuickTransfer.addActionListener(this);
        pnlQuickTransfer.add(btnQuickTransfer);

        showTransactions();
    }

    public void showTransactions() {

        ArrayList<AccountTransactHistory> userHistory
                = BalanceFunctions.getTransactions(currentUser.getEmail());

        model.setRowCount(0);

        int count = 0;

        for (AccountTransactHistory transaction : userHistory) {

            if (count >= 10) {
                break;
            }

            String id = transaction.getTransactionID();
            String type = transaction.getTransaction();
            LocalDate date = transaction.getDate();
            String dueDateFormatted = date.format(
                    DateTimeFormatter.ofPattern("MMMM dd, yyyy")
            );
            String balance = String.valueOf(transaction.getBalanceChange());

            model.addRow(new Object[]{id, type, dueDateFormatted, balance});

            count++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDeposit) {
            dashboard.switchPanel(dashboard.sideBar.btnDeposit, "btnDeposit", "Deposit", new DepositPanel(currentUser));
        } else if (e.getSource() == btnWithdraw) {
            dashboard.switchPanel(dashboard.sideBar.btnWithdraw, "btnWithdraw", "Withdraw", new WithdrawPanel(currentUser));
        } else if (e.getSource() == btnManagePayments) {
            dashboard.switchPanel(dashboard.sideBar.btnAutoPayments, "btnAutoPayments", "Auto Payments", new AutoPaymentPanel(currentUser.getEmail(), currentUser));
        } else if (e.getSource() == btnQuickTransfer) {
            String email = txtEmail.getText();
            String amountText = txtAmount.getText();

            try {

                if (email.isEmpty() && amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter recipient email!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter transfer amount!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount = Double.parseDouble(amountText);

                Account receiver = AccountFunctions.getUser(email);

                if (email.equalsIgnoreCase(currentUser.getEmail())) {
                    JOptionPane.showMessageDialog(this, "You cannot transfer money to your own account!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (receiver == null) {
                    JOptionPane.showMessageDialog(this, "Account not found!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (currentUser.getBalance() < amount) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sequentialTxnId = BalanceFunctions.getNextTransactionID();
                BalanceFunctions.addTransaction(currentUser.getEmail(), "Transferred", LocalDate.now(), "- " + amount, sequentialTxnId);

                String sequentialTxnId2 = BalanceFunctions.getNextTransactionID();
                BalanceFunctions.addTransaction(receiver.getEmail(), "Received", LocalDate.now(), "+ " + amount, sequentialTxnId2);

                System.out.println(sequentialTxnId + sequentialTxnId2);

                BalanceFunctions.transfer(currentUser, receiver, amount);

                lblAmount.setText("    ₱" + String.format("%.2f", currentUser.getBalance()));

                txtAmount.setText("");
                txtEmail.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!", "Invalid", JOptionPane.ERROR_MESSAGE);
            }

            showTransactions();
        }
    }

}
