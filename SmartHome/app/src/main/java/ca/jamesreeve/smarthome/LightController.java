package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 3/29/2017.
 */

public final class LightController implements Observer {

    static Light[] lights;
    MainActivity viewController;

    static final LightController buildLightController(int n, MainActivity viewController) {
        LightController result = new LightController(n, viewController);

        for (Light light: lights) {
            light.addObserver(result);
        };

        return result;

    };

    private LightController(int n, MainActivity viewController){
        lights = new Light[n];
        this.viewController = viewController;
        for(int i = 0; i < n; i++){
            Light light = new Light(i);
            lights[i] = light;
        }
    }

    public void changeState(int id){
        lights[id].changeState();
    }

    public Light.State getState(int id){
        return lights[id].getState();
    }

    @Override
    public void update(Observable light, Object arg) {
        Log.d("lightchange", "update: light status was changed to"+arg);
        // LightController observes all instances of Light. When a change is detected,
        // the view is updated
        viewController.setLightDisplay(
                ((Light) light).getId(),
                ((Light) light).getState()
        );
    }


}
