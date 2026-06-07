/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.SettingsFunctions;
import DashboardUIDefault.Colors;
import DashboardUIDefault.MainDashboard;
import static FeaturesPanelsUI.ChangeAddress.theme;
import static FeaturesPanelsUI.DashboardPanel.theme;
import Objects.Account;
import Objects.AccountPersonalInformation;
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
    private JTextField txtYear, txtDay;
    private Account user;
    private AccountPersonalInformation userInfo;
    private String selectedMonth, updateBirthday;
    private String[] months = {"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};
    public static Colors theme = Colors.LIGHT();

    public ChangeBirthday(Account user, AccountPersonalInformation userInfo) {
        if (user.getSystemTheme().equals("Light")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }
        this.user = user;
        this.userInfo = userInfo;
        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Birthday Change");
        setSize(375, 370);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(theme.BACKGROUND);

        // NEW FIRST NAME
        lblMonth = new JLabel("Month");
        lblMonth.setBounds(38, 25, 40, 10);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(theme.BACKGROUND);
        lblMonth.setForeground(theme.TEXT_BLACK);
        lblMonth.setFont(new Font("Arial", Font.PLAIN, 10));
        lblMonth.setHorizontalAlignment(JLabel.CENTER);
        add(lblMonth);

        cmbMonth = new JComboBox(months);
        cmbMonth.setBounds(32, 30, 295, 35);
        cmbMonth.setOpaque(false);
        cmbMonth.setFocusable(false);
        cmbMonth.setBackground(theme.PANELS_BACKGROUND);
        cmbMonth.setForeground(theme.TEXT_BLACK);
        add(cmbMonth);

        cmbMonth.addActionListener(e -> {
            String month = (String) cmbMonth.getSelectedItem();
            updateDays(month);
        });

        // NEW LAST NAME
        lblDay = new JLabel("Day");
        lblDay.setBounds(38, 85, 25, 10);
        lblDay.setOpaque(true);
        lblDay.setBackground(theme.BACKGROUND);
        lblDay.setForeground(theme.TEXT_BLACK);
        lblDay.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDay.setHorizontalAlignment(JLabel.CENTER);
        add(lblDay);

        cmbDay = new JComboBox();
        cmbDay.setBounds(32, 90, 295, 35);
        cmbDay.setOpaque(false);
        cmbDay.setFocusable(false);
        cmbDay.setBackground(theme.PANELS_BACKGROUND);
        cmbDay.setForeground(theme.TEXT_BLACK);
        add(cmbDay);

        lblYear = new JLabel("Year");
        lblYear.setBounds(38, 145, 30, 10);
        lblYear.setOpaque(true);
        lblYear.setBackground(theme.BACKGROUND);
        lblYear.setForeground(theme.TEXT_BLACK);
        lblYear.setFont(new Font("Arial", Font.PLAIN, 10));
        lblYear.setHorizontalAlignment(JLabel.CENTER);
        add(lblYear);

        cmbYear = new JComboBox();
        cmbYear.setBounds(32, 150, 295, 35);
        cmbYear.setOpaque(false);
        cmbYear.setFocusable(false);
        cmbYear.setBackground(theme.PANELS_BACKGROUND);
        cmbYear.setForeground(theme.TEXT_BLACK);
        add(cmbYear);

        // BUTTONS
        btnConfirm = new JButton("Confirm");
        btnConfirm.setHorizontalAlignment(JButton.CENTER);
        btnConfirm.setBounds(32, 210, 295, 35);
        btnConfirm.setBackground(theme.PRIMARY_BLUE);
        btnConfirm.setForeground(theme.TEXT_WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(32, 270, 295, 35);
        btnCancel.setBackground(theme.CancelButton);
        btnCancel.setForeground(theme.TEXT_WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        add(btnCancel);

        // YEAR OPTION
        int startYear = 2026;
        int endYear = 1950;
        String[] years = new String[startYear - endYear + 1];

        for (int i = 0; i < years.length; i++) {
            years[i] = String.valueOf(startYear - i);
        }
        for (String year : years) {
            cmbYear.addItem(year);
        }

        // FOR DAYS TO HAVE INITIAL OPTION
        updateDays((String) cmbMonth.getSelectedItem());

    }

    // FOR DIFFERENT DAYS EACH MONTH
    private void updateDays(String month) {
        String[] initialDays = new String[31];
        for (int i = 0; i < 31; i++) {
            initialDays[i] = String.valueOf(i + 1);
        }
        for (String day : initialDays) {
            cmbDay.addItem(day);
        }
        cmbDay.removeAllItems();

        int days = 31;

        switch (month) {
            case "April":
            case "June":
            case "September":
            case "November":
                days = 30;
                break;

            case "February":
                days = 28;
                break;

            default:
                days = 31;
                break;
        }

        for (int i = 1; i <= days; i++) {
            cmbDay.addItem(String.valueOf(i));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnConfirm) {

            UIManager.put("Button.focus", new Color(0, 0, 0, 0));
            String newMonth = String.valueOf(cmbMonth.getSelectedItem());
            String newDay = String.valueOf(cmbDay.getSelectedItem());
            String newYear = String.valueOf(cmbYear.getSelectedItem());
            String newBirthday = newMonth + " " + newDay + ", " + newYear;

            int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                updateBirthday = SettingsFunctions.changeBirthday(user.getEmail(), newBirthday);
                SettingsPanel.lblBirthdayField.setText(newBirthday);
                dispose();
                JOptionPane.showMessageDialog(this, "Saved Succesfully!", "Name Change", JOptionPane.INFORMATION_MESSAGE);
            } else {
                dispose();
            }

        } else if (e.getSource() == btnCancel) {
            dispose();
        }
    }

}
