package labib.com.nudgememvp.di.application;

import android.app.NotificationManager;

import javax.inject.Singleton;

import dagger.Component;
import labib.com.nudgememvp.App;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.service.NotifReciever;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);

    void inject(NotifReciever target);


    DataManager dataManager();

    NotificationManager notifManager();
}
