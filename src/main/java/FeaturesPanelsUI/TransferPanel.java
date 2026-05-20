/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import Objects.Account;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class TransferPanel extends JPanel implements ActionListener{
    private JLabel lblBalance, lblBalanceAmount, lblAmount, lblEmail, lblReceipt, lblName;
    private JButton btnTransfer, btnCancel;
    private JTextField txtAmount, txtEmail;
    private JPanel pnlProcess;
    private JTextArea txtReceipt;

    public TransferPanel(Account user) {
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        lblBalance = new JLabel("Balance");
        lblBalance.setForeground(Color.GRAY);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBalance.setBounds(25, 25, 250, 35);
        add(lblBalance);

        lblBalanceAmount = new JLabel("    ₱0.00");
        lblBalanceAmount.setForeground(Color.WHITE);
        lblBalanceAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        lblBalanceAmount.setBounds(25, 65, 250, 50);
        lblBalanceAmount.setOpaque(true);
        lblBalanceAmount.setBackground(new Color(82, 124, 174));
        add(lblBalanceAmount);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(25, 140, 375, 395);
        pnlProcess.setBackground(new Color(243, 243, 243));
        pnlProcess.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblEmail = new JLabel("Transfer to (email)");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEmail.setBounds(25, 25, 325, 35);
        pnlProcess.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(25, 70, 325, 35);
        txtEmail.setOpaque(false);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlProcess.add(txtEmail);

        lblName = new JLabel("Name: _________________________");
        lblName.setFont(new Font("Arial", Font.PLAIN, 18));
        lblName.setBounds(25, 115, 325, 35);
        pnlProcess.add(lblName);
        
        lblAmount = new JLabel("Amount");
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAmount.setBounds(25, 160, 325, 35);
        pnlProcess.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(25, 205, 325, 35);
        txtAmount.setOpaque(false);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlProcess.add(txtAmount);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(265, 325, 85, 35);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        pnlProcess.add(btnCancel);

        btnTransfer = new JButton("Transfer");
        btnTransfer.setHorizontalAlignment(JButton.CENTER);
        btnTransfer.setBounds(160, 325, 85, 35);
        btnTransfer.setBackground(new Color(82, 124, 174));
        btnTransfer.setForeground(Color.WHITE);
        btnTransfer.setFocusPainted(false);
        btnTransfer.addActionListener(this);
        pnlProcess.add(btnTransfer);

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
