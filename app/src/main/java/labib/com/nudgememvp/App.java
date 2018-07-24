package labib.com.nudgememvp;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.di.application.ApplicationComponent;
import labib.com.nudgememvp.di.application.ApplicationModule;
import labib.com.nudgememvp.di.application.DaggerApplicationComponent;


public class App extends Application {

    @Inject
    DataManager dataManager;


    static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.inject(this);

    }

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }


}
