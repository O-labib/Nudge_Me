package labib.com.nudgememvp.ui.main;

import android.database.Cursor;

import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void insertData() {
        long result = getDataManager().insertData();
        if (result != -1) {
            queryData();
        }
    }


    @Override
    public void queryData() {
        Cursor cursor = getDataManager().getAllData();
        getView().updateRecyclerView(cursor);
    }

    @Override
    public void queryInitialData() {
        Cursor cursor = getDataManager().getAllData();
        getView().initRecyclerView(cursor);
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
