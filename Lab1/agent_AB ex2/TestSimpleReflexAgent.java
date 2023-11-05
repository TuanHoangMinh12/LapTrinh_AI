public class TestSimpleReflexAgent {
    public static void main(String[] args) {
        Environment en1 = new Environment(Environment.LocationState.CLEAN, Environment.LocationState.DIRTY,Environment.LocationState.DIRTY,Environment.LocationState.DIRTY);
        Agent agent = new Agent(new AgentProgram());
        en1.addAgent(agent, Environment.LOCATION_A);

        en1.step(5);
    }
}