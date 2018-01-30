package jra.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InclusionsBase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inclusions_base);
    }

    public void afficherAuthentification(View vue) {
        Intent intention = new Intent(this, AuthentificationInclusions.class);
        startActivity(intention);
    } /// afficherAuthentification

    public void afficherCatalogue(View vue) {
        Intent intention = new Intent(this, CatalogueInclusions.class);
        startActivity(intention);
    } /// afficherCatalogue
}
