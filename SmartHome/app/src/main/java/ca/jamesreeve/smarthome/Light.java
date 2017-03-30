package ca.jamesreeve.smarthome;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Nick on 3/29/2017.
 */

public class Light {

    FirebaseDatabase database;
    DatabaseReference lightRef;

    public enum State{
        ON,OFF
    }

    private State state;
    private int id;

    public Light(int id){
        state = State.OFF;
        this.id = id;

        FirebaseDatabase.getInstance();
        lightRef = database.getReference("lights/0");
        lightRef.setValue("Test!");


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
