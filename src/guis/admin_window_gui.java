package guis;

import db_objs.MyJDBC;
import db_objs.RaceInfo;
import db_objs.RaceScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class admin_window_gui extends BaseFrame {
    private JTextArea displayArea;

    public admin_window_gui() {
        super("Admin Window");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton updateRaceInfoButton = new JButton("Update Race Info");
        updateRaceInfoButton.setFont(new Font("Dialog", Font.BOLD, 20));
        updateRaceInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new update_race_info_gui().setVisible(true);
            }
        });
        buttonPanel.add(updateRaceInfoButton);

        JButton updateRaceScoresButton = new JButton("Update Race Scores");
        updateRaceScoresButton.setFont(new Font("Dialog", Font.BOLD, 20));
        updateRaceScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new update_race_score_gui().setVisible(true);
            }
        });
        buttonPanel.add(updateRaceScoresButton);

        add(buttonPanel, BorderLayout.NORTH);

        JButton seeRaceInfoButton = new JButton("See Race Info");
        seeRaceInfoButton.setFont(new Font("Dialog", Font.BOLD, 20));
        seeRaceInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");

                ArrayList<RaceInfo> raceInfoList = MyJDBC.getRaceInfo();
                displayArea.append("Race Info:\n");
                for (RaceInfo raceInfo : raceInfoList) {
                    displayArea.append("Race Name: " + raceInfo.getRaceName() + "\n");
                    displayArea.append("Date: " + raceInfo.getRaceDate() + "\n");
                    displayArea.append("Location: " + raceInfo.getLocation() + "\n");
                    displayArea.append("Number of Laps: " + raceInfo.getNumberOfLaps() + "\n");
                    displayArea.append("__________________________________________________" + "\n\n");
                }
            }
        });
        add(seeRaceInfoButton, BorderLayout.WEST);

        JButton seeRaceScoreButton = new JButton("See Race Scores");
        seeRaceScoreButton.setFont(new Font("Dialog", Font.BOLD, 20));
        seeRaceScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText("");

                ArrayList<RaceScore> raceScoreList = MyJDBC.getRaceScores();
                for (RaceScore raceScore : raceScoreList) {
                    displayArea.append("Race ID: " + raceScore.getRaceId() + "\n");
                    displayArea.append("Driver ID: " + raceScore.getDriverId() + "\n");
                    displayArea.append("Position: " + raceScore.getPosition() + "\n");
                    displayArea.append("Fastest Lap Time (hh:mm:ss): " + raceScore.getFastestLapTime() + "\n");
                    displayArea.append("Total Time (hh:mm:ss): " + raceScore.getTotalTime() + "\n");
                    displayArea.append("Points Awarded: " + raceScore.getPointsAwarded() + "\n");
                    displayArea.append("________________________________________________________" + "\n\n");
                }
            }
        });
        add(seeRaceScoreButton, BorderLayout.EAST);
    }

    @Override
    protected void addGuiComponents() {

    }
}
