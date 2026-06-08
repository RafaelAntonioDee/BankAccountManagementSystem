/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import AppService.AccountFunctions;
import static DashboardUIDefault.BottomPanel.theme;
import static DashboardUIDefault.MainDashboard.theme;
import Objects.Account;
import Objects.AccountPersonalInformation;
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
    public static JLabel lblBank, lblAccName, lblAccEmail, lblLine, lblSelectIndicator;
    public JButton btnDashboard, btnDeposit, btnWithdraw, btnTransfer, btnTransactions, btnAutoPayments, btnSettings, btnLogout;
    public String SelectedButton = "btnDashboard";
    public static Colors theme = Colors.LIGHT();
    public static Icons icons = Icons.LIGHT();

    SidePanel(Account user) {

        if (user.getSystemTheme().equals("Light")) {
            theme = Colors.LIGHT();
            icons = Icons.LIGHT();
        } else {
            theme = Colors.DARK();
            icons = Icons.DARK();
        }


        setBounds(0, 0, 175, 620);
        setBackground(theme.SidePanel);
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setLayout(null);

        pnlSideBar_Header = new JPanel();
        pnlSideBar_Header.setBounds(0, 0, 175, 60);
        pnlSideBar_Header.setBackground(theme.PRIMARY_BLUE);
        pnlSideBar_Header.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlSideBar_Header.setLayout(null);
        add(pnlSideBar_Header);

        lblBank = new JLabel("Bank", Icons.BankIconWhite, JLabel.LEFT);
        lblBank.setForeground(theme.TEXT_WHITE);
        lblBank.setFont(new Font("Arial", Font.PLAIN, 20));
        lblBank.setBounds(25, 10, 125, 40);
        lblBank.setHorizontalAlignment(JLabel.CENTER);
        lblBank.setIconTextGap(10);
        pnlSideBar_Header.add(lblBank);

        btnDashboard = new JButton("Dashboard", icons.DashboardIcon);
        btnDashboard.setHorizontalAlignment(JButton.LEFT);
        btnDashboard.setIconTextGap(10);
        btnDashboard.setBounds(10, 70, 155, 30);
        btnDashboard.setBackground(theme.ButtonHighlight);
        btnDashboard.setForeground(theme.TEXT_BLACK);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setFocusPainted(false);
        btnDashboard.setLayout(null);
        btnDashboard.addMouseListener(this);
        add(btnDashboard);

        btnDeposit = new JButton("Deposit", icons.DepositIcon);
        btnDeposit.setHorizontalAlignment(JButton.LEFT);
        btnDeposit.setIconTextGap(10);
        btnDeposit.setBounds(10, 110, 155, 30);
        btnDeposit.setBackground(theme.ButtonHighlight);
        btnDeposit.setForeground(theme.TEXT_BLACK);
        btnDeposit.setBorderPainted(false);
        btnDeposit.setFocusPainted(false);
        btnDeposit.setContentAreaFilled(false);
        btnDeposit.setLayout(null);
        btnDeposit.addMouseListener(this);
        add(btnDeposit);

        btnWithdraw = new JButton("Withdraw", icons.WithdrawIcon);
        btnWithdraw.setHorizontalAlignment(JButton.LEFT);
        btnWithdraw.setIconTextGap(10);
        btnWithdraw.setBounds(10, 150, 155, 30);
        btnWithdraw.setBackground(theme.ButtonHighlight);
        btnWithdraw.setForeground(theme.TEXT_BLACK);
        btnWithdraw.setBorderPainted(false);
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.setContentAreaFilled(false);
        btnWithdraw.setLayout(null);
        btnWithdraw.addMouseListener(this);
        add(btnWithdraw);

        btnTransfer = new JButton("Transfer", icons.TransferIcon);
        btnTransfer.setHorizontalAlignment(JButton.LEFT);
        btnTransfer.setIconTextGap(10);
        btnTransfer.setBounds(10, 190, 155, 30);
        btnTransfer.setBackground(theme.ButtonHighlight);
        btnTransfer.setForeground(theme.TEXT_BLACK);
        btnTransfer.setBorderPainted(false);
        btnTransfer.setFocusPainted(false);
        btnTransfer.setContentAreaFilled(false);
        btnTransfer.setLayout(null);
        btnTransfer.addMouseListener(this);
        add(btnTransfer);

        btnTransactions = new JButton("Transactions", icons.TransactionsIcon);
        btnTransactions.setHorizontalAlignment(JButton.LEFT);
        btnTransactions.setIconTextGap(10);
        btnTransactions.setBounds(10, 230, 155, 30);
        btnTransactions.setBackground(theme.ButtonHighlight);
        btnTransactions.setForeground(theme.TEXT_BLACK);
        btnTransactions.setBorderPainted(false);
        btnTransactions.setFocusPainted(false);
        btnTransactions.setContentAreaFilled(false);
        btnTransactions.setLayout(null);
        btnTransactions.addMouseListener(this);
        add(btnTransactions);

        btnAutoPayments = new JButton("Auto Payments", icons.AutoPaymentIcon);
        btnAutoPayments.setHorizontalAlignment(JButton.LEFT);
        btnAutoPayments.setIconTextGap(10);
        btnAutoPayments.setBounds(10, 270, 155, 30);
        btnAutoPayments.setBackground(theme.ButtonHighlight);
        btnAutoPayments.setForeground(theme.TEXT_BLACK);
        btnAutoPayments.setBorderPainted(false);
        btnAutoPayments.setFocusPainted(false);
        btnAutoPayments.setContentAreaFilled(false);
        btnAutoPayments.addMouseListener(this);
        btnAutoPayments.setLayout(null);
        add(btnAutoPayments);

        btnSettings = new JButton("Settings", icons.SettingsIcon);
        btnSettings.setHorizontalAlignment(JButton.LEFT);
        btnSettings.setIconTextGap(10);
        btnSettings.setBounds(10, 310, 155, 30);
        btnSettings.setBackground(theme.ButtonHighlight);
        btnSettings.setForeground(theme.TEXT_BLACK);
        btnSettings.setBorderPainted(false);
        btnSettings.setFocusPainted(false);
        btnSettings.setContentAreaFilled(false);
        btnSettings.addMouseListener(this);
        btnSettings.setLayout(null);
        add(btnSettings);

        pnlAccount = new JPanel();
        pnlAccount.setBounds(0, 520, 175, 100);
        pnlAccount.setBackground(theme.HeaderFooterColor);
        pnlAccount.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlAccount.setLayout(null);
        add(pnlAccount);

        lblAccName = new JLabel();
        lblAccName.setBounds(20, 8, 135, 30);
        lblAccName.setForeground(theme.TEXT_BLACK);
        pnlAccount.add(lblAccName);

        lblAccEmail = new JLabel();
        lblAccEmail.setFont(new Font("Arial", Font.PLAIN, 10));
        lblAccEmail.setBounds(20, 28, 135, 30);
        lblAccEmail.setForeground(theme.TEXT_GRAY);
        pnlAccount.add(lblAccEmail);

        lblLine = new JLabel("_______________________");
        lblLine.setBounds(5, 37, 165, 30);
        lblLine.setForeground(theme.BORDER_GRAY);
        lblLine.setHorizontalAlignment(JLabel.CENTER);
        pnlAccount.add(lblLine);

        btnLogout = new JButton("Log Out", icons.LogoutIcon);
        btnLogout.setHorizontalAlignment(JButton.LEFT);
        btnLogout.setHorizontalTextPosition(JButton.RIGHT);
        btnLogout.setIconTextGap(10);
        btnLogout.setBounds(5, 62, 165, 30);
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusPainted(false);
        btnLogout.setContentAreaFilled(false);
        btnLogout.setForeground(theme.TEXT_BLACK);
        pnlAccount.add(btnLogout);

        lblSelectIndicator = new JLabel("");
        lblSelectIndicator.setBounds(0, 0, 5, 30);
        lblSelectIndicator.setOpaque(true);
        lblSelectIndicator.setBackground(theme.PRIMARY_BLUE);
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

    public void setUserDetails(AccountPersonalInformation userinfo) {
        lblAccName.setText(AccountFunctions.getFullName(userinfo));
        lblAccEmail.setText(userinfo.getEmail());
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
