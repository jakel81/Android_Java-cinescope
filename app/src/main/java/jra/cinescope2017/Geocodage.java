package jra.cinescope2017;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Geocodage extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewMessage;

    private Button buttonGo;
    private Button buttonGoBd;
    private Button buttonAllInBd;

    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private EditText editTextVoie;
    private EditText editTextVille;
    private EditText editTextPays;

    private Geocoder igeocodeur;
    private boolean ibGeocodeur;

    private double[] tLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geocodage);

        initInterface();

        editTextVoie.setText("35 Rue Gounod");
        editTextVille.setText("Paris");
        editTextPays.setText("France");

        ibGeocodeur = Geocoder.isPresent();

        if (ibGeocodeur) {

            igeocodeur = new Geocoder(this, Locale.FRANCE);

            textViewMessage.setText("Géocodage possible");
        } else {
            textViewMessage.setText("Géocodage impossible");

            buttonGo.setBackgroundColor(Color.RED);
            buttonGo.setEnabled(false);
        }
    }

    private void initInterface() {

        editTextLatitude = (EditText) findViewById(R.id.editTextLatitude);
        editTextLongitude = (EditText) findViewById(R.id.editTextLongitude);
        editTextVoie = (EditText) findViewById(R.id.editTextVoie);
        editTextVille = (EditText) findViewById(R.id.editTextVille);
        editTextPays = (EditText) findViewById(R.id.editTextPays);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

        buttonGo = (Button) findViewById(R.id.buttonGo);
        buttonGo.setOnClickListener(this);
        buttonGoBd = (Button) findViewById(R.id.buttonGoBd);
        buttonGoBd.setOnClickListener(this);
        buttonAllInBd = (Button) findViewById(R.id.buttonAllInBd);
        buttonAllInBd.setOnClickListener(this);

    }

    /**
     * getLatLongFromAdresse : les coordonnees d'une adresse, géocodage
     *
     * @param adresse
     * @return
     */
    private double[] getLatLongFromAdresse(Geocoder geocodeur, String adresse) {
        // Fonction perso pour le géocodage
        double tLatLng[] = {0.0, 0.0};

        try {
            List<Address> adresses = geocodeur.getFromLocationName(adresse, 1);
            if (adresses.size() > 0) {
                tLatLng[0] = adresses.get(0).getLatitude();
                tLatLng[1] = adresses.get(0).getLongitude();
            }
        } catch (IOException e) {
        }

        return tLatLng;

    } // / getLatLongFromAdresse

    @Override
    public void onClick(View v) {

        if (v == buttonGo) {
            Log.i("buttonGo", "buttonGo");
            String lsAdresse = editTextVoie.getText().toString() + "," + editTextVille.getText().toString() + "," + editTextPays.getText().toString();
            tLatLng = getLatLongFromAdresse(igeocodeur, lsAdresse);
            editTextLatitude.setText(Double.toString(tLatLng[0]));
            editTextLongitude.setText(Double.toString(tLatLng[1]));

            //textViewMessage.setText(Double.toString(tLatLng[0]) + " - " + Double.toString(tLatLng[1]));
        }

        if (v == buttonGoBd) {
            Log.i("buttonGoBd", "buttonGoBd");
            new TAGetAdresseCinema(this, textViewMessage).execute("http://192.168.1.39:8080/Cinescope2017Web/", "Cinema", "35E");
        }

        if (v == buttonAllInBd) {
            new TAGetAllAdressesCinema(this, textViewMessage).execute("http://192.168.1.39:8080/Cinescope2017Web/", "AdressesCinema", "35E");
        }

    }
}
