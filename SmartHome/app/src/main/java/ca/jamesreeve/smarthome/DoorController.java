package ca.jamesreeve.smarthome;

/**
 * Created by nicol on 2017-03-31.
 */

public class DoorController {

    Door[] doors;
    public DoorController(int n){
        doors = new Door[n];
        for(int i = 0; i < n; i++){
            Door d = new Door(i);
            doors[i] = d;
        }
    }

    public void changeState(int id){
        doors[id].changeState();
    }

    public Door.State getState(int id){
        return doors[id].getState();
    }
}
