/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginUI;

import AppService.AccountFunctions;
import LoginUI.LoginPage;
import Objects.UserAccount;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class RegisterPage extends JFrame implements ActionListener {

    private JLabel lblTitle, lblLine, lblFName, lblLName, lblEmail, lblPhoneNo, lblAddress, lblPersonal, lblNewPass, lblBirthday, lblDay, lblMonth, lblYear;
    private JTextField txtFName, txtLName, txtEmail, txtPhoneNo, txtAddress;
    private JComboBox cmbBirthDay, cmbBirthMonth, cmbBirthYear;
    private JButton btnSignup, btnLogin;
    private final JPasswordField txtNewPass;

    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "June", 
                       "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
 
    
    RegisterPage() {    
        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));

        setIconImage(BankIcon.getImage());
        setTitle("Bank Account Management System");
        setSize(375, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(243, 243, 243));

        //------------------------------- Title -------------------------------
        lblTitle = new JLabel("Sign Up");
        lblTitle.setBounds(32, 20, 100, 30);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitle);

        btnLogin = new JButton("<- Log In");
        btnLogin.setBounds(252, 20, 90, 20);
        btnLogin.setForeground(new Color(82, 124, 174));
        btnLogin.setBorderPainted(false);
        btnLogin.setFocusPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setHorizontalAlignment(JLabel.RIGHT);
        btnLogin.addActionListener(this);
        add(btnLogin);

        lblLine = new JLabel("________________________________________________");
        lblLine.setBounds(22, 40, 315, 30);
        lblLine.setForeground(new Color(82, 124, 174));
        lblLine.setHorizontalAlignment(JLabel.CENTER);
        add(lblLine);

        //------------------------------- Fill Up -------------------------------
        //---------------------------------EMAIL
        lblEmail = new JLabel("Email");
        lblEmail.setBounds(38, 80, 40, 10);
        lblEmail.setOpaque(true);
        lblEmail.setBackground(new Color(243, 243, 243));
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 10));
        lblEmail.setHorizontalAlignment(JLabel.CENTER);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(32, 85, 295, 35);
        txtEmail.setOpaque(false);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtEmail);

        //---------------------------------PASSWORD
        lblNewPass = new JLabel("New Password");
        lblNewPass.setBounds(38, 130, 83, 10);
        lblNewPass.setOpaque(true);
        lblNewPass.setBackground(new Color(243, 243, 243));
        lblNewPass.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewPass.setHorizontalAlignment(JLabel.CENTER);
        add(lblNewPass);

        txtNewPass = new JPasswordField();
        txtNewPass.setBounds(32, 135, 295, 35);
        txtNewPass.setOpaque(false);
        txtNewPass.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtNewPass);

        lblPersonal = new JLabel("Personal Information");
        lblPersonal.setBounds(27, 186, 110, 10);
        lblPersonal.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPersonal.setForeground(Color.GRAY);
        lblPersonal.setOpaque(true);
        lblPersonal.setBackground(new Color(243, 243, 243));
        add(lblPersonal);

        lblLine = new JLabel("___________________________________________");
        lblLine.setBounds(32, 178, 324, 15);
        lblLine.setForeground(Color.LIGHT_GRAY);
        lblLine.setHorizontalAlignment(JLabel.CENTER);
        add(lblLine);

        //---------------------------------NAME
        lblFName = new JLabel("First Name");
        lblFName.setBounds(38, 210, 65, 10);
        lblFName.setOpaque(true);
        lblFName.setBackground(new Color(243, 243, 243));
        lblFName.setFont(new Font("Arial", Font.PLAIN, 10));
        lblFName.setHorizontalAlignment(JLabel.CENTER);
        add(lblFName);

        txtFName = new JTextField();
        txtFName.setBounds(32, 215, 140, 35);
        txtFName.setOpaque(false);
        txtFName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtFName);

        lblLName = new JLabel("Last Name");
        lblLName.setBounds(193, 210, 62, 10);
        lblLName.setOpaque(true);
        lblLName.setBackground(new Color(243, 243, 243));
        lblLName.setFont(new Font("Arial", Font.PLAIN, 10));
        lblLName.setHorizontalAlignment(JLabel.CENTER);
        add(lblLName);

        txtLName = new JTextField();
        txtLName.setBounds(187, 215, 140, 35);
        txtLName.setOpaque(false);
        txtLName.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtLName);

        //---------------------------------ADRESS
        lblAddress = new JLabel("Address");
        lblAddress.setBounds(38, 260, 49, 10);
        lblAddress.setOpaque(true);
        lblAddress.setBackground(new Color(243, 243, 243));
        lblAddress.setFont(new Font("Arial", Font.PLAIN, 10));
        lblAddress.setHorizontalAlignment(JLabel.CENTER);
        add(lblAddress);

        txtAddress = new JTextField();
        txtAddress.setBounds(32, 265, 295, 35);
        txtAddress.setOpaque(false);
        txtAddress.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtAddress);

        //---------------------------------PHONE NUMBER
        lblPhoneNo = new JLabel("Phone Number");
        lblPhoneNo.setBounds(38, 310, 80, 10);
        lblPhoneNo.setOpaque(true);
        lblPhoneNo.setBackground(new Color(243, 243, 243));
        lblPhoneNo.setFont(new Font("Arial", Font.PLAIN, 10));
        lblPhoneNo.setHorizontalAlignment(JLabel.CENTER);
        add(lblPhoneNo);

        txtPhoneNo = new JTextField();
        txtPhoneNo.setBounds(32, 315, 295, 35);
        txtPhoneNo.setOpaque(false);
        txtPhoneNo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtPhoneNo);

        //---------------------------------BIRTHDAY
        lblBirthday = new JLabel("Birthday");
        lblBirthday.setBounds(32, 357, 110, 20);
        lblBirthday.setFont(new Font("Arial", Font.PLAIN, 11));
        lblBirthday.setForeground(Color.GRAY);
        lblBirthday.setOpaque(false);
        add(lblBirthday);

        lblMonth = new JLabel("Month");
        lblMonth.setBounds(38, 375, 35, 10);
        lblMonth.setOpaque(true);
        lblMonth.setBackground(new Color(243, 243, 243));
        lblMonth.setFont(new Font("Arial", Font.PLAIN, 10));
        lblMonth.setHorizontalAlignment(JLabel.CENTER);
        add(lblMonth);

        cmbBirthMonth = new JComboBox(months);
        cmbBirthMonth.setBounds(32, 380, 85, 35);
        cmbBirthMonth.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbBirthMonth.setOpaque(false);
        cmbBirthMonth.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(cmbBirthMonth);
 
        lblDay = new JLabel("Day");
        lblDay.setBounds(143, 375, 25, 10);
        lblDay.setOpaque(true);
        lblDay.setBackground(new Color(243, 243, 243));
        lblDay.setFont(new Font("Arial", Font.PLAIN, 10));
        lblDay.setHorizontalAlignment(JLabel.CENTER);
        add(lblDay);

        cmbBirthDay = new JComboBox();
        cmbBirthDay.setBounds(137, 380, 85, 35);
        cmbBirthDay.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbBirthDay.setOpaque(false);
        cmbBirthDay.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(cmbBirthDay);

        lblYear = new JLabel("Year");
        lblYear.setBounds(248, 375, 30, 10);
        lblYear.setOpaque(true);
        lblYear.setBackground(new Color(243, 243, 243));
        lblYear.setFont(new Font("Arial", Font.PLAIN, 10));
        lblYear.setHorizontalAlignment(JLabel.CENTER);
        add(lblYear);

        cmbBirthYear = new JComboBox();
        cmbBirthYear.setBounds(242, 380, 85, 35);
        cmbBirthYear.setUI(new javax.swing.plaf.basic.BasicComboBoxUI());
        cmbBirthYear.setOpaque(false);
        cmbBirthYear.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(cmbBirthYear);

        btnSignup = new JButton("Sign Up");
        btnSignup.setHorizontalAlignment(JButton.CENTER);
        btnSignup.setBounds(32, 430, 295, 35);
        btnSignup.setBackground(new Color(82, 124, 174));
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setFocusPainted(false);
        btnSignup.addActionListener(this);
        add(btnSignup);
 
        //Days Array
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
        days[i] = String.valueOf(i + 1);
        }for (String day : days) {
        cmbBirthDay.addItem(day);}
        
        //Years Array
        int startYear = 2026;
        int endYear = 1950;
        String[] years = new String[startYear - endYear + 1];
        for (int i = 0; i < years.length; i++) {
        years[i] = String.valueOf(startYear - i);
        }for (String year : years) {
        cmbBirthYear.addItem(year);}}

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnSignup) {
      
        String fName = txtFName.getText();
        String lName = txtLName.getText();
        String email = txtEmail.getText();
        String addr = txtAddress.getText();
        String phone = txtPhoneNo.getText();
        String pass = new String(txtNewPass.getPassword());
        String bday = cmbBirthMonth.getSelectedItem() + " " + cmbBirthDay.getSelectedItem() + ", " + cmbBirthYear.getSelectedItem();

        // Validate Email
        if (!AccountFunctions.validateEmail(email)) {
            JOptionPane.showMessageDialog(this, "Valid @gmail.com address required.");
            return;
        }
        // Validate Phone
        if (!AccountFunctions.validatePhoneNumber(phone)) {
            JOptionPane.showMessageDialog(this, "Phone number must be digits only (max 11).");
            return;
        }
        // Register
        AccountFunctions.registerUser(fName, lName, email, addr, phone, pass, bday);
        JOptionPane.showMessageDialog(this, "Account Created Successfully!");

        // Navigate back to Login
        new LoginPage().setVisible(true);
        this.dispose();
    }
    if (e.getSource()==btnLogin){
        new LoginPage().setVisible(true);
        this.dispose(); 
    }
}}