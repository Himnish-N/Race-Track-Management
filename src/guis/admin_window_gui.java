package guis;

import guis.RacingServiceFacade;
import javax.swing.*;
import java.awt.*;

public class admin_window_gui extends BaseFrame {
    private JTextArea displayArea;
    private RacingServiceFacade facade;

    public admin_window_gui() {
        super("Admin Window");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        facade = new RacingServiceFacade(); // Initialize the facade here
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
        updateRaceInfoButton.addActionListener(e -> {
            dispose();
            new update_race_info_gui().setVisible(true);
        });
        buttonPanel.add(updateRaceInfoButton);

        JButton updateRaceScoresButton = new JButton("Update Race Scores");
        updateRaceScoresButton.setFont(new Font("Dialog", Font.BOLD, 20));
        updateRaceScoresButton.addActionListener(e -> {
            dispose();
            new update_race_score_gui().setVisible(true);
        });
        buttonPanel.add(updateRaceScoresButton);

        add(buttonPanel, BorderLayout.NORTH);

        JButton seeRaceInfoButton = new JButton("See Race Info");
        seeRaceInfoButton.setFont(new Font("Dialog", Font.BOLD, 20));
        seeRaceInfoButton.addActionListener(e -> displayArea.setText(facade.getRaceInformation()));
        add(seeRaceInfoButton, BorderLayout.WEST);

        JButton seeRaceScoreButton = new JButton("See Race Scores");
        seeRaceScoreButton.setFont(new Font("Dialog", Font.BOLD, 20));
        seeRaceScoreButton.addActionListener(e -> displayArea.setText(facade.getRaceScores()));
        add(seeRaceScoreButton, BorderLayout.EAST);
    }

    @Override
    protected void addGuiComponents() {

    }
}
