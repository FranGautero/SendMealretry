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
        item1.setCalorias((float) 0);
        item1.setPrecio((float) 0);
        item1.setDescripcion("plato random");
        item1.setNombre("Ramen Opción 1");
        item1.setImg(R.drawable.ramen);
        Plato item2 = new Plato();
        item2.setCalorias((float) 0);
        item2.setPrecio((float) 0);
        item2.setDescripcion("plato random");
        item2.setNombre("Ramen Opción 2");
        item2.setImg(R.drawable.ramen);
        Plato item3 = new Plato();
        item3.setCalorias((float) 0);
        item3.setPrecio((float) 0);
        item3.setDescripcion("plato random");
        item3.setNombre("Ramen Opción 3");
        item3.setImg(R.drawable.ramen);
        Plato item4 = new Plato();
        item4.setCalorias((float) 0);
        item4.setPrecio((float) 0);
        item4.setDescripcion("plato random");
        item4.setNombre("Ramen Opción 4");
        item4.setImg(R.drawable.ramen);
        Plato item5 = new Plato();
        item5.setCalorias((float) 0);
        item5.setPrecio((float) 0);
        item5.setDescripcion("plato random");
        item5.setNombre("Ramen Opción 5");
        item5.setImg(R.drawable.ramen);
        Plato item6 = new Plato();
        item6.setCalorias((float) 0);
        item6.setPrecio((float) 0);
        item6.setDescripcion("plato random");
        item6.setNombre("Ramen Opción 6");
        item6.setImg(R.drawable.ramen);

//        Log.d(TAG, "PlatoDao: " + listaPlato.size());

        lp.add(item1);
        lp.add(item2);
        lp.add(item3);
        lp.add(item4);
        lp.add(item5);
        lp.add(item6);

        this.listaPlato = lp;

 //       Log.d(TAG, "PlatoDao: " + listaPlato.size());


    }




}
