package labib.com.nudgememvp.di.application;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import labib.com.nudgememvp.data.AppDataManager;
import labib.com.nudgememvp.data.DataManager;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new AppDataManager();
    }
}
