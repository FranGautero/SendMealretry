package gautero.tuma.sendmealretry.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import gautero.tuma.sendmealretry.model.Pedido;
import gautero.tuma.sendmealretry.model.Plato;

@Dao
public interface PedidoDao {

    @Insert
    void insertar(Pedido pedido);

    @Delete
    void borrar(Pedido pedido);

    @Update
    void actualizar(Pedido pedido);

    @Query("SELECT * FROM pedidos WHERE id = :id LIMIT 1")
    Plato buscar(String id);

    @Query("SELECT * FROM pedidos")
    List<Pedido> buscarTodos();
}
