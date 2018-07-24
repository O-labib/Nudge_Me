package labib.com.nudgememvp.ui.main;

import java.util.ArrayList;

import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.ui.base.BaseMvpPresenter;
import labib.com.nudgememvp.ui.base.BaseMvpView;

public interface MainContract {

    interface View extends BaseMvpView{

        void initRecyclerView(ArrayList<Nudge> nudges);

        void updateRecyclerView(ArrayList<Nudge> nudges);

        void onBackgroundServicePrepared();

        boolean isLocationPermitted();

        void requestLocationPermission();

        void newAlertDialog(String[] inputs, boolean cancelable, AlertDialogListener listener);

    }

    interface Presenter extends BaseMvpPresenter<View>{
        void queryData();

        void queryInitialData();

        void clearData();

        void deleteOne(long id);

        void showServiceStatus();

        void prepareBackgroundService();
    }

    interface AlertDialogListener {
        void onPositiveBtnClicked();

        void onNegativeBtnClicked();
    }
}
