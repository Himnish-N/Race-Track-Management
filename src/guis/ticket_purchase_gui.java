package guis;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;

// The Race class to hold race information
class Race {
    private String name;
    private Date date;
    private String location;

    public Race(String name, Date date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    @Override
    public String toString() {
        // Format the date as you like, here just using the default toString method
        return String.format("%s on %s at %s", name, date.toString(), location);
    }
}

public class ticket_purchase_gui extends JFrame {
    private JList<Race> raceList;
    private JComboBox<String> ticketTypeComboBox;
    private JButton confirmPurchaseButton;

    public ticket_purchase_gui() {
        setTitle("Race Ticketing System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        initComponents();
    }

    private void initComponents() {
        // Simulated race data, this would normally come from your database
        Vector<Race> races = new Vector<>(Arrays.asList(
                new Race("Grand Prix", new Date(), "Monaco"),
                new Race("Indy 500", new Date(), "Indianapolis"),
                new Race("Le Mans", new Date(), "Le Mans")
        ));

        raceList = new JList<>(races);
        raceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        raceList.setSelectedIndex(0);
        add(new JScrollPane(raceList), BorderLayout.CENTER);

        String[] ticketTypes = {"Standard", "VIP", "Premium"};
        ticketTypeComboBox = new JComboBox<>(ticketTypes);
        ticketTypeComboBox.setSelectedIndex(0);

        confirmPurchaseButton = new JButton("Confirm Purchase");
        confirmPurchaseButton.addActionListener(e -> confirmPurchase());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Ticket Type:"));
        bottomPanel.add(ticketTypeComboBox);
        bottomPanel.add(confirmPurchaseButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void confirmPurchase() {
        Race selectedRace = raceList.getSelectedValue();
        String ticketType = (String) ticketTypeComboBox.getSelectedItem();

        int paymentMethod = JOptionPane.showOptionDialog(
                this,
                "How would you like to complete the purchase?",
                "Complete Purchase",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Pay by Card", "Generate QR Code"},
                "Pay by Card"
        );

        if (paymentMethod == JOptionPane.YES_OPTION) {
            // Logic for paying by card goes here
            JOptionPane.showMessageDialog(this, "Payment successful!");
        } else if (paymentMethod == JOptionPane.NO_OPTION) {
            // Logic for generating QR code goes here
            JOptionPane.showMessageDialog(this, "QR code generated!");
        }

        String confirmationMessage = String.format("You have selected the race: %s\nTicket type: %s",
                selectedRace.toString(), ticketType);
        JOptionPane.showMessageDialog(this, confirmationMessage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ticket_purchase_gui frame = new ticket_purchase_gui();
            frame.setLocationRelativeTo(null); // Center on screen
            frame.setVisible(true);
        });
    }
}
