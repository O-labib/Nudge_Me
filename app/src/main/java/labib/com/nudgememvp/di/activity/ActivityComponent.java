package labib.com.nudgememvp.di.activity;

import dagger.Component;
import labib.com.nudgememvp.di.application.ApplicationComponent;
import labib.com.nudgememvp.ui.input.InputActivity;
import labib.com.nudgememvp.ui.main.MainActivity;
import labib.com.nudgememvp.ui.map.MapsActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity target);

    void inject(InputActivity target);

    void inject(MapsActivity target);

}
