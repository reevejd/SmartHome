package ca.jamesreeve.smarthome;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by nicol on 2017-03-31.
 */

public class TempSettings {

    FirebaseDatabase database;
    DatabaseReference tempSettingsRef;

    boolean active;
    int changeH;
    int changeM;
    float temp;

    public TempSettings(){
        database = FirebaseDatabase.getInstance();
        tempSettingsRef = database.getReference("tempsettings");

        tempSettingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("db", "dbok in tempsettings.java");
                active = ((boolean) dataSnapshot.child("active").getValue());
                changeH = ((Number) dataSnapshot.child("changeH").getValue()).intValue();
                changeM = ((Number) dataSnapshot.child("changeM").getValue()).intValue();
                temp = ((Number) dataSnapshot.child("temp").getValue()).floatValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("db", "dberror in tempsettings.java");
                Log.e("db",databaseError.toString());
                active = false;
            }
        });
    }

    public void deactivate(){
        active = false;
    }

    public boolean isActive(){
        return active;
    }

    public int[] getChangeTime(){
        int[] changeTime = new int[2];
        changeTime[0] = changeH;
        changeTime[1] = changeM;
        return changeTime;
    }

    public float getTemp(){
        return temp;
    }

}
