package gautero.tuma.sendmealretry.asyncTaskRes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;

import gautero.tuma.sendmealretry.notification.MyReciver;

import static java.lang.System.currentTimeMillis;


public class ConfirmarPedidoTask extends AsyncTask<String,Integer,Double> {


    private Context context;

    public ConfirmarPedidoTask(Context context) {
        this.context = context;
    }

    @Override
    protected Double doInBackground(String... strings) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Double aDouble) {

        Intent notificationIntent = new Intent(context, MyReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, 0) ;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE ) ;
        long currentTime = currentTimeMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP , currentTime , pendingIntent) ;

    }

}
