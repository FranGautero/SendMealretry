package gautero.tuma.sendmealretry.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Plato")
public class Plato {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "descripcion")
    private String descripcion;

    @ColumnInfo(name = "precio")
    private int precio;

    @ColumnInfo(name = "calorias")
    private int calorias;

    @ColumnInfo(name = "img")
    private int img;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
