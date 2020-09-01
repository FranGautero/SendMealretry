package gautero.tuma.sendmealretry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nombre = findViewById(R.id.editTextTextPersonName);
        EditText pass = findViewById(R.id.editTextTextPassword);
        EditText passValidation = findViewById(R.id.editTextTextPassword2);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        RadioButton debito = findViewById(R.id.radioButton);
        RadioButton credito = findViewById(R.id.radioButton2);
        EditText numeroTarjeta = findViewById(R.id.editTextNumber);
        EditText cvv = findViewById(R.id.editTextNumber2);
        EditText mesVencimiento = findViewById(R.id.editTextNumber3);
        EditText yearVencimiento = findViewById(R.id.editTextNumber4);
        EditText cbu = findViewById(R.id.editTextNumber5);
        EditText aliasCbu = findViewById(R.id.editTextTextPersonName2);
        Switch cargaInicial = findViewById(R.id.switch1);
        SeekBar sb1 = findViewById(R.id.seekBar2);
        CheckBox tyc = findViewById(R.id.checkBox);
        Button Registrar = findViewById(R.id.button);





    }
}