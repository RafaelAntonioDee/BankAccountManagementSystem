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

public class DepositPanel extends JPanel implements ActionListener {

    private JLabel lblBalance, lblBalanceAmount, lblAmount, lblModeOfTransac, lblGuideTitle;
    private JTextArea txaGuideBody;
    private JButton btnDeposit, btnCancel;
    private JButton btnAmt500, btnAmt1000, btnAmt2500, btnAmt5000;
    private JTextField txtAmount;
    private JComboBox<String> cmbModeOfTransac;
    private JComboBox<String> cmbBankList;
    private JPanel pnlProcess, pnlGuidelines;
    private Account currentuser;
    private String[] bankList = {"Select Bank Provider", "BDO Unibank", "BPI (Bank of the Philippine Islands)", "Metrobank", "Landbank of the Philippines", "Security Bank"},
            modeOfTransaction = {"Select Mode", "Linked Bank Account", "Local Banks", "Global Banks & Partners", "Over-the-Counter Kiosk (Touchpay/Cliqq)", "Over-the-Counter Cashier"};
    public static Colors theme = Colors.LIGHT();

    double balance = 0;

    public DepositPanel(Account user) {
        this.currentuser = AppService.AccountFunctions.getUser(user.getEmail());
        balance = currentuser.getBalance();

        if (currentuser.getSystemTheme().equals("Light") || currentuser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }

        setBounds(0, 0, 837, 560);
        setBackground(theme.BACKGROUND);
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setLayout(null);

        lblBalance = new JLabel("Balance");
        lblBalance.setForeground(theme.TEXT_GRAY);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 18));
        lblBalance.setBounds(25, 25, 250, 35);

        lblBalance = new JLabel("Available Balance");
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 16));
        lblBalance.setForeground(theme.TEXT_GRAY);
        lblBalance.setBounds(40, 20, 755, 25);
        add(lblBalance);

        lblBalanceAmount = new JLabel("    ₱" + String.format("%.2f", balance));
        lblBalanceAmount.setFont(new Font("Arial", Font.BOLD, 22));
        lblBalanceAmount.setBounds(40, 50, 755, 55);
        lblBalanceAmount.setOpaque(true);
        lblBalanceAmount.setForeground(theme.TEXT_WHITE);
        lblBalanceAmount.setBackground(theme.PRIMARY_BLUE);
        add(lblBalanceAmount);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(25, 140, 375, 395);
        pnlProcess.setBackground(theme.PANELS_BACKGROUND);
        pnlProcess.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlProcess.setBounds(40, 130, 420, 395);
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblAmount = new JLabel("Deposit Amount");
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAmount.setBounds(25, 25, 325, 35);
        lblAmount.setForeground(theme.TEXT_BLACK);
        lblAmount.setFont(new Font("Arial", Font.BOLD, 15));
        lblAmount.setBounds(30, 20, 360, 25);
        pnlProcess.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(30, 50, 360, 35);
        txtAmount.setFont(new Font("Arial", Font.PLAIN, 15));
        txtAmount.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(theme.BORDER_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtAmount.setForeground(theme.TEXT_BLACK);
        txtAmount.setBackground(theme.BACKGROUND);
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
            btn.setBackground(theme.PRIMARY_BLUE);
            btn.setForeground(theme.TEXT_WHITE);
            btn.setBorder(BorderFactory.createLineBorder(theme.BORDER_GRAY));
            btn.setFocusPainted(false);
            btn.addActionListener(this);
            pnlProcess.add(btn);
        }

        lblModeOfTransac = new JLabel("Mode of Transaction");
        lblModeOfTransac.setFont(new Font("Arial", Font.PLAIN, 18));
        lblModeOfTransac.setBounds(25, 115, 325, 35);
        lblModeOfTransac.setForeground(theme.TEXT_BLACK);
        lblModeOfTransac.setFont(new Font("Arial", Font.BOLD, 15));
        lblModeOfTransac.setBounds(30, 150, 360, 25);
        pnlProcess.add(lblModeOfTransac);

        cmbModeOfTransac = new JComboBox<>(modeOfTransaction);
        cmbModeOfTransac.setBounds(30, 180, 360, 35);
        cmbModeOfTransac.setFocusable(false);
        cmbModeOfTransac.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbModeOfTransac.setForeground(theme.TEXT_BLACK);
        cmbModeOfTransac.setBackground(theme.BACKGROUND);

        pnlProcess.add(cmbModeOfTransac);

        cmbBankList = new JComboBox<>(bankList);
        cmbBankList.setBounds(30, 225, 360, 35);
        cmbBankList.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbBankList.setFocusable(false);
        cmbBankList.setForeground(theme.TEXT_BLACK);
        cmbBankList.setBackground(theme.BACKGROUND);
        cmbBankList.setVisible(false);
        pnlProcess.add(cmbBankList);

        cmbModeOfTransac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cmbModeOfTransac.getSelectedItem();
                if ("Local Banks".equals(selected)) {
                    cmbBankList.setVisible(true);
                } else {
                    cmbBankList.setVisible(false);
                    cmbBankList.setSelectedIndex(0);
                }
