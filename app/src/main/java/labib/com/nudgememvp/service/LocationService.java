package labib.com.nudgememvp.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

import java.util.ArrayList;

import javax.inject.Inject;

import labib.com.nudgememvp.App;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.di.service.DaggerServiceComponent;
import labib.com.nudgememvp.di.service.ServiceComponent;
import labib.com.nudgememvp.utils.CommonUtils;

public class LocationService extends Service implements ServiceLocationListener {

    @Inject
    DataManager dataManager;

    @Inject
    NotificationManager notificationManager;

    int radius;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LocationService.class);
    }

    public static void start(Context context) {
        Intent starter = getStartIntent(context);
        context.startService(starter);
    }

    public static void stop(Context context) {
        context.stopService(getStartIntent(context));
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent component = DaggerServiceComponent.builder()
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .build();
        component.inject(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        getDataManager().updateServiceStatus(true);
        radius = getDataManager().retrieveRadius();
        getDataManager().getLocationUpdates(this);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {
        ArrayList<Nudge> nudges;
        nudges = getDataManager().getAllData();


        if (nudges == null || nudges.size() == 0) {
            onDestroy();
            return;
        }

        double currentLat = location.getLatitude();
        double currentLon = location.getLongitude();


        for (int i = 0; i < nudges.size(); i++) {
            double nudgeeLat = nudges.get(i).getLat();
            double nudgeLon = nudges.get(i).getLon();

            float[] results = new float[1];
            Location.distanceBetween(currentLat, currentLon, nudgeeLat, nudgeLon, results);

            if (results[0] <= radius) {

                String message = nudges.get(i).getMessage();
                String title = nudges.get(i).getPlaceName();
                long id = nudges.get(i).getId();
                CommonUtils.newNotification(this, notificationManager, message, title, id);
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getDataManager().updateServiceStatus(false);
        getDataManager().removeLocationUpdates();
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
