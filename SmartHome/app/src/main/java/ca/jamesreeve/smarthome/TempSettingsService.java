package ca.jamesreeve.smarthome;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.sql.Time;

public class TempSettingsService extends Service {

    int changeTime[];
    float temp;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);
        Log.d("LOG","Temp settings service started");
        changeTime = TempSettingsController.tempSettings.getChangeTime();
        temp = TempSettingsController.tempSettings.getTemp();

        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10000);
                        if(TempSettingsController.tempSettings.isActive()) {
                            int hours = new Time(System.currentTimeMillis()).getHours();
                            int minutes = new Time(System.currentTimeMillis()).getMinutes();
                            Log.d("CURRENT TIME", Integer.toString(hours) + " " + Integer.toString(minutes));

                            changeTime = TempSettingsController.tempSettings.getChangeTime();
                            Log.d("CHANGE TIME", Integer.toString(changeTime[0]) + " " + Integer.toString(changeTime[1]));
                            temp = TempSettingsController.tempSettings.getTemp();
                            Log.d("TEMP", Float.toString(temp));

                            //change temp
                            if (hours == changeTime[0] && minutes == changeTime[1]) {
                                //set temperature to stored value
                                TemperatureController.temperature.setTarget(temp);
                            }
                        }
                        else{
                            Log.d("ALERT","Temp settings are inactive");
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
        Log.d("LOG","Temp settings service destroyed");
    }
}
