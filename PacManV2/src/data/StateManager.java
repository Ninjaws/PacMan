package data;

public class StateManager {
    public enum State {
        CENTRALCONNECTREQUEST, LOBBYJOINREQUEST, INGAME
    }

    private static StateManager instance = null;
    private static State currentState = null;

    private StateManager() {

    }

    public void setCurrentState(State nextState) {
        System.out.println(nextState);
        currentState = nextState;
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static StateManager getInstance() {
        if(instance == null)
            instance = new StateManager();
        return instance;
    }


}
