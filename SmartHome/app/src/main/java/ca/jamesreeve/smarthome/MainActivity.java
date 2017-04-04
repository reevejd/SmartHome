package ca.jamesreeve.smarthome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView light0;
    ImageView light1;
    ImageView light2;
    ImageView light3;
    ImageView light4;
    ImageView light5;
    ImageView light6;
    ImageView light7;
    ImageView light8;
    ImageView[] lights = new ImageView[9];

    ImageView door0;
    ImageView door1;
    ImageView door2;
    ImageView door3;
    ImageView door4;
    ImageView[] doors = new ImageView[5];

    TextView tempdisplay;

    LightController lightController;
    static LightSettingsController lightSettingsController;


    TemperatureController temperatureController;

    DoorController doorController;
    static DoorSettingsController doorSettingsController;

    boolean lightsHidden = false;
    boolean doorsHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lightController = LightController.buildLightController(9, this);
        lightSettingsController = LightSettingsController.buildLightSettingsController();

        temperatureController = TemperatureController.buildTemperatureController(this);

        doorController = DoorController.buildDoorController(5, this);
        doorSettingsController = DoorSettingsController.buildDoorSettingsController();

        startService(new Intent(this,LightSettingsService.class));

        FloatingActionButton toggleLights = (FloatingActionButton) findViewById(R.id.toggleLights);
        toggleLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLights();
            }
        });

        FloatingActionButton toggleDoors = (FloatingActionButton) findViewById(R.id.toggleDoors);
        toggleDoors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleDoors();
            }
        });

        tempdisplay = (TextView) findViewById(R.id.tempdisplay);

        light0 = (ImageView) findViewById(R.id.light0);
        light0.setOnClickListener(this);
        light1 = (ImageView) findViewById(R.id.light1);
        light1.setOnClickListener(this);
        light2 = (ImageView) findViewById(R.id.light2);
        light2.setOnClickListener(this);
        light3 = (ImageView) findViewById(R.id.light3);
        light3.setOnClickListener(this);
        light4 = (ImageView) findViewById(R.id.light4);
        light4.setOnClickListener(this);
        light5 = (ImageView) findViewById(R.id.light5);
        light5.setOnClickListener(this);
        light6 = (ImageView) findViewById(R.id.light6);
        light6.setOnClickListener(this);
        light7 = (ImageView) findViewById(R.id.light7);
        light7.setOnClickListener(this);
        light8 = (ImageView) findViewById(R.id.light8);
        light8.setOnClickListener(this);
        lights[0] = light0;
        lights[1] = light1;
        lights[2] = light2;
        lights[3] = light3;
        lights[4] = light4;
        lights[5] = light5;
        lights[6] = light6;
        lights[7] = light7;
        lights[8] = light8;

        door0 = (ImageView) findViewById(R.id.door0);
        door0.setOnClickListener(this);
        door1 = (ImageView) findViewById(R.id.door1);
        door1.setOnClickListener(this);
        door2 = (ImageView) findViewById(R.id.door2);
        door2.setOnClickListener(this);
        door3 = (ImageView) findViewById(R.id.door3);
        door3.setOnClickListener(this);
        door4 = (ImageView) findViewById(R.id.door4);
        door4.setOnClickListener(this);

        doors[0] = door0;
        doors[1] = door1;
        doors[2] = door2;
        doors[3] = door3;
        doors[4] = door4;

    }

    public void toggleLights(){
        if(lightsHidden == true){
            lightsHidden = false;
            for(ImageView v : lights){
                v.setVisibility(View.VISIBLE);
            }
        }
        else{
            lightsHidden = true;
            for(ImageView v : lights){
                v.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void toggleDoors(){
        if(doorsHidden == true){
            doorsHidden = false;
            for(ImageView v : doors){
                v.setVisibility(View.VISIBLE);
            }
        }
        else{
            doorsHidden = true;
            for(ImageView v : doors){
                v.setVisibility(View.INVISIBLE);
            }
        }
    }


    public void setLightDisplay(int index, Light.State state) {
        lights[index].setImageResource(state == Light.State.ON ? R.drawable.circleon : R.drawable.circleoff);
    }

    public void setDoorDisplay(int index, Door.State state) {
        doors[index].setImageResource(state == Door.State.LOCKED ? R.drawable.lockedcircle : R.drawable.unlockedcircle);
    }

    public void setTemperatureDisplay(double value) {
        tempdisplay.setText(value + " C");
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.light0:
                if(lightController.getState(0) == Light.State.ON) {
                    light0.setImageResource(R.drawable.circleoff);
                }
                else{
                    light0.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(0);
                break;
            case R.id.light1:
                if(lightController.getState(1) == Light.State.ON) {
                    light1.setImageResource(R.drawable.circleoff);
                }
                else{
                    light1.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(1);
                break;
            case R.id.light2:
                if(lightController.getState(2) == Light.State.ON) {
                    light2.setImageResource(R.drawable.circleoff);
                }
                else{
                    light2.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(2);
                break;
            case R.id.light3:
                if(lightController.getState(3) == Light.State.ON) {
                    light3.setImageResource(R.drawable.circleoff);
                }
                else{
                    light3.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(3);
                break;
            case R.id.light4:
                if(lightController.getState(4) == Light.State.ON) {
                    light4.setImageResource(R.drawable.circleoff);
                }
                else{
                    light4.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(4);
                break;
            case R.id.light5:
                if(lightController.getState(5) == Light.State.ON) {
                    light5.setImageResource(R.drawable.circleoff);
                }
                else{
                    light5.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(5);
                break;
            case R.id.light6:
                if(lightController.getState(6) == Light.State.ON) {
                    light6.setImageResource(R.drawable.circleoff);
                }
                else{
                    light6.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(6);
                break;
            case R.id.light7:
                if(lightController.getState(7) == Light.State.ON) {
                    light7.setImageResource(R.drawable.circleoff);
                }
                else{
                    light7.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(7);
                break;
            case R.id.light8:
                if(lightController.getState(8) == Light.State.ON) {
                    light8.setImageResource(R.drawable.circleoff);
                }
                else{
                    light8.setImageResource(R.drawable.circleon);
                }
                lightController.changeState(8);
                break;
            case R.id.door0:
                if(doorController.getState(0) == Door.State.LOCKED){
                    door0.setImageResource(R.drawable.unlockedcircle);
                }
                else{
                    door0.setImageResource(R.drawable.lockedcircle);
                }
                doorController.changeState(0);
                break;
            case R.id.door1:
                if(doorController.getState(1) == Door.State.LOCKED){
                    door1.setImageResource(R.drawable.unlockedcircle);
                }
                else{
                    door1.setImageResource(R.drawable.lockedcircle);
                }
                doorController.changeState(1);
                break;
            case R.id.door2:
                if(doorController.getState(2) == Door.State.LOCKED){
                    door2.setImageResource(R.drawable.unlockedcircle);
                }
                else{
                    door2.setImageResource(R.drawable.lockedcircle);
                }
                doorController.changeState(2);
                break;
            case R.id.door3:
                if(doorController.getState(3) == Door.State.LOCKED){
                    door3.setImageResource(R.drawable.unlockedcircle);
                }
                else{
                    door3.setImageResource(R.drawable.lockedcircle);
                }
                doorController.changeState(3);
                break;
            case R.id.door4:
                if(doorController.getState(4) == Door.State.LOCKED){
                    door4.setImageResource(R.drawable.unlockedcircle);
                }
                else{
                    door4.setImageResource(R.drawable.lockedcircle);
                }
                doorController.changeState(4);
                break;
        }
    }

    public void displayEmergency(View v){
        Toast.makeText(this, "Emergency Services Notified", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //temperatureController.setThermostat(40);

            return true;
        }
        else if(id == R.id.LightSettings){
            Intent intent = new Intent(this, LightSettingsActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.DoorSettings){
            Intent intent = new Intent(this, DoorSettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
