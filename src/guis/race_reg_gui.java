package guis;

import db_objs.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class race_reg_gui extends JFrame {

    public race_reg_gui() {
        setTitle("Race Registration");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("Driver ID:");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Driver Name:");
        JTextField nameField = new JTextField();

        JLabel teamLabel = new JLabel("Team Name:");
        JTextField teamField = new JTextField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int driver_id = Integer.parseInt(idField.getText());
                String driver_name = nameField.getText();
                String team_name = teamField.getText();

                if (driver_name.isEmpty() || team_name.isEmpty()) {
                    JOptionPane.showMessageDialog(race_reg_gui.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Perform registration (e.g., insert into database)
                boolean success = MyJDBC.race_reg(driver_id, driver_name, team_name);

                if (success) {
                    JOptionPane.showMessageDialog(race_reg_gui.this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(race_reg_gui.this, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(teamLabel);
        panel.add(teamField);
        panel.add(registerButton);

        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new race_reg_gui().setVisible(true);
            }
        });
    }
}
