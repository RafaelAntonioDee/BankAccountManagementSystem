/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.AccountFunctions;
import Objects.Account;
import Objects.AccountPersonalInformation;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author rafra
 */
public class SettingsPanel extends JPanel implements ActionListener {

    static JLabel lblAccountInfo, lblPersonalInfo, lblName, lblEmail, lblAppPref, lblPassword, lblPhoneNumber, lblBirthday, lblAddress, lblNameField, lblBirthdayField, lblEmailField, lblPasswordField, lblPhoneField, lblAddressField, lblTemp;
    private JButton btnChangePhoneNumber, btnChangeName, btnChangeEmail, btnChangePassword, btnChangeAddress, btnChangeBirthday;
    private JPanel pnlPersonalInfo, pnlAccountInfo, pnlAppPreference;
    private JComboBox<String> cmbTheme, cmbGender;
    private String[] theme = {"System", "Dark", "Light"},
            gender = {"Male", "Female", "Croissant", "Prefer Not to Say"};

    public SettingsPanel() {
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        lblAccountInfo = new JLabel("Account Information");
        lblAccountInfo.setForeground(Color.GRAY);
        lblAccountInfo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAccountInfo.setBounds(25, 15, 250, 35);
        add(lblAccountInfo);

        pnlAccountInfo = new JPanel();
        pnlAccountInfo.setBounds(25, 60, 785, 160);
        pnlAccountInfo.setBackground(new Color(243, 243, 243));
        pnlAccountInfo.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlAccountInfo.setLayout(null);
        add(pnlAccountInfo);

        lblName = new JLabel("Name:");
        lblName.setFont(new Font("Calibri", Font.BOLD, 18));
        lblName.setBounds(25, 15, 54, 35);
        pnlAccountInfo.add(lblName);

        lblNameField = new JLabel("Kurt Paolo D. Redondo");
        lblNameField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblNameField.setBounds(84, 15, 325, 35);
        pnlAccountInfo.add(lblNameField);

        btnChangeName = new JButton("Edit");
        btnChangeName.setHorizontalAlignment(JButton.CENTER);
        btnChangeName.setBounds(700, 15, 60, 25);
        btnChangeName.setBackground(new Color(82, 124, 174));
        btnChangeName.setForeground(Color.WHITE);
        btnChangeName.setFocusPainted(false);
        btnChangeName.addActionListener(this);
        pnlAccountInfo.add(btnChangeName);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(25, 50, 51, 35);
        lblEmail.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlAccountInfo.add(lblEmail);

        lblEmailField = new JLabel("kurtpaolo67@gmail.com");
        lblEmailField.setBounds(81, 50, 325, 35);
        lblEmailField.setFont(new Font("Calibri", Font.PLAIN, 18));
        pnlAccountInfo.add(lblEmailField);

        btnChangeEmail = new JButton("Edit");
        btnChangeEmail.setHorizontalAlignment(JButton.CENTER);
        btnChangeEmail.setBounds(700, 50, 60, 25);
        btnChangeEmail.setBackground(new Color(82, 124, 174));
        btnChangeEmail.setForeground(Color.WHITE);
        btnChangeEmail.setFocusPainted(false);
        btnChangeEmail.addActionListener(this);
        pnlAccountInfo.add(btnChangeEmail);

        lblPhoneNumber = new JLabel("Phone Number:");
        lblPhoneNumber.setBounds(25, 85, 127, 35);
        lblPhoneNumber.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlAccountInfo.add(lblPhoneNumber);

        lblPhoneField = new JLabel("0967 123 4567");
        lblPhoneField.setBounds(157, 85, 325, 35);
        lblPhoneField.setFont(new Font("Calibri", Font.PLAIN, 18));
        pnlAccountInfo.add(lblPhoneField);

        btnChangePhoneNumber = new JButton("Edit");
        btnChangePhoneNumber.setHorizontalAlignment(JButton.CENTER);
        btnChangePhoneNumber.setBounds(700, 85, 60, 25);
        btnChangePhoneNumber.setBackground(new Color(82, 124, 174));
        btnChangePhoneNumber.setForeground(Color.WHITE);
        btnChangePhoneNumber.setFocusPainted(false);
        btnChangePhoneNumber.addActionListener(this);
        pnlAccountInfo.add(btnChangePhoneNumber);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(25, 120, 86, 35);
        lblPassword.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlAccountInfo.add(lblPassword);

        lblPasswordField = new JLabel("************");
        lblPasswordField.setBounds(116, 120, 325, 35);
        lblPasswordField.setFont(new Font("Calibri", Font.PLAIN, 18));
        pnlAccountInfo.add(lblPasswordField);

        btnChangePassword = new JButton("Edit");
        btnChangePassword.setHorizontalAlignment(JButton.CENTER);
        btnChangePassword.setBounds(700, 120, 60, 25);
        btnChangePassword.setBackground(new Color(82, 124, 174));
        btnChangePassword.setForeground(Color.WHITE);
        btnChangePassword.setFocusPainted(false);
        btnChangePassword.addActionListener(this);
        pnlAccountInfo.add(btnChangePassword);

        // PERSONAL INFORMATION
        lblAppPref = new JLabel("Personal Information");
        lblAppPref.setForeground(Color.GRAY);
        lblAppPref.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAppPref.setBounds(25, 230, 280, 35);
        add(lblAppPref);

        pnlPersonalInfo = new JPanel();
        pnlPersonalInfo.setBounds(25, 275, 785, 90);
        pnlPersonalInfo.setBackground(new Color(243, 243, 243));
        pnlPersonalInfo.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlPersonalInfo.setLayout(null);
        add(pnlPersonalInfo);

        lblBirthday = new JLabel("Birthday:");
        lblBirthday.setBounds(25, 15, 73, 35);
        lblBirthday.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlPersonalInfo.add(lblBirthday);

        lblBirthdayField = new JLabel("December 14, 2005");
        lblBirthdayField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblBirthdayField.setBounds(103, 15, 325, 35);
        pnlPersonalInfo.add(lblBirthdayField);

        btnChangeBirthday = new JButton("Edit");
        btnChangeBirthday.setHorizontalAlignment(JButton.CENTER);
        btnChangeBirthday.setBounds(700, 15, 60, 25);
        btnChangeBirthday.setBackground(new Color(82, 124, 174));
        btnChangeBirthday.setForeground(Color.WHITE);
        btnChangeBirthday.setFocusPainted(false);
        btnChangeBirthday.addActionListener(this);
        pnlPersonalInfo.add(btnChangeBirthday);

        lblAddress = new JLabel("Address:");
        lblAddress.setBounds(25, 50, 72, 35);
        lblAddress.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlPersonalInfo.add(lblAddress);

        lblAddressField = new JLabel("67 Zone 2 Ilaya St. Malaban Biñan Laguna");
        lblAddressField.setBounds(102, 50, 325, 35);
        lblAddressField.setFont(new Font("Calibri", Font.PLAIN, 18));
        pnlPersonalInfo.add(lblAddressField);

        btnChangeAddress = new JButton("Edit");
        btnChangeAddress.setHorizontalAlignment(JButton.CENTER);
        btnChangeAddress.setBounds(700, 50, 60, 25);
        btnChangeAddress.setBackground(new Color(82, 124, 174));
        btnChangeAddress.setForeground(Color.WHITE);
        btnChangeAddress.setFocusPainted(false);
        btnChangeAddress.addActionListener(this);
        pnlPersonalInfo.add(btnChangeAddress);

//        lblGender = new JLabel("Gender:");
//        lblGender.setBounds(25, 95, 67, 35);
//        lblGender.setFont(new Font("Calibri", Font.BOLD, 18));
//        pnlPersonalInfo.add(lblGender);
//        cmbGender = new JComboBox<String>(gender);
//        cmbGender.setBounds(97, 95, 200, 35);
//        cmbGender.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
//        cmbGender.setFont(new Font("Calibri", Font.PLAIN, 18));
//        cmbGender.setOpaque(false);
//        cmbGender.setFocusable(false);
//        cmbGender.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
//        pnlPersonalInfo.add(cmbGender);
//        lblLine = new JLabel("——————————————————————————————————————————————————————————————————————");
//        lblLine.setBounds(25, 126, 715, 30);
//        lblLine.setForeground(Color.LIGHT_GRAY);
//        pnlPersonalInfo.add(lblLine);
//        
        // APP PREFERENCE
        lblPersonalInfo = new JLabel("App Preference");
        lblPersonalInfo.setForeground(Color.GRAY);
        lblPersonalInfo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPersonalInfo.setBounds(25, 380, 280, 35);
        add(lblPersonalInfo);

        pnlAppPreference = new JPanel();
        pnlAppPreference.setBounds(25, 425, 785, 60);
        pnlAppPreference.setBackground(new Color(243, 243, 243));
        pnlAppPreference.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlAppPreference.setLayout(null);
        add(pnlAppPreference);

        lblPhoneNumber = new JLabel("Theme:");
        lblPhoneNumber.setBounds(25, 15, 63, 35);
        lblPhoneNumber.setFont(new Font("Calibri", Font.BOLD, 18));
        pnlAppPreference.add(lblPhoneNumber);

        cmbTheme = new JComboBox<String>(theme);
        cmbTheme.setBounds(97, 15, 120, 35);
        cmbTheme.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbTheme.setFont(new Font("Calibri", Font.PLAIN, 18));
        cmbTheme.setOpaque(false);
        cmbTheme.setFocusable(false);
        cmbTheme.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAppPreference.add(cmbTheme);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnChangeName) {
            ChangeName name = new ChangeName();
            name.setVisible(true);
        } else if (e.getSource() == btnChangePassword) {
            ChangePassword pass = new ChangePassword();
            pass.setVisible(true);
        } else if (e.getSource() == btnChangePhoneNumber) {
            ChangePhoneNumber phone = new ChangePhoneNumber();
            phone.setVisible(true);
        } else if (e.getSource() == btnChangeEmail) {
            ChangeEmail email = new ChangeEmail();
            email.setVisible(true);
        } else if (e.getSource() == btnChangeAddress) {
            ChangeAddress address = new ChangeAddress();
            address.setVisible(true);
        } else if (e.getSource() == btnChangeBirthday) {
            ChangeBirthday birth = new ChangeBirthday();
            birth.setVisible(true);
        }

    }

    public void setUserSettings(Account user, AccountPersonalInformation userinfo) {

        lblNameField.setText(AccountFunctions.getFullName(userinfo));
        lblEmailField.setText(user.getEmail());
        lblPhoneField.setText(userinfo.getPhoneNum());
        lblPasswordField.setText("********");
        lblBirthdayField.setText(userinfo.getBirthdate());
        lblAddressField.setText(userinfo.getAddress());
    }

}
