/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class TopPanel extends JPanel {

    public static JLabel lblTopBar;

    TopPanel() {
        setBounds(175, 0, 837, 60);
        setBackground(new Color(240, 240, 240));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        lblTopBar = new JLabel("Welcome back, Rafael!");
        lblTopBar.setFont(new Font("Arial", Font.PLAIN, 25));
        lblTopBar.setBounds(20, 5, 500, 50);
        add(lblTopBar);
    }
}
