package labib.com.nudgememvp.ui.main;

import java.util.ArrayList;

import labib.com.nudgememvp.Logy;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter, MainContract.AlertDialogListener {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void queryData() {
        ArrayList<Nudge> data = getDataManager().getAllData();
        getView().updateRecyclerView(data);
    }

    @Override
    public void queryInitialData() {
        ArrayList<Nudge> data = getDataManager().getAllData();
        getView().initRecyclerView(data);
    }

    @Override
    public void clearData() {
        String inputs[] = new String[]{"Clear all data?", "Yes", "Cancel"};
        getView().newAlertDialog(inputs, true, this);
    }

    @Override
    public void deleteOne(long id) {
        getDataManager().deleteSingleNudge(id);
        queryData();
    }

    @Override
    public void showServiceStatus() {
        boolean status = getDataManager().retrieveServiceStatus();
        String message;
        if (status) {
            message = "The App is awake";
        } else {
            message = "The App is asleep";
        }
        getView().showMessage(message);
    }

    @Override
    public void prepareBackgroundService() {
        boolean isLocationPermitted = getView().isLocationPermitted();
        new Logy(isLocationPermitted);

        if (isLocationPermitted) {
            new Logy("permitted");
            getView().onBackgroundServicePrepared();
        } else {
            new Logy("permitted");
            getView().requestLocationPermission();

        }
    }

    @Override
    public void onPositiveBtnClicked() {

        getDataManager().clearAllData();
        queryData();
    }

    @Override
    public void onNegativeBtnClicked() {

    }
}
