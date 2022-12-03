package Entitity;

import java.util.*;

public class Singleton{
    private static Singleton instance;

    private ArrayList<User> RegisteredUsers;

    private boolean UsersAdded = false;


    private Singleton() {
        this.RegisteredUsers = new ArrayList<User>();

    }

    public ArrayList<User> getRegisteredUsers(){
        return RegisteredUsers;
    }

    public void setRegisteredUsers(ArrayList<Data> RegisteredUser){
        RegisteredUsers.clear();

        for(Data val : RegisteredUser){
            this.RegisteredUsers.add((User)val);
        }
    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }


    public void addUser(Data u) {
        this.RegisteredUsers.add((User)u);
        return;
    }

    public void wasAdded(){
        UsersAdded = true;
    }

    public boolean getUsersAdded(){
        return UsersAdded;
    }

    
}
