package ca.jamesreeve.smarthome;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.sql.Time;

public class DoorSettingsService extends Service {

    int lockTime[];
    int unlockTime[];

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId){
        super.onStart(intent, startId);
        Log.d("LOG","Door settings service started");
        lockTime = DoorSettingsController.doorSettings.getLockTime();
        unlockTime = DoorSettingsController.doorSettings.getUnlockTime();

        new Thread(new Runnable(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(10000);
                        if(DoorSettingsController.doorSettings.isActive()) {
                            int hours = new Time(System.currentTimeMillis()).getHours();
                            int minutes = new Time(System.currentTimeMillis()).getMinutes();
                            Log.d("CURRENT TIME", Integer.toString(hours) + " " + Integer.toString(minutes));

                            lockTime = DoorSettingsController.doorSettings.getLockTime();
                            unlockTime = DoorSettingsController.doorSettings.getUnlockTime();
                            Log.d("ON TIME", Integer.toString(lockTime[0]) + " " + Integer.toString(lockTime[1]));
                            Log.d("OFF TIME", Integer.toString(unlockTime[0]) + " " + Integer.toString(unlockTime[1]));

                            //lock doors
                            if (hours == lockTime[0] && minutes == lockTime[1]) {
                                for (Door d : DoorController.doors) {
                                    d.lock();
                                }
                            }
                            //turn off
                            if (hours == unlockTime[0] && minutes == unlockTime[1]) {
                                for (Door d : DoorController.doors) {
                                    d.unlock();
                                }
                            }
                        }
                        else{
                            Log.d("ALERT","Door settings are inactive");
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
        Log.d("LOG","Door settings service destroyed");
    }
}
