package FeaturesPanelsUI;

import AppService.AccountFunctions;
import DashboardUIDefault.Colors;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import DataService.AutoPaymentService;
import static FeaturesPanelsUI.DashboardPanel.theme;
import Objects.Account;
import Objects.AccountPersonalInformation;
import Objects.AutoPayment;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author rafra
 */
public class AutoPaymentPanel extends JPanel implements ActionListener {

    private JLabel lblAutoPayment, lblRecipientType, lblSubscription, lblRecipient, lblAmount, lblFrequency, lblDate, lblReceipt;
    private JButton btnEnableAuto, btnCancel, btnUnsub;
    private JPanel pnlAutoPayment, pnlScheduledPayment, pnlAutoPayListContent;
    private JScrollPane pnlAutoPayList;
    private JTextField txtRecipient, txtAmount;
    private String currentEmail;
    private Account currentuser;
    private AccountPersonalInformation currentuserInfo;
    private int ScheduledCount = 0, y = 15;
    private JComboBox<String> cmbRecipient, cmbSubscriptions, cmbFrequency, cmbDay, cmbMonth, cmbYear;
    private String[] frequency = {"Daily", "Monthly", "Quarterly", "Semi-Annually", "Annually"},
            recipient = {"Subscription", "Another User"},
            subscriptions = {"Spotify", "Apple Music", "YouTube Premium", "Netflix", "Disney+", "Canva Pro"};
    public static Colors theme = Colors.LIGHT();
    private double balance;

    // Money Display Formatter 
    DecimalFormat amountFormat = new DecimalFormat("#,##0.00");

    public AutoPaymentPanel(String email, Account user) {
        this.currentEmail = user.getEmail();
        AppService.AutoPaymentFunctions.processDuePayments();
        this.currentuser = AppService.AccountFunctions.getUser(user.getEmail());
        this.currentuserInfo = AppService.AccountFunctions.getUserInfo(user.getEmail());
        this.balance = currentuser.getBalance();

        this.currentEmail = currentuser.getEmail();
        if (currentuser.getSystemTheme().equals("Light") || currentuser.getSystemTheme().equals("System")) {
            theme = Colors.LIGHT();
        } else {
            theme = Colors.DARK();
        }

        setBounds(0, 0, 837, 560);
        setBackground(theme.BACKGROUND);
        setBorder(new LineBorder(theme.BORDER_GRAY));
        setLayout(null);

        // AUTO PAYMENT
        lblAutoPayment = new JLabel("Auto Payment Setup");
        lblAutoPayment.setForeground(theme.TEXT_GRAY);
        lblAutoPayment.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAutoPayment.setBounds(25, 15, 250, 35);
        add(lblAutoPayment);

        pnlAutoPayment = new JPanel();
        pnlAutoPayment.setBounds(25, 60, 375, 475);
        pnlAutoPayment.setBackground(theme.PANELS_BACKGROUND);
        pnlAutoPayment.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlAutoPayment.setLayout(null);
        add(pnlAutoPayment);

        lblRecipientType = new JLabel("Recipient Type: ");
        lblRecipientType.setBounds(25, 25, 325, 35);
        lblRecipientType.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRecipientType.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblRecipientType);

        cmbRecipient = new JComboBox<String>(recipient);
        cmbRecipient.setBounds(25, 60, 325, 35);
        cmbRecipient.setForeground(theme.TEXT_BLACK);
        cmbRecipient.setBackground(theme.PANELS_BACKGROUND);
        cmbRecipient.setFocusable(false);
        pnlAutoPayment.add(cmbRecipient);

