package db_objs;

public class Team {
    private final int team_id;
    private final String team_name;
    private final String team_manager;
    private final String password;

    public Team(int team_id, String team_name, String team_manager, String password) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_manager = team_manager;
        this.password = password;
    }

    // Getters
    public int getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getTeam_manager() {
        return team_manager;
    }

    public String getPassword() {
        return password;
    }
}
