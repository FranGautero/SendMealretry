package gautero.tuma.sendmealretry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    final static int CODIGO_BUSCAR_PLATO = 420;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar tb = findViewById(R.id.toolbarHome);

        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings1:
                        Intent i = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(i);
                        return false;

                    case R.id.action_settings2:
                        Intent i2 = new Intent(HomeActivity.this, AddPlato.class);
                        startActivity(i2);
                        return false;

                    case R.id.action_settings3:
                        Intent i3 = new Intent(HomeActivity.this, SelectPlato.class);
                        int a = 2;
                        i3.putExtra("addCode", a);
                        startActivity(i3);

                        return false;
                    case R.id.action_settings4:
                        Intent i4 = new Intent(HomeActivity.this, PedidoActivity.class);
                        startActivity(i4);
                        return false;
                    default:
                        return false;

                }
            }
        });


    }
}