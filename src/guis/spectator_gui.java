package guis;

import db_objs.DriverInfo;
import db_objs.MyJDBC;
import db_objs.RaceInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class spectator_gui extends BaseFrame {
    private JTextArea raceInfoArea;
    private JTextArea driverInfoArea;

    public spectator_gui() {
        super("Race List for Spectators");
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel for the buttons
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        add(buttonPanel, BorderLayout.NORTH);

        // Refresh Button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Arial", Font.BOLD, 16));
        refreshButton.setBackground(new Color(111, 173, 94));

        refreshButton.setForeground(Color.WHITE);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshRaceList();
            }
        });
        buttonPanel.add(refreshButton, BorderLayout.EAST);

        // Buy Ticket Button
        JButton buyTicketButton = new JButton("Buy Ticket");
        buyTicketButton.setFont(new Font("Arial", Font.BOLD, 16));
        buyTicketButton.setBackground(Color.getHSBColor(0, 173, 179));
        buyTicketButton.setForeground(Color.WHITE);
        buyTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                new ticket_purchase_gui().setVisible(true);
            }
        });
        buttonPanel.add(buyTicketButton, BorderLayout.SOUTH);

        // back button
        JButton backButton = new JButton("Back");
        // backButton.setBounds(20, 20, 80, 30);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(209, 50, 53));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spectator_gui.this.dispose();
                // Open previous window (if any)
                // For example:
                new entry_gui().setVisible(true);
            }
        });
        buttonPanel.add(backButton, BorderLayout.WEST);

        // Labels for Race Info and Driver Info
        JLabel raceLabel = new JLabel("Race Info");
        raceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        raceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(raceLabel, BorderLayout.WEST);

        JLabel driverLabel = new JLabel("Driver Info");
        driverLabel.setFont(new Font("Arial", Font.BOLD, 18));
        driverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(driverLabel, BorderLayout.EAST);

        // Panel for displaying race and driver info
        JPanel infoPanel = new JPanel(new GridLayout(1, 2)); // Use GridLayout to display components side by side
        add(infoPanel, BorderLayout.CENTER);

        // Text area for displaying race info
        raceInfoArea = new JTextArea();
        raceInfoArea.setEditable(false);
        raceInfoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane raceScrollPane = new JScrollPane(raceInfoArea);
        infoPanel.add(raceScrollPane);

        // Text area for displaying driver info
        driverInfoArea = new JTextArea();
        driverInfoArea.setEditable(false);
        driverInfoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane driverScrollPane = new JScrollPane(driverInfoArea);
        infoPanel.add(driverScrollPane);

        // Initially load the race list
        refreshRaceList();
    }

    private void refreshRaceList() {
        raceInfoArea.setText("");
        driverInfoArea.setText("");

        ArrayList<RaceInfo> raceInfoList = MyJDBC.getRaceInfo();
        raceInfoArea.append("Race List for Spectators:\n\n");
        for (RaceInfo raceInfo : raceInfoList) {
            raceInfoArea.append("Race Name: " + raceInfo.getRaceName() + "\n");
            raceInfoArea.append("Date: " + raceInfo.getRaceDate() + "\n");
            raceInfoArea.append("--------------------------------------\n\n");
        }

        ArrayList<DriverInfo> driverScores = MyJDBC.getDriverInfo();
        driverInfoArea.append("Driver Scores:\n\n");
        for (DriverInfo driverScore : driverScores) {
            driverInfoArea.append("Driver Name: " + driverScore.getDriverName() + "\n");
            driverInfoArea.append("Score: " + driverScore.getScore() + "\n");
            driverInfoArea.append("--------------------------------------\n\n");
        }
    }

    private void generateTicketPopup() {
        // Select a random race from the race_info table
        ArrayList<RaceInfo> raceInfoList = MyJDBC.getRaceInfo();
        if (raceInfoList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No races available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Random random = new Random();
        RaceInfo randomRace = raceInfoList.get(random.nextInt(raceInfoList.size()));

        // Generate a random ticket number
        int ticketNumber = 10000 + random.nextInt(90000);

        // Display the ticket information in a popup box
        String ticketMessage = "Your ticket for race '" + randomRace.getRaceName() + "' on " + randomRace.getRaceDate() +
                " has been generated.\nTicket Number: " + ticketNumber;
        JOptionPane.showMessageDialog(this, ticketMessage, "Ticket Generated", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                spectator_gui gui = new spectator_gui();
                gui.setSize(800, 600);
                gui.setLocationRelativeTo(null);
                gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gui.setVisible(true);
            }
        });
    }

    @Override
    protected void addGuiComponents() {

    }
}
