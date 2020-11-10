package gautero.tuma.sendmealretry.database;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import java.util.List;

import gautero.tuma.sendmealretry.asyncTaskRes.ConfirmarPedidoTask;
import gautero.tuma.sendmealretry.model.Pedido;
import gautero.tuma.sendmealretry.model.Plato;

public class PedidoRepository implements OnPedidoResultCallback{

    private PedidoDao pedidoDao;
    private AppRepository.OnResultCallback callback;

    public PedidoRepository(Application application, AppRepository.OnResultCallback context){
        AppDatabase db = AppDatabase.getInstance(application);
        pedidoDao = db.pedidoDao();
        callback = context;
    }

    public void insertar(final Pedido pedido){

        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.insertar(pedido);
            }
        });
    }

    public void borrar(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.borrar(pedido);
            }
        });
    }

    public void actualizar(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.actualizar(pedido);
            }
        });
    }

    public void buscar(final String id) {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.buscar(id);
            }
        });
    }
 //pasar el context correcto

    public void buscarTodos() {
        new ConfirmarPedidoTask(, pedidoDao, this).execute();
    }

    @Override
    public void onResult(List<Pedido> pedidos) {

        Log.d("DEBUG", "Pedido found");
        callback.onResult(pedidos);

    }

    public interface OnResultCallback<T> {
        void onResult(List<T> result);
    }
}
