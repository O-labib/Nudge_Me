package labib.com.nudgememvp.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import labib.com.nudgememvp.App;
import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.ui.main.MainActivity;

public class NotifReciever extends BroadcastReceiver {

    @Inject
    DataManager dataManager;

    @Inject
    NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("DELETE_ACTION")) {
            ((App) context.getApplicationContext()).getApplicationComponent().inject(this);

            long nudgeId = intent.getLongExtra("nudgeId", 0);
            dataManager.deleteSingleNudge(nudgeId);
            ((MainActivity)context).onRestart();
            notificationManager.cancel((int) nudgeId);
        }

    }
}
