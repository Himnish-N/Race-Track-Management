package guis;

import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class register_driver_gui extends BaseFrame {
    public register_driver_gui() {
        super("Register Driver");
    }

    @Override
    protected void addGuiComponents() {
        JLabel label = new JLabel("Register Driver");
        label.setBounds(0, 20, super.getWidth(), 40);
        label.setFont(new Font("Dialog", Font.BOLD, 32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        JLabel driverNameLabel = new JLabel("Enter Driver Name:");
        driverNameLabel.setBounds(20, 80, getWidth() - 40, 24);
        driverNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(driverNameLabel);

        JTextField driverNameField = new JTextField();
        driverNameField.setBounds(20, 120, getWidth() - 40, 40);
        driverNameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(driverNameField);

        JLabel teamNameLabel = new JLabel("Enter Team Name:");
        teamNameLabel.setBounds(20, 180, getWidth() - 40, 24);
        teamNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(teamNameLabel);

        JTextField teamNameField = new JTextField();
        teamNameField.setBounds(20, 220, getWidth() - 40, 40);
        teamNameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(teamNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 280, getWidth() - 40, 24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 320, getWidth() - 40, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordField);

        JLabel rePasswordLabel = new JLabel("Re-enter Password:");
        rePasswordLabel.setBounds(20, 380, getWidth() - 40, 24);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(rePasswordLabel);

        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(20, 420, getWidth() - 40, 40);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(rePasswordField);

        JButton saveDriverButton = new JButton("Save Driver");
        saveDriverButton.setBounds(20, 480, getWidth() - 40, 40);
        saveDriverButton.setFont(new Font("Dialog", Font.BOLD, 20));
        saveDriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String driverName = driverNameField.getText().trim();
                String teamName = teamNameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(rePasswordField.getPassword());

                if (driverName.isEmpty() || teamName.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(register_driver_gui.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(register_driver_gui.this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = MyJDBC.registerNewDriver(driverName, teamName, password);
                if (success) {
                    JOptionPane.showMessageDialog(register_driver_gui.this, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    register_driver_gui.this.dispose(); // Close the registration window
                    // Optionally open the login window or main application window here
                } else {
                    JOptionPane.showMessageDialog(register_driver_gui.this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        add(saveDriverButton);

        JLabel loginLink = new JLabel("<html><a href='#'>Already have an account? Login here.</a></html>");
        loginLink.setBounds(0, 520, getWidth(), 30); // Adjusted bounds to span the full width of the form
        loginLink.setFont(new Font("Dialog", Font.PLAIN, 16));
        loginLink.setHorizontalAlignment(SwingConstants.CENTER);
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                register_driver_gui.this.dispose();

                new driver_login_gui().setVisible(true);
            }
        });
        add(loginLink);
    }
}
