package guis;

import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class update_race_score_gui extends JFrame {
    private JTextField raceIdField;
    private JTextField driverIdField;
    private JTextField positionField;
    private JTextField fastestLapField;
    private JTextField totalTimeField;
    private JTextField pointsAwardedField;

    public update_race_score_gui() {
        setTitle("Update Race Scores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(7, 2, 10, 10));

        JLabel raceIdLabel = new JLabel("Race ID:");
        raceIdField = new JTextField();
        mainPanel.add(raceIdLabel);
        mainPanel.add(raceIdField);

        JLabel driverIdLabel = new JLabel("Driver ID:");
        driverIdField = new JTextField();
        mainPanel.add(driverIdLabel);
        mainPanel.add(driverIdField);

        JLabel positionLabel = new JLabel("Position:");
        positionField = new JTextField();
        mainPanel.add(positionLabel);
        mainPanel.add(positionField);

        JLabel fastestLapLabel = new JLabel("Fastest Lap:");
        fastestLapField = new JTextField();
        mainPanel.add(fastestLapLabel);
        mainPanel.add(fastestLapField);

        JLabel totalTimeLabel = new JLabel("Total Time:");
        totalTimeField = new JTextField();
        mainPanel.add(totalTimeLabel);
        mainPanel.add(totalTimeField);

        JLabel pointsAwardedLabel = new JLabel("Points Awarded:");
        pointsAwardedField = new JTextField();
        mainPanel.add(pointsAwardedLabel);
        mainPanel.add(pointsAwardedField);

        JButton updateButton = new JButton("Update Race Scores");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic to update race scores in the database
                int raceId = Integer.parseInt(raceIdField.getText());
                int driverId = Integer.parseInt(driverIdField.getText());
                int position = Integer.parseInt(positionField.getText());
                String fastestLap = fastestLapField.getText();
                String totalTime = totalTimeField.getText();
                int pointsAwarded = Integer.parseInt(pointsAwardedField.getText());

                // Call method in MyJDBC to update race scores
                boolean success = MyJDBC.insertRaceScore(raceId, driverId, position, fastestLap, totalTime, pointsAwarded);

                // Display a message dialog based on the success of the update
                if (success) {
                    JOptionPane.showMessageDialog(null, "Race scores updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update race scores");
                }

                // Clear input fields after updating
                raceIdField.setText("");
                driverIdField.setText("");
                positionField.setText("");
                fastestLapField.setText("");
                totalTimeField.setText("");
                pointsAwardedField.setText("");
            }
        });
        mainPanel.add(updateButton);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new update_race_score_gui().setVisible(true);
            }
        });
    }
}
