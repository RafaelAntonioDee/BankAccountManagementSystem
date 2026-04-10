/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafra
 */
public class DashboardPanel extends JPanel implements ActionListener{
    
    private JLabel lblBalance, lblAmount, lblTransactions, lblQuickTransfer, lblFrom, lblTo, lblTransferAmount, lblScheduledPayments, lblTemporaryScheduled;
    private JPanel pnlBalance, pnlTransactions, pnlScheduledPay, pnlQuickTransfer;
    private JButton btnDeposit, btnWithdraw, btnQuickTransfer, btnManagePayments;
    private JTable TransactionsTable;
    private JScrollPane scroll;
    private JTextField txtAmount;
    private JComboBox cmbFrom, cmbTo;

    public DashboardPanel() {
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

        lblAmount = new JLabel("    ₱0.00");
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
        
        lblTransactions = new JLabel("View All Accounts");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setBounds(35, 230, 325, 35);
        add(lblTransactions);
        
        pnlTransactions = new JPanel();
        pnlTransactions.setBounds(25, 270, 425, 260);
        pnlTransactions.setBackground(new Color(243, 243, 243));
        pnlTransactions.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlTransactions.setLayout(null);
        add(pnlTransactions);
        
        lblTransactions = new JLabel("Recent Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setBounds(25, 10, 250, 35);
        pnlTransactions.add(lblTransactions);
        
        String[] columns = {"Transaction ID", "Transaction", "Date", "Balance"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        TransactionsTable = new JTable(model);
        TransactionsTable.getTableHeader().setReorderingAllowed(false);
        scroll = new JScrollPane(TransactionsTable);
        scroll.setBounds(25, 45, 375, 195);
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
        
        lblTemporaryScheduled = new JLabel("DITO 'YON, BASTA");
        lblTemporaryScheduled.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTemporaryScheduled.setBounds(25, 82, 250, 35);
        pnlScheduledPay.add(lblTemporaryScheduled);
        
        btnManagePayments = new JButton("Manage Payments");
        btnManagePayments.setHorizontalAlignment(JButton.CENTER);
        btnManagePayments.setBounds(25, 140, 280, 35);
        btnManagePayments.setBackground(new Color(82, 124, 174));
        btnManagePayments.setForeground(Color.WHITE);
        btnManagePayments.setFocusPainted(false);
        btnManagePayments.addActionListener(this);
        pnlScheduledPay.add(btnManagePayments);
        
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
        
        lblFrom = new JLabel("From: ");
        lblFrom.setFont(new Font("Arial", Font.PLAIN, 18));
        lblFrom.setBounds(25, 70, 100, 35);
        pnlQuickTransfer.add(lblFrom);
        
        cmbFrom = new JComboBox<String>();
        cmbFrom.setBounds(120, 70, 185, 35);
        cmbFrom.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbFrom.setOpaque(false);
        cmbFrom.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlQuickTransfer.add(cmbFrom);
        
        lblTo = new JLabel("To: ");
        lblTo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTo.setBounds(25, 115, 50, 35);
        pnlQuickTransfer.add(lblTo);
        
        cmbTo = new JComboBox<String>();
        cmbTo.setBounds(120, 115, 185, 35);
        cmbTo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbTo.setOpaque(false);
        cmbTo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlQuickTransfer.add(cmbTo);
        
        lblTransferAmount = new JLabel("Amount: ");
        lblTransferAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransferAmount.setBounds(25, 160, 100, 35);
        pnlQuickTransfer.add(lblTransferAmount);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(120, 160, 185, 35);
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
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }
}
