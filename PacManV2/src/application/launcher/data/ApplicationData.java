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
        return users.stream().anyMatch(user -> user.getUserName().equals(userName));
    }

    public boolean everyUserIsReady(String name){
        for(String user : launcherData.getLobby(name).getPlayers()){
            UserData userData = getUser(user);
            if(!userData.isReady())
                return false;
            else if(launcherData.getLobby(name).getPlayers().size() <= 1)
                return false;
        }
        return true;
    }


    public void removeUser(String name) {
        users.removeIf(user -> user.getUserName().equals(name));
    }

    public LauncherData getLauncherData() {
        return launcherData;
    }
}
