package database.data;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private long id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return username + "(id: " + id + ")";
    }

}