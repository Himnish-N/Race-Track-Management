package guis;

import db_objs.MyJDBC;
import db_objs.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class team_login_gui extends BaseFrame{
    public team_login_gui() {
        super("Team Login");
    }

    @Override
    protected void addGuiComponents() {
        setLayout(null); // Continue using null layout for precise control over component placement.

        // Team Login Label
        JLabel loginLabel = new JLabel("Team Login");
        loginLabel.setBounds(0, 20, super.getWidth(), 40);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        // Team Name Label
        JLabel teamNameLabel = new JLabel("Team Name:");
        teamNameLabel.setBounds(50, 80, 150, 25);
        teamNameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(teamNameLabel);

        // Team Name Text Field
        JTextField teamNameField = new JTextField();
        teamNameField.setBounds(50, 110, 300, 25);
        teamNameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(teamNameField);

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
        loginButton.setBounds(150, 230, 100, 30);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String team_name = teamNameField.getText();

                String password = String.valueOf(passwordField.getPassword());

                Team team = MyJDBC.validateTeamLogin(team_name, password);

                if (team != null){
                    team_login_gui.this.dispose();

                    team_info_gui team_info_gui = new team_info_gui(team);
                    team_info_gui.setVisible(true);

                    JOptionPane.showMessageDialog(team_info_gui, "Login Successful");
                }else{
                    JOptionPane.showMessageDialog(team_login_gui.this, "Login Failed");
                }
            }
        });
        add(loginButton);

        JLabel registerLabel = new JLabel("<html><a href='#'>Don't have an account? Register here.</a></html>");
        registerLabel.setBounds(0, 410, getWidth() - 10, 30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                team_login_gui.this.dispose();

                new register_team_gui().setVisible(true);
            }
        });
        add(registerLabel);
    }
}
