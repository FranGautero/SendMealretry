package gautero.tuma.sendmealretry.Actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import gautero.tuma.sendmealretry.R;
import gautero.tuma.sendmealretry.database.AppRepository;
import gautero.tuma.sendmealretry.model.Plato;
import gautero.tuma.sendmealretry.retrofit.PlatoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements AppRepository.OnResultCallback{
    final static int CODIGO_BUSCAR_PLATO = 420;
    AppRepository.OnResultCallback callback = this;
    private List tos;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
//        // Iniciar Session como usuario anónimo
        signInAnonymously();

        Toolbar tb = findViewById(R.id.toolbarHome);

        AppRepository repository = new AppRepository(HomeActivity.super.getApplication(), callback);

        repository.buscarTodos();



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

    private void getPlatoList(){

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.5:3001/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PlatoService platoService = retrofit.create(PlatoService.class);

        Call<List<Plato>> call = platoService.getPlatoList();

        call.enqueue(new Callback<List<Plato>>() {
            @Override
            public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                if (response.code() == 200) {
                    Log.d("DEBUG", "Returno Exitoso");
                }

                List<Plato> Platos = response.body();

                assert Platos != null;


                if(tos.isEmpty() || tos.size() < Platos.size()) {
                    AppRepository repository2 = new AppRepository(HomeActivity.super.getApplication(), callback);
                    for (Plato p : Platos) {
                        repository2.insertar(p);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Plato>> call, Throwable t) {
                Log.d("DEBUG", "Retorno Fallido");
            }
        });

    }

    @Override
    public void onResult(List result) {
        tos = result;
        getPlatoList();
    }




    private void signInAnonymously() {
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Exito
                            Log.d("TAG", "signInAnonymously:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // Error
                            Log.w("TAG", "signInAnonymously:failure", task.getException());
                            Toast.makeText(HomeActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}