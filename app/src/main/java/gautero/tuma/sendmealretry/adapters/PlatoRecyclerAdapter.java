package gautero.tuma.sendmealretry.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import gautero.tuma.sendmealretry.R;
import gautero.tuma.sendmealretry.Actividades.SelectPlato;
import gautero.tuma.sendmealretry.model.Plato;

public class PlatoRecyclerAdapter extends RecyclerView.Adapter<PlatoRecyclerAdapter.PlatoHolder>{

    List<Plato> platoList;

    Context context;




    public PlatoRecyclerAdapter(Context ct, List<Plato> pl){

        context = ct;
        platoList = pl;

    }

    @NonNull
    @Override
    public PlatoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.plato_row, parent, false);

        return new PlatoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlatoHolder holder, final int position) {

        String pr = "   Precio: $" + platoList.get(position).getPrecio();
        holder.precio.setText(pr);
        String cl = "   Calorias: " + platoList.get(position).getCalorias() + "  ";
        holder.calorias.setText(cl);

        final String tl = "   " + platoList.get(position).getTitulo();
        holder.titulo.setText(tl);
        String dc = "   Descripción: " + platoList.get(position).getDescripcion();
        holder.desc.setText(dc);
        holder.imageAdd.setImageResource(R.drawable.ic_baseline_add_24);


        Activity sp = SelectPlato.fa;

        StorageReference gsReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://tactile-bolt-296021.appspot.com/Fotos/"+platoList.get(position).getTitulo());

        final long THREE_MEGABYTE = 3 * 1024 * 1024;
        gsReference.getBytes(THREE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Exito
                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                DisplayMetrics dm = new DisplayMetrics();

                SelectPlato.fa.getWindowManager().getDefaultDisplay().getMetrics(dm);

                holder.imagePlato.setMinimumHeight(dm.heightPixels);
                holder.imagePlato.setMinimumWidth(dm.widthPixels);
                holder.imagePlato.setImageBitmap(bm);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                holder.imagePlato.setImageResource(R.drawable.ramen);
            }
        });




        if(sp.getIntent().getExtras().getInt("addCode") == 2){
            holder.imageAdd.setVisibility(View.INVISIBLE);
        }
        holder.imageAdd.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               Activity sp2 = SelectPlato.fa;
               Intent intentResultado = new Intent();

               Gson gson = new Gson();
               String myJson = gson.toJson(platoList.get(position));
               intentResultado.putExtra("myjson", myJson);
               intentResultado.putExtra("plato", platoList.get(position).getTitulo());
               sp2.setResult(Activity.RESULT_OK, intentResultado);
               sp2.finish();
           }
        });

    }

    @Override
    public int getItemCount() {
        return platoList.size();
    }

    public class PlatoHolder extends RecyclerView.ViewHolder{

        TextView precio, calorias, titulo, desc;
        ImageView imagePlato, imageAdd;

        public PlatoHolder(@NonNull View itemView) {
            super(itemView);

            precio = itemView.findViewById(R.id.textPrecio);
            calorias = itemView.findViewById(R.id.textCalorias);
            imagePlato = itemView.findViewById(R.id.imagePlato);
            titulo = itemView.findViewById(R.id.textNombre);
            desc = itemView.findViewById(R.id.textDescripcion);
            imageAdd = itemView.findViewById(R.id.imageViewAdd);

        }

        public ImageView getImageAdd() {
            return imageAdd;
        }

    }
}
