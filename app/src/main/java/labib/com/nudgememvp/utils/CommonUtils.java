package labib.com.nudgememvp.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;

import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.main.MainContract;

public final class CommonUtils {
    private CommonUtils() {
    }

    public static void newNotification(Context context, NotificationManager notificationManager, String message, String title, long notifID) {

        // notification channel id
        String id = "com.nudgeMe.myNotificationId";

        // build the notification

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    new NotificationChannel(id, "notices", NotificationManager.IMPORTANCE_DEFAULT);
            if (notificationManager != null)
                notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, id);

        // set notification message, title
        builder.setContentText(message)
                .setContentTitle("New Notice at " + title)
                .setSmallIcon(R.drawable.ic_notification);

        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);


        Intent deleteIntent = new Intent();
        deleteIntent.setAction("DELETE_ACTION");
        deleteIntent.putExtra("nudgeId", notifID);
        PendingIntent pendingIntentDelete =
                PendingIntent.getBroadcast(context, 12345, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.addAction(R.drawable.ic_clear_all, "Delete", pendingIntentDelete);


        // display notification
        Notification notification = builder.build();
        if (notificationManager != null) notificationManager.notify((int) notifID, notification);
    }




    public static void newAlertDialog(Context context, String[] inputs, boolean cancelable, final MainContract.AlertDialogListener listener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setMessage(inputs[0]).setCancelable(cancelable);

        if ((inputs.length > 1 && !inputs[1].equals(""))) {

            alertDialog.setPositiveButton(inputs[1], new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    listener.onPositiveBtnClicked();
                }
            });

        }

        if ((inputs.length > 2 && !inputs[2].equals(""))) {

            alertDialog.setNegativeButton(inputs[2], new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    listener.onNegativeBtnClicked();
                }
            });

        }
        alertDialog.show();
    }

}
