package gautero.tuma.sendmealretry.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import gautero.tuma.sendmealretry.model.Plato;

public class Converters {
    @TypeConverter
    public static ArrayList<Plato> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Plato>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Plato> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}