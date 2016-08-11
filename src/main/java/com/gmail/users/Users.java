package com.gmail.users;

/**
 * Created by Oleksandr_Danchenko on 10.08.2016.
 */
public enum Users {
    USER1("g6927910@gmail.com", "g6927910!@"),
    USER2("g6927911@gmail.com", "g6927911!@");

    private String email;
    private String password;

    Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
