package com.example.recuperatorioprogramacionandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    public static MutableLiveData <Double> Peso;
    public static MutableLiveData <Integer> Estatura;
    public static MutableLiveData <String> Sexo;
    public static MutableLiveData <String> MensajeError;
    private Spinner SpGenero;
    private Button BtnEnviar;
    private EditText EntradaAltura;
    private EditText EntradaPeso;
    private TextView TError;
    private MainActivityViewModel ViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Peso = new MutableLiveData<Double>();
        Estatura = new MutableLiveData<Integer>();
        Sexo = new MutableLiveData<String>();
        MensajeError = new MutableLiveData<String>();
        setContentView(R.layout.activity_main);
        SpGenero = findViewById (R.id.SpGenero);
        String[] items = new String[]{"Hombre", "Mujer"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ViewModel = new MainActivityViewModel (getApplication());
        SpGenero.setAdapter(adapter);
        BtnEnviar = findViewById (R.id.BGuardar);
        EntradaAltura = findViewById(R.id.EAltura);
        EntradaPeso = findViewById (R.id.EPeso);
        TError = findViewById(R.id.TError);
        BtnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewModel.EnviarDatos (EntradaPeso.getText().toString(), EntradaAltura.getText().toString(), SpGenero.getSelectedItem().toString());
            }
        });
        MensajeError.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TError.setText(s);
            }
        });
    }
}