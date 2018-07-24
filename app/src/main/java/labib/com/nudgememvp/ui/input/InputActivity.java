package labib.com.nudgememvp.ui.input;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.base.BaseActivity;
import labib.com.nudgememvp.ui.map.MapsActivity;
import labib.com.nudgememvp.utils.CommonUtils;

public class InputActivity extends BaseActivity<InputContract.Presenter> implements InputContract.View, SeekBar.OnSeekBarChangeListener {

    private static final int MAP_RC = 70;
    private static final int PERMISSION_RC = 707;

    double lat, lon;

    @BindView(R.id.messageInputET)
    EditText messageInputET;

    @BindView(R.id.placeInputET)
    EditText placeInputET;

    @BindView(R.id.mapBtn)
    ImageView mapBtn;

    @BindView(R.id.metersTV)
    TextView metersTV;

    @BindView(R.id.seekBar)
    SeekBar seekBar;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, InputActivity.class);
    }



    @Override
    protected int getContentResource() {
        return R.layout.activity_input;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        seekBar.setOnSeekBarChangeListener(this);
        getPresenter().getSavedRadius();
    }


    @Override
    public void initWithRadius(int progress, String text) {
        seekBar.setProgress(progress);
        metersTV.setText(text);
    }

    @Override
    public void onMapIntentReady() {
        startActivityForResult(MapsActivity.getStartIntent(this), MAP_RC);
    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }


    @OnClick(R.id.mapBtn)
    public void mapBtn() {
        getPresenter().prepareMapIntent();
    }

    @OnClick(R.id.submitBtn)
    public void newNudge() {

        String message = messageInputET.getText().toString().trim();
        String place = placeInputET.getText().toString().trim();
        int distanceM = seekBar.getProgress();
        getPresenter().checkData(message, place, lat, lon, distanceM);

    }

    @OnClick(R.id.hintBtn)
    public void showHint() {
        String[] inputs = new String[]{"How far from the target should you be alerted?"};
        CommonUtils.newAlertDialog(this, inputs, true, null);
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

    @Override
    public void onNudgeAdded() {
        finish();
    }

    @Override
    public void updateDistance(String distance) {
        metersTV.setText(distance);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (i == 0) {
            seekBar.setProgress(1);
            i = 1;
        }
        getPresenter().onSeekbarChanged(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public boolean isLocationPermitted() {
        return ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_RC);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PERMISSION_RC == requestCode) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPresenter().prepareMapIntent();
            }
        }
    }

}
