package pmdm.esteban.u3t2implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String IMPLICIT_INTENTS= "ImplicitIntents";
    private EditText etUri, etLocation, etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI(){
        Button btOpenUri, btOpenLocation, btShareText;

        etUri= findViewById(R.id.etUri);
        etLocation = findViewById(R.id.etLocation);
        etText= findViewById(R.id.etText);

        btOpenUri= findViewById(R.id.btOpenUri);
        btOpenLocation= findViewById(R.id.btOpenLocation);
        btShareText= findViewById(R.id.btShareText);

        btOpenUri.setOnClickListener(this);
        btOpenLocation.setOnClickListener(this);
        btShareText.setOnClickListener(this);
    }



    private void openWebsite(String urlText){

        //Parse the Uri and create the intent
        Uri webpage = Uri.parse(urlText);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        //Find an activity to hand the intent and start that activity
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }else{
            Log.d(IMPLICIT_INTENTS, "openWebsite: Can't handle this intent");
        }

    }

    private void openLocation(String location){
        //Se puede cordenadas o texto, en nuestro caso texto

        //Parse the location and create the intent

        Uri addressUri= Uri.parse("geo:0,0?q=" + location);
        Intent intent= new Intent(Intent.ACTION_VIEW, addressUri);

        //Find an activity to hand the intent and start that activity
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }else{
            Log.d(IMPLICIT_INTENTS, "openLocation: Can't handle this intent");
        }

    }

    private void shareText(String text){
        new ShareCompat.IntentBuilder(this)
                .setType("text/plain")   //The MIME Type of the item to share
                .setText(text)           //actual text to share .. Equivalent to : intent.putExtra(Intent.Extra_Text, text)
                .startChooser();         //Start a chosser activity for the current share intent
    }
    @SuppressLint("NonConstantResourceID")
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btOpenUri:
                openWebsite(etUri.getText().toString());
                Log.i("Entro","EntroBotonUri");
                break;
            case R.id.btOpenLocation:
                openLocation(etLocation.getText().toString());
                break;
            case R.id.btShareText:
                shareText(etText.getText().toString());
                break;
        }

    }
}