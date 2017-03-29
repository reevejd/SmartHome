package ca.jamesreeve.smarthome;

/**
 * Created by Nick on 3/29/2017.
 */

public class Light {

    public enum State{
        ON,OFF
    }

    private State state;
    private int id;

    public Light(int id){
        state = State.OFF;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public State getState(){
        return state;
    }

    public void changeState(){
        if(state == State.ON){
            state = State.OFF;
        }
        else{
            state = State.ON;
        }
    }
}
