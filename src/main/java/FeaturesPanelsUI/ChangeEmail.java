/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.AccountFunctions;
import AppService.SettingsFunctions;
import DashboardUIDefault.MainDashboard;
import Objects.Account;
import Objects.AccountPersonalInformation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class ChangeEmail extends JFrame implements ActionListener {

    private JLabel lblEmail, lblLogo;
    private JTextField txtEmail;
    private JButton btnConfirm, btnCancel;
    private Account user;
    private String updatedUser;
    private AccountPersonalInformation userInfo;

    public ChangeEmail(Account user, AccountPersonalInformation userInfo) {
        this.user = user;
        this.userInfo = userInfo;
        
        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Email Change");
        setSize(375, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 243, 243));

        // NEW ADDRESS
        lblEmail = new JLabel("New Address");
        lblEmail.setBounds(38, 25, 65, 10);
        lblEmail.setOpaque(true);
        lblEmail.setBackground(new Color(243, 243, 243));
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 10));
        lblEmail.setHorizontalAlignment(JLabel.CENTER);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(32, 30, 295, 35);
        txtEmail.setOpaque(false);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtEmail);

        // BUTTONS
        btnConfirm = new JButton("Confirm");
        btnConfirm.setHorizontalAlignment(JButton.CENTER);
        btnConfirm.setBounds(32, 90, 295, 35);
        btnConfirm.setBackground(new Color(82, 124, 174));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(32, 150, 295, 35);
        btnCancel.setBackground(new Color(243, 243, 243));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnConfirm) {
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            String currentEmail = SettingsPanel.lblEmailField.getText();
            String newEmailInput = txtEmail.getText();
            
            if (!AccountFunctions.validateEmail(newEmailInput)) {
                JOptionPane.showMessageDialog(this, "Invalid Email Address!");
                return;
            }
            
            if (newEmailInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
            } else if (currentEmail.equals(newEmailInput)) {
                JOptionPane.showMessageDialog(this, "New email cannot be the same as the old email!", "", JOptionPane.ERROR_MESSAGE);
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION);

                if (choice == 0) {
                    updatedUser = SettingsFunctions.changeEmail(user.getEmail(), newEmailInput);
                    SettingsPanel.lblEmailField.setText(newEmailInput);
                    dispose();
                    JOptionPane.showMessageDialog(this, "Saved Succesfully!", "Name Change", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == btnCancel) {
            dispose();
        }
    }
}
