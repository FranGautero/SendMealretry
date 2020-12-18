package gautero.tuma.sendmealretry.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import gautero.tuma.sendmealretry.Actividades.MainActivity;
import gautero.tuma.sendmealretry.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static String TAG = "data";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Pueden validar si el mensaje trae datos
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Payload del mensaje: " + remoteMessage.getData());
            // Realizar alguna acción en consecuencia
        }
        // Pueden validar si el mensaje trae una notificación
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Cuerpo de la notificación del mensaje: " + remoteMessage.getNotification().getBody());
            // También pueden usar:
            // remoteMessage.getNotification().getTitle()
        }
        // Cualquier otra acción que quieran realizar al recibir un mensaje de firebase, la pueden realizar aca
        // EJ:
        sendNotification("Su Pedido ha sido confirmado");
    }

    // Función para crear una notificación (completar)
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "pedidoNotifyChannel2";
            String desc = "canal creado";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel;
            channel = new NotificationChannel("pedidoNotify2", name, importance);
            channel.setDescription(desc);

            NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "pedidoNotify")
                .setSmallIcon(R.drawable.ic_baseline_airport_shuttle_24)
                .setContentTitle(messageBody)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(420, builder.build());

    }
}
