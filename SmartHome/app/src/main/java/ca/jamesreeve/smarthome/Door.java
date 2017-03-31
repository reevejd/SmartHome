package ca.jamesreeve.smarthome;

/**
 * Created by nicol on 2017-03-31.
 */

public class Door {
    public enum State{
        LOCKED,UNLOCKED
    }

    private State state;
    private int id;


    public Door(int id){
        state = State.LOCKED;
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public State getState(){
        return state;
    }

    public void changeState(){
        if(state == State.LOCKED){
            state = State.UNLOCKED;
        }
        else{
            state = State.LOCKED;
        }
    }

}
