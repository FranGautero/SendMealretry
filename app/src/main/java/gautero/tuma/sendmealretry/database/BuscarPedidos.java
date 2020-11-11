package gautero.tuma.sendmealretry.database;

import android.os.AsyncTask;

import java.util.List;

import gautero.tuma.sendmealretry.model.Pedido;
import gautero.tuma.sendmealretry.model.Plato;

class BuscarPedidos extends AsyncTask<String, Void, List<Pedido>> {

    private PedidoDao dao;
    private OnPedidoResultCallback callback;

    public BuscarPedidos(PedidoDao dao, OnPedidoResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {
        return dao.buscarTodos();
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {
        super.onPostExecute(pedidos);
        callback.onResult(pedidos);
    }
}
