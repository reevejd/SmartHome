package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by nicol on 2017-03-31.
 */

public class DoorController implements Observer {

    static Door[] doors;
    MainActivity viewController;

    static final DoorController buildDoorController(int n, MainActivity viewController) {
        DoorController result = new DoorController(n, viewController);

        for (Door door: doors) {
            door.addObserver(result);
        };

        return result;

    };

    private DoorController(int n, MainActivity viewController){
        doors = new Door[n];
        this.viewController = viewController;
        for(int i = 0; i < n; i++){
            Door door = new Door(i);
            doors[i] = door;
        }
    }

    public void changeState(int id){
        doors[id].changeState();
    }

    public Door.State getState(int id){
        return doors[id].getState();
    }

    @Override
    public void update(Observable door, Object arg) {
        Log.d("doorchange", "update: door status was changed to"+arg);
        // DoorController observes all instances of Door. When a change is detected,
        // the view is updated
        viewController.setDoorDisplay(
                ((Door) door).getId(),
                ((Door) door).getState()
        );
    }


}
