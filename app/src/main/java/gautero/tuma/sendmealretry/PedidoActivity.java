package gautero.tuma.sendmealretry;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gautero.tuma.sendmealretry.asyncTaskRes.ConfirmarPedidoTask;
import gautero.tuma.sendmealretry.database.PedidoDao;
import gautero.tuma.sendmealretry.model.Pedido;
import gautero.tuma.sendmealretry.model.Plato;

public class PedidoActivity extends AppCompatActivity {

    private static final int CODIGO_VER_PLATOS = 421;
    Button sp, cp;
    String lista = "";
    List<Plato> listaPlatos = new ArrayList<Plato>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        Toolbar tb = findViewById(R.id.toolbarPedido);

        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                finish();
                return false;
            }
        });


        sp = findViewById(R.id.buttonAddPlato);
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(PedidoActivity.this, SelectPlato.class);
                int a = 1;
                i.putExtra("addCode", a);
                startActivityForResult(i,CODIGO_VER_PLATOS);

            }

        });

        cp = findViewById(R.id.buttonConfirmar);
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Se lanza tarea Asincrónica
                //deberia guardar el pedido en la db
                ConfirmarPedidoTask ctask = new ConfirmarPedidoTask(PedidoActivity.this);
                ctask.execute(lista);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODIGO_VER_PLATOS){

            //en teoria aca recupero el objeto plato que seleccione
            Gson gson = new Gson();
            Plato ob = gson.fromJson(getIntent().getStringExtra("myjson"), Plato.class);
            listaPlatos.add(ob);

            // insertar el pedido en la db


            String platoName =
                    data.getExtras().getString("plato");
            TextView tv = findViewById(R.id.listaP);
            lista += platoName + ", ";
            tv.setText(lista);

        }
    }
}