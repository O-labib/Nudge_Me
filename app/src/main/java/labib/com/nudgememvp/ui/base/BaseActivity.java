package labib.com.nudgememvp.ui.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import labib.com.nudgememvp.App;
import labib.com.nudgememvp.di.activity.ActivityComponent;
import labib.com.nudgememvp.di.activity.ActivityModule;
import labib.com.nudgememvp.di.activity.DaggerActivityComponent;


public abstract class BaseActivity<T extends BaseMvpPresenter> extends AppCompatActivity implements BaseMvpView {

    ActivityComponent activityComponent;

    @Inject
    T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentResource());

        ButterKnife.bind(this);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplicationContext()).getApplicationComponent())
                .activityModule(new ActivityModule(this)).build();

        injectDependencies();
        mPresenter.attach(this);
        init(savedInstanceState);


    }

    public ActivityComponent getComponent() {
        return activityComponent;
    }

    public T getPresenter() {
        return mPresenter;
    }


    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    @LayoutRes
    protected abstract int getContentResource();

    protected abstract void init(@Nullable Bundle state);

    protected abstract void injectDependencies();


    @Override
    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}
