package labib.com.nudgememvp.di.activity;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.service.LocationService;
import labib.com.nudgememvp.ui.input.InputContract;
import labib.com.nudgememvp.ui.input.InputPresenter;
import labib.com.nudgememvp.ui.main.Adapter;
import labib.com.nudgememvp.ui.main.MainContract;
import labib.com.nudgememvp.ui.main.MainPresenter;
import labib.com.nudgememvp.ui.map.MapsContract;
import labib.com.nudgememvp.ui.map.MapsPresenter;


@Module
public class ActivityModule {

    private FragmentActivity mActivity;

    public ActivityModule(Context context) {
        try {
            mActivity = (FragmentActivity) context;
        } catch (Exception e) {

        }
    }

    @Provides
    @PerActivity
    MainContract.Presenter provideMainPresenter(DataManager dataManager) {
        return new MainPresenter(dataManager);
    }

    @Provides
    @PerActivity
    Adapter provideAdapter() {
        return new Adapter();
    }

    @Provides
    InputContract.Presenter provideInputPresenter(DataManager dataManager) {
        return new InputPresenter(dataManager);
    }


    @Provides
    @PerActivity
    MapsContract.Presenter provideMapsPresenter(DataManager dataManager) {
        return new MapsPresenter(dataManager);
    }


}
