package ca.jamesreeve.smarthome;

/**
 * Created by Nick on 3/29/2017.
 */

public class LightController {

    Light[] lights;

    public LightController(int n){
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
}
