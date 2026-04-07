/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class BottomPanel extends JPanel{
    JLabel lblDateToday, lblSystemName;
    
    BottomPanel(){
        setBounds(0, 620, 1012, 45);
        setBackground(new Color(240, 240, 240));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        LocalDate today = LocalDate.now();
        String DateToday = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
        String DayToday = today.format(DateTimeFormatter.ofPattern("EEEE"));

        lblDateToday = new JLabel(DayToday+": "+DateToday);
        lblDateToday.setBounds(20, 5, 975, 35);
        add(lblDateToday);
        
        lblSystemName = new JLabel("Bank Account Management System");
        lblSystemName.setBounds(20, 5, 975, 35);
        lblSystemName.setHorizontalAlignment(JLabel.RIGHT);
        add(lblSystemName);
    }
}
