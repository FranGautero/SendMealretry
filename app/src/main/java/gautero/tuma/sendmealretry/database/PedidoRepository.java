package gautero.tuma.sendmealretry.database;

import android.app.Application;
import android.content.Context;
import android.util.Log;


import java.util.List;

import gautero.tuma.sendmealretry.PedidoActivity;
import gautero.tuma.sendmealretry.asyncTaskRes.ConfirmarPedidoTask;
import gautero.tuma.sendmealretry.model.Pedido;
import gautero.tuma.sendmealretry.model.Plato;

public class PedidoRepository implements OnPedidoResultCallback{

    private PedidoDao pedidoDao;
    private OnPedidoResultCallback callback;

    public PedidoRepository(Application application, OnPedidoResultCallback context){
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


    public void buscarTodos() {
        new BuscarPedidos(pedidoDao, this).execute();
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
