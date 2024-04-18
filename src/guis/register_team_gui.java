package guis;

import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class register_team_gui extends BaseFrame {
    public register_team_gui() {
        super("Register Team");

    }

    @Override
    protected void addGuiComponents() {
        JLabel label = new JLabel("Register Team");
        label.setBounds(0, 20, super.getWidth(), 40);
        label.setFont(new Font("Dialog", Font.BOLD, 32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label);

        JLabel teamNameLabel = new JLabel("Enter Team Name:");
        teamNameLabel.setBounds(20, 100, getWidth() - 40, 24);
        teamNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(teamNameLabel);

        JTextField teamNameField = new JTextField();
        teamNameField.setBounds(20, 130, getWidth() - 40, 40);
        teamNameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(teamNameField);

        JLabel managerNameLabel = new JLabel("Enter Manager Name:");
        managerNameLabel.setBounds(20, 190, getWidth() - 40, 24);
        managerNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(managerNameLabel);

        JTextField managerNameField = new JTextField();
        managerNameField.setBounds(20, 220, getWidth() - 40, 40);
        managerNameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(managerNameField);

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setBounds(20, 280, getWidth() - 40, 24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(20, 310, getWidth() - 40, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordField);

        JLabel rePasswordLabel = new JLabel("Re-enter Password:");
        rePasswordLabel.setBounds(20, 370, getWidth() - 40, 24);
        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(rePasswordLabel);

        JPasswordField rePasswordField = new JPasswordField();
        rePasswordField.setBounds(20, 400, getWidth() - 40, 40);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(rePasswordField);

        JButton saveTeamButton = new JButton("Save Team");
        saveTeamButton.setBounds(20, 460, getWidth() - 40, 40);
        saveTeamButton.setFont(new Font("Dialog", Font.BOLD, 20));
        saveTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teamName = teamNameField.getText().trim();
                String managerName = managerNameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(rePasswordField.getPassword());

                if (teamName.isEmpty() || managerName.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(register_team_gui.this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(register_team_gui.this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean success = MyJDBC.registerNewTeam(teamName, managerName, password);
                if (success) {
                    JOptionPane.showMessageDialog(register_team_gui.this, "Team successfully registered.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close registration form
                } else {
                    JOptionPane.showMessageDialog(register_team_gui.this, "Failed to register team. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(saveTeamButton);

        JLabel loginLink = new JLabel("<html><a href='#'>Already have an account? Login here.</a></html>");
        loginLink.setBounds(20, 510, getWidth() - 40, 30);
        loginLink.setFont(new Font("Dialog", Font.PLAIN, 16));
        loginLink.setHorizontalAlignment(SwingConstants.CENTER);
        loginLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                new team_login_gui().setVisible(true);
            }
        });
        add(loginLink);
    }
}
