package gautero.tuma.sendmealretry.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Application;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import gautero.tuma.sendmealretry.R;
import gautero.tuma.sendmealretry.database.AppRepository;
import gautero.tuma.sendmealretry.model.Plato;

public class AddPlato extends AppCompatActivity implements AppRepository.OnResultCallback{

    Application context = this.getApplication();

   AppRepository.OnResultCallback callback = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plato);

        Button guardar = findViewById(R.id.guardarbutton);
        final Plato platonuevo = new Plato();

        guardar.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View view) {

                EditText nombre = findViewById(R.id.nombreplato);
                EditText descripcion = findViewById(R.id.descripcionplato);
                EditText precio = findViewById(R.id.precioplato);
                EditText calorias = findViewById(R.id.caloriasplato);

                platonuevo.setTitulo(nombre.getText().toString());
                platonuevo.setDescripcion(descripcion.getText().toString());
                platonuevo.setPrecio(Double.parseDouble(precio.getText().toString()));
                platonuevo.setCalorias(Integer.parseInt(calorias.getText().toString()));


                AppRepository repository = new AppRepository(context, callback);
                repository.insertar(platonuevo);

                Toast.makeText(AddPlato.this, "Plato Guardado", Toast.LENGTH_SHORT).show();
                finish();

            }

        });

//        AppRepository repository = new AppRepository(this.getApplication(), this);
//        repository.insertar(platonuevo);

        Toolbar tb = findViewById(R.id.toolbarPlato);

        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                finish();
                return false;
            }
        });



    }

    @Override
    public void onResult(List result) {

    }
}