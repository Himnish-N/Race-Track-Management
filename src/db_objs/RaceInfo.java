package db_objs;

import java.util.Date;

public class RaceInfo {
    private int raceId;
    private String raceName;
    private Date raceDate;
    private String location;
    private int number_of_laps;

    public RaceInfo(int raceId, String raceName, Date raceDate, String location, int number_of_laps) {
        this.raceId = raceId;
        this.raceName = raceName;
        this.raceDate = raceDate;
        this.location = location;
        this.number_of_laps = number_of_laps;
    }

    public int getRaceId() {
        return raceId;
    }

    public String getRaceName() {
        return raceName;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public String getLocation() {
        return location;
    }

    public int getNumberOfLaps() {
        return number_of_laps;
    }
}
