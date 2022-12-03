package BoundaryDB;

import java.sql.*;
import java.util.*;
import Entitity.*;




public class SeatsDB implements Database{

    private Connection dbConnect;


    public SeatsDB(){
        initializeConnection();
    }

    public ArrayList<Seat> getSeatsFromShowtime(int id) {
        ArrayList<Seat> DBUser = new ArrayList<>();
        try {
            String query = "SELECT * FROM Movies WHERE MovieID = ?";
            PreparedStatement stmt = dbConnect.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery(query);
            while (set.next()) {
                int showtimeID = set.getInt("ShowtimeID");
                int SeatID = set.getInt("ID");
                boolean Ruser = set.getBoolean("RUser");
                boolean Vacancy = set.getBoolean("Vacancy");
                DBUser.add(new Seat(Vacancy,SeatID,showtimeID,Ruser));
            }
            stmt.close();
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return DBUser;
    }


    public boolean checkRuserSeat(ArrayList<Seat>seats){
        int max = seats.size()/10;
        int counter = 0;
        for(Seat val : seats){
            if(val.getRuser()==true){
                counter++;
            }
            if(counter > max)
            return false;
        }
        return true;
    }





    @Override
    public void initializeConnection() {
        try {
            this.dbConnect = DriverManager.getConnection(Database.URL, Database.USERNAME, Database.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();        
        }
    }

    public void closeConnection(){
        try {
            this.dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
