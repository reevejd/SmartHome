package ca.jamesreeve.smarthome;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import java.sql.Time;

public class LightSettingsService extends Service {

    int onTime[];
    int offTime[];

    boolean turnOn = false;
    boolean turnOff = false;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);
        Log.d("LOG","Light settings service started");
        onTime = LightSettingsController.lightSettings.getOnTime();
        offTime = LightSettingsController.lightSettings.getOffTime();

        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10000);
                        if(LightSettingsController.lightSettings.isActive()) {
                            int hours = new Time(System.currentTimeMillis()).getHours();
                            int minutes = new Time(System.currentTimeMillis()).getMinutes();
                            Log.d("CURRENT TIME", Integer.toString(hours) + " " + Integer.toString(minutes));

                            onTime = LightSettingsController.lightSettings.getOnTime();
                            offTime = LightSettingsController.lightSettings.getOffTime();
                            Log.d("ON TIME", Integer.toString(onTime[0]) + " " + Integer.toString(onTime[1]));
                            Log.d("OFF TIME", Integer.toString(offTime[0]) + " " + Integer.toString(offTime[1]));

                            //turn on
                            if (hours == onTime[0] && minutes == onTime[1]) {
                                for (Light l : LightController.lights) {
                                    l.turnOn();
                                }
                            }
                            //turn off
                            if (hours == offTime[0] && minutes == offTime[1]) {
                                for (Light l : LightController.lights) {
                                    l.turnOff();
                                }
                            }
                        }
                        else{
                            Log.d("ALERT","Light settings are inactive");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    };
                }
            }
        }).start();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("LOG","Light settings service destroyed");
    }
}
