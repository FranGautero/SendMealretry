package gautero.tuma.sendmealretry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import gautero.tuma.sendmealretry.model.Plato;

public class AddPlato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plato);

        Button guardar = findViewById(R.id.guardarbutton);

        guardar.setOnClickListener(new View.OnClickListener(){



            @Override
            public void onClick(View view) {

                EditText nombre = findViewById(R.id.nombreplato);
                EditText descripcion = findViewById(R.id.descripcionplato);
                EditText precio = findViewById(R.id.precioplato);
                EditText calorias = findViewById(R.id.caloriasplato);
                Plato platonuevo = new Plato();
                platonuevo.setNombre(nombre.getText().toString());
                platonuevo.setDescripcion(descripcion.getText().toString());
                platonuevo.setPrecio(Float.parseFloat(precio.getText().toString()));
                platonuevo.setCalorias(Float.parseFloat(calorias.getText().toString()));

                Toast.makeText(AddPlato.this, "Plato Guardado", Toast.LENGTH_SHORT).show();

                finish();

            }

        });

        Toolbar tb = findViewById(R.id.toolbarPlato);

        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                finish();
                return false;
            }
        });



    }
}