package labib.com.nudgememvp.data.locationManager;

import android.annotation.SuppressLint;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import labib.com.nudgememvp.service.ServiceLocationListener;
import labib.com.nudgememvp.ui.map.MapsContract;

public class AppLocationManagerHelper implements LocationManagerHelper {


    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback currentLocationCallback;
    private LocationCallback locationUpdatesCallback;

    public AppLocationManagerHelper(FusedLocationProviderClient mFusedLocationClient) {
        this.mFusedLocationClient = mFusedLocationClient;

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void removeLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(locationUpdatesCallback);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void getLocationUpdates(final ServiceLocationListener locationListener) {

        locationUpdatesCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                locationListener.onLocationChanged(location);
            }
        };

        mFusedLocationClient.requestLocationUpdates(locationRequest, locationUpdatesCallback, null);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void getCurrentLocation(final MapsContract.LocationListener locationListener) {
        currentLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                locationListener.onCurrentLocationReady(location);
                mFusedLocationClient.removeLocationUpdates(currentLocationCallback);
            }
        };

        mFusedLocationClient.requestLocationUpdates(locationRequest, currentLocationCallback, null);
    }
}
