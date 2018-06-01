package application.launcher.data;

import java.io.Serializable;

public class UserData implements Serializable {

    private final String userName;
    private boolean isPacMan = false;

    public UserData(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isPacMan() {
        return isPacMan;
    }

    public void setPacMan(boolean pacMan) {
        isPacMan = pacMan;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
