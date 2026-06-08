/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author rafra
 */
public class AutoPaymentPanel extends JPanel implements ActionListener {

    private JLabel lblAutoPayment, lblRecipient, lblAmount, lblFrequency, lblDate, lblReceipt;
    private JButton btnEnableAuto, btnCancel, btnUnsub;
    private JPanel pnlAutoPayment, pnlScheduledPayment, pnlAutoPayListContent;
    private JScrollPane pnlAutoPayList;
    private JTextField txtRecipient, txtAmount;
    private String currentEmail;
    private Account currentuser;
    private AccountPersonalInformation currentuserInfo;
    private int ScheduledCount = 0, y = 15;
    private JComboBox<String> cmbFrequency, cmbDay, cmbMonth, cmbYear;
    private String[] frequency = {"Monthly", "Quarterly", "Semi-Annually", "Annually"};
    public static Colors theme = Colors.LIGHT();

    public AutoPaymentPanel(String email, Account user) {
        this.currentuser = AppService.AccountFunctions.getUser(user.getEmail());
        this.currentuserInfo = AppService.AccountFunctions.getUserInfo(user.getEmail());
        
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

        //---------------------------------AUTOPAYMENTd
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

        lblRecipient = new JLabel("Payee: ");
        lblRecipient.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRecipient.setBounds(25, 25, 325, 35);
        lblRecipient.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblRecipient);

        txtRecipient = new JTextField();
        txtRecipient.setBounds(25, 60, 325, 35);
        txtRecipient.setOpaque(false);
        txtRecipient.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(theme.BORDER_GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtRecipient.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(txtRecipient);

        lblAmount = new JLabel("Amount: ");
        lblAmount.setBounds(25, 95, 325, 35);
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAmount.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(25, 130, 325, 35);
        txtAmount.setOpaque(false);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtAmount.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(txtAmount);

        lblFrequency = new JLabel("Frequency: ");
        lblFrequency.setBounds(25, 165, 325, 35);
        lblFrequency.setFont(new Font("Arial", Font.PLAIN, 18));
        lblFrequency.setForeground(theme.TEXT_BLACK);
        pnlAutoPayment.add(lblFrequency);

        cmbFrequency = new JComboBox<String>(frequency);
        cmbFrequency.setBounds(25, 200, 325, 35);
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

        //---------------------------------RECEIPT
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

    // RECEIPT
    public void displayScheduledPayment(String ID, String RecipientName, double Amount, String Frequency, LocalDate DueDate) {
        ScheduledCount++;

        pnlScheduledPayment = new JPanel();
        pnlScheduledPayment.setName(RecipientName);
        pnlScheduledPayment.setBounds(15, y, 357, 110);
        pnlScheduledPayment.setBackground(theme.PANELS_BACKGROUND);
        pnlScheduledPayment.setBorder(new LineBorder(theme.BORDER_GRAY));
        pnlScheduledPayment.setLayout(null);
        pnlAutoPayListContent.add(pnlScheduledPayment);

        lblRecipient = new JLabel();
        lblRecipient.setFont(new Font("Arial", Font.PLAIN, 16));
        lblRecipient.setBounds(10, 5, 337, 25);
        lblRecipient.setText(RecipientName);
        lblRecipient.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblRecipient);

        lblAmount = new JLabel();
        lblAmount.setBounds(10, 30, 337, 25);
        lblAmount.setText("Amount: " + String.format("%.2f", Amount));
        lblAmount.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblAmount);

        lblFrequency = new JLabel();
        lblFrequency.setBounds(10, 55, 337, 25);
        lblFrequency.setText("Frequency: " + Frequency);
        lblFrequency.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblFrequency);

        String dueDateFormatted = DueDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

        lblDate = new JLabel();
        lblDate.setBounds(10, 80, 337, 25);
        lblDate.setText("Due Date: " + dueDateFormatted);
        lblDate.setForeground(theme.TEXT_BLACK);
        pnlScheduledPayment.add(lblDate);

        btnUnsub = new JButton("Unsubscribe");
        btnUnsub.setBounds(240, 75, 107, 25);
        btnUnsub.putClientProperty("ID", ID);
        btnUnsub.setHorizontalAlignment(JButton.CENTER);
        btnUnsub.setBackground(theme.CancelButton);
        btnUnsub.setForeground(theme.TEXT_WHITE);
        btnUnsub.setFocusPainted(false);
        pnlScheduledPayment.add(btnUnsub);
        btnUnsub.addActionListener(this);

        // FOR SCROLLPANE'S SPACING
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
            String payee = txtRecipient.getText().trim();
            if (!payee.isEmpty()) {
                try {
                    double amount = Double.parseDouble(txtAmount.getText().trim());

                    String frequency = String.valueOf(cmbFrequency.getSelectedItem());
                    LocalDate dueDate = LocalDate.now();

                    switch (frequency.toLowerCase()) {
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

                    String dueDateFormatted = dueDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

                    AppService.AutoPaymentFunctions.createAutoPayment(
                            currentEmail,
                            payee,
                            amount,
                            frequency,
                            LocalDate.parse(dueDateFormatted, format)
                    );

                    loadExistingAutoPayments();

                    txtRecipient.setText("");
                    txtAmount.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid amount!");
                }
            }
        } else if (e.getSource() == btnCancel) {
            txtRecipient.setText("");
            txtAmount.setText("");
        } else {
            JButton btn = (JButton) e.getSource();

            String id = (String) btn.getClientProperty("ID");

            AppService.AutoPaymentFunctions.removeAutoPay(id);

            loadExistingAutoPayments();

            System.out.println(id);
        }
    }
}
