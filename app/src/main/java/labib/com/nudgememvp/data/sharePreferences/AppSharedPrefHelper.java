package labib.com.nudgememvp.data.sharePreferences;

import android.content.SharedPreferences;

public class AppSharedPrefHelper implements SharedPrefHelper {

    private static final String SERVICE_STATUS = "serviceStatus";
    private static final String DISTANCE = "distance";
    private SharedPreferences sharedPreferences;

    public AppSharedPrefHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void updateServiceStatus(boolean status) {
        sharedPreferences.edit().putBoolean(SERVICE_STATUS, status).apply();
    }

    @Override
    public boolean retrieveServiceStatus() {
        return sharedPreferences.getBoolean(SERVICE_STATUS, false);
    }

    @Override
    public void updateRadius(int radius) {
        sharedPreferences.edit().putInt(DISTANCE, radius).apply();
    }

    @Override
    public int retrieveRadius() {
        return sharedPreferences.getInt(DISTANCE, 10);
    }
}
