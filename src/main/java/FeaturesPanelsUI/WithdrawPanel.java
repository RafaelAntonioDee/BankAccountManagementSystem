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
import static FeaturesPanelsUI.TransferPanel.theme;
import java.text.DecimalFormat;

public class WithdrawPanel extends JPanel implements ActionListener {

    private JLabel lblBalance, lblBalanceAmount, lblAmount, lblModeOfTransac, lblGuideTitle;
    private JTextArea txaGuideBody;
    private JButton btnWithdraw, btnCancel;
    private JButton btnAmt500, btnAmt1000, btnAmt2500, btnAmt5000;
    private JTextField txtAmount;
    private JComboBox cmbModeOfTransac;
    private JPanel pnlProcess, pnlGuidelines;
    private Account currentuser;
    private String[] modeOfTransac = {"Select Mode", "Linked Bank Account", "Over-the-Counter"};
    public static Colors theme = Colors.LIGHT();

    double balance = 0;
    
    // Money Display Formatter
    DecimalFormat amountFormat = new DecimalFormat("#,##0.00");

    public WithdrawPanel(Account user) {
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

        lblBalance = new JLabel("Available Balance");
        lblBalance.setForeground(theme.TEXT_GRAY);
        lblBalance.setFont(new Font("Arial", Font.PLAIN, 16));
        lblBalance.setBounds(40, 20, 755, 25);
        add(lblBalance);

        lblBalanceAmount = new JLabel("    ₱" + amountFormat.format(balance));
        lblBalanceAmount.setForeground(theme.TEXT_WHITE);
        lblBalanceAmount.setFont(new Font("Arial", Font.BOLD, 22));
        lblBalanceAmount.setBounds(40, 50, 755, 55);
        lblBalanceAmount.setOpaque(true);
        lblBalanceAmount.setBackground(theme.PRIMARY_BLUE);
        add(lblBalanceAmount);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(40, 130, 420, 395);
        pnlProcess.setBackground(theme.PANELS_BACKGROUND);
        pnlProcess.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblAmount = new JLabel("Withdraw Amount");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 15));
        lblAmount.setForeground(theme.TEXT_BLACK);
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
        lblModeOfTransac.setFont(new Font("Arial", Font.BOLD, 15));
        lblModeOfTransac.setForeground(theme.TEXT_BLACK);
        lblModeOfTransac.setBounds(30, 150, 360, 25);
        pnlProcess.add(lblModeOfTransac);

        cmbModeOfTransac = new JComboBox(modeOfTransac);
        cmbModeOfTransac.setBounds(30, 180, 360, 35);
        cmbModeOfTransac.setBackground(theme.BACKGROUND);
        cmbModeOfTransac.setForeground(theme.TEXT_BLACK);
        cmbModeOfTransac.setFocusable(false);
        cmbModeOfTransac.setFont(new Font("Arial", Font.PLAIN, 14));
        pnlProcess.add(cmbModeOfTransac);

        btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(160, 330, 110, 35);
        btnWithdraw.setBackground(theme.PRIMARY_BLUE);
        btnWithdraw.setForeground(theme.TEXT_WHITE);
        btnWithdraw.setFont(new Font("Arial", Font.BOLD, 13));
        btnWithdraw.setFocusPainted(false);
        btnWithdraw.addActionListener(this);
        pnlProcess.add(btnWithdraw);

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

        lblGuideTitle = new JLabel("Withdrawal Guidelines");
        lblGuideTitle.setFont(new Font("Arial", Font.BOLD, 25));
        lblGuideTitle.setForeground(theme.PRIMARY_BLUE);
        lblGuideTitle.setBounds(20, 30, 275, 25);
        pnlGuidelines.add(lblGuideTitle);

        String guideText = "Withdrawal Charges: \n"
                + "• Linked Bank Account: Free \n"
                + "• Over-the-Counter: ₱20.00  \n"
                + " \n"
                + "Processing Time:  \n"
                + "• Handled immediately in real-time. \n"
                + " \n"
                + "Notice:  \n"
                + "• The maximum withdrawal amount per\n"
                + "transaction is ₱ 100,000. \n"
                + "• Ensure your balance can cover  \n"
                + "both the withdrawal amount and the  \n"
                + "charge fee before submitting.  \n";

        txaGuideBody = new JTextArea(guideText);
        txaGuideBody.setFont(new Font("Arial", Font.BOLD, 15));
        txaGuideBody.setBackground(theme.PANELS_BACKGROUND);
        txaGuideBody.setForeground(theme.TEXT_BLACK);
        txaGuideBody.setBounds(20, 80, 275, 300);
        txaGuideBody.setEditable(false);
        pnlGuidelines.add(txaGuideBody);
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

        if (e.getSource() == btnWithdraw) {
            try {
                String amountText = txtAmount.getText();
                String mode = (String) cmbModeOfTransac.getSelectedItem();

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter amount!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (mode.equals("Select Mode")) {
                    JOptionPane.showMessageDialog(this, "Select transaction mode!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount = Double.parseDouble(amountText);
                
                if (amount > 10000) {
                    JOptionPane.showMessageDialog(this, "Amount exceeds per withdrawal limit!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                
                double fee = 0;
                
                if (mode.equals("Over-the-Counter")) {
                    fee = 20;
                }
                

                double totalDeduction = amount + fee;
                if (totalDeduction > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sequentialTxnId = BalanceFunctions.getNextTransactionID();

                balance = BalanceFunctions.withdraw(currentuser.getEmail(), totalDeduction, sequentialTxnId);
                lblBalanceAmount.setText("    ₱" + amountFormat.format(balance));

                showReceiptPopup(amount, fee, totalDeduction, mode, sequentialTxnId);
                clearInputs();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!", "Invalid", JOptionPane.ERROR_MESSAGE);
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

        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        Image scaledImage = BankIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        JLabel lblCheck = new JLabel(new ImageIcon(scaledImage), SwingConstants.CENTER);
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

        JLabel lblRefNum = new JLabel("Reference ID: " + txnId, SwingConstants.CENTER);
        lblRefNum.setFont(new Font("Arial", Font.BOLD, 12));
        lblRefNum.setForeground(new Color(0, 25, 75));
        lblRefNum.setBounds(19, 25, 240, 20);
        pnlRefTint.add(lblRefNum);

        JLabel lblCopy = new JLabel("[Copy]", SwingConstants.CENTER);
        lblCopy.setFont(new Font("Arial", Font.BOLD, 11));
        lblCopy.setForeground(theme.PRIMARY_BLUE);
        lblCopy.setBounds(217, 24, 55, 20);
        lblCopy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        lblCopy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                java.awt.datatransfer.StringSelection selection = new java.awt.datatransfer.StringSelection(txnId);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
                
                JOptionPane.showMessageDialog(dialog, "Reference ID copied to clipboard!", "Copied", JOptionPane.INFORMATION_MESSAGE);
                 
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                lblCopy.setForeground(new Color(0, 25, 75));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblCopy.setForeground(theme.PRIMARY_BLUE);
            }
        });
        pnlRefTint.add(lblCopy);
        
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
