package guis;

import db_objs.*;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {

    protected Driver driver;
    protected Team team;
    protected Admin admin;
    protected RaceInfo raceInfo;
    protected RaceScore raceScore;

    public BaseFrame(String title){ initialize(title); }
    public BaseFrame(String title, Driver driver){
        this.driver = driver;

        initialize(title);
    }
    public BaseFrame(String title, Team team){
        this.team = team;

        initialize(title);
    }
    public BaseFrame(String title, Admin admin){
        this.admin = admin;

        initialize(title);
    }
    public BaseFrame(String title, RaceInfo raceInfo){
        this.raceInfo = raceInfo;

        initialize(title);
    }
    public BaseFrame(String title, RaceScore raceScore){
        this.raceScore = raceScore;

        initialize(title);
    }

    private void initialize(String title) {
        setTitle(title);

        setSize(420, 600);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        setResizable(true); // Enable resizing

        setLocationRelativeTo(null);

        addGuiComponents();
    }

    protected abstract void addGuiComponents();
}
