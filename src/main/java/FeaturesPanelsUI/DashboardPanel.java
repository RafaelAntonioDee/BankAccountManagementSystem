/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.AccountFunctions;
import AppService.BalanceFunctions;
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

    public DashboardPanel(MainDashboard dashboard, Account user) {                
        this.currentUser = user;
        this.dashboard = dashboard;

        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        // Main Checking Account
        pnlBalance = new JPanel();
        pnlBalance.setBounds(25, 25, 425, 200);
        pnlBalance.setBackground(new Color(243, 243, 243));
        pnlBalance.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlBalance.setLayout(null);
        add(pnlBalance);

        lblBalance = new JLabel("Balance");
        lblBalance.setForeground(Color.GRAY);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBalance.setBounds(25, 20, 250, 35);
        pnlBalance.add(lblBalance);

        lblAmount = new JLabel("    ₱" + String.format("%.2f", user.getBalance()));
        lblAmount.setForeground(Color.WHITE);
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        lblAmount.setBounds(25, 60, 250, 50);
        lblAmount.setOpaque(true);
        lblAmount.setBackground(new Color(82, 124, 174));
        pnlBalance.add(lblAmount);

        btnDeposit = new JButton("Deposit");
        btnDeposit.setHorizontalAlignment(JButton.CENTER);
        btnDeposit.setBounds(25, 130, 90, 35);
        btnDeposit.setBackground(new Color(82, 124, 174));
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.setFocusPainted(false);
        btnDeposit.addActionListener(this);
        pnlBalance.add(btnDeposit);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setHorizontalAlignment(JButton.CENTER);
        btnWithdraw.setBounds(135, 130, 90, 35);
        btnWithdraw.setBackground(new Color(82, 174, 124));
        btnWithdraw.setForeground(Color.WHITE);
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.addActionListener(this);
        pnlBalance.add(btnWithdraw);

        // Recent Transactions
        pnlTransactions = new JPanel();
        pnlTransactions.setBounds(25, 250, 425, 285);
        pnlTransactions.setBackground(new Color(243, 243, 243));
        pnlTransactions.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlTransactions.setLayout(null);
        add(pnlTransactions);

        lblTransactions = new JLabel("Recent Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setBounds(25, 10, 250, 35);
        pnlTransactions.add(lblTransactions);

        String[] columns = {"Transaction ID", "Type", "Date", "Balance"};
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
        scroll.setBounds(25, 45, 375, 220);
        pnlTransactions.add(scroll);

        // Scheduled Payments
        pnlScheduledPay = new JPanel();
        pnlScheduledPay.setBounds(475, 25, 330, 200);
        pnlScheduledPay.setBackground(new Color(243, 243, 243));
        pnlScheduledPay.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlScheduledPay.setLayout(null);
        add(pnlScheduledPay);

        lblScheduledPayments = new JLabel("Scheduled Payments");
        lblScheduledPayments.setFont(new Font("Arial", Font.PLAIN, 18));
        lblScheduledPayments.setBounds(25, 10, 250, 35);
        pnlScheduledPay.add(lblScheduledPayments);

        btnManagePayments = new JButton("Manage Payments");
        btnManagePayments.setHorizontalAlignment(JButton.CENTER);
        btnManagePayments.setBounds(25, 140, 280, 35);
        btnManagePayments.setBackground(new Color(82, 124, 174));
        btnManagePayments.setForeground(Color.WHITE);
        btnManagePayments.setFocusPainted(false);
        btnManagePayments.addActionListener(this);
        pnlScheduledPay.add(btnManagePayments);

        AutoPayment autopaydata = AppService.AutoPaymentFunctions.getFirstAutoPay(user.getEmail());

        if (autopaydata != null) {
            pnlAutoPay = new JPanel();
            pnlAutoPay.setBounds(25, 45, 280, 85);
            pnlAutoPay.setBackground(new Color(243, 243, 243));
            pnlAutoPay.setBorder(new LineBorder(new Color(82, 124, 174)));
            pnlAutoPay.setLayout(null);
            pnlScheduledPay.add(pnlAutoPay);

            lblRecipient = new JLabel();
            lblRecipient.setFont(new Font("Arial", Font.PLAIN, 16));
            lblRecipient.setBounds(10, 5, 337, 25);
            lblRecipient.setText(autopaydata.getPayee());
            pnlAutoPay.add(lblRecipient);

            lblAutoAmount = new JLabel();
            lblAutoAmount.setBounds(10, 30, 337, 25);
            lblAutoAmount.setText("Amount: " + String.format("%.2f", autopaydata.getAmount()));
            pnlAutoPay.add(lblAutoAmount);

            lblFrequency = new JLabel();
            lblFrequency.setBounds(10, 55, 337, 25);
            lblFrequency.setText("Frequency: " + autopaydata.getFrequency());
            pnlAutoPay.add(lblFrequency);

            String dueDateFormatted = autopaydata.getDate().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

            lblDate = new JLabel();
            lblDate.setBounds(135, 30, 337, 25);
            lblDate.setText("Due Date: " + dueDateFormatted);
            pnlAutoPay.add(lblDate);
        }

        // Quick Transfer
        pnlQuickTransfer = new JPanel();
        pnlQuickTransfer.setBounds(475, 250, 330, 280);
        pnlQuickTransfer.setBackground(new Color(243, 243, 243));
        pnlQuickTransfer.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlQuickTransfer.setLayout(null);
        add(pnlQuickTransfer);

        lblQuickTransfer = new JLabel("Quick Transfer");
        lblQuickTransfer.setFont(new Font("Arial", Font.PLAIN, 18));
        lblQuickTransfer.setBounds(25, 10, 250, 35);
        pnlQuickTransfer.add(lblQuickTransfer);

        lblTo = new JLabel("To: ");
        lblTo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTo.setBounds(25, 85, 50, 35);
        pnlQuickTransfer.add(lblTo);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 85, 185, 35);
        txtEmail.setOpaque(false);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlQuickTransfer.add(txtEmail);

        lblTransferAmount = new JLabel("Amount: ");
        lblTransferAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransferAmount.setBounds(25, 145, 100, 35);
        pnlQuickTransfer.add(lblTransferAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(120, 145, 185, 35);
        txtAmount.setOpaque(false);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlQuickTransfer.add(txtAmount);

        btnQuickTransfer = new JButton("Transfer");
        btnQuickTransfer.setHorizontalAlignment(JButton.CENTER);
        btnQuickTransfer.setBounds(215, 220, 90, 35);
        btnQuickTransfer.setBackground(new Color(82, 124, 174));
        btnQuickTransfer.setForeground(Color.WHITE);
        btnQuickTransfer.setFocusPainted(false);
        btnQuickTransfer.addActionListener(this);
        pnlQuickTransfer.add(btnQuickTransfer);

        showTransactions();
    }

    public void showTransactions() {

        ArrayList<AccountTransactHistory> userHistory = BalanceFunctions.getTransactions(currentUser.getEmail());
        model.setRowCount(0);

        LocalDate today = LocalDate.now();

        for (AccountTransactHistory transaction : userHistory) {

            String id = transaction.getTransactionID();
            String type = transaction.getTransaction();
            LocalDate date = transaction.getDate();
            String dueDateFormatted = date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
            String balance = String.valueOf(transaction.getBalanceChange());

            model.addRow(new Object[]{id, type, dueDateFormatted, balance});

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDeposit) {
            dashboard.switchPanel(dashboard.sideBar.btnDeposit, "btnDeposit", "Deposit", new DepositPanel(currentUser));
        } else if (e.getSource() == btnWithdraw) {
            dashboard.switchPanel(dashboard.sideBar.btnWithdraw, "btnWithdraw", "Withdraw", new WithdrawPanel(currentUser));
        } else if (e.getSource() == btnManagePayments) {
            dashboard.switchPanel(dashboard.sideBar.btnAutoPayments, "btnAutoPayments", "Auto Payments", new AutoPaymentPanel(currentUser.getEmail()));
        } else if (e.getSource() == btnQuickTransfer) {
            String email = txtEmail.getText();
            String amountText = txtAmount.getText();

            try {
                double amount = Double.parseDouble(amountText);

                Account receiver = AccountFunctions.getUser(email);

                if (receiver == null) {
                    JOptionPane.showMessageDialog(this, "Account not found!");
                    return;
                }

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!");
                    return;
                }

                if (currentUser.getBalance() < amount) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                    return;
                }

                BalanceFunctions.transfer(currentUser, receiver, amount);

                lblAmount.setText("    ₱" + String.format("%.2f", currentUser.getBalance()));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!");
            }
            
            showTransactions();
        }
    }

}
