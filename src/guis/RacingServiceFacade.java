package guis;

import db_objs.RaceInfo;
import db_objs.RaceScore;
import db_objs.MyJDBC;
import java.util.ArrayList;

public class RacingServiceFacade {
    private final MyJDBC jdbc;

    public RacingServiceFacade() {
        this.jdbc = MyJDBC.getInstance(); // Assuming MyJDBC is a Singleton
    }

    public String getRaceInformation() {
        ArrayList<RaceInfo> raceInfos = MyJDBC.getRaceInfo();
        StringBuilder displayText = new StringBuilder("Race Info:\n");
        for (RaceInfo info : raceInfos) {
            displayText.append("Race Name: ").append(info.getRaceName()).append("\n")
                    .append("Date: ").append(info.getRaceDate()).append("\n")
                    .append("Location: ").append(info.getLocation()).append("\n")
                    .append("Number of Laps: ").append(info.getNumberOfLaps()).append("\n")
                    .append("__________________________________________________\n\n");
        }
        return displayText.toString();
    }

    public String getRaceScores() {
        ArrayList<RaceScore> raceScores = jdbc.getRaceScores();
        StringBuilder displayText = new StringBuilder("Race Scores:\n");
        for (RaceScore score : raceScores) {
            displayText.append("Race ID: ").append(score.getRaceId()).append("\n")
                    .append("Driver ID: ").append(score.getDriverId()).append("\n")
                    .append("Position: ").append(score.getPosition()).append("\n")
                    .append("Fastest Lap Time (hh:mm:ss): ").append(score.getFastestLapTime()).append("\n")
                    .append("Total Time (hh:mm:ss): ").append(score.getTotalTime()).append("\n")
                    .append("Points Awarded: ").append(score.getPointsAwarded()).append("\n")
                    .append("________________________________________________________\n\n");
        }
        return displayText.toString();
    }
}
