/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import javax.swing.ImageIcon;

/**
 *
 * @author rafra
 */
public class Icons {

    public static final ImageIcon BankIcon = new ImageIcon(Icons.class.getResource("/images/BankLogo.png"));
    public static final ImageIcon BankIconWhite = new ImageIcon(Icons.class.getResource("/images/BankLogoWhite.png"));
    public ImageIcon DashboardIcon;
    public ImageIcon DepositIcon;
    public ImageIcon WithdrawIcon;
    public ImageIcon TransferIcon;
    public ImageIcon TransactionsIcon;
    public ImageIcon AutoPaymentIcon;
    public ImageIcon SettingsIcon;
    public ImageIcon LogoutIcon;
    public ImageIcon SearchIcon;

    public static Icons LIGHT() {
        Icons i = new Icons();

        i.DashboardIcon = new ImageIcon(Icons.class.getResource("/images/DashboardLogo.png"));
        i.DepositIcon = new ImageIcon(Icons.class.getResource("/images/DepositLogo.png"));
        i.WithdrawIcon = new ImageIcon(Icons.class.getResource("/images/WithdrawLogo.png"));
        i.TransferIcon = new ImageIcon(Icons.class.getResource("/images/TransferLogo.png"));
        i.TransactionsIcon = new ImageIcon(Icons.class.getResource("/images/TransactionsLogo.png"));
        i.AutoPaymentIcon = new ImageIcon(Icons.class.getResource("/images/AutoPaymentLogo.png"));
        i.SettingsIcon = new ImageIcon(Icons.class.getResource("/images/SettingsLogo.png"));
        i.LogoutIcon = new ImageIcon(Icons.class.getResource("/images/LogoutLogo.png"));
        i.SearchIcon = new ImageIcon(Icons.class.getResource("/images/search.png"));

        return i;
    }

    public static Icons DARK() {
        Icons i = new Icons();

        i.DashboardIcon = new ImageIcon(Icons.class.getResource("/images/DashboardLogoWhite.png"));
        i.DepositIcon = new ImageIcon(Icons.class.getResource("/images/DepositLogoWhite.png"));
        i.WithdrawIcon = new ImageIcon(Icons.class.getResource("/images/WithdrawLogoWhite.png"));
        i.TransferIcon = new ImageIcon(Icons.class.getResource("/images/TransferLogoWhite.png"));
        i.TransactionsIcon = new ImageIcon(Icons.class.getResource("/images/TransactionsLogoWhite.png"));
        i.AutoPaymentIcon = new ImageIcon(Icons.class.getResource("/images/AutoPaymentLogoWhite.png"));
        i.SettingsIcon = new ImageIcon(Icons.class.getResource("/images/SettingsLogoWhite.png"));
        i.LogoutIcon = new ImageIcon(Icons.class.getResource("/images/LogoutLogoWhite.png"));
        i.SearchIcon = new ImageIcon(Icons.class.getResource("/images/searchWhite.png"));
        
        return i;
    }
}
