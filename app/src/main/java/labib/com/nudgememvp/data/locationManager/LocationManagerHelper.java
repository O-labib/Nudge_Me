package labib.com.nudgememvp.data.locationManager;

import labib.com.nudgememvp.service.ServiceLocationListener;
import labib.com.nudgememvp.ui.map.MapsContract;

public interface LocationManagerHelper {

    void removeLocationUpdates();

    void getLocationUpdates(ServiceLocationListener locationListener);

    void getCurrentLocation(MapsContract.LocationListener locationListener);
}
