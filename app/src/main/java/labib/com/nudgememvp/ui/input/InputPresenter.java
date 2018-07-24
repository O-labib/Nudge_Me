package labib.com.nudgememvp.ui.input;

import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.ui.base.BasePresenter;

public class InputPresenter extends BasePresenter<InputContract.View> implements InputContract.Presenter {

    public InputPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void checkData(String message, String place, double lat, double lon, int distanceM) {
        if (message.equals("")) {
            getView().showMessage("Please add a message");
            return;
        }
        if (place.equals("")) {
            getView().showMessage("Please add a place title");
            return;
        }
        if (lat == 0 || lon == 0) {
            getView().showMessage("Please pick coordinates from the map");
            getView().highlightMapBtn();
            return;
        }
        long result = getDataManager().insertData(message, place, lat, lon);
        if (result != -1) {
            getView().showMessage("successfully added");
            getView().onNudgeAdded();
        }

        getDataManager().updateRadius(distanceM * 10);
    }

    @Override
    public void onSeekbarChanged(int i) {
        int value = i * 10;
        String distance = value + " m";
        getView().updateDistance(distance);
    }

    @Override
    public void getSavedRadius() {
        int savedRadius = getDataManager().retrieveRadius();
        int progress = savedRadius / 10;
        String distance = savedRadius + " m";
        getView().initWithRadius(progress, distance);
    }

    @Override
    public void prepareMapIntent() {
        boolean isLocationPermitted = getView().isLocationPermitted();
        if (isLocationPermitted) {
            getView().onMapIntentReady();
        } else {
            getView().requestLocationPermission();
        }
    }
}
