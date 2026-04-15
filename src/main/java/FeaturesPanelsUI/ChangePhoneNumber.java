/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import DashboardUIDefault.MainDashboard;
import static FeaturesPanelsUI.SettingsPanel.lblPhoneField;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class ChangePhoneNumber extends JFrame implements ActionListener {

    private JLabel lblLogin, lblTitle, lblNewPhone, lblLogo, lblLine, lblOr;
    private JTextField txtNewPhone;
    private JButton btnConfirm, btnCancel;

    public ChangePhoneNumber() {

        // LOGO
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Phone Number Change");
        setSize(375, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 243, 243));

        lblNewPhone = new JLabel("New Phone Number");
        lblNewPhone.setBounds(38, 25, 100, 10);
        lblNewPhone.setOpaque(true);
        lblNewPhone.setBackground(new Color(243, 243, 243));
        lblNewPhone.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewPhone.setHorizontalAlignment(JLabel.CENTER);
        add(lblNewPhone);

        txtNewPhone = new JTextField();
        txtNewPhone.setBounds(32, 30, 295, 35);
        txtNewPhone.setOpaque(false);
        txtNewPhone.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtNewPhone);

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
            String newPhoneInput = txtNewPhone.getText();
            String currentPhone = SettingsPanel.lblPhoneField.getText();

            if (newPhoneInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Missing Fields", JOptionPane.ERROR_MESSAGE);
            } else if (currentPhone.equals(newPhoneInput)) {
                JOptionPane.showMessageDialog(this, "New phone number cannot be the same as the old phone number!", "", JOptionPane.ERROR_MESSAGE);
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION);

                if (choice == 0) {
                    SettingsPanel.lblPhoneField.setText(newPhoneInput);
                    dispose();
                    JOptionPane.showMessageDialog(this, "Saved Succesfully!", "Name Change", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == btnCancel) {
            dispose();
        }
    }
}
