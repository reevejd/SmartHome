package ca.jamesreeve.smarthome;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Nick on 3/29/2017.
 */

public final class LightController implements Observer {

    static Light[] lights;

    static final LightController buildLightController(int n) {
        LightController result = new LightController(n);

        for (Light light: lights) {
            light.addObserver(result);
        };

        return result;

    };

    private LightController(int n){
        lights = new Light[n];
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
        // call display function here?
    }


}
