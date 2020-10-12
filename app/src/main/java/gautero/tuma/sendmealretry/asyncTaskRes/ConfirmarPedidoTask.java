package gautero.tuma.sendmealretry.asyncTaskRes;

import android.os.AsyncTask;
import android.widget.Toast;

import gautero.tuma.sendmealretry.MainActivity;
import gautero.tuma.sendmealretry.PedidoActivity;
import gautero.tuma.sendmealretry.model.Plato;


public class ConfirmarPedidoTask extends AsyncTask<String,Integer,Double> {


    @Override
    protected Double doInBackground(String... strings) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


}
