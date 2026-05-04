/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import Objects.UserAccount;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class SidePanel extends JPanel implements MouseListener {

    public JPanel pnlSideBar, pnlSideBar_Header, pnlAccount;
    public JLabel lblBank, lblAccName, lblAccEmail, lblLine, lblSelectIndicator;
    public JButton btnDashboard, btnDeposit, btnWithdraw, btnTransfer, btnTransactions, btnAutoPayments, btnSettings, btnLogout;
    public String SelectedButton = "btnDashboard";

    SidePanel() {
        setBounds(0, 0, 175, 620);
        setBackground(new Color(228, 228, 236));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        pnlSideBar_Header = new JPanel();
        pnlSideBar_Header.setBounds(0, 0, 175, 60);
        pnlSideBar_Header.setBackground(new Color(82, 124, 174));
        pnlSideBar_Header.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlSideBar_Header.setLayout(null);
        add(pnlSideBar_Header);

        lblBank = new JLabel("Bank", Icons.BankIconWhite, JLabel.LEFT);
        lblBank.setForeground(Color.WHITE);
        lblBank.setFont(new Font("Arial", Font.PLAIN, 20));
        lblBank.setBounds(25, 10, 125, 40);
        lblBank.setHorizontalAlignment(JLabel.CENTER);
        lblBank.setIconTextGap(10);
        pnlSideBar_Header.add(lblBank);

        btnDashboard = new JButton("Dashboard", Icons.DashboardIcon);
        btnDashboard.setHorizontalAlignment(JButton.LEFT);
        btnDashboard.setIconTextGap(10);
        btnDashboard.setBounds(10, 70, 155, 30);
        btnDashboard.setBackground(new Color(201, 211, 221));
        btnDashboard.setBorderPainted(false);
        btnDashboard.setFocusPainted(false);
        btnDashboard.setLayout(null);
        btnDashboard.addMouseListener(this);
        add(btnDashboard);

        btnDeposit = new JButton("Deposit", Icons.DepositIcon);
        btnDeposit.setHorizontalAlignment(JButton.LEFT);
        btnDeposit.setIconTextGap(10);
        btnDeposit.setBounds(10, 110, 155, 30);
        btnDeposit.setBackground(new Color(201, 211, 221));
        btnDeposit.setBorderPainted(false);
        btnDeposit.setFocusPainted(false);
        btnDeposit.setContentAreaFilled(false);
        btnDeposit.setLayout(null);
        btnDeposit.addMouseListener(this);
        add(btnDeposit);

        btnWithdraw = new JButton("Withdraw", Icons.WithdrawIcon);
        btnWithdraw.setHorizontalAlignment(JButton.LEFT);
        btnWithdraw.setIconTextGap(10);
        btnWithdraw.setBounds(10, 150, 155, 30);
        btnWithdraw.setBackground(new Color(201, 211, 221));
        btnWithdraw.setBorderPainted(false);
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.setContentAreaFilled(false);
        btnWithdraw.setLayout(null);
        btnWithdraw.addMouseListener(this);
        add(btnWithdraw);

        btnTransfer = new JButton("Transfer", Icons.TransferIcon);
        btnTransfer.setHorizontalAlignment(JButton.LEFT);
        btnTransfer.setIconTextGap(10);
        btnTransfer.setBounds(10, 190, 155, 30);
        btnTransfer.setBackground(new Color(201, 211, 221));
        btnTransfer.setBorderPainted(false);
        btnTransfer.setFocusPainted(false);
        btnTransfer.setContentAreaFilled(false);
        btnTransfer.setLayout(null);
        btnTransfer.addMouseListener(this);
        add(btnTransfer);

        btnTransactions = new JButton("Transactions", Icons.TransactionsIcon);
        btnTransactions.setHorizontalAlignment(JButton.LEFT);
        btnTransactions.setIconTextGap(10);
        btnTransactions.setBounds(10, 230, 155, 30);
        btnTransactions.setBackground(new Color(201, 211, 221));
        btnTransactions.setBorderPainted(false);
        btnTransactions.setFocusPainted(false);
        btnTransactions.setContentAreaFilled(false);
        btnTransactions.setLayout(null);
        btnTransactions.addMouseListener(this);
        add(btnTransactions);

        btnAutoPayments = new JButton("Auto Payments", Icons.AutoPaymentIcon);
        btnAutoPayments.setHorizontalAlignment(JButton.LEFT);
        btnAutoPayments.setIconTextGap(10);
        btnAutoPayments.setBounds(10, 270, 155, 30);
        btnAutoPayments.setBackground(new Color(201, 211, 221));
        btnAutoPayments.setBorderPainted(false);
        btnAutoPayments.setFocusPainted(false);
        btnAutoPayments.setContentAreaFilled(false);
        btnAutoPayments.addMouseListener(this);
        btnAutoPayments.setLayout(null);
        add(btnAutoPayments);

        btnSettings = new JButton("Settings", Icons.SettingsIcon);
        btnSettings.setHorizontalAlignment(JButton.LEFT);
        btnSettings.setIconTextGap(10);
        btnSettings.setBounds(10, 310, 155, 30);
        btnSettings.setBackground(new Color(201, 211, 221));
        btnSettings.setBorderPainted(false);
        btnSettings.setFocusPainted(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.addMouseListener(this);
        btnSettings.setLayout(null);
        add(btnSettings);

        pnlAccount = new JPanel();
        pnlAccount.setBounds(0, 520, 175, 100);
        pnlAccount.setBackground(new Color(240, 240, 240));
        pnlAccount.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlAccount.setLayout(null);
        add(pnlAccount);

        lblAccName = new JLabel("Rafael Antonio Dee");
        lblAccName.setBounds(20, 8, 135, 30);
        pnlAccount.add(lblAccName);

        lblAccEmail = new JLabel("hatdog@gmail.com");
        lblAccEmail.setFont(new Font("Arial", Font.PLAIN, 10));
        lblAccEmail.setBounds(20, 28, 135, 30);
        lblAccEmail.setForeground(Color.GRAY);
        pnlAccount.add(lblAccEmail);

        lblLine = new JLabel("_______________________");
        lblLine.setBounds(5, 37, 165, 30);
        lblLine.setForeground(Color.LIGHT_GRAY);
        lblLine.setHorizontalAlignment(JLabel.CENTER);
        pnlAccount.add(lblLine);

        btnLogout = new JButton("Log Out", Icons.LogoutIcon);
        btnLogout.setHorizontalAlignment(JButton.LEFT);
        btnLogout.setHorizontalTextPosition(JButton.RIGHT);
        btnLogout.setIconTextGap(10);
        btnLogout.setBounds(5, 62, 165, 30);
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setContentAreaFilled(false);
        pnlAccount.add(btnLogout);

        lblSelectIndicator = new JLabel("");
        lblSelectIndicator.setBounds(0, 0, 5, 30);
        lblSelectIndicator.setOpaque(true);
        lblSelectIndicator.setBackground(new Color(82, 124, 174));
        btnDashboard.add(lblSelectIndicator);
    }

    void clearSelected() {
        btnDashboard.setContentAreaFilled(false);
        btnDeposit.setContentAreaFilled(false);
        btnWithdraw.setContentAreaFilled(false);
        btnTransfer.setContentAreaFilled(false);
        btnTransactions.setContentAreaFilled(false);
        btnAutoPayments.setContentAreaFilled(false);
        btnSettings.setContentAreaFilled(false);
    }

    void removeIndicator() {
        switch (SelectedButton) {
            case "btnDashboard":
                btnDashboard.remove(lblSelectIndicator);
                break;
            case "btnDeposit":
                btnDeposit.remove(lblSelectIndicator);
                break;
            case "btnWithdraw":
                btnWithdraw.remove(lblSelectIndicator);
                break;
            case "btnTransfer":
                btnTransfer.remove(lblSelectIndicator);
                break;
            case "btnTransactions":
                btnTransactions.remove(lblSelectIndicator);
                break;
            case "btnAutoPayments":
                btnAutoPayments.remove(lblSelectIndicator);
                break;
            case "btnSettings":
                btnSettings.remove(lblSelectIndicator);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == btnDashboard) {
            btnDashboard.setContentAreaFilled(true);
        } else if (e.getSource() == btnDeposit) {
            btnDeposit.setContentAreaFilled(true);
        } else if (e.getSource() == btnWithdraw) {
            btnWithdraw.setContentAreaFilled(true);
        } else if (e.getSource() == btnTransfer) {
            btnTransfer.setContentAreaFilled(true);
        } else if (e.getSource() == btnTransactions) {
            btnTransactions.setContentAreaFilled(true);
        } else if (e.getSource() == btnAutoPayments) {
            btnAutoPayments.setContentAreaFilled(true);
        } else if (e.getSource() == btnSettings) {
            btnSettings.setContentAreaFilled(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        clearSelected();
        switch (SelectedButton) {
            case "btnDashboard":
                btnDashboard.setContentAreaFilled(true);
                break;
            case "btnDeposit":
                btnDeposit.setContentAreaFilled(true);
                break;
            case "btnWithdraw":
                btnWithdraw.setContentAreaFilled(true);
                break;
            case "btnTransfer":
                btnTransfer.setContentAreaFilled(true);
                break;
            case "btnTransactions":
                btnTransactions.setContentAreaFilled(true);
                break;
            case "btnAutoPayments":
                btnAutoPayments.setContentAreaFilled(true);
                break;
            case "btnSettings":
                btnSettings.setContentAreaFilled(true);
                break;
            default:
                break;
        }
    }
    
    public void setUserDetails(UserAccount user) {
    lblAccName.setText(user.getFullName()); 
    lblAccEmail.setText(user.getEmail());   
}

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
