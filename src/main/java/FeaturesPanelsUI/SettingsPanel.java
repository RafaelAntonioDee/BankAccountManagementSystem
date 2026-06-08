/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.AccountFunctions;
import DashboardUIDefault.Colors;
import DashboardUIDefault.MainDashboard;
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
    private JComboBox<String> cmbTheme;
    private String[] SystemTheme = {"System", "Dark", "Light"};
//            gender = {"Male", "Female", "Croissant", "Prefer Not to Say"};
    private Account currentuser;
    private AccountPersonalInformation currentuserInfo;
    public static Colors theme = Colors.LIGHT();

    public SettingsPanel(Account user, AccountPersonalInformation userInfo) {
        this.currentuser = AppService.AccountFunctions.getUser(user.getEmail());
        this.currentuserInfo = AppService.AccountFunctions.getUserInfo(user.getEmail());

        if (currentuser.getSystemTheme().equals("Light") || currentuser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }

        setBounds(0, 0, 837, 560);
        setBackground(theme.BACKGROUND);
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setLayout(null);

        lblAccountInfo = new JLabel("Account Information");
        lblAccountInfo.setForeground(theme.TEXT_GRAY);
        lblAccountInfo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAccountInfo.setBounds(25, 15, 250, 35);
        add(lblAccountInfo);

        pnlAccountInfo = new JPanel();
        pnlAccountInfo.setBounds(25, 60, 785, 160);
        pnlAccountInfo.setBackground(theme.PANELS_BACKGROUND);
        pnlAccountInfo.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlAccountInfo.setLayout(null);
        add(pnlAccountInfo);

        lblName = new JLabel("Name:");
        lblName.setFont(new Font("Calibri", Font.BOLD, 18));
        lblName.setBounds(25, 15, 54, 35);
        lblName.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblName);

        lblNameField = new JLabel(currentuserInfo.getFirstName() + " " + currentuserInfo.getLastName());
        lblNameField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblNameField.setBounds(84, 15, 325, 35);
        lblNameField.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblNameField);

        btnChangeName = new JButton("Edit");
        btnChangeName.setHorizontalAlignment(JButton.CENTER);
        btnChangeName.setBounds(700, 15, 60, 25);
        btnChangeName.setBackground(theme.PRIMARY_BLUE);
        btnChangeName.setForeground(theme.TEXT_WHITE);
        btnChangeName.setFocusPainted(false);
        btnChangeName.addActionListener(this);
        pnlAccountInfo.add(btnChangeName);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(25, 50, 51, 35);
        lblEmail.setFont(new Font("Calibri", Font.BOLD, 18));
        lblEmail.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblEmail);

        lblEmailField = new JLabel(currentuserInfo.getEmail());
        lblEmailField.setBounds(81, 50, 325, 35);
        lblEmailField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblEmailField.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblEmailField);

        btnChangeEmail = new JButton("Edit");
        btnChangeEmail.setHorizontalAlignment(JButton.CENTER);
        btnChangeEmail.setBounds(700, 50, 60, 25);
        btnChangeEmail.setBackground(theme.PRIMARY_BLUE);
        btnChangeEmail.setForeground(theme.TEXT_WHITE);
        btnChangeEmail.setFocusPainted(false);
        btnChangeEmail.addActionListener(this);
        pnlAccountInfo.add(btnChangeEmail);

        lblPhoneNumber = new JLabel("Phone Number:");
        lblPhoneNumber.setBounds(25, 85, 127, 35);
        lblPhoneNumber.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPhoneNumber.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblPhoneNumber);

        lblPhoneField = new JLabel(currentuserInfo.getPhoneNum());
        lblPhoneField.setBounds(157, 85, 325, 35);
        lblPhoneField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblPhoneField.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblPhoneField);

        btnChangePhoneNumber = new JButton("Edit");
        btnChangePhoneNumber.setHorizontalAlignment(JButton.CENTER);
        btnChangePhoneNumber.setBounds(700, 85, 60, 25);
        btnChangePhoneNumber.setBackground(theme.PRIMARY_BLUE);
        btnChangePhoneNumber.setForeground(theme.TEXT_WHITE);
        btnChangePhoneNumber.setFocusPainted(false);
        btnChangePhoneNumber.addActionListener(this);
        pnlAccountInfo.add(btnChangePhoneNumber);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(25, 120, 86, 35);
        lblPassword.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPassword.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblPassword);

        String password = currentuser.getPassword();
        String hiddenPass = "*".repeat(password.length());

        lblPasswordField = new JLabel(hiddenPass);
        lblPasswordField.setBounds(116, 120, 325, 35);
        lblPasswordField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblPasswordField.setForeground(theme.TEXT_BLACK);
        pnlAccountInfo.add(lblPasswordField);

        btnChangePassword = new JButton("Edit");
        btnChangePassword.setHorizontalAlignment(JButton.CENTER);
        btnChangePassword.setBounds(700, 120, 60, 25);
        btnChangePassword.setBackground(theme.PRIMARY_BLUE);
        btnChangePassword.setForeground(theme.TEXT_WHITE);
        btnChangePassword.setFocusPainted(false);
        btnChangePassword.addActionListener(this);
        pnlAccountInfo.add(btnChangePassword);

        // PERSONAL INFORMATION
        lblAppPref = new JLabel("Personal Information");
        lblAppPref.setForeground(theme.TEXT_GRAY);
        lblAppPref.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAppPref.setBounds(25, 230, 280, 35);
        add(lblAppPref);

        pnlPersonalInfo = new JPanel();
        pnlPersonalInfo.setBounds(25, 275, 785, 90);
        pnlPersonalInfo.setBackground(theme.PANELS_BACKGROUND);
        pnlPersonalInfo.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlPersonalInfo.setLayout(null);
        add(pnlPersonalInfo);

        lblBirthday = new JLabel("Birthday:");
        lblBirthday.setBounds(25, 15, 73, 35);
        lblBirthday.setFont(new Font("Calibri", Font.BOLD, 18));
        lblBirthday.setForeground(theme.TEXT_BLACK);
        pnlPersonalInfo.add(lblBirthday);

        lblBirthdayField = new JLabel(currentuserInfo.getBirthdate());
        lblBirthdayField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblBirthdayField.setBounds(103, 15, 325, 35);
        lblBirthdayField.setForeground(theme.TEXT_BLACK);
        pnlPersonalInfo.add(lblBirthdayField);

        btnChangeBirthday = new JButton("Edit");
        btnChangeBirthday.setHorizontalAlignment(JButton.CENTER);
        btnChangeBirthday.setBounds(700, 15, 60, 25);
        btnChangeBirthday.setBackground(theme.PRIMARY_BLUE);
        btnChangeBirthday.setForeground(theme.TEXT_WHITE);
        btnChangeBirthday.setFocusPainted(false);
        btnChangeBirthday.addActionListener(this);
        pnlPersonalInfo.add(btnChangeBirthday);

        lblAddress = new JLabel("Address:");
        lblAddress.setBounds(25, 50, 72, 35);
        lblAddress.setFont(new Font("Calibri", Font.BOLD, 18));
        lblAddress.setForeground(theme.TEXT_BLACK);
        pnlPersonalInfo.add(lblAddress);

        lblAddressField = new JLabel(currentuserInfo.getAddress());
        lblAddressField.setBounds(102, 50, 325, 35);
        lblAddressField.setFont(new Font("Calibri", Font.PLAIN, 18));
        lblAddressField.setForeground(theme.TEXT_BLACK);
        pnlPersonalInfo.add(lblAddressField);

        btnChangeAddress = new JButton("Edit");
        btnChangeAddress.setHorizontalAlignment(JButton.CENTER);
        btnChangeAddress.setBounds(700, 50, 60, 25);
        btnChangeAddress.setBackground(theme.PRIMARY_BLUE);
        btnChangeAddress.setForeground(theme.TEXT_WHITE);
        btnChangeAddress.setFocusPainted(false);
        btnChangeAddress.addActionListener(this);
        pnlPersonalInfo.add(btnChangeAddress);

        // APP PREFERENCE
        lblPersonalInfo = new JLabel("App Preference");
        lblPersonalInfo.setForeground(theme.TEXT_GRAY);
        lblPersonalInfo.setFont(new Font("Arial", Font.PLAIN, 18));
        lblPersonalInfo.setBounds(25, 380, 280, 35);
        add(lblPersonalInfo);

        pnlAppPreference = new JPanel();
        pnlAppPreference.setBounds(25, 425, 785, 60);
        pnlAppPreference.setBackground(theme.PANELS_BACKGROUND);
        pnlAppPreference.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlAppPreference.setLayout(null);
        add(pnlAppPreference);

        lblPhoneNumber = new JLabel("Theme:");
        lblPhoneNumber.setBounds(25, 15, 63, 35);
        lblPhoneNumber.setFont(new Font("Calibri", Font.BOLD, 18));
        lblPhoneNumber.setForeground(theme.TEXT_BLACK);
        pnlAppPreference.add(lblPhoneNumber);

        cmbTheme = new JComboBox<String>(SystemTheme);
        cmbTheme.setBounds(97, 15, 120, 35);
        cmbTheme.setFocusable(false);
        cmbTheme.setForeground(theme.TEXT_BLACK);
        cmbTheme.setBackground(theme.PANELS_BACKGROUND);
        pnlAppPreference.add(cmbTheme);

        cmbTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppService.AccountFunctions.ChangeTheme(currentuser.getEmail(), cmbTheme.getSelectedItem().toString());
                MainDashboard md = new MainDashboard(currentuser, currentuserInfo);
                md.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(SettingsPanel.this);
                frame.dispose();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Account user = AppService.AccountFunctions.getUser(lblEmailField.getText());
        AccountPersonalInformation userinfo = AppService.AccountFunctions.getUserInfo(lblEmailField.getText());

        setUserSettings(user, userinfo);

        if (e.getSource() == btnChangeName) {
            ChangeName name = new ChangeName(user, userinfo);
            name.setVisible(true);
        } else if (e.getSource() == btnChangePassword) {
            ChangePassword pass = new ChangePassword(user, userinfo);
            pass.setVisible(true);
        } else if (e.getSource() == btnChangePhoneNumber) {
            ChangePhoneNumber phone = new ChangePhoneNumber(user, userinfo);
            phone.setVisible(true);
        } else if (e.getSource() == btnChangeEmail) {
            ChangeEmail email = new ChangeEmail(user, userinfo);
            email.setVisible(true);
        } else if (e.getSource() == btnChangeAddress) {
            ChangeAddress address = new ChangeAddress(user, userinfo);
            address.setVisible(true);
        } else if (e.getSource() == btnChangeBirthday) {
            ChangeBirthday birth = new ChangeBirthday(user, userinfo);
            birth.setVisible(true);
        }

    }

    public void setUserSettings(Account user, AccountPersonalInformation userinfo) {

        String password = user.getPassword();
        String hiddenPassword = "*".repeat(password.length());

        lblNameField.setText(AccountFunctions.getFullName(userinfo));
        lblEmailField.setText(user.getEmail());
        lblPhoneField.setText(userinfo.getPhoneNum());
        lblPasswordField.setText(hiddenPassword);
        lblBirthdayField.setText(userinfo.getBirthdate());
        lblAddressField.setText(userinfo.getAddress());
    }

}
