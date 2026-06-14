package FeaturesPanelsUI;

import Objects.Account;
import AppService.BalanceFunctions;
import AppService.AccountFunctions;
import static AppService.BalanceFunctions.addTransaction;
import DashboardUIDefault.Colors;
import static FeaturesPanelsUI.DepositPanel.theme;
import Objects.AccountPersonalInformation;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TransferPanel extends JPanel implements ActionListener {

    private JLabel lblBalance, lblBalanceAmount, lblAmount, lblEmail, lblName, lblGuideTitle;
    private JTextArea txaGuideBody;
    private JButton btnTransfer, btnCancel;
    private JButton btnAmt500, btnAmt1000, btnAmt2500, btnAmt5000;
    private JTextField txtAmount, txtEmail;
    private JPanel pnlProcess, pnlGuidelines;
    private Account currentUser;
    public static Colors theme = Colors.LIGHT();

    private double balance = 0;

    // Money Display Formatter
    DecimalFormat amountFormat = new DecimalFormat("#,##0.00");

    public TransferPanel(Account user) {
        this.currentUser = AppService.AccountFunctions.getUser(user.getEmail());
        this.balance = currentUser.getBalance();

        if (currentUser.getSystemTheme().equals("Light") || currentUser.getSystemTheme().equals("System")) {
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

        lblEmail = new JLabel("Transfer to (Recipient Email)");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
        lblEmail.setForeground(theme.TEXT_BLACK);
        lblEmail.setBounds(30, 15, 360, 25);
        pnlProcess.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(30, 45, 360, 35);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(theme.BORDER_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtEmail.setForeground(theme.TEXT_BLACK);
        txtEmail.setBackground(theme.BACKGROUND);
        pnlProcess.add(txtEmail);

        lblName = new JLabel("Transferring to: ---");
        lblName.setFont(new Font("Arial", Font.ITALIC, 13));
        lblName.setForeground(theme.PRIMARY_BLUE);
        lblName.setBounds(30, 85, 360, 20);
        pnlProcess.add(lblName);

        txtEmail.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                lookupRecipientName();
            }
        });
        txtEmail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    lookupRecipientName();
                }
            }
        });

        lblAmount = new JLabel("Transfer Amount");
        lblAmount.setFont(new Font("Arial", Font.BOLD, 15));
        lblAmount.setForeground(theme.TEXT_BLACK);
        lblAmount.setBounds(30, 120, 360, 25);
        pnlProcess.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(30, 150, 360, 35);
        txtAmount.setFont(new Font("Arial", Font.PLAIN, 15));
        txtAmount.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtAmount.setBackground(theme.BACKGROUND);
        txtAmount.setForeground(theme.TEXT_BLACK);
        pnlProcess.add(txtAmount);

        btnAmt500 = new JButton("₱500");
        btnAmt500.setBounds(30, 195, 80, 28);
        btnAmt1000 = new JButton("₱1000");
        btnAmt1000.setBounds(120, 195, 80, 28);
        btnAmt2500 = new JButton("₱2500");
        btnAmt2500.setBounds(210, 195, 80, 28);
        btnAmt5000 = new JButton("₱5000");
        btnAmt5000.setBounds(300, 195, 90, 28);

        JButton[] quickButtons = {btnAmt500, btnAmt1000, btnAmt2500, btnAmt5000};
        for (JButton btn : quickButtons) {
            btn.setBackground(theme.PRIMARY_BLUE);
            btn.setForeground(theme.TEXT_WHITE);
            btn.setBorder(BorderFactory.createLineBorder(theme.BORDER_GRAY));
            btn.setFocusPainted(false);
            btn.addActionListener(this);
            pnlProcess.add(btn);
        }

        btnTransfer = new JButton("Transfer");
        btnTransfer.setBounds(160, 330, 110, 35);
        btnTransfer.setBackground(theme.PRIMARY_BLUE);
        btnTransfer.setForeground(theme.TEXT_WHITE);
        btnTransfer.setFont(new Font("Arial", Font.BOLD, 13));
        btnTransfer.setFocusPainted(false);
        btnTransfer.addActionListener(this);
        pnlProcess.add(btnTransfer);

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

        lblGuideTitle = new JLabel("Transfer Guidelines");
        lblGuideTitle.setFont(new Font("Arial", Font.BOLD, 25));
        lblGuideTitle.setForeground(theme.PRIMARY_BLUE);
        lblGuideTitle.setBounds(20, 30, 275, 25);
        pnlGuidelines.add(lblGuideTitle);

        String guideText = "Security Notice: \n"
                + "• Direct Peer-to-Peer transfers are\n"
                + "processed immediately and cannot \n"
                + "be cancelled or reversed once it's\n"
                + "submitted. Always double check the\n"
                + "target email credential carefully.\n"
                + " \n"
                + "Processing Time:  \n"
                + "• Handled immediately in real-time. \n"
                + " \n"
                + "Notice:  \n"
                + "• The maximum transfer amount per\n"
                + "transaction is ₱ 50,000. \n"
                + "• Ensure your balance can cover the  \n"
                + "transfer amount prior to submitting.";

        txaGuideBody = new JTextArea(guideText);
        txaGuideBody.setFont(new Font("Arial", Font.BOLD, 15));
        txaGuideBody.setBackground(theme.PANELS_BACKGROUND);
        txaGuideBody.setForeground(theme.TEXT_BLACK);
        txaGuideBody.setBounds(20, 80, 275, 300);
        pnlGuidelines.add(txaGuideBody);
    }

    private void lookupRecipientName() {
        String email = txtEmail.getText().trim();
        
        if (email.isEmpty()) {
            lblName.setText("Transferring to: ---");
            lblName.setForeground(theme.PRIMARY_BLUE);
        }
        else if (AppService.AccountFunctions.getUser(email) == null) {
            lblName.setText("Account Not Found!");
            lblName.setForeground(new Color(185, 70, 75));
            return;
        }
        

        Account receiver = AccountFunctions.getUser(email);
        AccountPersonalInformation receiveInfo = AccountFunctions.getUserInfo(email);
        
        if (receiver != null) {
            lblName.setText("Transferring to: " + receiveInfo.getFirstName() + " " + receiveInfo.getLastName());
        }
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

        if (e.getSource() == btnTransfer) {
            try {
                String email = txtEmail.getText().trim();
                String amountText = txtAmount.getText().trim();

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter recipient email!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (email.equalsIgnoreCase(currentUser.getEmail())) {
                    JOptionPane.showMessageDialog(this, "You cannot transfer money to your own account!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter transfer amount!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double amount = Double.parseDouble(amountText);

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (balance < amount) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (amount > 50000) {
                    JOptionPane.showMessageDialog(this, "Amount exceeds per transfer limit!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Account receiver = AccountFunctions.getUser(email);
                if (receiver == null) {
                    JOptionPane.showMessageDialog(this, "Recipient account not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int choice = JOptionPane.showConfirmDialog(this, "Are you sure?", "Transfer Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    DecimalFormat amountFormat = new DecimalFormat("#,##0.00");

                    String sequentialTxnId = BalanceFunctions.getNextTransactionID();
                    BalanceFunctions.addTransaction(currentUser.getEmail(), "Transfer", LocalDate.now(), "- " + amountFormat.format(amount), sequentialTxnId);

                    String sequentialTxnId2 = BalanceFunctions.getNextTransactionID();
                    BalanceFunctions.addTransaction(receiver.getEmail(), "Received", LocalDate.now(), "+ " + amountFormat.format(amount), sequentialTxnId2);

                    System.out.println(sequentialTxnId + sequentialTxnId2);

                    BalanceFunctions.transfer(currentUser, receiver, amount);

                    balance = AccountFunctions.getUser(currentUser.getEmail()).getBalance();
                    lblBalanceAmount.setText("    ₱" + amountFormat.format(balance));

                    showReceiptPopup(amount, receiver, sequentialTxnId);
                    clearInputs();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid entry tracking numeric inputs!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == btnCancel) {
            clearInputs();
        }
    }

    private void clearInputs() {
        txtEmail.setText("");
        txtAmount.setText("");
        lblName.setText("Transferring to: ---");
        lblName.setForeground(theme.PRIMARY_BLUE);
    }

    private void showReceiptPopup(double amount, Account receiver, String txnId) {
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

        JLabel lblSentVia = new JLabel("Transfer Successful", SwingConstants.CENTER);
        lblSentVia.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSentVia.setForeground(Color.GRAY);
        lblSentVia.setBounds(0, 45, 325, 25);
        pnlWhiteCard.add(lblSentVia);

        JSeparator sep1 = new JSeparator();
        sep1.setBounds(20, 85, 285, 10);
        pnlWhiteCard.add(sep1);

        JLabel lblAmtTitle = new JLabel("Sent Amount");
        lblAmtTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblAmtTitle.setBounds(25, 110, 110, 25);
        pnlWhiteCard.add(lblAmtTitle);

        JLabel lblAmtVal = new JLabel("₱" + String.format("%.2f", amount), SwingConstants.RIGHT);
        lblAmtVal.setFont(new Font("Arial", Font.BOLD, 18));
        lblAmtVal.setForeground(new Color(0, 25, 75));
        lblAmtVal.setBounds(140, 105, 160, 30);
        pnlWhiteCard.add(lblAmtVal);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(20, 160, 285, 10);
        pnlWhiteCard.add(sep2);

        JLabel lblToTitle = new JLabel("Recipient Email");
        lblToTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        lblToTitle.setForeground(Color.GRAY);
        lblToTitle.setBounds(25, 180, 100, 20);
        pnlWhiteCard.add(lblToTitle);

        JLabel lblToVal = new JLabel(receiver.getEmail(), SwingConstants.RIGHT);
        lblToVal.setFont(new Font("Arial", Font.BOLD, 12));
        lblToVal.setBounds(130, 180, 170, 20);
        pnlWhiteCard.add(lblToVal);

        JLabel lblBalTitle = new JLabel("Remaining Balance");
        lblBalTitle.setFont(new Font("Arial", Font.PLAIN, 12));
        lblBalTitle.setForeground(Color.GRAY);
        lblBalTitle.setBounds(25, 210, 120, 20);
        pnlWhiteCard.add(lblBalTitle);

        balance = AccountFunctions.getUser(currentUser.getEmail()).getBalance();

        JLabel lblBalVal = new JLabel("₱" + String.format("%.2f", balance), SwingConstants.RIGHT);
        lblBalVal.setFont(new Font("Arial", Font.PLAIN, 12));
        lblBalVal.setBounds(150, 210, 150, 20);
        pnlWhiteCard.add(lblBalVal);

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

        JLabel lblTime = new JLabel("Channel: Peer-to-Peer Transfer", SwingConstants.CENTER);
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
            chooser.setSelectedFile(new File("Transfer_Receipt_" + txnId + ".png"));
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
