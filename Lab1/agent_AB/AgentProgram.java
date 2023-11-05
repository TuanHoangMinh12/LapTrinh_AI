import java.util.Random;

public class AgentProgram {
    private int score =0;

    public Action execute(Percept p) {// location, status
        //TODO
        Environment.LocationState status = p.getLocationState();
        String location = p.getAgentLocation();


        if(status == Environment.LocationState.DIRTY) {
            score+=500;
            return Environment.SUCK_DIRT;
        } else {
            if(location.equals(Environment.LOCATION_A) ) {
                Random random = new Random();
                int a = random.nextInt(4);
                switch (a){
                    case 0, 2:
                        score-=100;
                        return null;

                    case 1:
                        score-=10;
                        return  Environment.MOVE_DOWN;
                    case 3:
                        score-=10;
                        return  Environment.MOVE_RIGHT;
                }

            }


            if(location.equals(Environment.LOCATION_B)) {
                Random random = new Random();
                int a = random.nextInt(4);
                switch (a){
                    case 0, 3:
                        score-=100;
                        return null;

                    case 1:
                        score-=10;
                        return  Environment.MOVE_DOWN;
                    case 2:
                        score-=10;
                        return Environment.MOVE_LEFT;
                }
            }
            if(location.equals(Environment.LOCATION_C)) {
                Random random = new Random();
                int a = random.nextInt(4);
                switch (a){
                    case 0:
                        score-=10;
                        return Environment.MOVE_UP;

                    case 1, 2:
                        score-=100;
                        return  null;
                    case 3:
                        score-=10;
                        return Environment.MOVE_RIGHT;
                }
            }
            if(location.equals(Environment.LOCATION_D)) {
                Random random = new Random();
                int a = random.nextInt(4);
                switch (a){
                    case 0:
                        score-=10;
                        return Environment.MOVE_UP;

                    case 1, 3:
                        score-=100;
                        return  null;
                    case 2:
                        score-=10;
                        return Environment.MOVE_LEFT;
                }
            }

        }


        return null;
    }
    public int getScore() {
        return score;
    }
}