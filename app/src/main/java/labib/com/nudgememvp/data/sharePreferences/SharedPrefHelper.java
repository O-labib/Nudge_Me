package labib.com.nudgememvp.data.sharePreferences;

public interface SharedPrefHelper {

    void updateServiceStatus(boolean status);

    boolean retrieveServiceStatus();

    void updateRadius(int radius);

    int retrieveRadius();

}
