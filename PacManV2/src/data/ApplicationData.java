package data;

import java.util.ArrayList;
import java.util.List;

public class ApplicationData {

    private static ApplicationData instance;
    private List<User> users;
    private LauncherData launcherData;

    public static ApplicationData getInstance() {
        if (instance == null)
            instance = new ApplicationData();

        return instance;
    }

    private ApplicationData() {
        users = new ArrayList<>();
        launcherData = new LauncherData();
    }


    public List<User> getUsers() {
        return users;
    }

    public User getUser(String userName){
        return users.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    public LauncherData getLauncherData() {
        return launcherData;
    }
}
