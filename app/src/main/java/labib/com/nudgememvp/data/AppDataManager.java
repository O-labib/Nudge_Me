package labib.com.nudgememvp.data;

import java.util.ArrayList;

import labib.com.nudgememvp.data.db.AppDatabaseHelper;
import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.data.locationManager.AppLocationManagerHelper;
import labib.com.nudgememvp.data.sharePreferences.AppSharedPrefHelper;
import labib.com.nudgememvp.service.ServiceLocationListener;
import labib.com.nudgememvp.ui.map.MapsContract;

public class AppDataManager implements DataManager {

    private AppDatabaseHelper appDatabaseHelper;
    private AppLocationManagerHelper appLocationManagerHelper;
    private AppSharedPrefHelper appSharedPrefHelper;

    public AppDataManager(AppDatabaseHelper appDatabaseHelper, AppLocationManagerHelper appLocationManagerHelper, AppSharedPrefHelper appSharedPrefHelper) {
        this.appDatabaseHelper = appDatabaseHelper;
        this.appLocationManagerHelper = appLocationManagerHelper;
        this.appSharedPrefHelper = appSharedPrefHelper;
    }

    @Override
    public long insertData(String message, String place, double lat, double lon) {
        return appDatabaseHelper.insertData(message, place, lat, lon);
    }

    @Override
    public ArrayList<Nudge> getAllData() {
        return appDatabaseHelper.getAllData();
    }

    @Override
    public void clearAllData() {
        appDatabaseHelper.clearAllData();
    }

    @Override
    public void deleteSingleNudge(long id) {
        appDatabaseHelper.deleteSingleNudge(id);
    }


    @Override
    public void removeLocationUpdates() {
        appLocationManagerHelper.removeLocationUpdates();
    }

    @Override
    public void getLocationUpdates(ServiceLocationListener locationListener) {
        appLocationManagerHelper.getLocationUpdates(locationListener);
    }

    @Override
    public void getCurrentLocation(MapsContract.LocationListener locationListener) {
        appLocationManagerHelper.getCurrentLocation(locationListener);
    }

    @Override
    public void updateServiceStatus(boolean status) {
        appSharedPrefHelper.updateServiceStatus(status);
    }

    @Override
    public boolean retrieveServiceStatus() {
        return appSharedPrefHelper.retrieveServiceStatus();
    }

    @Override
    public void updateRadius(int radius) {
        appSharedPrefHelper.updateRadius(radius);
    }

    @Override
    public int retrieveRadius() {
        return appSharedPrefHelper.retrieveRadius();

    }
}
