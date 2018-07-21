package labib.com.nudgememvp.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {


    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {

    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }
}
