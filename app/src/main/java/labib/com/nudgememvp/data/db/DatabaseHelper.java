package labib.com.nudgememvp.data.db;

import android.database.Cursor;

public interface DatabaseHelper {

    long insertData();

    Cursor getAllData();


    void clearAllData();

    void deleteSingleNudge(long id);
}
