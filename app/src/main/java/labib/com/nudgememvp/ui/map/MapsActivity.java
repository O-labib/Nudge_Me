package labib.com.nudgememvp.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.OnClick;
import labib.com.nudgememvp.Logy;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.base.BaseMapActivity;

public class MapsActivity extends BaseMapActivity<MapsContract.Presenter> implements MapsContract.View, OnMapReadyCallback, GoogleMap.OnMapLongClickListener, PlaceSelectionListener {

    private GoogleMap mMap;
    PlaceAutocompleteFragment placeAutoComplete;


    public static Intent getStartIntent(Context context) {
        return new Intent(context, MapsActivity.class);
    }


    @Override
    protected int getContentResource() {
        return R.layout.activity_maps;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.searchBar);
        placeAutoComplete.setBoundsBias(new LatLngBounds(new LatLng(31.4260706, 31.6754662), new LatLng(31.4260706, 31.6754662)));
        placeAutoComplete.setOnPlaceSelectedListener(this);
    }


    @OnClick(R.id.getLocationBtn)
    public void getLocation() {
        getPresenter().getCurrentLocation();
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        showMessage("Long click on a place to pick it");

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setOnMapLongClickListener(this);

        getPresenter().getCurrentLocation();
    }


    @Override
    public void onCurrentLocationUpdated(Location location) {

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latLng).title("Your location"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 19));
        placeAutoComplete.setBoundsBias(new LatLngBounds(latLng, latLng));

    }


    @Override
    public void onMapLongClick(LatLng latLng) {

        Intent intent = getIntent();
        intent.putExtra("lat", latLng.latitude);
        intent.putExtra("lon", latLng.longitude);
        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void onPlaceSelected(Place place) {
        mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title((String) place.getName()));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
    }

    @Override
    public void onError(Status status) {
    }

    @Override
    public void showMessage(String s) {
        Toast.makeText(MapsActivity.this, s, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void disableTouch() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    @Override
    public void restoreTouch() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}
