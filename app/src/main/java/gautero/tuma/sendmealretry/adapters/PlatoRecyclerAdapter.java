package gautero.tuma.sendmealretry.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gautero.tuma.sendmealretry.R;
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
    public void onBindViewHolder(@NonNull PlatoHolder holder, int position) {

        String pr = "Precio: " + platoList.get(position).getPrecio();
        holder.precio.setText(pr);
        String cl = "Calorias: " + platoList.get(position).getCalorias();
        holder.calorias.setText(cl);
        holder.imagePlato.setImageResource(platoList.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return platoList.size();
    }

    public class PlatoHolder extends RecyclerView.ViewHolder{

        TextView precio, calorias;
        ImageView imagePlato;

        public PlatoHolder(@NonNull View itemView) {
            super(itemView);

            precio = itemView.findViewById(R.id.textPrecio);
            calorias = itemView.findViewById(R.id.textCalorias);
            imagePlato = itemView.findViewById(R.id.imagePlato);

        }
    }
}
