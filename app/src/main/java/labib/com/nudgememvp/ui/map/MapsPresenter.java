package labib.com.nudgememvp.ui.map;

import android.location.Location;

import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.ui.base.BasePresenter;

public class MapsPresenter extends BasePresenter<MapsContract.View> implements MapsContract.Presenter,
        MapsContract.LocationListener {


    public MapsPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void getCurrentLocation() {
        getView().disableTouch();
        getDataManager().getCurrentLocation(this);
    }


    @Override
    public void onCurrentLocationReady(Location location) {
        getView().restoreTouch();
        getView().onCurrentLocationUpdated(location);
    }
}
