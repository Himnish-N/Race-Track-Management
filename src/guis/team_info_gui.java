package guis;
import db_objs.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class team_info_gui extends BaseFrame {
    public team_info_gui(Team team) {
        super("Team Info", team);
    }

    @Override
    protected void addGuiComponents() {
        String welcomeMessage = "<html>" +
                "<body style='text-align:center'>" +
                "<b>Welcome, " + team.getTeam_manager() + "!</b><br>" +
                "Here is your team's information:</body></html>";

        JLabel welcomeMessageLabel = new JLabel(welcomeMessage);
        welcomeMessageLabel.setBounds(10, 60, getWidth() - 20, 40);
        welcomeMessageLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);

        JLabel teamIdLabel = new JLabel("Team ID: " + team.getTeam_id());
        teamIdLabel.setBounds(10, 120, getWidth() - 20, 30);
        teamIdLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(teamIdLabel);

        JLabel teamNameLabel = new JLabel("Team Name: " + team.getTeam_name());
        teamNameLabel.setBounds(10, 160, getWidth() - 20, 30);
        teamNameLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(teamNameLabel);

        JLabel teamManagerLabel = new JLabel("Team Manager: " + team.getTeam_manager());
        teamManagerLabel.setBounds(10, 200, getWidth() - 20, 30);
        teamManagerLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(teamManagerLabel);

        JButton editDetailsButton = new JButton("Edit Details");
        editDetailsButton.setBounds(0, 240, getWidth()-100, 30);
        editDetailsButton.setFont(new Font("Dialog", Font.BOLD, 16));
        editDetailsButton.setHorizontalAlignment(SwingConstants.CENTER);
        add(editDetailsButton);

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 230, getWidth() - 20, 30);
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 16));
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                team_info_gui.this.dispose();

                new driver_login_gui().setVisible(true);
            }
        });
        // The password field is generally not displayed in a GUI for security reasons
        // If you need to allow the manager to change the password, you would
        // add components to facilitate that instead of displaying the current password
    }
}
