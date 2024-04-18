package db_objs;

public class RaceScore {
    private int scoreId;
    private int raceId;
    private int driverId;
    private int position;
    private String fastestLapTime;
    private String totalTime;
    private int pointsAwarded;
    // Add more attributes as needed

    public RaceScore(int scoreId, int raceId, int driverId, int position, String fastestLapTime, String totalTime, int pointsAwarded) {
        this.scoreId = scoreId;
        this.raceId = raceId;
        this.driverId = driverId;
        this.position = position;
        this.fastestLapTime = fastestLapTime;
        this.totalTime = totalTime;
        this.pointsAwarded = pointsAwarded;
    }

    public int getScoreId() {
        return scoreId;
    }

    public int getRaceId() {
        return raceId;
    }

    public int getDriverId() {
        return driverId;
    }

    public int getPosition() {
        return position;
    }

    public String getFastestLapTime() {
        return fastestLapTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public int getPointsAwarded() {
        return pointsAwarded;
    }
}
