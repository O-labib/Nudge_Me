package labib.com.nudgememvp.ui.main.inputFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.Window;

import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.base.BaseDialog;

public class InputFragment extends BaseDialog<InputContract.Presenter> implements InputContract.View {

    private static final String TAG = "InputDialog";


    public static InputFragment newInstance() {
        InputFragment fragment = new InputFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }


    @Override
    protected int getContentResource() {
        return R.layout.dialog_fragment_input;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        Window window = getDialog().getWindow();
        if (window!=null) window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    protected void injectDependencies() {
        getActivityComponent().inject(this);
    }

    @Override
    public void dismissDialog() {

    }
}
