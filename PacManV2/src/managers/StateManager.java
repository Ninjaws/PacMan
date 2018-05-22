package managers;

public class StateManager {
    public enum State {
        CENTRALCONNECTREQUEST, LOBBYJOINREQUEST, INGAME, MESSAGE,
    }

    private static StateManager instance = null;

    private StateManager() {

    }

    public static StateManager getInstance() {
        if(instance == null)
            instance = new StateManager();
        return instance;
    }


}
