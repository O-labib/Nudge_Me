package labib.com.nudgememvp.data.db;

import android.database.Cursor;

public class AppDatabaseHelper implements DatabaseHelper {

    DatabaseOpenHelper databaseOpenHelper;

    public AppDatabaseHelper(DatabaseOpenHelper databaseOpenHelper) {
        this.databaseOpenHelper = databaseOpenHelper;
    }

    @Override
    public long insertData() {
        return databaseOpenHelper.newNudge("omar", "labib", 15, 20);
    }

    @Override
    public Cursor getAllData() {
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
