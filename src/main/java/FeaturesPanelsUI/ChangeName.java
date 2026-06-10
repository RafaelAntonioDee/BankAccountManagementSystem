/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.AccountFunctions;
import AppService.SettingsFunctions;
import DashboardUIDefault.Colors;
import DashboardUIDefault.*;
import static FeaturesPanelsUI.ChangeEmail.theme;
import static FeaturesPanelsUI.DashboardPanel.theme;
import static FeaturesPanelsUI.SettingsPanel.lblNameField;
import Objects.Account;
import Objects.AccountPersonalInformation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class ChangeName extends JDialog implements ActionListener {

    private JLabel lblLogin, lblTitle, lblFirst, lblLast, lblLogo, lblLine, lblOr;
    private JTextField txtFirst, txtLast;
    private JButton btnConfirm, btnCancel;
    private String updateFirst, updatedLast;
    private Account currentuser;
    private AccountPersonalInformation currentuserInfo;
    public static Colors theme = Colors.LIGHT();

    public ChangeName(Account user, AccountPersonalInformation userInfo) {
        this.currentuser = AppService.AccountFunctions.getUser(user.getEmail());
        this.currentuserInfo = AppService.AccountFunctions.getUserInfo(user.getEmail());

        if (currentuser.getSystemTheme().equals("Light") || currentuser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }

        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Name Change");
        setSize(375, 310);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(theme.BACKGROUND);

        // NEW FIRST NAME
        lblFirst = new JLabel("New First Name");
        lblFirst.setBounds(38, 25, 85, 10);
        lblFirst.setOpaque(true);
        lblFirst.setBackground(theme.BACKGROUND);
        lblFirst.setForeground(theme.TEXT_BLACK);
        lblFirst.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFirst.setHorizontalAlignment(JLabel.CENTER);
        add(lblFirst);

        txtFirst = new JTextField();
        txtFirst.setBounds(32, 30, 295, 35);
        txtFirst.setOpaque(false);
        txtFirst.setBackground(theme.PANELS_BACKGROUND);
        txtFirst.setForeground(theme.TEXT_BLACK);
        txtFirst.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtFirst);

        // NEW LAST NAME
        lblLast = new JLabel("New Last Name");
        lblLast.setBounds(38, 85, 85, 10);
        lblLast.setOpaque(true);
        lblLast.setBackground(new Color(243, 243, 243));
        lblLast.setBackground(theme.BACKGROUND);
        lblLast.setForeground(theme.TEXT_BLACK);
        lblLast.setFont(new Font("Arial", Font.PLAIN, 10));
        lblLast.setHorizontalAlignment(JLabel.CENTER);
        add(lblLast);

        txtLast = new JTextField();
        txtLast.setBounds(32, 90, 295, 35);
        txtLast.setOpaque(false);
        txtLast.setBackground(theme.PANELS_BACKGROUND);
        txtLast.setForeground(theme.TEXT_BLACK);
        txtLast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtLast);

        // BUTTONS
        btnConfirm = new JButton("Confirm");
        btnConfirm.setHorizontalAlignment(JButton.CENTER);
        btnConfirm.setBounds(32, 150, 295, 35);
        btnConfirm.setBackground(theme.PRIMARY_BLUE);
        btnConfirm.setForeground(theme.TEXT_WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(32, 210, 295, 35);
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
            String newFirst = txtFirst.getText();
            String newLast = txtLast.getText();

            if (newFirst.isEmpty() || newLast.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!AccountFunctions.validateFirstName(newFirst)) {
                JOptionPane.showMessageDialog(this, "Invalid First Name!", "Invalid", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!AccountFunctions.validateLastName(newLast)) {
                JOptionPane.showMessageDialog(this, "Invalid Last Name!", "Invalid", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (choice == 0) {
                updateFirst = SettingsFunctions.changeFirstName(currentuser.getEmail(), newFirst);
                updatedLast = SettingsFunctions.changeLastName(currentuser.getEmail(), newLast);
                SettingsPanel.lblNameField.setText(newFirst + " " + newLast);
                DashboardUIDefault.SidePanel.lblAccName.setText(newFirst + " " + newLast);
                dispose();
                JOptionPane.showMessageDialog(this, "Saved Succesfully!", "Name Change", JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == btnCancel) {
            dispose();
        }
    }

}
