package labib.com.nudgememvp.data;

import android.database.Cursor;

import labib.com.nudgememvp.data.db.AppDatabaseHelper;

public class AppDataManager implements DataManager {

    AppDatabaseHelper appDatabaseHelper;

    public AppDataManager(AppDatabaseHelper appDatabaseHelper) {
        this.appDatabaseHelper = appDatabaseHelper;
    }

    @Override
    public long insertData() {
        return appDatabaseHelper.insertData();
    }

    @Override
    public Cursor getAllData() {
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
