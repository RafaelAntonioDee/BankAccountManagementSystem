/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import DashboardUIDefault.MainDashboard;
import static FeaturesPanelsUI.SettingsPanel.lblNameField;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class ChangeName extends JFrame implements ActionListener {

    private JLabel lblLogin, lblTitle, lblFirst, lblLast, lblLogo, lblLine, lblOr;
    private JTextField txtFirst, txtLast;
    private JButton btnConfirm, btnCancel;

    public ChangeName() {
        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Name Change");
        setSize(375, 310);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(243, 243, 243));

        // NEW FIRST NAME
        lblFirst = new JLabel("New First Name");
        lblFirst.setBounds(38, 25, 85, 10);
        lblFirst.setOpaque(true);
        lblFirst.setBackground(new Color(243, 243, 243));
        lblFirst.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFirst.setHorizontalAlignment(JLabel.CENTER);
        add(lblFirst);

        txtFirst = new JTextField();
        txtFirst.setBounds(32, 30, 295, 35);
        txtFirst.setOpaque(false);
        txtFirst.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtFirst);

        // NEW LAST NAME
        lblLast = new JLabel("New Last Name");
        lblLast.setBounds(38, 85, 85, 10);
        lblLast.setOpaque(true);
        lblLast.setBackground(new Color(243, 243, 243));
        lblLast.setFont(new Font("Arial", Font.PLAIN, 10));
        lblLast.setHorizontalAlignment(JLabel.CENTER);
        add(lblLast);

        txtLast = new JTextField();
        txtLast.setBounds(32, 90, 295, 35);
        txtLast.setOpaque(false);
        txtLast.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtLast);

        // BUTTONS
        btnConfirm = new JButton("Confirm");
        btnConfirm.setHorizontalAlignment(JButton.CENTER);
        btnConfirm.setBounds(32, 150, 295, 35);
        btnConfirm.setBackground(new Color(82, 124, 174));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(this);
        add(btnConfirm);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(32, 210, 295, 35);
        btnCancel.setBackground(new Color(243, 243, 243));
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
            } else {
                int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Change Confirmation", JOptionPane.YES_NO_OPTION);
                
                if (choice == 0) {
                    String newName = newFirst + " " + newLast;
                    SettingsPanel.lblNameField.setText(newName);
                    dispose();
                    JOptionPane.showMessageDialog(this, "Saved Succesfully!", "Name Change", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource() == btnCancel) {
            dispose();
        }
    }

}
