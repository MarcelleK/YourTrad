package org.libreapps.yourtrad.obj;

import org.json.JSONObject;

public class User {

    private final int id;
    private final String firstname;
    private final String lastname;
    private final String username;
    private final String birthdate;
    private final String pays;
    private final String email;
    private final String password;

    public User(JSONObject jObject) {
        this.id         = jObject.optInt("id");
        this.firstname  = jObject.optString("firstname");
        this.lastname   = jObject.optString("lastname");
        this.username   = jObject.optString("username");
        this.birthdate  = jObject.optString("birthdate");
        this.pays       = jObject.optString("pays");
        this.email      = jObject.optString("email");
        this.password   = jObject.optString("password");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return firstname;
    }

    public String getFirstName() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }
};

