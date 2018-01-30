package jra.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class ResultatRechercheSDP extends AppCompatActivity {

    private ListView listViewCinema;
    private TextView textViewArrondissement;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultat_recherche_sdp);

        listViewCinema = (ListView) findViewById(R.id.listVewCinema);
        textViewArrondissement = (TextView) findViewById(R.id.textViewArrondissement);



        Bundle params = this.getIntent().getExtras();
        String lsCinema = params.getString("code_arrondissement");
        String lsArrondissement = params.getString("nom_arrondissement");

        new TAResultatRechercheSDP(this, listViewCinema, textViewArrondissement).execute("http://172.26.10.23:8084/Cinescope2017Web/", "SallesDeParisByArrondissement", lsCinema, lsArrondissement);
    }
}
