package gautero.tuma.sendmealretry.Actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;

import gautero.tuma.sendmealretry.R;
import gautero.tuma.sendmealretry.database.AppRepository;
import gautero.tuma.sendmealretry.model.Plato;

public class AddPlato extends AppCompatActivity implements AppRepository.OnResultCallback{

    Application context = this.getApplication();

    Button selectFoto, sacarFoto;
    ImageView fotoPlato;
    private StorageReference mStorage;
    private byte[] data1;

   AppRepository.OnResultCallback callback = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plato);

        mStorage = FirebaseStorage.getInstance().getReference();

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

                StorageReference filePath = mStorage.child("Fotos").child(platonuevo.getTitulo());
                filePath.putBytes(data1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    }
                });

                //TODO agregar plato al json server

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

        selectFoto = findViewById(R.id.ButtonBuscarFoto);

        selectFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });

        sacarFoto = findViewById(R.id.buttonSacarFoto);

        sacarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarCamara();
            }
        });

    }

    @Override
    public void onResult(List result) {

    }

    static final int CAMARA_REQUEST = 1;
    static final int GALERIA_REQUEST = 2;

    private void lanzarCamara() {
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camaraIntent, CAMARA_REQUEST);
    }

    private void abrirGaleria() {
        Intent galeriaIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeriaIntent, GALERIA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == CAMARA_REQUEST || requestCode == GALERIA_REQUEST) && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data1 = baos.toByteArray(); // Imagen en arreglo de bytes

            fotoPlato = findViewById(R.id.fotoPlato);

            fotoPlato.setImageBitmap(imageBitmap);



        }
    }
}