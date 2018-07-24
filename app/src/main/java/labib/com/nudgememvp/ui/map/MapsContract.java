package labib.com.nudgememvp.ui.map;

import android.location.Location;

import labib.com.nudgememvp.ui.base.BaseMvpPresenter;
import labib.com.nudgememvp.ui.base.BaseMvpView;

public interface MapsContract {

    interface View extends BaseMvpView {
        void onCurrentLocationUpdated(Location location);

        void restoreTouch();

        void disableTouch();
    }

    interface Presenter extends BaseMvpPresenter<View> {
        void getCurrentLocation();
    }

    interface LocationListener {
        void onCurrentLocationReady(Location location);
    }
}
