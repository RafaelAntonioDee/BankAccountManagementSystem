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
    
    private JLabel lblAutoPayment, lblRecipient, lblAmount, lblFrequency, lblDate, lblEnable, lblBirthday, lblMonth, lblDay, lblYear, lblReceipt;
    private JButton btnEnableAuto, btnCancel;
    private JPanel pnlAutoPayment;
    private JTextField txtRecipient, txtAmount;
    private JTextArea txtReceipt;
    private JComboBox<String> cmbFrequency, cmbBirthDay, cmbBirthMonth, cmbBirthYear;
    private String[] frequency = {"Monthly", "Quarterly", "Semi-Anually", "Anually"};     
            
    public AutoPaymentPanel() {
        
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);
        
        //---------------------------------AUTOPAYMENT
        
        lblAutoPayment = new JLabel("Auto Payment Setup");
        lblAutoPayment.setForeground(Color.GRAY);
        lblAutoPayment.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAutoPayment.setBounds(25, 25, 250, 35);
        add(lblAutoPayment);
        
        pnlAutoPayment = new JPanel();
        pnlAutoPayment.setBounds(25, 70, 375, 465);
        pnlAutoPayment.setBackground(new Color(243, 243, 243));
        pnlAutoPayment.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlAutoPayment.setLayout(null);
        add(pnlAutoPayment);
        
        lblRecipient = new JLabel("Payee: ");
        lblRecipient.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRecipient.setBounds(25, 25, 325, 35);
        pnlAutoPayment.add(lblRecipient);
        
        txtRecipient = new JTextField();
        txtRecipient.setBounds(25, 60, 325, 35);
        txtRecipient.setOpaque(false);
        txtRecipient.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(txtRecipient);
        
        lblAmount = new JLabel("Amount: ");
        lblAmount.setBounds(25, 95, 325, 35);
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        pnlAutoPayment.add(lblAmount);
        
        txtAmount = new JTextField();
        txtAmount.setBounds(25, 130, 325, 35);
        txtAmount.setOpaque(false);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(txtAmount);
        
        lblFrequency = new JLabel("Frequency: ");
        lblFrequency.setBounds(25, 165, 325, 35);
        lblFrequency.setFont(new Font("Arial", Font.PLAIN, 18));
        pnlAutoPayment.add(lblFrequency);
        
        cmbFrequency = new JComboBox<String>(frequency);
        cmbFrequency.setBounds(25, 200, 325, 35);
        cmbFrequency.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbFrequency.setFont(new Font("Arial", Font.PLAIN, 18));
        cmbFrequency.setOpaque(false);
        cmbFrequency.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbFrequency);
        
        lblBirthday = new JLabel("Starting Date: ");
        lblBirthday.setBounds(25, 245, 150, 20);
        lblBirthday.setFont(new Font("Arial", Font.PLAIN, 18));
        pnlAutoPayment.add(lblBirthday);
        
        lblMonth = new JLabel("Month");
        lblMonth.setBounds(30, 275, 35, 10);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(new Color(243, 243, 243));
        lblMonth.setFont(new Font("Arial", Font.PLAIN, 10));
        lblMonth.setHorizontalAlignment(JLabel.CENTER);
        pnlAutoPayment.add(lblMonth);

        cmbBirthMonth = new JComboBox();
        cmbBirthMonth.setBounds(25, 280, 130, 35);
        cmbBirthMonth.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbBirthMonth.setOpaque(false);
        cmbBirthMonth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbBirthMonth);
        
        lblDay = new JLabel("Day");
        lblDay.setBounds(175, 275, 25, 10);
        lblDay.setOpaque(true);
        lblDay.setBackground(new Color(243, 243, 243));
        lblDay.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDay.setHorizontalAlignment(JLabel.CENTER);
        pnlAutoPayment.add(lblDay);

        cmbBirthDay = new JComboBox();
        cmbBirthDay.setBounds(170, 280, 80, 35);
        cmbBirthDay.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbBirthDay.setOpaque(false);
        cmbBirthDay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbBirthDay);
        
        lblYear = new JLabel("Year");
        lblYear.setBounds(260, 275, 30, 10);
        lblYear.setOpaque(true);
        lblYear.setBackground(new Color(243, 243, 243));
        lblYear.setFont(new Font("Arial", Font.PLAIN, 10));
        lblYear.setHorizontalAlignment(JLabel.CENTER);
        pnlAutoPayment.add(lblYear);

        cmbBirthYear = new JComboBox();
        cmbBirthYear.setBounds(265, 280, 85, 35);
        cmbBirthYear.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbBirthYear.setOpaque(false);
        cmbBirthYear.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbBirthYear);
        
        btnEnableAuto = new JButton("Set Up Payment");
        btnEnableAuto.setHorizontalAlignment(JButton.CENTER);
        btnEnableAuto.setBounds(120, 395, 125, 35);
        btnEnableAuto.setBackground(new Color(82, 124, 174));
        btnEnableAuto.setForeground(Color.WHITE);
        btnEnableAuto.setFocusPainted(false);
        btnEnableAuto.addActionListener(this);
        pnlAutoPayment.add(btnEnableAuto);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(265, 395, 85, 35);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        pnlAutoPayment.add(btnCancel);
        
        //---------------------------------RECEIPT
        
        lblReceipt = new JLabel("Receipt");
        lblReceipt.setForeground(Color.GRAY);
        lblReceipt.setFont(new Font("Arial", Font.PLAIN, 18));
        lblReceipt.setBounds(425, 25, 325, 35);
        add(lblReceipt);

        txtReceipt = new JTextArea();
        txtReceipt.setBounds(425, 65, 387, 470);
        txtReceipt.setBorder(new LineBorder(Color.LIGHT_GRAY));
        txtReceipt.setLineWrap(true);
        txtReceipt.setWrapStyleWord(true);
        txtReceipt.setEditable(false);
        add(txtReceipt);
        
        
        
        
        
        
     
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
