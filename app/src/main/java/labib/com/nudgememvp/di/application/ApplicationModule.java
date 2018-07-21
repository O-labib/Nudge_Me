package labib.com.nudgememvp.di.application;


import android.app.Application;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import labib.com.nudgememvp.data.AppDataManager;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.data.db.AppDatabaseHelper;
import labib.com.nudgememvp.data.db.DatabaseOpenHelper;
import labib.com.nudgememvp.utils.AppConstants;

@Module
public class ApplicationModule {


    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Named("applicationContext")
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }


    @Provides
    @Named("databaseName")
    @Singleton
    String provideDatabaseName() {
        return AppConstants.Database_Name;
    }

    @Provides
    @Singleton
    int provideDatabaseVersion() {
        return AppConstants.Database_Version;
    }

    @Provides
    @Singleton
    DatabaseOpenHelper provideDBOpenHelper(@Named("applicationContext") Context context,
                                           @Named("databaseName") String databaseName,
                                           int databaseVersion) {
        return new DatabaseOpenHelper(context, databaseName, databaseVersion);
    }

    @Provides
    @Singleton
    AppDatabaseHelper provideDatabaseHelper(DatabaseOpenHelper databaseOpenHelper) {
        return new AppDatabaseHelper(databaseOpenHelper);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDatabaseHelper appDatabaseHelper) {
        return new AppDataManager(appDatabaseHelper);
    }
}
