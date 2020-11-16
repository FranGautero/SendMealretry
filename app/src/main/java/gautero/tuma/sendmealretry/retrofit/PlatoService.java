package gautero.tuma.sendmealretry.retrofit;

import java.util.List;

import gautero.tuma.sendmealretry.model.Plato;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlatoService {

    @GET("plato/{id}")
    Call<Plato> getPlato(@Path("id") String id);

    @GET("platos")
    Call<List<Plato>> getPlatoList();

    @POST("plato")
    Call<Plato> createPlato(@Body Plato plato);
}
