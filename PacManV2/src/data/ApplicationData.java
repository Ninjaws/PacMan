package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationData implements Serializable {

    private List<User> users;
    private LauncherData launcherData;

    public ApplicationData() {
        users = new ArrayList<>();
        launcherData = new LauncherData();
    }


    public synchronized void addUser(User user){
        users.add(user);
    }

//    public List<User> getUsers() {
//        return users;
//    }

    public User getUser(String userName){
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public synchronized LauncherData getLauncherData() {
        return launcherData;
    }
}
