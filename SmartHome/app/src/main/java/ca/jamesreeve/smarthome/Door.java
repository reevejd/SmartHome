package ca.jamesreeve.smarthome;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Observable;

/**
 * Created by nicol on 2017-03-31.
 */

public class Door extends Observable {

    FirebaseDatabase database;
    DatabaseReference doorRef;

    public enum State{
        LOCKED,UNLOCKED
    }

    private State state;
    private int id;


    public Door(int id){
        state = State.LOCKED;
        this.id = id;

        database = FirebaseDatabase.getInstance();

        doorRef = database.getReference("doors/"+id);
        doorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                state = (((String) dataSnapshot.getValue()).equals("LOCKED") ? Door.State.LOCKED : Door.State.UNLOCKED);
                setChanged();
                notifyObservers();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("dberror", "db error in light.java: ");
                state = Door.State.LOCKED;
                doorRef.setValue(state);
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
        state = (state == Door.State.LOCKED ? state.UNLOCKED : state.LOCKED);
        doorRef.setValue(state);
    }

}
