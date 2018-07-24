package labib.com.nudgememvp.ui.input;

import labib.com.nudgememvp.ui.base.BaseMvpPresenter;
import labib.com.nudgememvp.ui.base.BaseMvpView;

public interface InputContract {

    interface View extends BaseMvpView {
        void highlightMapBtn();

        void onNudgeAdded();

        void updateDistance(String distance);

        void initWithRadius(int progress, String text);

        void onMapIntentReady();

        boolean isLocationPermitted();

        void requestLocationPermission();
    }

    interface Presenter extends BaseMvpPresenter<View>{
        void checkData(String message, String place, double lat, double lon, int distance);

        void onSeekbarChanged(int i);

        void getSavedRadius();


        void prepareMapIntent();
    }
}
