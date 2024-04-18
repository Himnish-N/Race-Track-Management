package guis;

import db_objs.Driver;
import db_objs.Team;

import javax.swing.*;

public class App_launcher{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new audience_gui().setVisible(true);
//                new register_team_gui().setVisible(true);
//                new register_driver_gui().setVisible(true);
                new entry_gui().setVisible(true);
//                new driver_login_gui().setVisible(true);
//                new driver_info_gui(
//                        new Driver(2, "neo", "raphal", "pass", 0)
//                ).setVisible(true);

//                new team_info_gui(
//                        new Team(1, "he", "hi", "sdlkj")
//                ).setVisible(true);

//                new  admin_window_gui().setVisible(true);
//                new update_race_info_gui().setVisible(true);
            }
        });
    }

}
