package data;

import java.util.ArrayList;
import java.util.List;

public class ApplicationData {

    private List<User> users;

    public ApplicationData() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }
}
