/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FeaturesPanelsUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafra
 */
public class TransactionsPanel extends JPanel implements ActionListener {

    private JLabel lblDateRange, lblTransactions;
    private JPanel pnlProcess;
    private JComboBox cmbDateRange;
    private JTable TransactionsTable;
    private JScrollPane scroll;

    public TransactionsPanel() {
        setBounds(0, 0, 837, 560);
        setBackground(new Color(243, 243, 243));
        setBorder(new LineBorder(Color.LIGHT_GRAY));
        setLayout(null);

        pnlProcess = new JPanel();
        pnlProcess.setBounds(25, 25, 787, 510);
        pnlProcess.setBackground(new Color(243, 243, 243));
        pnlProcess.setBorder(new LineBorder(Color.LIGHT_GRAY));
        pnlProcess.setLayout(null);
        add(pnlProcess);

        lblDateRange = new JLabel("Date Range");
        lblDateRange.setFont(new Font("Arial", Font.PLAIN, 18));
        lblDateRange.setBounds(25, 25, 737, 35);
        pnlProcess.add(lblDateRange);

        cmbDateRange = new JComboBox();
        cmbDateRange.setBounds(25, 65, 300, 35);
        cmbDateRange.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbDateRange.setOpaque(false);
        cmbDateRange.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        pnlProcess.add(cmbDateRange);

        lblTransactions = new JLabel("Transactions");
        lblTransactions.setFont(new Font("Arial", Font.PLAIN, 18));
        lblTransactions.setBounds(25, 125, 737, 35);
        pnlProcess.add(lblTransactions);

        String[] columns = {"Transaction ID", "Transaction", "Date", "Balance"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        TransactionsTable = new JTable(model);
        TransactionsTable.getTableHeader().setReorderingAllowed(false);
        scroll = new JScrollPane(TransactionsTable);
        scroll.setBounds(25, 165, 737, 320);
        pnlProcess.add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
