package gautero.tuma.sendmealretry.model;

import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pedidos")
public class Pedido {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "platos")
    private List<Plato> platos;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "direccion")
    private String direccion;


    @ColumnInfo(name = "delivery")
    private Boolean delivery;

    public Pedido() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean getDeliery(){
        return delivery;
    }

    public void setDelivery(Boolean param){
        this.delivery = param;
    }

    public List<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(List<Plato> platos) {
        this.platos = platos;
    }



}
