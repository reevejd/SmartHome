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

    LightController lightController;
    static LightSettingsController lightSettingsController;

    boolean lightsHidden = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lightController = LightController.buildLightController(9, this);
        lightSettingsController = LightSettingsController.buildLightSettingsController();

        FloatingActionButton toggleLights = (FloatingActionButton) findViewById(R.id.toggleLights);
        toggleLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleLights();
            }
        });

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

    public void setLightDisplay(int index, Light.State state) {
        lights[index].setImageResource(state == Light.State.ON ? R.drawable.lighton : R.drawable.lightoff);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.light0:
                if(lightController.getState(0) == Light.State.ON) {
                    light0.setImageResource(R.drawable.lightoff);
                }
                else{
                    light0.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(0);
                break;
            case R.id.light1:
                if(lightController.getState(1) == Light.State.ON) {
                    light1.setImageResource(R.drawable.lightoff);
                }
                else{
                    light1.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(1);
                break;
            case R.id.light2:
                if(lightController.getState(2) == Light.State.ON) {
                    light2.setImageResource(R.drawable.lightoff);
                }
                else{
                    light2.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(2);
                break;
            case R.id.light3:
                if(lightController.getState(3) == Light.State.ON) {
                    light3.setImageResource(R.drawable.lightoff);
                }
                else{
                    light3.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(3);
                break;
            case R.id.light4:
                if(lightController.getState(4) == Light.State.ON) {
                    light4.setImageResource(R.drawable.lightoff);
                }
                else{
                    light4.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(4);
                break;
            case R.id.light5:
                if(lightController.getState(5) == Light.State.ON) {
                    light5.setImageResource(R.drawable.lightoff);
                }
                else{
                    light5.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(5);
                break;
            case R.id.light6:
                if(lightController.getState(6) == Light.State.ON) {
                    light6.setImageResource(R.drawable.lightoff);
                }
                else{
                    light6.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(6);
                break;
            case R.id.light7:
                if(lightController.getState(7) == Light.State.ON) {
                    light7.setImageResource(R.drawable.lightoff);
                }
                else{
                    light7.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(7);
                break;
            case R.id.light8:
                if(lightController.getState(8) == Light.State.ON) {
                    light8.setImageResource(R.drawable.lightoff);
                }
                else{
                    light8.setImageResource(R.drawable.lighton);
                }
                lightController.changeState(8);
                break;
        }
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
            return true;
        }
        else if(id == R.id.LightSettings){
            Intent intent = new Intent(this, LightSettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
