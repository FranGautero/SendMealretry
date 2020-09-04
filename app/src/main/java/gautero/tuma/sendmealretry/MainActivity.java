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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

                Boolean registroOk1 = true;

                EditText passAux = findViewById(R.id.editTextTextPassword);
                EditText passValidationAux = findViewById(R.id.editTextTextPassword2);

                if(!passAux.getText().toString().isEmpty()){

                    if(passAux.getText().toString().equals(passValidationAux.getText().toString())){
                        registroOk1 = true;
                    }else{
                        Toast.makeText(MainActivity.this, "Las Claves no Coinciden", Toast.LENGTH_SHORT).show();
                        registroOk1 = false;
                    }

                }else{
                    Toast.makeText(MainActivity.this, "Falta ingresar Clave", Toast.LENGTH_SHORT).show();
                    registroOk1 = false;
                }

                // 3.. valida Tarjeta

                Boolean registroOk2 = true;

                EditText numeroTarjetaAux = findViewById(R.id.editTextNumber);
                if(!numeroTarjetaAux.getText().toString().isEmpty()){
                    if(numeroTarjetaAux.getText().toString().length() == 20){
                        registroOk2 = true;
                    }else{
                        Toast.makeText(MainActivity.this, "Nro de Tarjeta Invalido", Toast.LENGTH_SHORT).show();
                        registroOk2 = false;
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Falta Número de Tarjeta", Toast.LENGTH_SHORT).show();
                    registroOk2 = false;
                }

                // 4.. Valida Tipo de Tarjeta

                Boolean registroOk3 = true;

                RadioButton debito = findViewById(R.id.radioButton);
                RadioButton credito = findViewById(R.id.radioButton2);
                if(debito.isChecked() || credito.isChecked()){
                    registroOk3 = true;
                }else{
                    Toast.makeText(MainActivity.this, "Falta Seleccionar Tipo de Tarjeta", Toast.LENGTH_SHORT).show();
                    registroOk3 = false;
                }

                // 5.. Valida CVV

                Boolean registroOk4 = true;

                EditText cvvAux = findViewById(R.id.editTextNumber2);
                if(!cvvAux.getText().toString().isEmpty()){
                    if(cvvAux.getText().toString().length() == 3){
                        registroOk4 = true;
                    }else{
                        Toast.makeText(MainActivity.this, "CVV Inválido", Toast.LENGTH_SHORT).show();
                        registroOk4 = false;
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Falta CVV", Toast.LENGTH_SHORT).show();
                    registroOk4 = false;
                }

                // 6.. Valida Fecha Vencimiento

                Boolean registroOk5 = true;

                EditText mesVencimientoAux = findViewById(R.id.editTextNumber3);
                EditText yearVencimientoAux = findViewById(R.id.editTextNumber4);

                if(!mesVencimientoAux.getText().toString().isEmpty() && !yearVencimientoAux.getText().toString().isEmpty()){
                        registroOk5 = true;
                }else{
                    Toast.makeText(MainActivity.this, "Falta Completar Fecha de Vencimiento", Toast.LENGTH_SHORT).show();
                    registroOk5 = false;
                }

                // 7.. Valida Monto de la SeekBar

                Boolean registroOk6 = true;

                Switch cargaInicialAux = findViewById(R.id.switch1);
                SeekBar sb1Aux = findViewById(R.id.seekBar2);

                if(cargaInicialAux.isChecked()){

                    if(sb1Aux.getProgress() > 0){
                        registroOk6 = true;
                    }else{
                        Toast.makeText(MainActivity.this, "El Valor del monto inicial debe ser mayor a 0", Toast.LENGTH_SHORT).show();
                        registroOk6 = false;
                    }

                }

                // 8.. Valida Fecha de Vencimiento

                Boolean registroOk7 = true;
                
                DateFormat formato = new SimpleDateFormat("MM-yy");
                String fechaIngresadaStr = mesVencimientoAux.getText().toString()+"-"+yearVencimientoAux.getText().toString();
                Date fechaIngresada = null;

                try {
                    fechaIngresada = formato.parse(fechaIngresadaStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar calActual = Calendar.getInstance();
                Date fechaActual = new Date();
                calActual.setTime(fechaActual);
                calActual.add(Calendar.MONTH, 2);

                Calendar calIngresada = Calendar.getInstance();
                calIngresada.setTime(fechaIngresada);

                if (calIngresada.before(calActual)){
                    Toast.makeText(MainActivity.this, "El Vencimiento de la Tarjeta debe ser mayor a 3 meses", Toast.LENGTH_SHORT).show();
                    registroOk7 = false;
                } else {
                    registroOk7=true;
                }

                if(registroOk && registroOk1 && registroOk2  && registroOk3  && registroOk4  && registroOk5  && registroOk6 && registroOk7) Toast.makeText(MainActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }

        });




    }
}


