package gautero.tuma.sendmealretry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.List;

import gautero.tuma.sendmealretry.adapters.PlatoRecyclerAdapter;
import gautero.tuma.sendmealretry.model.Plato;
import gautero.tuma.sendmealretry.model.PlatoDao;

public class SelectPlato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        PlatoDao platolistCreator = new PlatoDao();
        List<Plato> platoList = platolistCreator.getListaPlato();

        RecyclerView platoRecycler = findViewById(R.id.platoRecycler);
        PlatoRecyclerAdapter platoAdapter = new PlatoRecyclerAdapter(this, platoList);
        platoRecycler.setAdapter(platoAdapter);
        platoRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
}