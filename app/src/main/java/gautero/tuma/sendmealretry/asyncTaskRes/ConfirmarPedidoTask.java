package gautero.tuma.sendmealretry.asyncTaskRes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;

import java.util.List;

import gautero.tuma.sendmealretry.database.OnPedidoResultCallback;
import gautero.tuma.sendmealretry.database.OnPlatoResultCallback;
import gautero.tuma.sendmealretry.database.PedidoDao;
import gautero.tuma.sendmealretry.database.PlatoDao;
import gautero.tuma.sendmealretry.model.Pedido;
import gautero.tuma.sendmealretry.notification.MyReciver;

import static java.lang.System.currentTimeMillis;


public class ConfirmarPedidoTask extends AsyncTask<String,Integer, List<Pedido>>{

    private PedidoDao dao;
    private OnPedidoResultCallback callback;
    private Context context;

    public ConfirmarPedidoTask(Context context, PedidoDao dao1, OnPedidoResultCallback resultCallback) {
        this.context = context;
        this.dao = dao1;
        this.callback = resultCallback;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {

        // no se porque va esto aca pero asi esta en todos lados
        return dao.buscarTodos();
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {

        Intent notificationIntent = new Intent(context, MyReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, 0) ;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE ) ;
        long currentTime = currentTimeMillis();
        alarmManager.set(AlarmManager.RTC_WAKEUP , currentTime , pendingIntent) ;

        super.onPostExecute(pedidos);
        callback.onResult(pedidos);

    }

}
