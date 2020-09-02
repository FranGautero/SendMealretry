package gautero.tuma.sendmealretry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Boolean ValidationsOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nombre = findViewById(R.id.editTextTextPersonName);
        EditText pass = findViewById(R.id.editTextTextPassword);
        EditText passValidation = findViewById(R.id.editTextTextPassword2);
        final EditText email = findViewById(R.id.editTextTextEmailAddress);
        RadioButton debito = findViewById(R.id.radioButton);
        RadioButton credito = findViewById(R.id.radioButton2);
        EditText numeroTarjeta = findViewById(R.id.editTextNumber);
        EditText cbu = findViewById(R.id.editTextNumber5);
        EditText aliasCbu = findViewById(R.id.editTextTextPersonName2);
        Switch cargaInicial = findViewById(R.id.switch1);
        CheckBox tyc = findViewById(R.id.checkBox);
        Button Registrar = findViewById(R.id.button);


        //Cuando el Switch de carga inicial esta en true la seeker bar se activa
        cargaInicial.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Switch cargaInicial = findViewById(R.id.switch1);
                SeekBar sb1 = findViewById(R.id.seekBar2);
                TextView tv1 = findViewById(R.id.textView4);
                if (cargaInicial.isChecked()) {

                    tv1.setVisibility(View.VISIBLE);
                    sb1.setVisibility(View.VISIBLE);
                } else {
                    tv1.setVisibility(View.GONE);
                    sb1.setVisibility(View.GONE);
                }


            }
        });

        //cuando el numero de tarjeta es de 20 digitos se habilitan los campos de cvv y fecha de vencimientod de la tarjeta
        numeroTarjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                EditText cvv = findViewById(R.id.editTextNumber2);
                EditText mesVencimiento = findViewById(R.id.editTextNumber3);
                EditText yearVencimiento = findViewById(R.id.editTextNumber4);
                if (editable.length() == 20) {
                    cvv.setEnabled(true);
                    mesVencimiento.setEnabled(true);
                    yearVencimiento.setEnabled(true);
                } else {
                    cvv.setEnabled(false);
                    mesVencimiento.setEnabled(false);
                    yearVencimiento.setEnabled(false);
                }

            }
        });


        //cuando se mueve la seekbar se muestra el monto que se está seleccionando
        SeekBar sbaux = findViewById(R.id.seekBar2);
        sbaux.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Monto :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();
            }
        });

        //cuando se checkean los terminos y condiciones se habilita el boton de registrar
        tyc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //consultar como es el tema de la variable v
                CheckBox tycAux = findViewById(R.id.checkBox);
                if (tycAux.isChecked()) {
                    Button RegistrarAux = findViewById(R.id.button);
                    RegistrarAux.setEnabled(true);
                } else {
                    Button RegistrarAux = findViewById(R.id.button);
                    RegistrarAux.setEnabled(false);
                }
            }

        });

        Registrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Boolean registroOk = true;

                // 1.. valida email
                EditText emailAux = findViewById(R.id.editTextTextEmailAddress);
                if (!emailAux.getText().toString().isEmpty()) {
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailAux.getText().toString()).matches()) {
                        Toast.makeText(MainActivity.this, "Email Inválido", Toast.LENGTH_SHORT).show();
                        registroOk = false;
                    }else registroOk = true;
                }else {
                    Toast.makeText(MainActivity.this, "Falta ingresar Email", Toast.LENGTH_SHORT).show();
                    registroOk = false;
                }

                // 2.. valida clave

                EditText passAux = findViewById(R.id.editTextTextPassword);
                EditText passValidationAux = findViewById(R.id.editTextTextPassword2);

                if(!passAux.getText().toString().isEmpty()){

                    if(passAux.getText().toString().equals(passValidationAux.getText().toString())){
                        registroOk = true;
                    }else{
                        Toast.makeText(MainActivity.this, "Las Claves no Coinciden", Toast.LENGTH_SHORT).show();
                        registroOk = false;
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Falta ingresar Clave", Toast.LENGTH_SHORT).show();
                    registroOk = false;
                }

                // 3.. valida Tarjeta

                EditText numeroTarjetaAux = findViewById(R.id.editTextNumber);
                if(!numeroTarjetaAux.getText().toString().isEmpty()){
                    if(numeroTarjetaAux.getText().toString().length() == 20){
                        registroOk = true;
                    }else{
                        Toast.makeText(MainActivity.this, "Nro de Tarjeta Invalido", Toast.LENGTH_SHORT).show();
                        registroOk = false;
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Falta Número de Tarjeta", Toast.LENGTH_SHORT).show();
                    registroOk = false;
                }


                if(registroOk) Toast.makeText(MainActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }

        });



        //Validaciones

        boolean emailValido = false;
        if (!email.getText().toString().isEmpty()) {
            if (email.getText().toString().split("@", 2).length > 0) {
                emailValido = true;
            }
        }

        boolean claveValida = false;
        if (!pass.getText().toString().isEmpty() && !passValidation.getText().toString().isEmpty()) {
            if (pass.getText().toString() == passValidation.getText().toString()) {
                claveValida = true;
            }
        }

        Boolean tipoTarjeta = false;
        if (debito.isChecked() || credito.isChecked()) {
            tipoTarjeta = true;
        }

        boolean cvvValido = false;
        EditText cvvAux = findViewById(R.id.editTextNumber2);
        if (cvvAux.getText().toString().length() > 3) cvvValido = true;

        Boolean mesValido = false;
        Boolean añoValido = false;

        EditText mesVencimientoAux = findViewById(R.id.editTextNumber3);
        EditText yearVencimientoAux = findViewById(R.id.editTextNumber4);

        if (!mesVencimientoAux.getText().toString().isEmpty() && !yearVencimientoAux.getText().toString().isEmpty()){

        }



    }
}


