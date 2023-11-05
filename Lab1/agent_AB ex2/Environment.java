
import java.util.Objects;



public class Environment {
    public static final Action MOVE_LEFT = new DynamicAction("LEFT");
    public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
    public static final Action SUCK_DIRT = new DynamicAction("SUCK");

    public static final Action MOVE_UP = new DynamicAction("UP");

    public static final Action MOVE_DOWN = new DynamicAction("DOWN");
    public static final String LOCATION_A = "A";
    public static final String LOCATION_B = "B";

    public static final  String LOCATION_C ="C";
    public static final  String LOCATION_D ="D";


    public enum LocationState {
        CLEAN, DIRTY
    }

    private EnvironmentState envState;
    private boolean isDone = false;// all squares are CLEAN
    private Agent agent = null;

    public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
        envState = new EnvironmentState(locAState, locBState,locCState,locDState);
    }

    // add an agent into the enviroment
    public void addAgent(Agent agent, String location) {
        // TODO
        this.agent = agent;
        envState.setAgentLocation(location);
    }

    public EnvironmentState getCurrentState() {
        return this.envState;
    }

    // Update enviroment state when agent do an action
    public EnvironmentState executeAction(Action action) {
        // TODO
        if(action == SUCK_DIRT) {
            String agentLocation = envState.getAgentLocation();
            envState.setLocationState(agentLocation,LocationState.CLEAN);
        }
        if(Objects.equals(envState.getAgentLocation(), LOCATION_B)) {
            if(action == MOVE_LEFT) {
                envState.setAgentLocation(LOCATION_A);
            }
            if(action == MOVE_DOWN) {
                envState.setAgentLocation(LOCATION_D);
            }
        }
        if(Objects.equals(envState.getAgentLocation(), LOCATION_A)) {
            if(action == MOVE_RIGHT) {
                envState.setAgentLocation(LOCATION_B);
            }
            if(action == MOVE_DOWN) {
                envState.setAgentLocation(LOCATION_C);
            }
        }
        if(Objects.equals(envState.getAgentLocation(), LOCATION_C)) {
            if(action == MOVE_RIGHT) {
                envState.setAgentLocation(LOCATION_D);
            }
            if(action == MOVE_UP) {
                envState.setAgentLocation(LOCATION_A);
            }
        }
        if(Objects.equals(envState.getAgentLocation(), LOCATION_D)) {
            if(action == MOVE_LEFT) {
                envState.setAgentLocation(LOCATION_C);
            }
            if(action == MOVE_UP) {
                envState.setAgentLocation(LOCATION_B);
            }
        }
        return envState;

    }

    // get percept<AgentLocation, LocationState> at the current location where agent
    // is in.
    public Percept getPerceptSeenBy() {
        // TODO
        String agentLocation = envState.getAgentLocation();
        LocationState stateAgent = envState.getLocationState(agentLocation);


        return new Percept(agentLocation,stateAgent);
    }

    public void step() {
        envState.display();
        String agentLocation = this.envState.getAgentLocation();
        Action anAction = agent.execute(getPerceptSeenBy());
        EnvironmentState es = executeAction(anAction);

        System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);
        System.out.println("Performance measure: "+ agent.getScore());

        if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
                && (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
            isDone = true;// if both squares are clean, then agent do not need to do any action

        es.display();
        System.out.println("Performance measure: "+ agent.getScore());
    }

    public void step(int n) {
        for (int i = 0; i < n; i++) {
            step();
            System.out.println("-------------------------");
        }
    }

    public void stepUntilDone() {
        int i = 0;

        while (!isDone) {
            System.out.println("step: " + i++);
            step();
        }
    }
}