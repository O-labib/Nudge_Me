package labib.com.nudgememvp.data;

import java.util.ArrayList;

import labib.com.nudgememvp.data.db.AppDatabaseHelper;
import labib.com.nudgememvp.data.db.Nudge;

public class AppDataManager implements DataManager {

    AppDatabaseHelper appDatabaseHelper;

    public AppDataManager(AppDatabaseHelper appDatabaseHelper) {
        this.appDatabaseHelper = appDatabaseHelper;
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
}
