package labib.com.nudgememvp.ui.main;

import android.database.Cursor;

import labib.com.nudgememvp.ui.base.BaseMvpPresenter;
import labib.com.nudgememvp.ui.base.BaseMvpView;

public interface MainContract {

    interface View extends BaseMvpView{

        void initRecyclerView(Cursor cursor);

        void updateRecyclerView(Cursor cursor);
    }

    interface Presenter extends BaseMvpPresenter<View>{
        void insertData();

        void queryData();

        void queryInitialData();

        void clearData();

        void deleteOne(long id);
    }
}
