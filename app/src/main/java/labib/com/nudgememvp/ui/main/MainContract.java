package labib.com.nudgememvp.ui.main;

import android.database.Cursor;

import java.util.ArrayList;

import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.ui.base.BaseMvpPresenter;
import labib.com.nudgememvp.ui.base.BaseMvpView;

public interface MainContract {

    interface View extends BaseMvpView{

        void initRecyclerView(ArrayList<Nudge> nudges);

        void updateRecyclerView(ArrayList<Nudge> nudges);
    }

    interface Presenter extends BaseMvpPresenter<View>{
        void insertData();

        void queryData();

        void queryInitialData();

        void clearData();

        void deleteOne(long id);
    }
}
