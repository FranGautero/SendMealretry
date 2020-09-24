package gautero.tuma.sendmealretry.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gautero.tuma.sendmealretry.R;

public class PlatoDao {

    private static final String TAG = "tag";
    List <Plato> listaPlato;

    public List<Plato> getListaPlato() {
        return listaPlato;
    }

    public void setListaPlato(List<Plato> listaPlato) {
        this.listaPlato = listaPlato;
    }

    public PlatoDao() {
         List<Plato> lp = new ArrayList<>();
        Plato item1 = new Plato();
        item1.setCalorias(3000);
        item1.setPrecio(400);
        item1.setDescripcion("plato random");
        item1.setNombre("Ramen Opción 1");
        item1.setImg(R.drawable.ramen);
        Plato item2 = new Plato();
        item2.setCalorias(4000);
        item2.setPrecio(500);
        item2.setDescripcion("plato random");
        item2.setNombre("Ramen Opción 2");
        item2.setImg(R.drawable.ramen);
        Plato item3 = new Plato();
        item3.setCalorias(5000);
        item3.setPrecio(600);
        item3.setDescripcion("plato random");
        item3.setNombre("Ramen Opción 3");
        item3.setImg(R.drawable.ramen);
        Plato item4 = new Plato();
        item4.setCalorias(6000);
        item4.setPrecio(700);
        item4.setDescripcion("plato random");
        item4.setNombre("Ramen Opción 4");
        item4.setImg(R.drawable.ramen);
        Plato item5 = new Plato();
        item5.setCalorias(6000);
        item5.setPrecio(800);
        item5.setDescripcion("plato random");
        item5.setNombre("Ramen Opción 5");
        item5.setImg(R.drawable.ramen);
        Plato item6 = new Plato();
        item6.setCalorias(7000);
        item6.setPrecio(900);
        item6.setDescripcion("plato random");
        item6.setNombre("Ramen Opción 6");
        item6.setImg(R.drawable.ramen);
        Plato item7 = new Plato();
        item7.setCalorias(8000);
        item7.setPrecio(1000);
        item7.setDescripcion("plato random");
        item7.setNombre("Ramen Opción 7");
        item7.setImg(R.drawable.ramen);
        Plato item8 = new Plato();
        item8.setCalorias(8000);
        item8.setPrecio(1100);
        item8.setDescripcion("plato random");
        item8.setNombre("Ramen Opción 8");
        item8.setImg(R.drawable.ramen);

//        Log.d(TAG, "PlatoDao: " + listaPlato.size());

        lp.add(item1);
        lp.add(item2);
        lp.add(item3);
        lp.add(item4);
        lp.add(item5);
        lp.add(item6);
        lp.add(item7);
        lp.add(item8);


        this.listaPlato = lp;

 //       Log.d(TAG, "PlatoDao: " + listaPlato.size());


    }




}
