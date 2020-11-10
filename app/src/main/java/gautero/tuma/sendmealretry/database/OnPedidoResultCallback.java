package gautero.tuma.sendmealretry.database;

import java.util.List;

import gautero.tuma.sendmealretry.model.Pedido;

public interface OnPedidoResultCallback {

    void onResult(List<Pedido> pedidos);

}
