package labib.com.nudgememvp.di.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.ui.main.Adapter;
import labib.com.nudgememvp.ui.main.MainContract;
import labib.com.nudgememvp.ui.main.MainPresenter;
import labib.com.nudgememvp.ui.main.inputFragment.InputContract;
import labib.com.nudgememvp.ui.main.inputFragment.InputPresenter;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(Context context) {
        mActivity = (AppCompatActivity) context;
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
    FragmentManager provideFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
