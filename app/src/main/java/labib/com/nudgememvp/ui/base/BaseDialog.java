package labib.com.nudgememvp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import labib.com.nudgememvp.di.activity.ActivityComponent;
import labib.com.nudgememvp.ui.main.MainActivity;

public abstract class BaseDialog<T extends BaseMvpPresenter> extends android.support.v4.app.DialogFragment implements BaseMvpDialog {

    private BaseActivity mActivity;
    Unbinder unbinder;

    @Inject
    T mPresenter;

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
//        setCancelable(false);

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

    @Override
    public void showMessage(String s) {
        if (mActivity != null) {
            mActivity.showMessage(s);
        }
    }

    public void show(FragmentManager fragmentManager, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
        if (prevFragment != null) {
            transaction.remove(prevFragment);
        }
        transaction.addToBackStack(null);
        this.show(transaction, tag);

    }

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    public void dismissDialog(String tag) {
        dismiss();
    }

    protected abstract int getContentResource();

    protected abstract void init(@Nullable Bundle state);

    protected abstract void injectDependencies();


    public interface Callback {

        //   void onFragmentAttached();
        void onFragmentDetached(String tag);
    }

}
