package db_objs;

public class Race {
    private String race_name;
    private String race_date;
    private String location;

    public Race(String race_name, String race_datedate, String location) {
        this.race_name = race_name;
        this.race_date = race_datedate;
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", race_name, race_date.toString(), location);
    }

    // Getters
    public String getName() {
        return race_name;
    }

    public String getDate() {
        return race_date;
    }

    public String getLocation() {
        return location;
    }
}
