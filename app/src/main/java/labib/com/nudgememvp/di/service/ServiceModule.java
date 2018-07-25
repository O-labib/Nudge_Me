package labib.com.nudgememvp.di.service;


import android.content.Context;
import android.media.MediaPlayer;

import dagger.Module;
import dagger.Provides;
import labib.com.nudgememvp.R;

@Module
public class ServiceModule {

    Context context;

    public ServiceModule(Context context) {
        this.context = context;
    }

}