        lblRecipient = new JLabel("Recipient (email):");
        lblRecipient.setBounds(25, 95, 325, 35);
        lblRecipient.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRecipient.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblRecipient);

        txtRecipient = new JTextField();
        txtRecipient.setBounds(25, 130, 325, 35);
        txtRecipient.setBackground(theme.BACKGROUND);
        txtRecipient.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(theme.BORDER_GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtRecipient.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(txtRecipient);

        lblSubscription = new JLabel("Choose a Subscription:");
        lblSubscription.setBounds(25, 95, 325, 35);
        lblSubscription.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSubscription.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblSubscription);

        cmbSubscriptions = new JComboBox<String>(subscriptions);
        cmbSubscriptions.setBounds(25, 130, 325, 35);
        cmbSubscriptions.setForeground(theme.TEXT_BLACK);
        cmbSubscriptions.setBackground(theme.PANELS_BACKGROUND);
        cmbSubscriptions.setFocusable(false);
        pnlAutoPayment.add(cmbSubscriptions);

        // INITIAL VISIBILITY STATE (Defaults to index 0: "Subscription")
        lblRecipient.setVisible(false);
        txtRecipient.setVisible(false);
        lblSubscription.setVisible(true);
        cmbSubscriptions.setVisible(true);

        cmbRecipient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cmbRecipient.getSelectedItem();
                if (selected != null && "Another User".equals(selected.trim())) {
                    lblSubscription.setVisible(false);
                    cmbSubscriptions.setVisible(false);

                    lblRecipient.setVisible(true);
                    txtRecipient.setVisible(true);
                } else {
                    lblRecipient.setVisible(false);
                    txtRecipient.setVisible(false);

                    lblSubscription.setVisible(true);
                    cmbSubscriptions.setVisible(true);
                }
                pnlAutoPayment.revalidate();
                pnlAutoPayment.repaint();
            }
        });

        lblAmount = new JLabel("Amount: ");
        lblAmount.setBounds(25, 165, 325, 35);
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAmount.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(25, 200, 325, 35);
        txtAmount.setBackground(theme.BACKGROUND);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(theme.BORDER_GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtAmount.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(txtAmount);

        lblFrequency = new JLabel("Frequency: ");
        lblFrequency.setBounds(25, 235, 325, 35);
        lblFrequency.setFont(new Font("Arial", Font.PLAIN, 18));
        lblFrequency.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblFrequency);

        cmbFrequency = new JComboBox<String>(frequency);
        cmbFrequency.setBounds(25, 270, 325, 35);
        cmbFrequency.setForeground(theme.TEXT_BLACK);
        cmbFrequency.setBackground(theme.PANELS_BACKGROUND);
        cmbFrequency.setFocusable(false);
        pnlAutoPayment.add(cmbFrequency);

        btnEnableAuto = new JButton("Set Up Payment");
        btnEnableAuto.setHorizontalAlignment(JButton.CENTER);
        btnEnableAuto.setBounds(120, 395, 125, 35);
        btnEnableAuto.setBackground(theme.PRIMARY_BLUE);
        btnEnableAuto.setForeground(theme.TEXT_WHITE);
        btnEnableAuto.setFocusPainted(false);
        btnEnableAuto.addActionListener(this);
        pnlAutoPayment.add(btnEnableAuto);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(265, 395, 85, 35);
        btnCancel.setBackground(theme.CancelButton);
        btnCancel.setForeground(theme.TEXT_WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        pnlAutoPayment.add(btnCancel);

        // RESIBO NG MGA AUTO PAYMENT
        lblReceipt = new JLabel("Enabled Auto Payments");
        lblReceipt.setForeground(theme.TEXT_GRAY);
        lblReceipt.setFont(new Font("Arial", Font.PLAIN, 18));
        lblReceipt.setBounds(425, 15, 325, 35);
        add(lblReceipt);

        pnlAutoPayList = new JScrollPane();
        pnlAutoPayList.setBounds(425, 60, 387, 475);
        pnlAutoPayList.setOpaque(true);
        pnlAutoPayList.setBorder(null);
        pnlAutoPayList.setBackground(theme.PANELS_BACKGROUND);
        pnlAutoPayList.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlAutoPayList.getVerticalScrollBar().setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, theme.SidePanel));

        pnlAutoPayList.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = theme.ThumbBar;
                trackColor = theme.TrackBar;
            }
        });

        add(pnlAutoPayList);
        pnlAutoPayList.getViewport().setOpaque(false);

        pnlAutoPayListContent = new JPanel();
        pnlAutoPayListContent.setLayout(null);
        pnlAutoPayListContent.setOpaque(false);
        pnlAutoPayListContent.setBackground(theme.PANELS_BACKGROUND);
        pnlAutoPayList.setViewportView(pnlAutoPayListContent);

        loadExistingAutoPayments();
    }

    public void loadExistingAutoPayments() {
        resetList();
        ArrayList<AutoPayment> list = AppService.AutoPaymentFunctions.getAllUserPayments(currentEmail);
        if (list == null) {
            return;
        }

        for (AutoPayment p : list) {
            displayScheduledPayment(
                    p.getAutoPayID(),
                    p.getPayee(),
                    p.getAmount(),
                    p.getFrequency(),
                    p.getDate()
            );
        }
    }

    public void displayScheduledPayment(String ID, String RecipientName, double Amount, String Frequency, LocalDate DueDate) {
        ScheduledCount++;

        pnlScheduledPayment = new JPanel();
        pnlScheduledPayment.setName(RecipientName);
        pnlScheduledPayment.setBounds(15, y, 357, 110);
        pnlScheduledPayment.setBackground(theme.PANELS_BACKGROUND);
        pnlScheduledPayment.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlScheduledPayment.setLayout(null);
        pnlAutoPayListContent.add(pnlScheduledPayment);

        JLabel lblItemRecipient = new JLabel();
        lblItemRecipient.setFont(new Font("Arial", Font.PLAIN, 16));
        lblItemRecipient.setBounds(10, 5, 337, 25);
        lblItemRecipient.setText(RecipientName);
        lblItemRecipient.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblItemRecipient);

        JLabel lblItemAmount = new JLabel();
        lblItemAmount.setBounds(10, 30, 337, 25);
        lblItemAmount.setText("Amount: " + amountFormat.format(Amount));
        lblItemAmount.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblItemAmount);

        JLabel lblItemFrequency = new JLabel();
        lblItemFrequency.setBounds(10, 55, 337, 25);
        lblItemFrequency.setText("Frequency: " + Frequency);
        lblItemFrequency.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblItemFrequency);

        String dueDateFormatted = DueDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

        JLabel lblItemDate = new JLabel();
        lblItemDate.setBounds(10, 80, 337, 25);
        lblItemDate.setText("Due Date: " + dueDateFormatted);
        lblItemDate.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblItemDate);

        btnUnsub = new JButton("Unsubscribe");
        btnUnsub.setBounds(240, 75, 107, 25);
        btnUnsub.putClientProperty("ID", ID);
        btnUnsub.setHorizontalAlignment(JButton.CENTER);
        btnUnsub.setBackground(theme.CancelButton);
        btnUnsub.setForeground(theme.TEXT_WHITE);
        btnUnsub.setFocusPainted(false);
        pnlScheduledPayment.add(btnUnsub);
        btnUnsub.addActionListener(this);

        int itemHeight = 115;
        int padding = 25;
        int height = (ScheduledCount * itemHeight) + padding;

        pnlAutoPayListContent.setPreferredSize(new Dimension(337, height));
        pnlAutoPayListContent.revalidate();
        pnlAutoPayListContent.repaint();

        y += 115;
    }

    public void resetList() {
        ScheduledCount = 0;
        y = 15;
        pnlAutoPayListContent.removeAll();
        pnlAutoPayListContent.revalidate();
        pnlAutoPayListContent.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEnableAuto) {
            String payee = "";
            String amountText = txtAmount.getText().trim();
            
            // IF "Subscription" IS CHOSEN
            if (!txtRecipient.isVisible()) {
                payee = (String) cmbSubscriptions.getSelectedItem();
            }
            // IF "Another User" IS CHOSEN
            else {
                String email = txtRecipient.getText().trim();
                payee = email;

                if (email.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter recipient email!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (email.equalsIgnoreCase(currentuser.getEmail())) {
                    JOptionPane.showMessageDialog(this, "You cannot transfer to yourself!", "Invalid", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Account receiver = AccountFunctions.getUser(email);
                if (receiver == null) {
                    JOptionPane.showMessageDialog(this, "Recipient account not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // DUPLICATE AUTO PAYMENT CHECKER
            ArrayList<AutoPayment> userPayments = AppService.AutoPaymentFunctions.getAllUserPayments(currentEmail);
            if (userPayments != null) {
                for (AutoPayment payment : userPayments) {
                    if (payment.getPayee().equalsIgnoreCase(payee)) {
                        JOptionPane.showMessageDialog(this, "An enabled auto-payment for this payee already exists.", "Invalid", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // AMOUNT VALIDATION
            double doubleAmount;
            try {
                doubleAmount = Double.parseDouble(amountText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (doubleAmount <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount!", "Invalid", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (balance < doubleAmount) {
                JOptionPane.showMessageDialog(this, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (doubleAmount > 50000) {
                JOptionPane.showMessageDialog(this, "Amount exceeds per transfer limit!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to set up this auto-payment?", "Transfer Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (choice == JOptionPane.YES_OPTION) {
                String frequency = String.valueOf(cmbFrequency.getSelectedItem());
                LocalDate dueDate = LocalDate.now();

                switch (frequency.toLowerCase()) {
                    case "daily":
                        dueDate = dueDate.plusDays(1);
                        break;
                    case "monthly":
                        dueDate = dueDate.plusMonths(1);
                        break;
                    case "quarterly":
                        dueDate = dueDate.plusMonths(3);
                        break;
                    case "semi-annually":
                        dueDate = dueDate.plusMonths(6);
                        break;
                    case "annually":
                        dueDate = dueDate.plusYears(1);
                        break;
                    default:
                        break;
                }

                AppService.AutoPaymentFunctions.createAutoPayment(
                        currentEmail,
                        payee,
                        doubleAmount,
                        frequency,
                        dueDate
                );

                AppService.AutoPaymentFunctions.processDuePayments();
                loadExistingAutoPayments();

                txtRecipient.setText("");
                txtAmount.setText("");
            }

        } else if (e.getSource() == btnCancel) {
            txtRecipient.setText("");
            txtAmount.setText("");

        } else if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            if (btn.getClientProperty("ID") == null) {
                return;
            }

            String id = (String) btn.getClientProperty("ID");

            if (!AppService.AutoPaymentFunctions.canUnsubscribe(id)) {
                JOptionPane.showMessageDialog(this, "A due payment has not been processed yet.", "Invalid", JOptionPane.ERROR_MESSAGE);
                return;
            }

            AppService.AutoPaymentFunctions.removeAutoPayment(id);
            loadExistingAutoPayments();
        }
    }
}
