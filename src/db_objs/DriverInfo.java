package db_objs;

public class DriverInfo {
    private int driverId;
    private String driverName;
    private String driverTeam;
    private String password;
    private int score;
    // Add more attributes as needed

    public DriverInfo(int driverId, String driverName, String driverTeam, String password, int score) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverTeam =  driverTeam;
        this.password = password;
        this.score = score;
    }

    public int getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }
}
