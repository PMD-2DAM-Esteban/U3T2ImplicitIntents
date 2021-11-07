package pmdm.esteban.u3t2implicitintents;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.actions.NoteIntents;

public class MoreIntents extends AppCompatActivity implements View.OnClickListener {

    EditText subejoNota, textoNota, nombreTemporizador, numeroTemporizador, numeroLLamar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_intents);
        setupUI();

    }

    public void setupUI(){
        Button botonNota, botonTemporizador, botonLlamar;



    textoNota= findViewById(R.id.textoNota);
    subejoNota= findViewById(R.id.sujetoNota);
    nombreTemporizador= findViewById(R.id.nombreTemporizador);
    numeroTemporizador=findViewById(R.id.numeroTemporizador);
    numeroLLamar=findViewById(R.id.numeroLLamar);


    botonTemporizador=findViewById(R.id.brTemporizador);
    botonTemporizador.setOnClickListener(this);

    botonNota= findViewById(R.id.btNota);
    botonNota.setOnClickListener(this);

    botonLlamar= findViewById(R.id.btLLamar);
    botonLlamar.setOnClickListener(this);


    }

    public void createNote(String subject, String text) {
        Intent intent = new Intent(NoteIntents.ACTION_CREATE_NOTE)
                .putExtra(NoteIntents.EXTRA_NAME, subject)
                .putExtra(NoteIntents.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void startTimer(String message, int seconds) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_LENGTH, seconds)
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btNota:
                createNote(subejoNota.getText().toString(), textoNota.getText().toString());
                Log.i("Entro", "EntroNotas");
                break;
            case R.id.brTemporizador:
               startTimer(nombreTemporizador.getText().toString(), Integer.parseInt(numeroTemporizador.getText().toString()));
                Log.i("Entro", "EntroTemporizador");
                break;
            case R.id.btLLamar:
                dialPhoneNumber(numeroLLamar.getText().toString());
                Log.i("Entro", "Entrollamar");
                break;

        }
    }
}