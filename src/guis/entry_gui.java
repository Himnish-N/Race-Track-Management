package guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class entry_gui extends BaseFrame {
    public entry_gui() {
        super("Welcome to RACE");
    }

    @Override
    protected void addGuiComponents() {
        setLayout(new BorderLayout());  // Keep BorderLayout for the main layout

        // Welcome label at the top
        JLabel welcomeLabel = new JLabel("Welcome to RACE", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 36));
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel for buttons with GridBagLayout for flexible positioning
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Constraints common to all components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);  // Margins around components
        gbc.anchor = GridBagConstraints.CENTER;

        // Helper method to add buttons
        addButton(bottomPanel, "Spectator", 0, 0, 1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the spectator GUI
                new spectator_gui().setVisible(true);
                dispose(); // Close the current GUI
            }
        });
        addButton(bottomPanel, "Driver", 1, 0, 1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the driver GUI
                new driver_login_gui().setVisible(true);
                dispose(); // Close the current GUI
            }
        });
        addButton(bottomPanel, "Team", 0, 1, 1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the team GUI
                new team_login_gui().setVisible(true);
                dispose(); // Close the current GUI
            }
        });
        addButton(bottomPanel, "Admin", 1, 1, 1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the admin GUI
                new admin_login_gui().setVisible(true);
                dispose(); // Close the current GUI
            }
        });

        add(bottomPanel, BorderLayout.CENTER);  // Add the bottom panel to the center to use extra space
    }

    // Helper method to add buttons to the panel
    private void addButton(JPanel panel, String text, int gridx, int gridy, int gridwidth, ActionListener actionListener) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton button = new JButton(text);
        button.setFont(new Font("Dialog", Font.PLAIN, 16));
        button.addActionListener(actionListener); // Add action listener to the button
        panel.add(button, gbc);
    }
}
