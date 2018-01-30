package jra.cinescope2017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AuthentificationInclusions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification_inclusions);
    } /// onCreate

    public void retourAccueil(View vue) {
        Intent intention = new Intent(this, InclusionsBase.class);
        startActivity(intention);
    } /// retourAccueil

    public void afficherAuthentification(View vue) {
        Intent intention = new Intent(this, AuthentificationInclusions.class);
        startActivity(intention);
    } /// afficherAuthentification

    public void afficherCatalogue(View vue) {
        Intent intention = new Intent(this, CatalogueInclusions.class);
        startActivity(intention);
    } /// afficherCatalogue
}
