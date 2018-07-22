package labib.com.nudgememvp.data.db;

import java.util.ArrayList;

public class AppDatabaseHelper implements DatabaseHelper {

    DatabaseOpenHelper databaseOpenHelper;

    public AppDatabaseHelper(DatabaseOpenHelper databaseOpenHelper) {
        this.databaseOpenHelper = databaseOpenHelper;
    }

    @Override
    public long insertData(String message, String place, double lat, double lon) {
        return databaseOpenHelper.newNudge(message, place, lat, lon);
    }

    @Override
    public ArrayList<Nudge> getAllData() {
        return databaseOpenHelper.getAllData();
    }

    @Override
    public void clearAllData() {
        databaseOpenHelper.clearAll();
    }

    @Override
    public void deleteSingleNudge(long id) {
        databaseOpenHelper.deleteOne(id);
    }
}
