package db_objs;

public class Driver {
    private final int driver_id;
    private final String driver_name, driver_team, password;
    private final int score;

    public Driver(int driver_id, String driver_name, String driver_team, String password, int score){
        this.driver_id = driver_id;
        this.driver_name = driver_name;
        this.driver_team = driver_team;
        this.password = password;
        this.score = score;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public String getDriver_team() {
        return driver_team;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;

    }
}
