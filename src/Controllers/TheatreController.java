package Controllers;
import java.util.ArrayList;
import BoundaryDB.TheatreDB;
import Entitity.Theatre;

public class TheatreController{
    private TheatreDB db;

    public TheatreController(){
       db = new TheatreDB();
    }

    public ArrayList<Theatre> getTheatreInfo(){
        return db.getAllTheatres();
    }
}
