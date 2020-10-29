package gautero.tuma.sendmealretry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;

import gautero.tuma.sendmealretry.adapters.PlatoRecyclerAdapter;
import gautero.tuma.sendmealretry.database.AppRepository;
import gautero.tuma.sendmealretry.model.Plato;
import gautero.tuma.sendmealretry.database.PlatoDao;

public class SelectPlato extends AppCompatActivity implements AppRepository.OnResultCallback{

    public static Activity fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        fa = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_plato);


        Toolbar tb = findViewById(R.id.toolbarSelectPlato);

        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                finish();
                return false;
            }
        });

        //generar la lista de platos de la db

        AppRepository repository = new AppRepository(this.getApplication(), this);
        repository.buscarTodos();

//        RecyclerView platoRecycler = findViewById(R.id.platoRecycler);
//        PlatoRecyclerAdapter platoAdapter = new PlatoRecyclerAdapter(this, platoList);
//        platoRecycler.setAdapter(platoAdapter);
//        platoRecycler.setLayoutManager(new LinearLayoutManager(this));




    }

    @Override
    public void onResult(List result) {
        RecyclerView platoRecycler = findViewById(R.id.platoRecycler);
        PlatoRecyclerAdapter platoAdapter = new PlatoRecyclerAdapter(this, result);
        platoRecycler.setAdapter(platoAdapter);
        platoRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}