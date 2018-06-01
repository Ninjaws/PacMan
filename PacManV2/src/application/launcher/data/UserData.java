package application.launcher.data;

import java.io.Serializable;
import java.util.Comparator;


public class UserData implements Serializable, Comparator<UserData>{

    private final String userName;
    private boolean isPacMan = false;
    private boolean isReady = false;

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

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isReady() {
        return isReady;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userName='" + userName + '\'' +
                ", isPacMan=" + isPacMan +
                ", isReady=" + isReady +
                '}';
    }

    @Override
    public int compare(UserData o1, UserData o2) {
        return o1.userName.compareTo(o2.userName);
    }
}
