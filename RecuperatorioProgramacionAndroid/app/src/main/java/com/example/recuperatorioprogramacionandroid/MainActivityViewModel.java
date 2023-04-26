package com.example.recuperatorioprogramacionandroid;

import android.app.Application;
import static com.example.recuperatorioprogramacionandroid.MainActivity.Peso;
import static com.example.recuperatorioprogramacionandroid.MainActivity.Estatura;
import static com.example.recuperatorioprogramacionandroid.MainActivity.Sexo;
import static com.example.recuperatorioprogramacionandroid.MainActivity.MensajeError;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;

public class MainActivityViewModel extends AndroidViewModel {
    private Context ctx;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.ctx = application.getApplicationContext();
    }
    public void EnviarDatos (String peso, String altura, String sexo) {
        if (peso == null || altura == null || peso.isEmpty() || altura.isEmpty()) {
            MensajeError.setValue("Uno de los campos está vacío, llénalo e intenta de nuevo.");
        } else {
            try {
                Peso.setValue(Double.parseDouble(peso));
                Estatura.setValue(Integer.parseInt(altura));
                Sexo.setValue(sexo);
                MensajeError.setValue("");
                if ((Peso.getValue() <= 200 && Estatura.getValue() <= 230) && (Peso.getValue() >= 20 && Estatura.getValue() >= 100)) {
                    Intent IntentResultado = new Intent (ctx, ResultadoIMC.class);
                    ctx.startActivity(IntentResultado);
                } else {
                    if (Peso.getValue() > 200) {
                        MensajeError.setValue("El peso es muy alto. Intenta entrar otro valor, o hacerte una liposucción.");
                    }
                    if (Peso.getValue() < 20) {
                        MensajeError.setValue("El peso es muy bajo. Intenta entrar otro valor, o comer más.");
                    }
                    if (Estatura.getValue() > 230) {
                        MensajeError.setValue("La estatura es muy alta. ¿Eres una jirafa? Disculpas si ése era el valor que quisiste entrar.");
                    }
                    if (Estatura.getValue() < 100) {
                        MensajeError.setValue("La estatura es muy baja. ¿Eres un duende? Disculpas si ése era el valor que quisiste entrar.");
                    }
                    if (Estatura.getValue() < 100 && Peso.getValue() < 20) {
                        MensajeError.setValue("La estatura y el peso son excepcionalmente bajos. ¿Eres un enano o estás probando los errores aquí?");
                    }
                    if (Estatura.getValue() > 230 && Peso.getValue() > 200) {
                        MensajeError.setValue("La estatura y el peso son excepcionalmente altos. ¿Eres un gigante o estás probando los errores aquí?");
                    }
                    if ((Estatura.getValue() < 100 && Peso.getValue() > 200) || (Estatura.getValue() > 230 && Peso.getValue() < 20)) {
                        MensajeError.setValue("Ya no sé qué decir, éstos valores no tienen sentido. Espero sólo estés probando los errores.");
                    }
                }
            } catch (NumberFormatException nfe) {
                MensajeError.setValue("La estatura va en centímetros enteros. El peso admite decimales con punto. Pasó una excepción de formato de números.");
            }
        }
    }
}
