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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String IMPLICIT_INTENTS= "ImplicitIntents";
    private EditText etUri, etLocation, etText, zoomNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
    }

    private void setUI(){
        Button btOpenUri, btOpenLocation, btShareText, moreIntents;

        etUri= findViewById(R.id.etUri);
        etLocation = findViewById(R.id.etLocation);
        etText= findViewById(R.id.etText);
        zoomNum= findViewById(R.id.zoomNum);

        moreIntents=findViewById(R.id.moreIntents);
        btOpenUri= findViewById(R.id.btOpenUri);
        btOpenLocation= findViewById(R.id.btOpenLocation);
        btShareText= findViewById(R.id.btShareText);
moreIntents.setOnClickListener(this);
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

    private void openLocation(String location) throws Exception {
        //Se puede cordenadas o texto, en nuestro caso texto
        int zoomInt= Integer.parseInt(zoomNum.getText().toString());
        System.out.println("Zoom"+zoomInt);
        //Parse the location and create the intent
        //Hacemos la excepcion por el zoom
        if (!(zoomInt>=1) || !(zoomInt<24)){
            throw new Exception("Numeros  de zoom mayor de 1 o menor de 24");
        }
        Uri addressUri= Uri.parse("geo:0,0?q="+location+"&z="+zoomNum) ;
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
                try {
                    openLocation(etLocation.getText().toString());
                }catch (Exception e){
             Log.e("Location", e.getMessage());
           zoomNum.setError(e.getMessage());
                }
                break;
            case R.id.btShareText:
                shareText(etText.getText().toString());
                break;
            case R.id.moreIntents:
                Intent acti=new Intent(this,MoreIntents.class);
                startActivity(acti);
                break;
        }

    }
}