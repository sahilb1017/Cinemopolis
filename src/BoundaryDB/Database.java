package BoundaryDB;

import Entitity.*;
import java.util.ArrayList;

public interface Database{

    final String USERNAME = "ensf480";

    final String PASSWORD = "password";

    final String URL = "jdbc:mysql://localhost/cinemopolis";

    public void initializeConnection();


}

