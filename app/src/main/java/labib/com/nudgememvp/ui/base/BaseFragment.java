package labib.com.nudgememvp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import labib.com.nudgememvp.di.activity.ActivityComponent;

abstract public class BaseFragment<T extends BaseMvpPresenter> extends android.support.v4.app.Fragment implements BaseMvpView {

    private BaseActivity mActivity;
    Unbinder unbinder;

    @Inject
    T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;


            injectDependencies();
            if (mPresenter != null) {
                mPresenter.attach(this);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentResource(), container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        init(savedInstanceState);
    }


    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getComponent();
        }
        return null;
    }

    public BaseActivity getHostActivity() {
        if (mActivity != null) {
            return mActivity;
        }
        return null;
    }

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void showMessage(String s) {
        if (mActivity != null) {
            mActivity.showMessage(s);
        }
    }

    protected abstract int getContentResource();

    protected abstract void init(@Nullable Bundle state);

    protected abstract void injectDependencies();

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        mPresenter.detach();
        super.onDestroyView();
    }
}
