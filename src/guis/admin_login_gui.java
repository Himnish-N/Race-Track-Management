package guis;

import db_objs.Admin;
import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin_login_gui extends BaseFrame {
    public admin_login_gui() {
        super("Admin Login");
    }

    @Override
    protected void addGuiComponents() {
        JLabel loginLabel = new JLabel("Admin Login");
        loginLabel.setBounds(0, 20, super.getWidth(), 40);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        JLabel adminNameLabel = new JLabel("Admin Name:");
        adminNameLabel.setBounds(50, 80, 150, 25);
        adminNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(adminNameLabel);

        // Admin Name Text Field
        JTextField adminNameField = new JTextField();
        adminNameField.setBounds(50, 110, 300, 25);
        adminNameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(adminNameField);

        JLabel passwordLabel = new JLabel("Admin Password:");
        passwordLabel.setBounds(50, 150, 150, 25);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 180, 300, 25);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 230, 100, 30);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String admin_name = adminNameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                Admin admin = MyJDBC.validateAdminLogin(admin_name, password);
                if (admin != null) {
                    admin_login_gui.this.dispose();
                    new admin_window_gui().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(admin_login_gui.this, "Login Failed");
                }
            }
        });
        add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(250, 230, 100, 30);
        backButton.setFont(new Font("Dialog", Font.BOLD, 20));
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window and go back to the previous window (if any)
                admin_login_gui.this.dispose();
                // Assuming there's another previous GUI class called HomeGUI
                new entry_gui().setVisible(true);
            }
        });
        add(backButton);
    }
}
