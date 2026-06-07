/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import DataService.AutoPaymentService;
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
    private int ScheduledCount = 0, y = 15;
    private JComboBox<String> cmbFrequency, cmbDay, cmbMonth, cmbYear;
    private String[] frequency = {"Monthly", "Quarterly", "Semi-Annually", "Annually"};

    public AutoPaymentPanel() {

        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        //---------------------------------AUTOPAYMENTd
        lblAutoPayment = new JLabel("Auto Payment Setup");
        lblAutoPayment.setForeground(Color.GRAY);
        lblAutoPayment.setFont(new Font("Arial", Font.PLAIN, 18));
        lblAutoPayment.setBounds(25, 15, 250, 35);
        add(lblAutoPayment);

        pnlAutoPayment = new JPanel();
        pnlAutoPayment.setBounds(25, 60, 375, 475);
        pnlAutoPayment.setBackground(new Color(243, 243, 243));
        pnlAutoPayment.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlAutoPayment.setLayout(null);
        add(pnlAutoPayment);

        lblRecipient = new JLabel("Payee: ");
        lblRecipient.setFont(new Font("Arial", Font.PLAIN, 18));
        lblRecipient.setBounds(25, 25, 325, 35);
        pnlAutoPayment.add(lblRecipient);

        txtRecipient = new JTextField();
        txtRecipient.setBounds(25, 60, 325, 35);
        txtRecipient.setOpaque(false);
        txtRecipient.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(txtRecipient);

        lblAmount = new JLabel("Amount: ");
        lblAmount.setBounds(25, 95, 325, 35);
        lblAmount.setFont(new Font("Arial", Font.PLAIN, 18));
        pnlAutoPayment.add(lblAmount);

        txtAmount = new JTextField();
        txtAmount.setBounds(25, 130, 325, 35);
        txtAmount.setOpaque(false);
        txtAmount.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(txtAmount);

        lblFrequency = new JLabel("Frequency: ");
        lblFrequency.setBounds(25, 165, 325, 35);
        lblFrequency.setFont(new Font("Arial", Font.PLAIN, 18));
        pnlAutoPayment.add(lblFrequency);

        cmbFrequency = new JComboBox<String>(frequency);
        cmbFrequency.setBounds(25, 200, 325, 35);
        cmbFrequency.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbFrequency.setFont(new Font("Arial", Font.PLAIN, 18));
        cmbFrequency.setOpaque(false);
        cmbFrequency.setFocusable(false);
        cmbFrequency.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlAutoPayment.add(cmbFrequency);

        btnEnableAuto = new JButton("Set Up Payment");
        btnEnableAuto.setHorizontalAlignment(JButton.CENTER);
        btnEnableAuto.setBounds(120, 395, 125, 35);
        btnEnableAuto.setBackground(new Color(82, 124, 174));
        btnEnableAuto.setForeground(Color.WHITE);
        btnEnableAuto.setFocusPainted(false);
        btnEnableAuto.addActionListener(this);
        pnlAutoPayment.add(btnEnableAuto);

        btnCancel = new JButton("Cancel");
        btnCancel.setHorizontalAlignment(JButton.CENTER);
        btnCancel.setBounds(265, 395, 85, 35);
        btnCancel.setBackground(Color.GRAY);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(this);
        pnlAutoPayment.add(btnCancel);

        //---------------------------------RECEIPT
        lblReceipt = new JLabel("Enabled Auto Payments");
        lblReceipt.setForeground(Color.GRAY);
        lblReceipt.setFont(new Font("Arial", Font.PLAIN, 18));
        lblReceipt.setBounds(425, 15, 325, 35);
        add(lblReceipt);

        pnlAutoPayList = new JScrollPane();
        pnlAutoPayList.setBounds(425, 60, 395, 475);
        pnlAutoPayList.setOpaque(true);
        pnlAutoPayList.setBorder(null);
        add(pnlAutoPayList);

        pnlAutoPayList.getViewport().setOpaque(false);

        pnlAutoPayListContent = new JPanel();
        pnlAutoPayListContent.setLayout(null);
        pnlAutoPayListContent.setOpaque(false);

        pnlAutoPayList.setViewportView(pnlAutoPayListContent);
    }

    public AutoPaymentPanel(String email) {
        this();
        this.currentEmail = email;
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
        pnlScheduledPayment.setBackground(new Color(243, 243, 243));
        pnlScheduledPayment.setBorder(new LineBorder(new Color(82, 124, 174)));
        pnlScheduledPayment.setLayout(null);
        pnlAutoPayListContent.add(pnlScheduledPayment);

        lblRecipient = new JLabel();
        lblRecipient.setFont(new Font("Arial", Font.PLAIN, 16));
        lblRecipient.setBounds(10, 5, 337, 25);
        lblRecipient.setText(RecipientName);
        pnlScheduledPayment.add(lblRecipient);

        lblAmount = new JLabel();
        lblAmount.setBounds(10, 30, 337, 25);
        lblAmount.setText("Amount: " + String.format("%.2f", Amount));
        pnlScheduledPayment.add(lblAmount);

        lblFrequency = new JLabel();
        lblFrequency.setBounds(10, 55, 337, 25);
        lblFrequency.setText("Frequency: " + Frequency);
        pnlScheduledPayment.add(lblFrequency);

        String dueDateFormatted = DueDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

        lblDate = new JLabel();
        lblDate.setBounds(10, 80, 337, 25);
        lblDate.setText("Due Date: " + dueDateFormatted);
        pnlScheduledPayment.add(lblDate);

        btnUnsub = new JButton("Unsubscribe");
        btnUnsub.setBounds(240, 75, 107, 25);
        btnUnsub.putClientProperty("ID", ID);
        btnUnsub.setHorizontalAlignment(JButton.CENTER);
        btnUnsub.setBackground(Color.GRAY);
        btnUnsub.setForeground(Color.WHITE);
        btnUnsub.setFocusPainted(false);
        pnlScheduledPayment.add(btnUnsub);
        btnUnsub.addActionListener(this);
        
        // FOR SCROLLPANE'S SPACING
        int itemHeight = 115;
        int padding = 25;
        int height = (ScheduledCount * itemHeight) + padding;
        
        pnlAutoPayListContent.setPreferredSize(new Dimension(357, height));
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
