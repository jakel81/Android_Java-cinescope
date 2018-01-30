package jra.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        liste = (ListView) findViewById(R.id.listeAccueil);

        liste.setOnItemClickListener(this);

        String[] tItems = {"Tous les films", "Box Office", "Hit parade du public", "Avis des critiques", "Salles de Paris", "Recherche avancée", "Inscription", "Authentification", "Mon compte", "Importations", "Géocodage"};

        String[] tImages = new String[11];
        tImages[0] = String.valueOf(R.drawable.cine_1);
        tImages[1] = String.valueOf(R.drawable.cine_2);
        tImages[2] = String.valueOf(R.drawable.cine_3);
        tImages[3] = String.valueOf(R.drawable.cine_4);
        tImages[4] = String.valueOf(R.drawable.cine10);
        tImages[5] = String.valueOf(R.drawable.cine_5);
        tImages[6] = String.valueOf(R.drawable.inscription);
        tImages[7] = String.valueOf(R.drawable.authentification);
        tImages[8] = String.valueOf(R.drawable.mon_compte);
        tImages[9] = String.valueOf(R.drawable.mon_compte);
        tImages[10] = String.valueOf(R.drawable.mon_compte);

        try {

            List<Map<String, String>> listeItems = new ArrayList<>();
            Map<String, String> hm;

            for (int i = 0; i < tItems.length; i++) {
                hm = new HashMap<>();

                hm.put("image", tImages[i]);
                hm.put("item", tItems[i]);
                listeItems.add(hm);
            }

            SimpleAdapter sa = new SimpleAdapter(
                    this,
                    listeItems,
                    R.layout.layout_ligne,
                    new String[]{"image", "item"},
                    new int[]{R.id.imageAccueil, R.id.textAccueil}
            );

            liste.setAdapter(sa);

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Erreur ? " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Map<String, String> mapItem = (Map<String, String>) parent.getAdapter().getItem(position);

        Toast.makeText(this, mapItem.get("text"), Toast.LENGTH_SHORT).show();

        Intent intention = new Intent();

        Toast.makeText(this, "Position: " + Integer.toString(position), Toast.LENGTH_SHORT).show();

        if (position == 0) {
            intention.setClass(this, TousLesFilms.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 1) {
            intention.setClass(this, BoxOffice.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 2) {
            intention.setClass(this, HitParadeDuPublic.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 3) {
            intention.setClass(this, AvisDesCritiques.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 4) {
            intention.setClass(this, SallesDeParis.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 5) {
            intention.setClass(this, RechercheAvancee.class);
            intention.putExtra("motRecherche", "MacInToc");
            startActivityForResult(intention, position + 1);
        }

        if (position == 6) {
            intention.setClass(this, Inscription.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 7) {
            intention.setClass(this, Authentification.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 8) {
            String lsNom = Globale.getNom();
            if (lsNom.equals("inconnu") || lsNom.isEmpty()) {
                Toast.makeText(this, Globale.getNom() + ". Veuillez vous identifier !!!", Toast.LENGTH_LONG).show();
            } else {
                intention.setClass(this, MonCompte.class);
                intention.putExtra("id", Globale.getId());
                intention.putExtra("nom", Globale.getNom());
                intention.putExtra("mdp", Globale.getMdp());
                intention.putExtra("email", Globale.getEmail());
                startActivityForResult(intention, position + 1);
            }
        }

        if (position == 9) {
            intention.setClass(this, Importations.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 10) {
            intention.setClass(this, Main.class);
            startActivityForResult(intention, position + 1);
        }

        if (position == 11) {
            intention.setClass(this, Geocodage.class);
            startActivityForResult(intention, position + 1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case 2: // Box Office
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();
                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK BO", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler BO", Toast.LENGTH_SHORT).show();

                        return;
                } // / switch (resultCode)

            case 3: // HPDP
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK HDPP", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler HDPP", Toast.LENGTH_SHORT).show();

                        return;
                }


            case 4: //ADC
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK ADC", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler ADC", Toast.LENGTH_SHORT).show();

                        return;
                }

            case 5: //SDP
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK SDP", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler SDP", Toast.LENGTH_SHORT).show();

                        return;
                }

            case 6: //RA
                String lsDataRetour;
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        lsDataRetour = data.getStringExtra("dateRetourOK");
                        Toast.makeText(this, lsDataRetour, Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        lsDataRetour = data.getStringExtra("dateRetourKO");
                        Toast.makeText(this, lsDataRetour, Toast.LENGTH_SHORT).show();

                        return;
                }

            case 7: //Inscription
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "Inscription OK", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler Inscription", Toast.LENGTH_SHORT).show();

                        return;
                }

            case 8: // Auth
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK Auth", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler Auth", Toast.LENGTH_SHORT).show();

                        return;
                }

            case 9: // Compte
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK Compte", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler Compte", Toast.LENGTH_SHORT).show();

                        return;
                }

            case 10: // Importation
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK Importation", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler Importation", Toast.LENGTH_SHORT).show();

                        return;
                }

            case 11: // Geocodage
                Toast.makeText(this, Integer.toString(requestCode), Toast.LENGTH_SHORT).show();

                switch (resultCode) {

                    case RESULT_OK:

                        // --- Recuperation des donnees recues
                        Toast.makeText(this, "OK Geocodage", Toast.LENGTH_SHORT).show();

                        return;

                    case RESULT_CANCELED:

                        Toast.makeText(this, "Annuler Geocodage", Toast.LENGTH_SHORT).show();

                        return;
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuItemChoix.menuItemChoix(this, item.getItemId());
    } // onOptionsItemSelected


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}