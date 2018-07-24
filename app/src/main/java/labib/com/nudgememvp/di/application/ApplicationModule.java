package labib.com.nudgememvp.di.application;


import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.preference.PreferenceManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import labib.com.nudgememvp.data.AppDataManager;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.data.db.AppDatabaseHelper;
import labib.com.nudgememvp.data.db.DatabaseOpenHelper;
import labib.com.nudgememvp.data.locationManager.AppLocationManagerHelper;
import labib.com.nudgememvp.data.sharePreferences.AppSharedPrefHelper;
import labib.com.nudgememvp.di.service.PerService;
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
    DataManager provideDataManager(AppDatabaseHelper appDatabaseHelper,
                                   AppLocationManagerHelper appLocationManagerHelper,
                                   AppSharedPrefHelper appSharedPrefHelper) {
        return new AppDataManager(appDatabaseHelper, appLocationManagerHelper, appSharedPrefHelper);
    }

    @Provides
    @Singleton
    FusedLocationProviderClient provideFusedLocation(@Named("applicationContext") Context context) {
        return LocationServices.getFusedLocationProviderClient(context);

    }


    @Provides
    @Singleton
    AppLocationManagerHelper provideLocationManagerHelper(FusedLocationProviderClient fusedLocationProviderClient) {
        return new AppLocationManagerHelper(fusedLocationProviderClient);
    }

    @Provides
    @Singleton
    LocationManager provideLocationManager(@Named("applicationContext") Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPref(@Named("applicationContext") Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    AppSharedPrefHelper provideSharedHelper(SharedPreferences sharedPreferences) {
        return new AppSharedPrefHelper(sharedPreferences);
    }

    @Provides
    @Singleton
    NotificationManager provideNotificationManager(@Named("applicationContext") Context context) {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
