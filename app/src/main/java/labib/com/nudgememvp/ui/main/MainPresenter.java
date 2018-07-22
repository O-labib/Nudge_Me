package labib.com.nudgememvp.ui.main;

import java.util.ArrayList;

import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void insertData() {
        long result = getDataManager().insertData("omar","labib",20,20);
        if (result != -1) {
            queryData();
        }
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
        getDataManager().clearAllData();
        queryData();
    }

    @Override
    public void deleteOne(long id) {
        getDataManager().deleteSingleNudge(id);
        queryData();
    }
}
