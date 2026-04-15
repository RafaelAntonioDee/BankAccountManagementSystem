/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import DashboardUIDefault.MainDashboard;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class ChangeBirthday extends JFrame implements ActionListener {

    private JLabel lblMonth, lblDay, lblYear;
    private JComboBox cmbMonth, cmbDay, cmbYear;
    private JButton btnConfirm, btnCancel;

    public ChangeBirthday () {
        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Birthday Change");
        setSize(375, 370);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 243, 243));

        // NEW FIRST NAME
        lblMonth = new JLabel("Month");
        lblMonth.setBounds(38, 25, 40, 10);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(new Color(243, 243, 243));
        lblMonth.setFont(new Font("Arial", Font.PLAIN, 10));
        lblMonth.setHorizontalAlignment(JLabel.CENTER);
        add(lblMonth);

        cmbMonth = new JComboBox();
        cmbMonth.setBounds(32, 30, 295, 35);
        cmbMonth.setOpaque(false);
        cmbMonth.setFocusable(false);
        cmbMonth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(cmbMonth);

        // NEW LAST NAME
        lblDay = new JLabel("Day");
        lblDay.setBounds(38, 85, 25, 10);
        lblDay.setOpaque(true);
        lblDay.setBackground(new Color(243, 243, 243));
        lblDay.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDay.setHorizontalAlignment(JLabel.CENTER);
        add(lblDay);

        cmbDay = new JComboBox();
        cmbDay.setBounds(32, 90, 295, 35);
        cmbDay.setOpaque(false);
        cmbDay.setFocusable(false);
        cmbDay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(cmbDay);
        
        lblYear = new JLabel("Year");
        lblYear.setBounds(38, 145, 30, 10);
        lblYear.setOpaque(true);
        lblYear.setBackground(new Color(243, 243, 243));
        lblYear.setFont(new Font("Arial", Font.PLAIN, 10));
        lblYear.setHorizontalAlignment(JLabel.CENTER);
        add(lblYear);

        cmbYear = new JComboBox();
        cmbYear.setBounds(32, 150, 295, 35);
        cmbYear.setOpaque(false);
        cmbYear.setFocusable(false);
        cmbYear.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(cmbYear);

        // BUTTONS
        
        btnConfirm = new JButton("Confirm");
        btnConfirm.setHorizontalAlignment(JButton.CENTER);
        btnConfirm.setBounds(32, 210, 295, 35);
        btnConfirm.setBackground(new Color(82, 124, 174));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(32, 270, 295, 35);
        btnCancel.setBackground(new Color(243, 243, 243));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == btnConfirm)    {
            dispose();
            // Change of Name Function
        }
        
        else if (e.getSource() == btnCancel)    {
            dispose();
        }
    }

}
