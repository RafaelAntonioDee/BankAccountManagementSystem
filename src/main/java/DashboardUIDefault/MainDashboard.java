/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import FeaturesPanelsUI.*;
import LoginUI.LoginPage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class MainDashboard extends JFrame implements ActionListener {

    private JPanel pnlTopBar, pnlBotBar, pnlMain;
    private JLabel lblTopBar, lblDateToday, lblSystemName;
    private SidePanel sideBar;
    private TopPanel topBar;
    private BottomPanel botBar;
    private MainPanel mainPanel;
    private AutoPaymentPanel pnlAutoPay;
    private DashboardPanel pnlDashboard;
    private DepositPanel pnlDeposit;
    private SettingsPanel pnlSettings;
    private TransactionsPanel pnlTransactions;
    private TransferPanel pnlTransfer;
    private WithdrawPanel pnlWithdraw;

    public MainDashboard() {
        setIconImage(Icons.BankIcon.getImage());
        setTitle("Bank Account Management System");
        setSize(1025, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //------------------------------- Panels Setup -------------------------------
        //SIDE BAR
        sideBar = new SidePanel();
        add(sideBar);

        sideBar.btnLogout.addActionListener(this);
        sideBar.btnDashboard.addActionListener(this);
        sideBar.btnDeposit.addActionListener(this);
        sideBar.btnWithdraw.addActionListener(this);
        sideBar.btnTransfer.addActionListener(this);
        sideBar.btnTransactions.addActionListener(this);
        sideBar.btnSettings.addActionListener(this);
        sideBar.btnAutoPayments.addActionListener(this);

        //TOP BAR
        topBar = new TopPanel();
        add(topBar);

        //BOTTOM BAR
        botBar = new BottomPanel();
        add(botBar);

        //MAIN PANEL
        mainPanel = new MainPanel();
        add(mainPanel);

        pnlDashboard = new DashboardPanel();
        mainPanel.add(pnlDashboard);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sideBar.btnLogout) {
            dispose();
            LoginPage page = new LoginPage();
            page.setVisible(true);
        } else if (e.getSource() == sideBar.btnDashboard) {
            sideBar.removeIndicator();
            sideBar.btnDashboard.add(sideBar.lblSelectIndicator);
            sideBar.SelectedButton = "btnDashboard";
            sideBar.clearSelected();
            mainPanel.removeAll();
            topBar.lblTopBar.setText("Dashboard");
            pnlDashboard = new DashboardPanel();
            mainPanel.add(pnlDashboard);
        } else if (e.getSource() == sideBar.btnDeposit) {
            sideBar.removeIndicator();
            sideBar.btnDeposit.add(sideBar.lblSelectIndicator);
            sideBar.SelectedButton = "btnDeposit";
            sideBar.clearSelected();
            mainPanel.removeAll();
            topBar.lblTopBar.setText("Deposit");
            pnlDeposit = new DepositPanel();
            mainPanel.add(pnlDeposit);
        } else if (e.getSource() == sideBar.btnWithdraw) {
            sideBar.removeIndicator();
            sideBar.btnWithdraw.add(sideBar.lblSelectIndicator);
            sideBar.SelectedButton = "btnWithdraw";
            sideBar.clearSelected();
            mainPanel.removeAll();
            topBar.lblTopBar.setText("Withdraw");
            pnlWithdraw = new WithdrawPanel();
            mainPanel.add(pnlWithdraw);
        } else if (e.getSource() == sideBar.btnTransfer) {
            sideBar.removeIndicator();
            sideBar.btnTransfer.add(sideBar.lblSelectIndicator);
            sideBar.SelectedButton = "btnTransfer";
            sideBar.clearSelected();
            mainPanel.removeAll();
            topBar.lblTopBar.setText("Transfer");
            pnlTransfer = new TransferPanel();
            mainPanel.add(pnlTransfer);
        } else if (e.getSource() == sideBar.btnTransactions) {
            sideBar.removeIndicator();
            sideBar.btnTransactions.add(sideBar.lblSelectIndicator);
            sideBar.SelectedButton = "btnTransactions";
            sideBar.clearSelected();
            mainPanel.removeAll();
            topBar.lblTopBar.setText("Transaction History");
            pnlTransactions = new TransactionsPanel();
            mainPanel.add(pnlTransactions);
        } else if (e.getSource() == sideBar.btnAutoPayments) {
            sideBar.removeIndicator();
            sideBar.btnAutoPayments.add(sideBar.lblSelectIndicator);
            sideBar.SelectedButton = "btnAutoPayments";
            sideBar.clearSelected();
            mainPanel.removeAll();
            topBar.lblTopBar.setText("Auto Payments");
            pnlAutoPay = new AutoPaymentPanel();
            mainPanel.add(pnlAutoPay);
        } else if (e.getSource() == sideBar.btnSettings) {
            sideBar.removeIndicator();
            sideBar.btnSettings.add(sideBar.lblSelectIndicator);
            sideBar.SelectedButton = "btnSettings";
            sideBar.clearSelected();
            mainPanel.removeAll();
            topBar.lblTopBar.setText("Settings");
            pnlSettings = new SettingsPanel();
            mainPanel.add(pnlSettings);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
