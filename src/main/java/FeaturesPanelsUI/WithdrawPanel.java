package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Objects.Account;
import AppService.BalanceFunctions;
import DashboardUIDefault.Colors;
import static FeaturesPanelsUI.DepositPanel.theme;

public class WithdrawPanel extends JPanel implements ActionListener {

    private JLabel lblBalance, lblBalanceAmount, lblAmount, lblModeOfTransac, lblGuideTitle;
    private JButton btnWithdraw, btnCancel;
    private JButton btnAmt500, btnAmt1000, btnAmt2500, btnAmt5000;
    private JTextField txtAmount;
    private JComboBox cmbModeOfTransac;
    private JPanel pnlProcess, pnlGuidelines;
    private Account user;
    public static Colors theme = Colors.LIGHT();

    double balance = 0;

    public WithdrawPanel(Account user) {
        if (user.getSystemTheme().equals("Light")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }
        this.user = user;
        balance = user.getBalance();
        
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        lblBalance = new JLabel("Available Balance");
        lblBalance.setForeground(Color.GRAY);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 16));
        lblBalance.setBounds(40, 20, 755, 25);
        add(lblBalance);

        lblBalanceAmount = new JLabel("    ₱" + String.format("%.2f", balance));
        lblBalanceAmount.setForeground(Color.WHITE);
        lblBalanceAmount.setFont(new Font("Arial", Font.BOLD, 22));
        lblBalanceAmount.setBounds(40, 50, 755, 55);
        lblBalanceAmount.setOpaque(true);
        lblBalanceAmount.setBackground(new Color(82, 124, 174));
        add(lblBalanceAmount);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(40, 130, 420, 395);
        pnlProcess.setBackground(Color.WHITE);
        pnlProcess.setBorder(new LineBorder(new Color(220, 220, 220)));
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblAmount = new JLabel("Withdraw Amount");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 15));
        lblAmount.setBounds(30, 20, 360, 25);
        pnlProcess.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(30, 50, 360, 35);
        txtAmount.setFont(new Font("Arial", Font.PLAIN, 15));
        txtAmount.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlProcess.add(txtAmount);

        btnAmt500 = new JButton("₱500");
        btnAmt500.setBounds(30, 95, 80, 28);
        btnAmt1000 = new JButton("₱1000");
        btnAmt1000.setBounds(120, 95, 80, 28);
        btnAmt2500 = new JButton("₱2500");
        btnAmt2500.setBounds(210, 95, 80, 28);
        btnAmt5000 = new JButton("₱5000");
        btnAmt5000.setBounds(300, 95, 90, 28);

        JButton[] quickButtons = {btnAmt500, btnAmt1000, btnAmt2500, btnAmt5000};
        for (JButton btn : quickButtons) {
            btn.setBackground(Color.WHITE);
            btn.setForeground(new Color(82, 124, 174));
            btn.setBorder(BorderFactory.createLineBorder(new Color(82, 124, 174)));
            btn.setFocusPainted(false);
            btn.addActionListener(this);
            pnlProcess.add(btn);
        }

        lblModeOfTransac = new JLabel("Mode of Transaction");
        lblModeOfTransac.setFont(new Font("Arial", Font.BOLD, 15));
        lblModeOfTransac.setBounds(30, 150, 360, 25);
        pnlProcess.add(lblModeOfTransac);

        cmbModeOfTransac = new JComboBox();
        cmbModeOfTransac.setBounds(30, 180, 360, 35);
        cmbModeOfTransac.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbModeOfTransac.addItem("Select Mode");
        cmbModeOfTransac.addItem("Linked Bank Account");
        cmbModeOfTransac.addItem("Generate a QR Code");
        cmbModeOfTransac.addItem("Over-the-Counter Cashier");
        pnlProcess.add(cmbModeOfTransac);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(160, 330, 110, 35);
        btnWithdraw.setBackground(new Color(82, 124, 174));
        btnWithdraw.setForeground(Color.WHITE);
        btnWithdraw.setFont(new Font("Arial", Font.BOLD, 13));
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.addActionListener(this);
        pnlProcess.add(btnWithdraw);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(280, 330, 110, 35);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        pnlProcess.add(btnCancel);

        pnlGuidelines = new JPanel();
        pnlGuidelines.setBounds(480, 130, 315, 395);
        pnlGuidelines.setBackground(Color.WHITE);
        pnlGuidelines.setBorder(new LineBorder(new Color(220, 220, 220)));
        pnlGuidelines.setLayout(null);
        add(pnlGuidelines);

        lblGuideTitle = new JLabel("Withdrawal Guidelines");
        lblGuideTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lblGuideTitle.setForeground(new Color(82, 124, 174));
        lblGuideTitle.setBounds(20, 20, 275, 25);
        pnlGuidelines.add(lblGuideTitle);

        String guideText = "<html>"
                + "<body style='font-family:Arial; font-size:11px; color:#555555;'>"
                + "<b>Withdrawal Charges:</b><br>"
                + "• Linked Bank Account: Free<br>"
                + "• Generate a QR Code: Free<br>"
                + "• Over-the-Counter Cashier: ₱20.00 processing fee added to total deduction<br><br>"
                + "<b>Processing Time:</b><br>"
                + "• Handled immediately in real-time.<br><br>"
                + "<b>Notice:</b><br>"
                + "Make sure your balance can cover both the withdrawal amount and the fee before submitting.."
                + "</body>"
                + "</html>";

        JLabel lblGuideBody = new JLabel(guideText);
        lblGuideBody.setBounds(20, 55, 275, 300);
        lblGuideBody.setVerticalAlignment(SwingConstants.TOP);
        pnlGuidelines.add(lblGuideBody);
    }

    private String getNextTransactionID() {
        java.util.ArrayList<Objects.AccountTransactHistory> history = BalanceFunctions.getTransactions(user.getEmail());
        int nextNum = 1; 
        
        if (history != null && !history.isEmpty()) {
            try {
                String lastID = history.get(history.size() - 1).getTransactionID(); 
                int lastNum = Integer.parseInt(lastID.replaceAll("[^0-9]", ""));
                nextNum = lastNum + 1; 
            } catch (Exception e) {
                nextNum = history.size() + 1;
            }
        }
        return String.format("T%04d", nextNum);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAmt500) txtAmount.setText("500");
        else if (e.getSource() == btnAmt1000) txtAmount.setText("1000");
        else if (e.getSource() == btnAmt2500) txtAmount.setText("2500");
        else if (e.getSource() == btnAmt5000) txtAmount.setText("5000");

        if (e.getSource() == btnWithdraw) {
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
                double totalDeduction = amount + fee;
                if (totalDeduction > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                    return;
                }

                String sequentialTxnId = getNextTransactionID();

                balance = BalanceFunctions.withdraw(user.getEmail(), totalDeduction, sequentialTxnId);
                lblBalanceAmount.setText("    ₱" + String.format("%.2f", balance));

                showReceiptPopup(amount, fee, totalDeduction, mode, sequentialTxnId);
                clearInputs();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        }

        if (e.getSource() == btnCancel) {
            clearInputs();
        }
    }

    private void clearInputs() {
        txtAmount.setText("");
        cmbModeOfTransac.setSelectedIndex(0);
    }

    private void showReceiptPopup(double amount, double fee, double totalDeduction, String mode, String txnId) {
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog(parentWindow, "Receipt", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(400, 520);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel pnlReceiptImage = new JPanel();
        pnlReceiptImage.setBackground(new Color(82, 124, 174)); 
        pnlReceiptImage.setLayout(null);

        JPanel pnlWhiteCard = new JPanel();
        pnlWhiteCard.setBackground(Color.WHITE);
        pnlWhiteCard.setBounds(30, 40, 325, 360);
        pnlWhiteCard.setLayout(null);
        pnlReceiptImage.add(pnlWhiteCard);

        JLabel lblCheck = new JLabel("[ ✓ ]", SwingConstants.CENTER);
        lblCheck.setFont(new Font("Arial", Font.BOLD, 24));
        lblCheck.setForeground(new Color(82, 124, 174));
        lblCheck.setBounds(0, 15, 325, 30);
        pnlWhiteCard.add(lblCheck);

        JLabel lblSentVia = new JLabel("Disbursed via Withdrawal", SwingConstants.CENTER);
        lblSentVia.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSentVia.setForeground(Color.GRAY);
        lblSentVia.setBounds(0, 45, 325, 25);
        pnlWhiteCard.add(lblSentVia);

        JSeparator sep1 = new JSeparator();
        sep1.setBounds(20, 85, 285, 10);
        pnlWhiteCard.add(sep1);

        JLabel lblAmtTitle = new JLabel("Withdraw Amount");
        lblAmtTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblAmtTitle.setBounds(25, 105, 130, 25);
        pnlWhiteCard.add(lblAmtTitle);

        JLabel lblAmtVal = new JLabel(String.format("%.2f", amount), SwingConstants.RIGHT);
        lblAmtVal.setFont(new Font("Arial", Font.BOLD, 14));
        lblAmtVal.setBounds(160, 105, 140, 25);
        pnlWhiteCard.add(lblAmtVal);

        JLabel lblFeeTitle = new JLabel("Service Fee");
        lblFeeTitle.setFont(new Font("Arial", Font.PLAIN, 13));
        lblFeeTitle.setForeground(Color.GRAY);
        lblFeeTitle.setBounds(25, 135, 110, 20);
        pnlWhiteCard.add(lblFeeTitle);

        JLabel lblFeeVal = new JLabel(String.format("%.2f", fee), SwingConstants.RIGHT);
        lblFeeVal.setFont(new Font("Arial", Font.PLAIN, 13));
        lblFeeVal.setForeground(Color.GRAY);
        lblFeeVal.setBounds(150, 135, 150, 20);
        pnlWhiteCard.add(lblFeeVal);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(20, 175, 285, 10);
        pnlWhiteCard.add(sep2);

        JLabel lblTotalTitle = new JLabel("Total Account Debit");
        lblTotalTitle.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotalTitle.setForeground(new Color(0, 25, 75));
        lblTotalTitle.setBounds(25, 200, 130, 25);
        pnlWhiteCard.add(lblTotalTitle);

        JLabel lblTotalVal = new JLabel("₱" + String.format("%.2f", totalDeduction), SwingConstants.RIGHT);
        lblTotalVal.setFont(new Font("Arial", Font.BOLD, 20));
        lblTotalVal.setForeground(new Color(0, 25, 75));
        lblTotalVal.setBounds(145, 195, 155, 30);
        pnlWhiteCard.add(lblTotalVal);

        JPanel pnlRefTint = new JPanel();
        pnlRefTint.setBackground(new Color(242, 245, 253));
        pnlRefTint.setBounds(0, 260, 325, 100);
        pnlRefTint.setLayout(null);
        pnlWhiteCard.add(pnlRefTint);

        JLabel lblRefNum = new JLabel("Transaction ID: " + txnId, SwingConstants.CENTER);
        lblRefNum.setFont(new Font("Arial", Font.BOLD, 12));
        lblRefNum.setForeground(new Color(0, 25, 75));
        lblRefNum.setBounds(0, 25, 325, 20);
        pnlRefTint.add(lblRefNum);

        JLabel lblTime = new JLabel("Channel Mode: " + mode, SwingConstants.CENTER);
        lblTime.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTime.setForeground(Color.GRAY);
        lblTime.setBounds(0, 50, 325, 20);
        pnlRefTint.add(lblTime);

        dialog.add(pnlReceiptImage, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        pnlButtons.setBackground(new Color(82, 124, 174));

        JButton btnSave = new JButton("Save Receipt as Image");
        JButton btnClose = new JButton("Back");
        
        pnlButtons.add(btnSave);
        pnlButtons.add(btnClose);
        dialog.add(pnlButtons, BorderLayout.SOUTH);

        btnSave.addActionListener(ae -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File("Withdrawal_Receipt_" + txnId + ".png"));
            if (chooser.showSaveDialog(dialog) == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = new BufferedImage(pnlReceiptImage.getWidth(), pnlReceiptImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = img.getGraphics();
                    pnlReceiptImage.paint(g);
                    g.dispose();
                    ImageIO.write(img, "png", chooser.getSelectedFile());
                    JOptionPane.showMessageDialog(dialog, "Saved successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage());
                }
            }
        });

        btnClose.addActionListener(ae -> dialog.dispose());
        dialog.setVisible(true);
    }
}