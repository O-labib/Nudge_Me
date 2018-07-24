package labib.com.nudgememvp.di.service;

import dagger.Component;
import labib.com.nudgememvp.di.application.ApplicationComponent;
import labib.com.nudgememvp.service.LocationService;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(LocationService locationService);
}
