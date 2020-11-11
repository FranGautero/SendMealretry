package gautero.tuma.sendmealretry.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import gautero.tuma.sendmealretry.model.Plato;

//TODO CHECKEAR QUE ONDA EL CONVERTER ESTE

public class Converters {
    @TypeConverter
    public static List<Plato> fromString(String value) {
        Type listType = new TypeToken<List<Plato>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<Plato> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}