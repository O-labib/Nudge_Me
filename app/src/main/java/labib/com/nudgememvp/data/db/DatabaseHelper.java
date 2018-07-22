package labib.com.nudgememvp.data.db;

import java.util.ArrayList;

public interface DatabaseHelper {

    long insertData(String message, String place, double lat, double lon);

    ArrayList<Nudge> getAllData();

    void clearAllData();

    void deleteSingleNudge(long id);
}
