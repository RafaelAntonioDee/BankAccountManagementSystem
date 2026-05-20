package FeaturesPanelsUI;

import Objects.Account;
import AppService.BalanceFunctions;
import AppService.AccountFunctions;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TransferPanel extends JPanel implements ActionListener {

    private JLabel lblBalance, lblBalanceAmount, lblAmount, lblEmail, lblReceipt, lblName;
    private JButton btnTransfer, btnCancel;
    private JTextField txtAmount, txtEmail;
    private JPanel pnlProcess;
    private JTextArea txtReceipt;

    private ArrayList<Account> accounts = new ArrayList<>();
    private Account currentUser;

    public TransferPanel(Account user) {
        this.currentUser = user;
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        lblBalance = new JLabel("Balance");
        lblBalance.setForeground(Color.GRAY);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBalance.setBounds(25, 25, 250, 35);
        add(lblBalance);

        lblBalanceAmount = new JLabel("    ₱" + String.format("%.2f",currentUser.getBalance()));
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

        lblEmail = new JLabel("Transfer to (email)");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEmail.setBounds(25, 25, 325, 35);
        pnlProcess.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(25, 70, 325, 35);
        pnlProcess.add(txtEmail);

        lblName = new JLabel("Name: _________________________");
        lblName.setFont(new Font("Arial", Font.PLAIN, 18));
        lblName.setBounds(25, 115, 325, 35);
        pnlProcess.add(lblName);

        lblAmount = new JLabel("Amount");
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAmount.setBounds(25, 160, 325, 35);
        pnlProcess.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(25, 205, 325, 35);
        pnlProcess.add(txtAmount);

        btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(160, 325, 85, 35);
        btnTransfer.setBackground(new Color(82, 124, 174));
        btnTransfer.setForeground(Color.WHITE);
        btnTransfer.addActionListener(this);
        pnlProcess.add(btnTransfer);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(265, 325, 85, 35);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        pnlProcess.add(btnCancel);

        lblReceipt = new JLabel("Receipt");
        lblReceipt.setForeground(Color.GRAY);
        lblReceipt.setFont(new Font("Arial", Font.PLAIN, 18));
        lblReceipt.setBounds(425, 25, 325, 35);
        add(lblReceipt);

        txtReceipt = new JTextArea();
        txtReceipt.setBounds(425, 65, 387, 470);
        txtReceipt.setBorder(new LineBorder(Color.LIGHT_GRAY));
        txtReceipt.setEditable(false);
        add(txtReceipt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnTransfer) {

            String email = txtEmail.getText();
            String amountText = txtAmount.getText();

            try {
                double amount = Double.parseDouble(amountText);

                Account receiver = AccountFunctions.getUser(email);

                if (receiver == null) {
                    JOptionPane.showMessageDialog(this, "Account not found!");
                    return;
                }

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!");
                    return;
                }

                if (currentUser.getBalance() < amount) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                    return;
                }

                BalanceFunctions.transfer(currentUser, receiver, amount);

                lblBalanceAmount.setText("    ₱" + String.format("%.2f",currentUser.getBalance()));
                lblName.setText("Name: " + receiver.getEmail());

                txtReceipt.setText(
                        "Transfer Successful\n" +
                        "To: " + receiver.getEmail() + "\n" +
                        "Amount: ₱" + amount + "\n" +
                        "Remaining Balance: ₱" + currentUser.getBalance()
                );

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid amount!");
            }
        }

        if (e.getSource() == btnCancel) {
            txtEmail.setText("");
            txtAmount.setText("");
            txtReceipt.setText("");
            lblName.setText("Name: _________________________");
        }
    }
}
