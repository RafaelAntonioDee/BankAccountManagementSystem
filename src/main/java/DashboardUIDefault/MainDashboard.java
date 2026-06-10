/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import FeaturesPanelsUI.*;
import AppService.AccountFunctions;
import static FeaturesPanelsUI.DashboardPanel.theme;
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
    public static Account currentUser;
    public static AccountPersonalInformation currentUserInfo;
    public static Colors theme = Colors.LIGHT();
    public static Icons icons = Icons.LIGHT();

    public MainDashboard(Account user, AccountPersonalInformation userinfo) {
        this.currentUser = AppService.AccountFunctions.getUser(user.getEmail());
        this.currentUserInfo = AppService.AccountFunctions.getUserInfo(user.getEmail());

        if (user.getSystemTheme().equals("Light") || user.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
            icons = Icons.LIGHT();
        } else {
            theme = Colors.DARK();
            icons = Icons.DARK();
        }

        setIconImage(Icons.BankIcon.getImage());
        setTitle("Bank Account Management System");
        setSize(1025, 700);
        setLayout(null);
        setLocationRelativeTo(null);
        setBackground(theme.BACKGROUND);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //------------------------------- Panels Setup -------------------------------
        //SIDE BAR
        sideBar = new SidePanel(user);
        sideBar.setName("sideBar");
        add(sideBar);
        sideBar.setUserDetails(currentUserInfo);

        sideBar.btnLogout.addActionListener(this);
        sideBar.btnDashboard.addActionListener(this);
        sideBar.btnDeposit.addActionListener(this);
        sideBar.btnWithdraw.addActionListener(this);
        sideBar.btnTransfer.addActionListener(this);
        sideBar.btnTransactions.addActionListener(this);
        sideBar.btnSettings.addActionListener(this);
        sideBar.btnAutoPayments.addActionListener(this);

        //TOP BAR
        topBar = new TopPanel(AccountFunctions.getFullName(userinfo), user);
        add(topBar);

        //BOTTOM BAR
        botBar = new BottomPanel(user);
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
            switchPanel(sideBar.btnTransactions, "btnTransactions", "Transaction History", new TransactionsPanel(currentUser));

        } else if (e.getSource() == sideBar.btnAutoPayments) {
            switchPanel(sideBar.btnAutoPayments, "btnAutoPayments", "Auto Payments", new AutoPaymentPanel(currentUser.getEmail(), currentUser));

        } else if (e.getSource() == sideBar.btnSettings) {
            switchPanel(sideBar.btnSettings, "Settings", "Settings", new SettingsPanel(currentUser, currentUserInfo));
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
