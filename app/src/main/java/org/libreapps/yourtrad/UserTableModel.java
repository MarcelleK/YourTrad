package org.libreapps.yourtrad;

import org.json.JSONException;
import org.libreapps.yourtrad.obj.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UserTableModel {
    private String[] columnsNames = {"Id", "Nom", "Prenom", "Email", "Password"};

    private ArrayList<User> usersList = new ArrayList<User>();
    private ConnectionRest connectionRest = null;

    public UserTableModel() {
    }

    public String[] getUserHeaders() {
        return columnsNames;
    }

    //RETURN TABLE ROWS
    public String[][] getUsers() {
        usersList = refreshUsers();
        User obj;

        if (usersList != null) {
            String[][] users = new String[usersList.size()][5];

            for (int i = 0; i < usersList.size(); i++) {
                obj = usersList.get(i);
                users[i][0] = "" + obj.getId();
                users[i][1] = "" + obj.getName();
                users[i][2] = "" + obj.getFirstName();
                users[i][3] = "" + obj.getEmail();
                users[i][4] = "" + obj.getPassword();
            }
            return users;
        }
        return new String[0][5];
    }

    public ArrayList<User> refreshUsers() {
        try {
            connectionRest = new ConnectionRest();
            connectionRest.execute("GET");
            String listJsonObjs = (String) connectionRest.get();

            if (listJsonObjs != null) {
                return connectionRest.parse(listJsonObjs);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User get(int row) {
        return usersList.get(row);
    }

}

