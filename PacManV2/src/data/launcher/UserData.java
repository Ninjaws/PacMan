package data.launcher;

import java.io.Serializable;

public class UserData implements Serializable {

    private final String userName;

    public UserData(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
