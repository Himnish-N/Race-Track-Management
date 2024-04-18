package guis;

import db_objs.Driver;
import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class driver_login_gui extends BaseFrame{
    public driver_login_gui() {
        super("Driver Login");
    }

    @Override
    protected void addGuiComponents() {
        setLayout(null); // Continue using null layout for precise control over component placement.

        // Driver Login Label
        JLabel loginLabel = new JLabel("Driver Login");
        loginLabel.setBounds(0, 20, super.getWidth(), 40);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        // Driver Name Label
        JLabel driverNameLabel = new JLabel("Driver Name:");
        driverNameLabel.setBounds(50, 80, 150, 25);
        driverNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(driverNameLabel);

        // Driver Name Text Field
        JTextField driverNameField = new JTextField();
        driverNameField.setBounds(50, 110, 300, 25);
        driverNameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(driverNameField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 150, 150, 25);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(50, 180, 300, 25);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 230, 100, 30);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String driver_name = driverNameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                Driver driver = MyJDBC.validateDriverLogin(driver_name, password);

                if (driver != null) {
                    driver_login_gui.this.dispose();
                    driver_info_gui driver_info_gui = new driver_info_gui(driver);
                    driver_info_gui.setVisible(true);
                    JOptionPane.showMessageDialog(driver_info_gui, "login successful");
                } else {
                    JOptionPane.showMessageDialog(driver_login_gui.this, "Login Failed");
                }
            }
        });
        add(loginButton);

// Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(220, 230, 100, 30);
        backButton.setFont(new Font("Dialog", Font.BOLD, 20));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driver_login_gui.this.dispose();
                // Open previous window (if any)
                // For example:
                new entry_gui().setVisible(true);
            }
        });
        add(backButton);

        // Register Label with a clickable link
        JLabel registerLabel = new JLabel("<html><a href='#'>Don't have an account? Register here.</a></html>");
        registerLabel.setBounds(0, 410, getWidth() - 10, 30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                driver_login_gui.this.dispose();

                new register_driver_gui().setVisible(true);
            }
        });
        add(registerLabel);
    }
}
