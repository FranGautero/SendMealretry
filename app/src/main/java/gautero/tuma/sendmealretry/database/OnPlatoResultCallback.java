package gautero.tuma.sendmealretry.database;

import java.util.List;

import gautero.tuma.sendmealretry.model.Plato;

public interface OnPlatoResultCallback {
    void onResult(List<Plato> plato);
}
