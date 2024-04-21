package guis;
import db_objs.Driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class driver_info_gui extends BaseFrame {
    public driver_info_gui(Driver driver) {
        super("Driver Info", driver);
    }

    @Override
    protected void addGuiComponents() {
        setLayout(null);  // Keep using null layout for precise control

        // Welcome message
        JLabel welcomeMessageLabel = new JLabel("<html><body style='text-align:center'><b>Welcome, " +
                driver.getDriver_name() + "!</b><br>Here is your information:</body></html>");
        welcomeMessageLabel.setBounds(10, 20, getWidth() - 20, 40);
        welcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);

        // Driver name
        JLabel driverNameLabel = new JLabel("Driver Name: " + driver.getDriver_name());
        driverNameLabel.setBounds(10, 70, getWidth() - 20, 30);
        driverNameLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(driverNameLabel);

        // Team name
        JLabel teamNameLabel = new JLabel("Team Name: " + driver.getDriver_team());
        teamNameLabel.setBounds(10, 110, getWidth() - 20, 30);
        teamNameLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(teamNameLabel);

        // Score (assuming 'getScore' method exists in your Driver class)
        JLabel scoreLabel = new JLabel("Score: " + driver.getScore());  // Adjust according to actual method
        scoreLabel.setBounds(10, 150, getWidth() - 20, 30);
        scoreLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(scoreLabel);

        // Edit details button
//        JButton editDetailsButton = new JButton("Edit Details");
//        editDetailsButton.setBounds(10, 190, getWidth() - 20, 30);
//        editDetailsButton.setFont(new Font("Dialog", Font.BOLD, 16));
//        add(editDetailsButton);

        JButton regButton = new JButton("Register for race !");
        regButton.setBounds(10, 260, getWidth() - 20, 30);
        regButton.setFont(new Font("Dialog", Font.BOLD, 16));
        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                driver_info_gui.this.dispose();

                new race_reg_gui().setVisible(true);
            }
        });
        add(regButton);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 230, getWidth() - 20, 30);
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 16));
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                driver_info_gui.this.dispose();

                new driver_login_gui().setVisible(true);
            }
        });
        add(logoutButton);




    }
}
