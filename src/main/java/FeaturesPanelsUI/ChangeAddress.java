/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import AppService.SettingsFunctions;
import DashboardUIDefault.Colors;
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
public class ChangeAddress extends JFrame implements ActionListener {

    private JLabel lblAddress, lblLogo;
    private JTextField txtAddress;
    private JButton btnConfirm, btnCancel;
    private String updatedAddress;
    private Account user;
    private AccountPersonalInformation userInfo;
    public static Colors theme = Colors.LIGHT();

    public ChangeAddress(Account user, AccountPersonalInformation userInfo) {
        if (user.getSystemTheme().equals("Light") || user.getSystemTheme().equals("System")) {
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
        setTitle("Address Change");
        setSize(375, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(theme.BACKGROUND);

        // NEW ADDRESS
        lblAddress = new JLabel("New Address");
        lblAddress.setBounds(38, 25, 65, 10);
        lblAddress.setOpaque(true);
        lblAddress.setFont(new Font("Arial", Font.PLAIN, 10));
        lblAddress.setForeground(theme.TEXT_BLACK);
        lblAddress.setBackground(theme.BACKGROUND);
        lblAddress.setHorizontalAlignment(JLabel.CENTER);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(32, 30, 295, 35);
        txtAddress.setOpaque(false);
        txtAddress.setForeground(theme.TEXT_BLACK);
        txtAddress.setBackground(theme.PANELS_BACKGROUND);
        txtAddress.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtAddress);

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
            String newAddress = txtAddress.getText();

            if (newAddress.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    updatedAddress = SettingsFunctions.changeAddress(user.getEmail(), newAddress);
                    SettingsPanel.lblAddressField.setText(newAddress);
                    dispose();
                    JOptionPane.showMessageDialog(this, "Saved Succesfully!", "Name Change", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == btnCancel) {
            dispose();
        }
    }
}
