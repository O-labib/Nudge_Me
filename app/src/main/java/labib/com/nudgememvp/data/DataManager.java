package labib.com.nudgememvp.data;

import labib.com.nudgememvp.data.db.DatabaseHelper;
import labib.com.nudgememvp.data.locationManager.LocationManagerHelper;
import labib.com.nudgememvp.data.sharePreferences.SharedPrefHelper;
import labib.com.nudgememvp.ui.map.MapsContract;

public interface DataManager extends DatabaseHelper,LocationManagerHelper,SharedPrefHelper {



}
