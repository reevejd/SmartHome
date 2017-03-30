package ca.jamesreeve.smarthome;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

/**
 * Created by Nick on 3/29/2017.
 */

public class Light extends Observable {

    FirebaseDatabase database;
    DatabaseReference lightRef;

    public enum State{
        ON,OFF
    }

    private State state;
    private int id;

    public Light(int id){
        this.id = id;

        database = FirebaseDatabase.getInstance();

        lightRef = database.getReference("lights/"+id);
        lightRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                state = (((String) dataSnapshot.getValue()).equals("ON") ? State.ON : State.OFF);
                setChanged();
                notifyObservers(this);
                // need to call display method here?
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("dberror", "db error in light.java: ");
                state = State.OFF;
                lightRef.setValue(state);
                setChanged();
                notifyObservers();
            }
        });




    }

    public int getId(){
        return id;
    }

    public State getState(){
        return state;
    }

    public void changeState(){
        state = (state == State.ON ? state.OFF : state.ON);
        lightRef.setValue(state);

    }
}
