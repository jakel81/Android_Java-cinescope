package jra.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RechercheAvancee extends AppCompatActivity implements View.OnClickListener {

    private Button buttonValider;
    private Button buttonAnnuler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_avancee);

        buttonValider = (Button) findViewById(R.id.buttonValider);
        buttonAnnuler = (Button) findViewById(R.id.buttonAnnuler);

        buttonValider.setOnClickListener(this);
        buttonAnnuler.setOnClickListener(this);

        Bundle params = this.getIntent().getExtras();
        String valeur = params.getString("motRecherche");
        Toast.makeText(this, valeur, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == buttonValider) {
            Intent intentionResultat = new Intent();
            intentionResultat.putExtra("dataRetour OK", "100 entrées trouvées");
            setResult(RESULT_OK, intentionResultat);
            finish();

        }
        if (v == buttonAnnuler) {

            Intent intentionResultat = new Intent();
            intentionResultat.putExtra("dataRetour KO", "Recherche annulée");
            setResult(RESULT_CANCELED, intentionResultat);
            finish();
        }
    }
}
