/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class AutoPaymentPanel extends JPanel implements ActionListener{
    private JLabel lblTemp;

    public AutoPaymentPanel() {
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);
        
        lblTemp = new JLabel("DI PA TAPOS TO :))))");
        lblTemp.setBounds(25,25,787,30);
        add(lblTemp);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
