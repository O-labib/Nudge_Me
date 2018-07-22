package labib.com.nudgememvp.ui.main.inputFragment;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.base.BaseDialog;
import labib.com.nudgememvp.ui.map.MapsActivity;

public class InputFragment extends BaseDialog<InputContract.Presenter> implements InputContract.View {

    private static final String TAG = "InputDialog";
    private static final int MAP_RC = 70;

    double lat, lon;

    @BindView(R.id.messageInputET)
    EditText messageInputET;

    @BindView(R.id.placeInputET)
    EditText placeInputET;

    @BindView(R.id.mapBtn)
    ImageView mapBtn;


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


    @OnClick(R.id.mapBtn)
    public void mapIntent() {
        startActivityForResult(MapsActivity.getStartIntent(getHostActivity()), MAP_RC);
    }

    @OnClick(R.id.submitBtn)
    public void newNudge() {
        String message = messageInputET.getText().toString().trim();
        String place = placeInputET.getText().toString().trim();

        getPresenter().checkData(message, place, lat, lon);

    }
    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAP_RC && resultCode == Activity.RESULT_OK) {
            lat = data.getDoubleExtra("lat", 0);
            lon = data.getDoubleExtra("lon", 0);
        }
    }

    @Override
    public void highlightMapBtn() {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mapBtn, pvhX, pvhY);
        objectAnimator.setRepeatCount(3);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }
}
