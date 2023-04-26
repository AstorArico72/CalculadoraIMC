package com.example.recuperatorioprogramacionandroid;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.recuperatorioprogramacionandroid.MainActivity.Peso;
import static com.example.recuperatorioprogramacionandroid.MainActivity.Estatura;
import static com.example.recuperatorioprogramacionandroid.MainActivity.Sexo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ResultadoIMC extends AppCompatActivity {
    private TextView TResultado;
    private TextView TAltura;
    private TextView TPeso;
    private TextView TEstado;
    private TextView TSexo;
    private DecimalFormat Redondeador = new DecimalFormat("#.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_imc);

        this.TResultado = findViewById(R.id.TResultadoImc);
        this.TEstado = findViewById(R.id.TEstado);
        this.TSexo = findViewById(R.id.TSexo);
        this.TAltura = findViewById(R.id.TEstatura);
        this.TPeso = findViewById(R.id.TPeso);

        TSexo.setText("Sexo: " + Sexo.getValue());

        CambiarDatos (Peso.getValue(), Estatura.getValue());
    }

    private void CambiarDatos (double peso, int estatura) {
        double estaturaEnMetros = (double) estatura / 100;
        double ResultadoImc = peso / (estaturaEnMetros * estaturaEnMetros);
        ResultadoImc = Double.parseDouble(Redondeador.format(ResultadoImc));

        peso = Double.parseDouble(Redondeador.format (peso));

        TPeso.setText("Peso: " + peso + " kg");
        TAltura.setText("Estatura: " + estatura + " cm");

        TResultado.setText(ResultadoImc + "");
        if (ResultadoImc < 20) {
            TResultado.setTextColor(Color.parseColor("#0000FF"));
            TEstado.setTextColor(Color.parseColor("#0000FF"));
            TEstado.setText("Bajo peso");
        }
        if (ResultadoImc > 20 && ResultadoImc < 25) {
            TResultado.setTextColor(Color.parseColor("#00FF00"));
            TEstado.setTextColor(Color.parseColor("#00FF00"));
            TEstado.setText("Peso saludable");
        }
        if (ResultadoImc > 25) {
            TResultado.setTextColor(Color.parseColor("#FF0000"));
            TEstado.setTextColor(Color.parseColor("#FF0000"));
            TEstado.setText("Sobrepeso");
        }
    }
}