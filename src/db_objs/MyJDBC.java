package db_objs;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;



//public class MyJDBC {
//    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/race_manage";
//    private static final String DB_USERNAME = "root";
//    private static final String DB_PASSWORD = "0802";

public class MyJDBC {
    private static MyJDBC instance;
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/race_manage";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "0802";

    // Private constructor to prevent instantiation
    private MyJDBC() {}

    // Public method to get the instance
    public static MyJDBC getInstance() {
        if (instance == null) {
            synchronized (MyJDBC.class) {
                if (instance == null) {
                    instance = new MyJDBC();
                }
            }
        }
        return instance;
    }


    public static Admin validateAdminLogin(String admin_name, String admin_password){
        String query = "SELECT * FROM admins WHERE admin_name = ? AND admin_password = ?";
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, admin_name);
            preparedStatement.setString(2, admin_password);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    int admin_id = resultSet.getInt("admin_id");
                    String adminName = resultSet.getString("admin_name"); // Rename variable to avoid conflict
                    return new Admin(admin_id, adminName, admin_password);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // Existing driver login validation method (unchanged)
    public static Driver validateDriverLogin(String driverName, String password) {
        String query = "SELECT * FROM drivers WHERE driver_name = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, driverName);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int driverId = resultSet.getInt("driver_id");
                    String teamName = resultSet.getString("driver_team");
                    int score = resultSet.getInt("score");
                    return new Driver(driverId, driverName, teamName, password, score);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // New team login validation method
    public static Team validateTeamLogin(String teamName, String password) {
        String query = "SELECT * FROM teams WHERE team_name = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, teamName);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int teamId = resultSet.getInt("team_id");
                    String teamManager = resultSet.getString("team_manager");
                    return new Team(teamId, teamName, teamManager, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registerNewDriver(String driverName, String teamName, String password) {
        String query = "INSERT INTO drivers (driver_name, driver_team, password) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, driverName);
            preparedStatement.setString(2, teamName);
            preparedStatement.setString(3, password); // Consider hashing the password

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean registerNewTeam(String teamName, String managerName, String password) {
        String query = "INSERT INTO teams (team_name, team_manager, password) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, teamName);
            preparedStatement.setString(2, managerName);
            preparedStatement.setString(3, password); // This should be a hashed password

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            return false;
        }
    }

//    public static List<RaceInfo> getAllRaceInfo(){
//        List<RaceInfo> raceInfoList = new ArrayList<>();
//        String query = "SELECT * FROM race_info";
//        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//             PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//            while (resultSet.next()) {
//                int raceId = resultSet.getInt("race_id");
//                String raceName = resultSet.getString("race_name");
//                Date raceDate = resultSet.getDate("race_date");
//                String raceLocation = resultSet.getString("location");
//                int no_of_laps = resultSet.getInt("no_of_laps");
//                RaceInfo raceInfo = new RaceInfo(raceId, raceName, raceDate, raceLocation, no_of_laps);
//                raceInfoList.add(raceInfo);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return raceInfoList;
//    }

    public static ArrayList<RaceInfo> getRaceInfo(){
        ArrayList<RaceInfo> raceInfoList = new ArrayList<>();
        String query = "SELECT * FROM race_info";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            PreparedStatement selectAllRaces = connection.prepareStatement(query);
            // No need to set any parameters in this query

            ResultSet resultSet = selectAllRaces.executeQuery();

            System.out.println("Executing SQL Query: " + query);

            while (resultSet.next()){
                int raceId = resultSet.getInt("race_id");
                String raceName = resultSet.getString("race_name");
                Date raceDate = resultSet.getDate("race_date");
                String location = resultSet.getString("location");
                int no_of_laps = resultSet.getInt("number_of_laps");

                RaceInfo raceInfo = new RaceInfo(raceId, raceName, raceDate, location, no_of_laps);
                raceInfoList.add(raceInfo);
            }

            resultSet.close(); // Close the result set
            selectAllRaces.close(); // Close the prepared statement
            connection.close(); // Close the connection
        } catch (SQLException e){
            e.printStackTrace();
        }
        return raceInfoList;
    }

    public static ArrayList<RaceScore> getRaceScores() {
        ArrayList<RaceScore> raceScoreList = new ArrayList<>();
        String query = "SELECT * FROM race_scores";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement selectAllRaceScores = connection.prepareStatement(query);
             ResultSet resultSet = selectAllRaceScores.executeQuery()) {

            System.out.println("Executing SQL Query: " + query);

            while (resultSet.next()) {
                int scoreId = resultSet.getInt("score_id");
                int raceId = resultSet.getInt("race_id");
                int driverId = resultSet.getInt("driver_id");
                int position = resultSet.getInt("position");
                String fastestLap = resultSet.getString("fastest_lap_time");
                String totalTime = resultSet.getString("total_time");
                int pointsAwarded = resultSet.getInt("points_awarded");

                RaceScore raceScore = new RaceScore(scoreId, raceId, driverId, position, fastestLap, totalTime, pointsAwarded);
                raceScoreList.add(raceScore);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return raceScoreList;
    }

    public static boolean insertRaceInfo(String raceName, String location, Date raceDate, int numberOfLaps) {
        String query = "INSERT INTO race_info (race_name, race_date, location, number_of_laps) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, raceName);
            preparedStatement.setDate(2, new java.sql.Date(raceDate.getTime()));
            preparedStatement.setString(3, location);
            preparedStatement.setInt(4, numberOfLaps);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertRaceScore(int raceId, int driverId, int position, String fastestLap, String totalTime, int pointsAwarded) {
        String query = "INSERT INTO race_scores (race_id, driver_id, position, fastest_lap_time, total_time, points_awarded) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, raceId);
            preparedStatement.setInt(2, driverId);
            preparedStatement.setInt(3, position);
            preparedStatement.setString(4, fastestLap);
            preparedStatement.setString(5, totalTime);
            preparedStatement.setInt(6, pointsAwarded);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve current score of the driver
                int currentScore = 0;
                String selectQuery = "SELECT score FROM drivers WHERE driver_id = ?";
                try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                    selectStatement.setInt(1, driverId);
                    try (ResultSet resultSet = selectStatement.executeQuery()) {
                        if (resultSet.next()) {
                            currentScore = resultSet.getInt("score");
                        } else {
                            throw new SQLException("Driver not found");
                        }
                    }
                }

                // Calculate new score
                int newScore = currentScore + pointsAwarded;

                // Update driver's score in the database
                String updateQuery = "UPDATE drivers SET score = ? WHERE driver_id = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setInt(1, newScore);
                    updateStatement.setInt(2, driverId);
                    updateStatement.executeUpdate();
                }
            }

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static ArrayList<DriverInfo> getDriverInfo() {
        ArrayList<DriverInfo> driverInfoList = new ArrayList<>();
        String query = "SELECT * FROM drivers";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Assuming DriverInfo class has appropriate constructor and getters
                int driverId = resultSet.getInt("driver_id");
                String driverName = resultSet.getString("driver_name");
                String driverTeam = resultSet.getString("driver_team");
                String password = resultSet.getString("password");
                int score = resultSet.getInt("score");

                DriverInfo driverInfo = new DriverInfo(driverId, driverName, driverTeam, password, score);
                driverInfoList.add(driverInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverInfoList;
    }

    public static boolean race_reg(int driver_id, String driverName, String teamName) {
        String query = "INSERT INTO race_reg (driver_id, driver_name, driver_team) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,driver_id);
            preparedStatement.setString(2, driverName);
            preparedStatement.setString(3, teamName);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

