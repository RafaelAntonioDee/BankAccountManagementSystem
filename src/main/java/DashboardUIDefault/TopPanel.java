/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import AppService.AccountFunctions;
import static DashboardUIDefault.MainDashboard.theme;
import Objects.Account;

/**
 *
 * @author rafra
 */
public class TopPanel extends JPanel {

    public static JLabel lblTopBar;
    public static Colors theme = Colors.LIGHT();
    public static Account currentuser;

    TopPanel(String Name, Account user) {
        this.currentuser = AppService.AccountFunctions.getUser(user.getEmail());

        if (currentuser.getSystemTheme().equals("Light") || currentuser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }
        setBounds(175, 0, 837, 60);
        setBackground(theme.HeaderFooterColor);
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setLayout(null);

        lblTopBar = new JLabel("Welcome back, " + Name);
        lblTopBar.setFont(new Font("Arial", Font.PLAIN, 25));
        lblTopBar.setForeground(theme.TEXT_BLACK);
        lblTopBar.setBounds(20, 5, 500, 50);
        add(lblTopBar);
    }
}
