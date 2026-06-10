/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.AccountFunctions;
import AppService.SettingsFunctions;
import DashboardUIDefault.Colors;
import DashboardUIDefault.MainDashboard;
import static FeaturesPanelsUI.DashboardPanel.theme;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Objects.Account;
import Objects.AccountPersonalInformation;

/**
 *
 * @author rafra
 */
public class ChangePhoneNumber extends JDialog implements ActionListener {

    private JLabel lblLogin, lblTitle, lblNewPhone, lblLogo, lblLine, lblOr;
    private JTextField txtNewPhone;
    private JButton btnConfirm, btnCancel;
    private Account currentuser;
    private AccountPersonalInformation currentuserInfo;
    public static Colors theme = Colors.LIGHT();

    public ChangePhoneNumber(Account user, AccountPersonalInformation userInfo) {
        this.currentuser = AppService.AccountFunctions.getUser(user.getEmail());
        this.currentuserInfo = AppService.AccountFunctions.getUserInfo(user.getEmail());

        if (currentuser.getSystemTheme().equals("Light") || currentuser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }

        // LOGO
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Phone Number Change");
        setSize(375, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(theme.BACKGROUND);

        lblNewPhone = new JLabel("New Phone Number");
        lblNewPhone.setBounds(38, 25, 100, 10);
        lblNewPhone.setOpaque(true);
        lblNewPhone.setBackground(theme.BACKGROUND);
        lblNewPhone.setForeground(theme.TEXT_BLACK);
        lblNewPhone.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewPhone.setHorizontalAlignment(JLabel.CENTER);
        add(lblNewPhone);

        txtNewPhone = new JTextField();
        txtNewPhone.setBounds(32, 30, 295, 35);
        txtNewPhone.setOpaque(false);
        txtNewPhone.setBackground(theme.PANELS_BACKGROUND);
        txtNewPhone.setForeground(theme.TEXT_BLACK);
        txtNewPhone.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtNewPhone);

        // BUTTONS
        btnConfirm = new JButton("Confirm");
        btnConfirm.setHorizontalAlignment(JButton.CENTER);
        btnConfirm.setBounds(32, 90, 295, 35);
        btnConfirm.setBackground(theme.PRIMARY_BLUE);
        btnConfirm.setForeground(theme.TEXT_WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(32, 150, 295, 35);
        btnCancel.setBackground(theme.CancelButton);
        btnCancel.setForeground(theme.TEXT_WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnConfirm) {
            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            String newPhoneInput = txtNewPhone.getText();
            String currentPhone = SettingsPanel.lblPhoneField.getText();
            
            // PHONE NUMBER VALIDATION
            if (newPhoneInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!AccountFunctions.validatePhoneNumber(newPhoneInput)) {
                JOptionPane.showMessageDialog(this, "Invalid Phone Number!", "Try Again", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (currentPhone.equals(newPhoneInput)) {
                JOptionPane.showMessageDialog(this, "New phone number cannot be the same as the old phone number!", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (choice == 0) {
                SettingsFunctions.changePhone(currentuser.getEmail(), newPhoneInput);
                SettingsPanel.lblPhoneField.setText(newPhoneInput);
                dispose();
                JOptionPane.showMessageDialog(this, "Saved Succesfully!", "Phone Change", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        else if (e.getSource() == btnCancel) {
            dispose();
        }
    }
}
