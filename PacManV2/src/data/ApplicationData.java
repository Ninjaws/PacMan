package data;

import data.launcher.LauncherData;
import data.launcher.UserData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationData implements Serializable {

    private List<UserData> users;
    private LauncherData launcherData;

    public ApplicationData() {
        users = new ArrayList<>();
        launcherData = new LauncherData();
    }


    public synchronized void addUser(UserData user) {
        users.add(user);
    }

    public List<UserData> getUsers() {
        return users;
    }

    public UserData getUser(String userName) {
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public synchronized boolean userExists(String userName) {
        System.out.println("user: " + userName);
        System.out.println("users: " + users);
        return users.stream().anyMatch(user -> user.getUserName().equals(userName));
    }


    public synchronized void removeUser(String name) {
        users.removeIf(user -> user.getUserName().equals(name));
    }

    public synchronized LauncherData getLauncherData() {
        return launcherData;
    }
}
