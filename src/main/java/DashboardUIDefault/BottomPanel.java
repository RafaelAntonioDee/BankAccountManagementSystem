/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import DashboardUIDefault.Colors;
import Objects.Account;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class BottomPanel extends JPanel {

    JLabel lblDateToday, lblSystemName;
    public static Colors theme = Colors.LIGHT();

    BottomPanel(Account user) {

        if (user.getSystemTheme().equals("Light") || user.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }

        setBounds(0, 620, 1012, 45);
        setBackground(new Color(240, 240, 240));
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setBackground(theme.HeaderFooterColor);
        setLayout(null);

        LocalDate today = LocalDate.now();
        String DateToday = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        String DayToday = today.format(DateTimeFormatter.ofPattern("EEEE"));

        lblDateToday = new JLabel(DayToday + ": " + DateToday);
        lblDateToday.setBounds(20, 5, 975, 35);
        lblDateToday.setForeground(theme.TEXT_BLACK);
        add(lblDateToday);

        lblSystemName = new JLabel("Bank Account Management System");
        lblSystemName.setBounds(20, 5, 975, 35);
        lblSystemName.setHorizontalAlignment(JLabel.RIGHT);
        lblSystemName.setForeground(theme.TEXT_BLACK);
        add(lblSystemName);
    }

    private void setBackground(Colors theme) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
