package gautero.tuma.sendmealretry.Actividades;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.Random;

import gautero.tuma.sendmealretry.R;



public class MapActivity extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap=googleMap;
        actualizarMapa(myMap);

        myMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                myMap.clear();
                myMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                Intent intentResultado = new Intent();
                intentResultado.putExtra("ubi", latLng);


                Random r = new Random();

                // Una direccion aleatoria de 0 a 359 grados
                int direccionRandomEnGrados = r.nextInt(360);

                // Una distancia aleatoria de 100 a 1000 metros
                int distanciaMinima = 100;
                int distanciaMaxima = 1000;
                int distanciaRandomEnMetros = r.nextInt(distanciaMaxima - distanciaMinima) + distanciaMinima;

                LatLng nuevaPosicion = SphericalUtil.computeOffset(
                        latLng,
                        distanciaRandomEnMetros,
                        direccionRandomEnGrados
                );

                myMap.addMarker(new MarkerOptions()
                        .position(nuevaPosicion)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                PolylineOptions rectOptions = new
                        PolylineOptions().add(latLng).color(Color.BLUE)
                        .add(nuevaPosicion).color(Color.BLUE);

                Polyline polyline = myMap.addPolyline(rectOptions);

                intentResultado.putExtra("ubiResto", nuevaPosicion);
                MapActivity.this.setResult(Activity.RESULT_OK, intentResultado);

            }




        });

    }
    private void actualizarMapa(GoogleMap mMap) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        mMap.setMyLocationEnabled(true);

    }
}



