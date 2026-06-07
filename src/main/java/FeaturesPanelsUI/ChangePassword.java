/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.SettingsFunctions;
import DashboardUIDefault.MainDashboard;
import LoginUI.LoginPage;
import Objects.Account;
import Objects.AccountPersonalInformation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class ChangePassword extends JFrame implements ActionListener {

    private JLabel lblLogin, lblTitle, lblCurrentPass, lblNewPass, lblConfirmPass, lblLogo, lblLine, lblOr;
    private JPasswordField txtCurrent, txtFirst, txtNewPass, txtConfirmPass;
    private JButton btnConfirm, btnCancel;
    String updatedPassword;
    private Account user;
    private AccountPersonalInformation userInfo;

    public ChangePassword(Account user, AccountPersonalInformation userInfo) {
        this.user = user;
        this.userInfo = userInfo;

        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Password Change");
        setSize(375, 370);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 243, 243));

        // NEW FIRST NAME
        lblCurrentPass = new JLabel("Current Password");
        lblCurrentPass.setBounds(38, 25, 90, 10);
        lblCurrentPass.setOpaque(true);
        lblCurrentPass.setBackground(new Color(243, 243, 243));
        lblCurrentPass.setFont(new Font("Arial", Font.PLAIN, 10));
        lblCurrentPass.setHorizontalAlignment(JLabel.CENTER);
        add(lblCurrentPass);

        txtCurrent = new JPasswordField();
        txtCurrent.setBounds(32, 30, 295, 35);
        txtCurrent.setOpaque(false);
        txtCurrent.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtCurrent);

        // NEW LAST NAME
        lblNewPass = new JLabel("New Password");
        lblNewPass.setBounds(38, 85, 75, 10);
        lblNewPass.setOpaque(true);
        lblNewPass.setBackground(new Color(243, 243, 243));
        lblNewPass.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewPass.setHorizontalAlignment(JLabel.CENTER);
        add(lblNewPass);

        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(32, 90, 295, 35);
        txtNewPass.setOpaque(false);
        txtNewPass.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtNewPass);

        lblConfirmPass = new JLabel("Confirm New Password");
        lblConfirmPass.setBounds(38, 145, 115, 10);
        lblConfirmPass.setOpaque(true);
        lblConfirmPass.setBackground(new Color(243, 243, 243));
        lblConfirmPass.setFont(new Font("Arial", Font.PLAIN, 10));
        lblConfirmPass.setHorizontalAlignment(JLabel.CENTER);
        add(lblConfirmPass);

        txtConfirmPass = new JPasswordField();
        txtConfirmPass.setBounds(32, 150, 295, 35);
        txtConfirmPass.setOpaque(false);
        txtConfirmPass.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtConfirmPass);

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

        if (e.getSource() == btnConfirm) {
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            String currentPass = txtCurrent.getText();
            String newPassInput = txtNewPass.getText();
            String confirmPassInput = txtConfirmPass.getText();

            if (currentPass.isEmpty() || newPassInput.isEmpty() || confirmPassInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
            } else if (confirmPassInput.length() < 8) {
                JOptionPane.showMessageDialog(this, "Password must be 8 characters long!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!currentPass.equals(user.getPassword())) {
                JOptionPane.showMessageDialog(this, "Current password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (currentPass.equals(newPassInput)) {
                JOptionPane.showMessageDialog(this, "New password can't be the same as the old!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!newPassInput.equals(confirmPassInput)) {
                JOptionPane.showMessageDialog(this, "Password do not match", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION);

                if (choice == 0) {
                    updatedPassword = SettingsFunctions.changePassword(user.getEmail(), confirmPassInput);
                    String hiddenPass = "*".repeat(confirmPassInput.length());
                    SettingsPanel.lblPasswordField.setText(hiddenPass);
                    dispose();
                    JOptionPane.showMessageDialog(this, "Password Changed. You will be logged out!", "Password Change", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == btnCancel) {
            dispose();
        }
    }
}
