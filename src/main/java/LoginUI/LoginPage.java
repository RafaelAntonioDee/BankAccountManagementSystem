/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginUI;

import DashboardUIDefault.MainDashboard;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author rafra
 */
public class LoginPage extends JFrame implements ActionListener {

    private JLabel lblLogin, lblTitle, lblEmail, lblPass, lblLogo, lblLine, lblOr;
    private JTextField txtEmail;
    private JPasswordField txtPass;
    private JButton btnLogin, btnSignup;

    public LoginPage() {
        //------------------------------- Frame Initialization -------------------------------
        ImageIcon BankIcon = new ImageIcon(getClass().getResource("/images/BankLogo.png"));
        ImageIcon ResizedBankIcon = new ImageIcon(BankIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

        setIconImage(BankIcon.getImage());
        setTitle("Bank Account Management System");
        setSize(375, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(243, 243, 243));

        //------------------------------- Title -------------------------------
        lblTitle = new JLabel("Bank Account Management");
        lblTitle.setBounds(12, 40, 335, 30);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(243, 243, 243));
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle);

        lblLogo = new JLabel(ResizedBankIcon);
        lblLogo.setBounds(145, 27, 60, 60);
        add(lblLogo);

        //------------------------------- Email -------------------------------
        lblEmail = new JLabel("Email");
        lblEmail.setBounds(38, 105, 40, 10);
        lblEmail.setOpaque(true);
        lblEmail.setBackground(new Color(243, 243, 243));
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 10));
        lblEmail.setHorizontalAlignment(JLabel.CENTER);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(32, 110, 295, 35);
        txtEmail.setOpaque(false);
        txtEmail.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtEmail);

        //------------------------------- Password -------------------------------
        lblPass = new JLabel("Password");
        lblPass.setBounds(38, 165, 60, 10);
        lblPass.setOpaque(true);
        lblPass.setBackground(new Color(243, 243, 243));
        lblPass.setFont(new Font("Arial", Font.PLAIN, 10));
        lblPass.setHorizontalAlignment(JLabel.CENTER);
        add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setBounds(32, 170, 295, 35);
        txtPass.setOpaque(false);
        txtPass.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        add(txtPass);

        //------------------------------- Buttons -------------------------------
        btnLogin = new JButton("Log In");
        btnLogin.setHorizontalAlignment(JButton.CENTER);
        btnLogin.setBounds(32, 230, 295, 35);
        btnLogin.setBackground(new Color(82, 124, 174));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(this);
        add(btnLogin);

        lblOr = new JLabel("or");
        lblOr.setBounds(165, 283, 25, 30);
        lblOr.setForeground(Color.LIGHT_GRAY);
        lblOr.setOpaque(true);
        lblOr.setBackground(new Color(243, 243, 243));
        lblOr.setHorizontalAlignment(JLabel.CENTER);
        add(lblOr);

        lblLine = new JLabel("___________________________________________");
        lblLine.setBounds(32, 277, 295, 30);
        lblLine.setForeground(Color.LIGHT_GRAY);
        lblLine.setHorizontalAlignment(JLabel.CENTER);
        add(lblLine);

        btnSignup = new JButton("Sign Up");
        btnSignup.setHorizontalAlignment(JButton.CENTER);
        btnSignup.setBounds(32, 330, 295, 35);
        btnSignup.setBackground(new Color(243, 243, 243));
        btnSignup.setFocusPainted(false);
        btnSignup.addActionListener(this);
        add(btnSignup);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            dispose();
            MainDashboard page = new MainDashboard();
            page.setVisible(true);
        } else if (e.getSource() == btnSignup) {
            dispose();
            RegisterPage page = new RegisterPage();
            page.setVisible(true);
        }
    }

}
