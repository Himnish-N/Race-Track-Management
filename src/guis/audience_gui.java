package guis;

import javax.swing.*;
import java.awt.*;

public class audience_gui extends BaseFrame{
    public audience_gui(){
        super("ALL teams");
    }
    @Override
    protected void addGuiComponents(){
        JLabel audlabel = new JLabel("Hello, User !!!");

        audlabel.setBounds(0,20, super.getWidth(), 40);

        audlabel.setFont(new Font("Dialog", Font.BOLD, 32));

        audlabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(audlabel);

        JButton seeTeams = new JButton("See Teams");
        seeTeams.setBounds(20, 200, getWidth() - 50, 40);
        seeTeams.setFont(new Font("Dialogs", Font.BOLD, 20));
        add(seeTeams);

        JButton seeDrivers = new JButton("See Drivers");
        seeDrivers.setBounds(20, 400, getWidth() - 50, 40);
        seeDrivers.setFont(new Font("Dialogs", Font.BOLD, 20));
        add(seeDrivers);


    }
}
