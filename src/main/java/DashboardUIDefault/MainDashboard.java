/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import FeaturesPanelsUI.*;
import AppService.AccountFunctions;
import LoginUI.LoginPage;
import Objects.Account;
import Objects.AccountPersonalInformation;
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
    public SidePanel sideBar;
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
    private Account currentUser;
    private AccountPersonalInformation currentuserinfo;
    
    /**
     *
     * @param user
     */
    public MainDashboard(Account user, AccountPersonalInformation userinfo) {
        this.currentUser = user; 
        this.currentuserinfo = userinfo; 
        
        setIconImage(Icons.BankIcon.getImage());
        setTitle("Bank Account Management System");
        setSize(1025, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //------------------------------- Panels Setup -------------------------------
        //SIDE BAR
        sideBar = new SidePanel();
        sideBar.setName("sideBar");
        add(sideBar);
        sideBar.setUserDetails(currentuserinfo);

        sideBar.btnLogout.addActionListener(this);
        sideBar.btnDashboard.addActionListener(this);
        sideBar.btnDeposit.addActionListener(this);
        sideBar.btnWithdraw.addActionListener(this);
        sideBar.btnTransfer.addActionListener(this);
        sideBar.btnTransactions.addActionListener(this);
        sideBar.btnSettings.addActionListener(this);
        sideBar.btnAutoPayments.addActionListener(this);

        //TOP BAR
        topBar = new TopPanel(AccountFunctions.getFullName(userinfo));
        add(topBar);

        //BOTTOM BAR
        botBar = new BottomPanel();
        add(botBar);

        //MAIN PANEL
        mainPanel = new MainPanel();
        add(mainPanel);

        pnlDashboard = new DashboardPanel(this, user);
        mainPanel.add(pnlDashboard);
    }

    public void switchPanel(JButton button, String name, String title, JPanel panel) {
        sideBar.removeIndicator();
        button.add(sideBar.lblSelectIndicator);
        sideBar.SelectedButton = name;
        sideBar.clearSelected();

        mainPanel.removeAll();
        topBar.lblTopBar.setText(title);

        mainPanel.add(panel);
        mainPanel.revalidate();
        mainPanel.repaint();
        button.setContentAreaFilled(true);
        sideBar.revalidate();
        sideBar.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sideBar.btnLogout) {
            dispose();
            LoginPage page = new LoginPage();
            page.setVisible(true);
        } else if (e.getSource() == sideBar.btnDashboard) {
            switchPanel(sideBar.btnDashboard, "btnDashboard", "Dashboard", new DashboardPanel(this, currentUser));

        } else if (e.getSource() == sideBar.btnDeposit) {
            switchPanel(sideBar.btnDeposit, "btnDeposit", "Deposit", new DepositPanel(currentUser));

        } else if (e.getSource() == sideBar.btnWithdraw) {
            switchPanel(sideBar.btnWithdraw, "btnWithdraw", "Withdraw", new WithdrawPanel(currentUser));

        } else if (e.getSource() == sideBar.btnTransfer) {
            switchPanel(sideBar.btnTransfer, "btnTransfer", "Transfer", new TransferPanel(currentUser));

        } else if (e.getSource() == sideBar.btnTransactions) {
            switchPanel(sideBar.btnTransactions, "btnTransactions", "Transaction History", new TransactionsPanel());

        } else if (e.getSource() == sideBar.btnAutoPayments) {
            switchPanel(sideBar.btnAutoPayments, "btnAutoPayments", "Auto Payments", new AutoPaymentPanel());

        } else if (e.getSource() == sideBar.btnSettings) {
            if (pnlSettings == null) {
        pnlSettings = new SettingsPanel();
        }
            pnlSettings.setUserSettings(currentUser, currentuserinfo);
            switchPanel(sideBar.btnSettings, "Settings", "Settings", pnlSettings);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