//                pnlProcess.repaint();
            }
        });

        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(160, 330, 110, 35);
        btnDeposit.setBackground(theme.PRIMARY_BLUE);
        btnDeposit.setForeground(theme.TEXT_WHITE);
        btnDeposit.setFont(new Font("Arial", Font.BOLD, 13));
        btnDeposit.setFocusPainted(false);
        btnDeposit.addActionListener(this);
        pnlProcess.add(btnDeposit);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(280, 330, 110, 35);
        btnCancel.setBackground(theme.CancelButton);
        btnCancel.setForeground(theme.TEXT_WHITE);
        btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        pnlProcess.add(btnCancel);

        pnlGuidelines = new JPanel();
        pnlGuidelines.setBounds(480, 130, 315, 395);
        pnlGuidelines.setBackground(theme.PANELS_BACKGROUND);
        pnlGuidelines.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlGuidelines.setLayout(null);
        add(pnlGuidelines);

        lblGuideTitle = new JLabel("Deposit Guidelines");
        lblGuideTitle.setFont(new Font("Arial", Font.BOLD, 25));
        lblGuideTitle.setForeground(theme.PRIMARY_BLUE);
        lblGuideTitle.setBounds(20, 30, 275, 25);
        pnlGuidelines.add(lblGuideTitle);

        String guideText = "Deposit Charges: \n"
                + "• Linked Bank Account: Free \n"
                + "• Local Banks: Free  \n"
                + "• Over-the-Counter Kiosk: ₱20.00  \n"
                + "• Over-the-Counter Cashier: ₱20.00  \n"
                + " \n"
                + "Processing Time:  \n"
                + "• Handled immediately in real-time. \n"
                + " \n"
                + "Notice:  \n"
                + " • Ensure your source funds are active  \n"
                + "before submitting. Deductions for  \n"
                + "processing fees are automatically  \n"
                + "handled upon deposit completion.";

        txaGuideBody = new JTextArea(guideText);
        txaGuideBody.setFont(new Font("Arial", Font.BOLD, 15));
        txaGuideBody.setBackground(theme.PANELS_BACKGROUND);
        txaGuideBody.setForeground(theme.TEXT_BLACK);
        txaGuideBody.setBounds(20, 80, 275, 300);
        txaGuideBody.setEditable(false);
