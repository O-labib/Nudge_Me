package labib.com.nudgememvp.di.application;

import javax.inject.Singleton;

import dagger.Component;
import labib.com.nudgememvp.App;
import labib.com.nudgememvp.data.DataManager;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);

    DataManager dataManager();
}
