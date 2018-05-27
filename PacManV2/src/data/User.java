package data;

import java.io.Serializable;

public class User implements Serializable {

    private final String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