//        txaGuideBody.setVerticalAlignment(SwingConstants.TOP);
        pnlGuidelines.add(txaGuideBody);
    }

    private String getNextTransactionID() {
        java.util.ArrayList<Objects.AccountTransactHistory> history = BalanceFunctions.getTransactions(currentuser.getEmail());
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
        if (e.getSource() == btnAmt500) {
            txtAmount.setText("500");
        } else if (e.getSource() == btnAmt1000) {
            txtAmount.setText("1000");
        } else if (e.getSource() == btnAmt2500) {
            txtAmount.setText("2500");
        } else if (e.getSource() == btnAmt5000) {
            txtAmount.setText("5000");
        }

        if (e.getSource() == btnDeposit) {
            try {
                String amountText = txtAmount.getText();
                String mode = (String) cmbModeOfTransac.getSelectedItem();

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter an amount!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (mode.equals("Select Mode")) {
                    JOptionPane.showMessageDialog(this, "Select transaction mode!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (mode.equals("Local Banks") && cmbBankList.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Please choose your local bank provider!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double fee = 0;
                if (mode.equals("Over-the-Counter Kiosk (Touchpay/Cliqq)") || mode.equals("Over-the-Counter Cashier")) {
                    fee = 20;
                }

                double netAmount = amount - fee;
                if (netAmount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount too small after fees!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sequentialTxnId = getNextTransactionID();

                balance = BalanceFunctions.deposit(currentuser.getEmail(), netAmount, sequentialTxnId);
                lblBalanceAmount.setText("    ₱" + String.format("%.2f", balance));

                String displayMode = mode;
                if (mode.equals("Local Banks")) {
                    displayMode = mode + " (" + cmbBankList.getSelectedItem() + ")";
                }

                showReceiptPopup(amount, fee, netAmount, displayMode, sequentialTxnId);
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
        cmbBankList.setSelectedIndex(0);
        cmbBankList.setVisible(false);
    }

    private void showReceiptPopup(double amount, double fee, double netAmount, String mode, String txnId) {
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

        JLabel lblSentVia = new JLabel("Received via Deposit", SwingConstants.CENTER);
        lblSentVia.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSentVia.setForeground(Color.GRAY);
        lblSentVia.setBounds(0, 45, 325, 25);
        pnlWhiteCard.add(lblSentVia);

        JSeparator sep1 = new JSeparator();
        sep1.setBounds(20, 85, 285, 10);
        pnlWhiteCard.add(sep1);

        JLabel lblAmtTitle = new JLabel("Gross Amount");
        lblAmtTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblAmtTitle.setBounds(25, 105, 110, 25);
        pnlWhiteCard.add(lblAmtTitle);

        JLabel lblAmtVal = new JLabel(String.format("%.2f", amount), SwingConstants.RIGHT);
        lblAmtVal.setFont(new Font("Arial", Font.BOLD, 14));
        lblAmtVal.setBounds(150, 105, 150, 25);
        pnlWhiteCard.add(lblAmtVal);

        JLabel lblFeeTitle = new JLabel("Fee Deducted");
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

        JLabel lblTotalTitle = new JLabel("Net Deposited");
        lblTotalTitle.setFont(new Font("Arial", Font.BOLD, 13));
        lblTotalTitle.setForeground(new Color(0, 25, 75));
        lblTotalTitle.setBounds(25, 200, 130, 25);
        pnlWhiteCard.add(lblTotalTitle);

        JLabel lblTotalVal = new JLabel("₱" + String.format("%.2f", netAmount), SwingConstants.RIGHT);
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
        lblTime.setFont(new Font("Arial", Font.PLAIN, 11));
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
            chooser.setSelectedFile(new File("Deposit_Receipt_" + txnId + ".png"));
            if (chooser.showSaveDialog(dialog) == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = new BufferedImage(pnlReceiptImage.getWidth(), pnlReceiptImage.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = img.getGraphics();
                    pnlReceiptImage.paint(g);
                    g.dispose();
                    ImageIO.write(img, "png", chooser.getSelectedFile());
                    JOptionPane.showMessageDialog(dialog, "Saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnClose.addActionListener(ae -> dialog.dispose());
        dialog.setVisible(true);
    }
}
