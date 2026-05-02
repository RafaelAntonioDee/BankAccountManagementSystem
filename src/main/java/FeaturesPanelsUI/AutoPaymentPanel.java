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
public class AutoPaymentPanel extends JPanel implements ActionListener {

    private JLabel lblAutoPayment, lblRecipient, lblAmount, lblFrequency, lblDate, lblEnable, lblBirthday, lblMonth, lblDay, lblYear, lblReceipt;
    private JButton btnEnableAuto, btnCancel;
    private JPanel pnlAutoPayment, pnlScheduledPayment, pnlAutoPayListContent;
    private JScrollPane pnlAutoPayList;
    private JTextField txtRecipient, txtAmount;
    private int startYear = 1970, endYear = 2050, ScheduledCount = 0, y = 15;
    private JComboBox<String> cmbFrequency, cmbDay, cmbMonth, cmbYear;
    private String[] frequency = {"every second", "Monthly", "Quarterly", "Semi-Anually", "Anually"},
            months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},
            days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"},
            years = {"1970",  "2026"};

    public AutoPaymentPanel() {

        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        //---------------------------------AUTOPAYMENT
        lblAutoPayment = new JLabel("Auto Payment Setup");
        lblAutoPayment.setForeground(Color.GRAY);
        lblAutoPayment.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAutoPayment.setBounds(25, 15, 250, 35);
        add(lblAutoPayment);

        pnlAutoPayment = new JPanel();
        pnlAutoPayment.setBounds(25, 60, 375, 475);
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
        cmbFrequency.setFocusable(false);
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

        cmbMonth = new JComboBox<String>(months);
        cmbMonth.setBounds(25, 280, 130, 35);
        cmbMonth.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbMonth.setOpaque(false);
        cmbMonth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbMonth);

        lblDay = new JLabel("Day");
        lblDay.setBounds(175, 275, 25, 10);
        lblDay.setOpaque(true);
        lblDay.setBackground(new Color(243, 243, 243));
        lblDay.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDay.setHorizontalAlignment(JLabel.CENTER);
        pnlAutoPayment.add(lblDay);

        cmbDay = new JComboBox<String>(days);
        cmbDay.setBounds(170, 280, 80, 35);
        cmbDay.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbDay.setOpaque(false);
        cmbDay.setFocusable(false);
        cmbDay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbDay);

        lblYear = new JLabel("Year");
        lblYear.setBounds(268, 275, 30, 10);
        lblYear.setOpaque(true);
        lblYear.setBackground(new Color(243, 243, 243));
        lblYear.setFont(new Font("Arial", Font.PLAIN, 10));
        lblYear.setHorizontalAlignment(JLabel.CENTER);
        pnlAutoPayment.add(lblYear);

        cmbYear = new JComboBox<String>(years);
        cmbYear.setBounds(265, 280, 85, 35);
        cmbYear.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbYear.setOpaque(false);
        cmbYear.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbYear);

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
        lblReceipt = new JLabel("Enabled Auto Payments");
        lblReceipt.setForeground(Color.GRAY);
        lblReceipt.setFont(new Font("Arial", Font.PLAIN, 18));
        lblReceipt.setBounds(425, 15, 325, 35);
        add(lblReceipt);

        pnlAutoPayList = new JScrollPane();
        pnlAutoPayList.setBounds(425, 60, 395, 475);
        pnlAutoPayList.setOpaque(true);
        pnlAutoPayList.setBorder(null);
        add(pnlAutoPayList);

        pnlAutoPayList.getViewport().setOpaque(false);

        pnlAutoPayListContent = new JPanel();
        pnlAutoPayListContent.setLayout(null);
        pnlAutoPayListContent.setOpaque(false);
        
        pnlAutoPayList.setViewportView(pnlAutoPayListContent);
        
        

//        lblTemp = new JLabel("DI PA TAPOS TO :))))");
//        lblTemp.setBounds(25,25,787,30);
//        add(lblTemp);
        
        //b
        
//practice push
    }
    

    // RECEIPT
    
    public void createScheduledPayment(String RecipientName, int Amount, String Frequency, String DueDate) {
        ScheduledCount++;

        pnlScheduledPayment = new JPanel();
        pnlScheduledPayment.setName(RecipientName);
        pnlScheduledPayment.setBounds(15, y, 357, 110);
        pnlScheduledPayment.setBackground(new Color(243, 243, 243));
        pnlScheduledPayment.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlScheduledPayment.setLayout(null);
        pnlAutoPayListContent.add(pnlScheduledPayment);

        lblRecipient = new JLabel();
        lblRecipient.setFont(new Font("Arial", Font.PLAIN, 16));
        lblRecipient.setBounds(10, 5, 337, 25);
        lblRecipient.setText(RecipientName);
        pnlScheduledPayment.add(lblRecipient);

        lblAmount = new JLabel();
        lblAmount.setBounds(10, 30, 337, 25);
        lblAmount.setText("Amount: " + Amount);
        pnlScheduledPayment.add(lblAmount);

        lblFrequency = new JLabel();
        lblFrequency.setBounds(10, 55, 337, 25);
        lblFrequency.setText("Frequency: " + Frequency);
        pnlScheduledPayment.add(lblFrequency);

        lblDate = new JLabel();
        lblDate.setBounds(10, 80, 337, 25);
        lblDate.setText("Due Date: " + DueDate);
        pnlScheduledPayment.add(lblDate);

        pnlAutoPayListContent.setPreferredSize(new Dimension(357, y));
        pnlAutoPayListContent.revalidate();
        pnlAutoPayListContent.repaint();

        y += 115;
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnEnableAuto) {
            createScheduledPayment(txtRecipient.getText(), Integer.parseInt(txtAmount.getText()), cmbFrequency.getSelectedItem().toString(), cmbMonth.getSelectedItem() + " " + cmbDay.getSelectedItem() + " " + cmbYear.getSelectedItem());
        }

    }
}
