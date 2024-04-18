package guis;

import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class update_race_info_gui extends JFrame {
    private JTextField raceNameField;
    private JTextField locationField;
    private JTextField lapsField;
    private JFormattedTextField dateField;

    public update_race_info_gui() {
        setTitle("Update Race Information");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel raceNameLabel = new JLabel("Race Name:");
        raceNameField = new JTextField();
        mainPanel.add(raceNameLabel);
        mainPanel.add(raceNameField);

        JLabel locationLabel = new JLabel("Location:");
        locationField = new JTextField();
        mainPanel.add(locationLabel);
        mainPanel.add(locationField);

        JLabel dateLabel = new JLabel("Date (yyyy-mm-dd):");
        dateField = new JFormattedTextField(createDateFormat());
        mainPanel.add(dateLabel);
        mainPanel.add(dateField);

        JLabel lapsLabel = new JLabel("Number of Laps:");
        lapsField = new JTextField();
        mainPanel.add(lapsLabel);
        mainPanel.add(lapsField);

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic to update race info in the database
                String raceName = raceNameField.getText();
                String location = locationField.getText();
                String dateString = dateField.getText();
                int numberOfLaps = Integer.parseInt(lapsField.getText());

                // Parse the date string to Date object
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date raceDate = null;
                try {
                    raceDate = dateFormat.parse(dateString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                // Call method to update race info in the database
                boolean success = MyJDBC.insertRaceInfo(raceName, location, raceDate, numberOfLaps);

                // Display a message based on the success of the update
                if (success) {
                    JOptionPane.showMessageDialog(null, "Race information updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update race information");
                }

                // Close the window after updating
                dispose();
            }

        });
        mainPanel.add(updateButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window without updating
            }
        });
        mainPanel.add(cancelButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private SimpleDateFormat createDateFormat() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Ensure strict date parsing
        return dateFormat;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                update_race_info_gui window = new update_race_info_gui();
                window.setVisible(true);
            }
        });
    }
}
