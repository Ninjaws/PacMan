package application.launcher.data;

import application.launcher.data.LauncherData;
import application.launcher.data.UserData;

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


    public void addUser(UserData user) {
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

    public boolean userExists(String userName) {
        UserData newUserData = new UserData(userName);
        for(UserData userData : users){
            if(newUserData.compare(newUserData,userData) == 0)
                return true;
        }
        return false;
    }

    public boolean isThereAPacMan(String lobbyName, String name){
        for(String userName : getLauncherData().getLobby(lobbyName).getPlayers()){
            UserData userData = getUser(userName);
            if(userData.isPacMan() && !userData.getUserName().equals(name))
                return true;
        }
        return false;
    }


    public void removeUser(String name) {
        users.removeIf(user -> user.getUserName().equals(name));
    }

    public LauncherData getLauncherData() {
        return launcherData;
    }
}
