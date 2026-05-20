/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Objects.Account;
import AppService.BalanceFunctions;

public class DepositPanel extends JPanel implements ActionListener {

    private JLabel lblBalance, lblBalanceAmount, lblAmount, lblModeOfTransac, lblReceipt;
    private JButton btnDeposit, btnCancel;
    private JTextField txtAmount;
    private JComboBox<String> cmbModeOfTransac;
    private JPanel pnlProcess;
    private JTextArea txtReceipt;
    private Account user;

    double balance = 0;

    public DepositPanel(Account user) {
        this.user = user;
        balance = user.getBalance();
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        lblBalance = new JLabel("Balance");
        lblBalance.setForeground(Color.GRAY);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBalance.setBounds(25, 25, 250, 35);
        add(lblBalance);

        lblBalanceAmount = new JLabel("    ₱" + String.format("%.2f",balance));
        lblBalanceAmount.setForeground(Color.WHITE);
        lblBalanceAmount.setFont(new Font("Arial", Font.PLAIN, 20));
        lblBalanceAmount.setBounds(25, 65, 250, 50);
        lblBalanceAmount.setOpaque(true);
        lblBalanceAmount.setBackground(new Color(82, 124, 174));
        add(lblBalanceAmount);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(25, 140, 375, 395);
        pnlProcess.setBackground(new Color(243, 243, 243));
        pnlProcess.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblAmount = new JLabel("Deposit Amount");
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAmount.setBounds(25, 25, 325, 35);
        pnlProcess.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(25, 70, 325, 35);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlProcess.add(txtAmount);

        lblModeOfTransac = new JLabel("Mode of Transaction");
        lblModeOfTransac.setFont(new Font("Arial", Font.PLAIN, 18));
        lblModeOfTransac.setBounds(25, 115, 325, 35);
        pnlProcess.add(lblModeOfTransac);

        cmbModeOfTransac = new JComboBox<>();
        cmbModeOfTransac.setBounds(25, 160, 325, 35);
        cmbModeOfTransac.addItem("Select Mode");
        cmbModeOfTransac.addItem("Over the Counter");
        cmbModeOfTransac.addItem("Local Banks");
        cmbModeOfTransac.addItem("Global Banks & Partners");
        pnlProcess.add(cmbModeOfTransac);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(265, 325, 85, 35);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        pnlProcess.add(btnCancel);

        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(160, 325, 85, 35);
        btnDeposit.setBackground(new Color(82, 124, 174));
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.setFocusPainted(false);
        btnDeposit.addActionListener(this);
        pnlProcess.add(btnDeposit);

        lblReceipt = new JLabel("Receipt");
        lblReceipt.setForeground(Color.GRAY);
        lblReceipt.setFont(new Font("Arial", Font.PLAIN, 18));
        lblReceipt.setBounds(425, 25, 325, 35);
        add(lblReceipt);

        txtReceipt = new JTextArea();
        txtReceipt.setBounds(425, 65, 387, 470);
        txtReceipt.setBorder(new LineBorder(Color.LIGHT_GRAY));
        txtReceipt.setLineWrap(true);
        txtReceipt.setWrapStyleWord(true);
        txtReceipt.setEditable(false);
        add(txtReceipt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDeposit) {
            try {
                String amountText = txtAmount.getText();
                String mode = (String) cmbModeOfTransac.getSelectedItem();

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter amount!");
                    return;
                }

                if (mode.equals("Select Mode")) {
                    JOptionPane.showMessageDialog(this, "Select transaction mode!");
                    return;
                }

                double amount = Double.parseDouble(amountText);

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!");
                    return;
                }

                double fee = 0;

                if (mode.equals("Global Banks & Partners")) {
                    fee = 20;
                }

                double netAmount = amount - fee;

                if (netAmount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount too small after fees!");
                    return;
                }

                balance = BalanceFunctions.deposit(user.getEmail(), netAmount);

                lblBalanceAmount.setText("    ₱" + String.format("%.2f", balance));

                String receipt =
                        "----- DEPOSIT RECEIPT -----\n\n" +
                        "Amount Entered:  ₱" + amount + "\n" +
                        "Mode:  " + mode + "\n" +
                        "Fee:  ₱" + fee + "\n" +
                        "Net Deposit:  ₱" + netAmount + "\n\n" +
                        "Updated Balance:  ₱" + String.format("%.2f", balance) + "\n" +
                        "\nTransaction Successful";

                txtReceipt.setText(receipt);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        }

        if (e.getSource() == btnCancel) {
            txtAmount.setText("");
            cmbModeOfTransac.setSelectedIndex(0);
            txtReceipt.setText("");
        }
    }
}
