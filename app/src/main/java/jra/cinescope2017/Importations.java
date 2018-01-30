package jra.cinescope2017;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Importations extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private TextView textViewMessage;
    private Button buttonArrondissement;
    private Button buttonDepartement;
    private Button buttonCinema;
    private Button buttonGenre;
    private Button buttonMedia;
    private Button buttonPays;
    private Button buttonVille;
    private String lsTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.importations);

        initInterface();
        initEvents();
    }

    private void initEvents() {

        buttonArrondissement.setOnClickListener(this);
        buttonDepartement.setOnClickListener(this);
        buttonCinema.setOnClickListener(this);
        buttonGenre.setOnClickListener(this);
        buttonMedia.setOnClickListener(this);
        buttonPays.setOnClickListener(this);
        buttonVille.setOnClickListener(this);

    }

    private void initInterface() {

        buttonArrondissement = (Button) findViewById(R.id.buttonArrondissement);
        buttonDepartement = (Button) findViewById(R.id.buttonDepartement);
        buttonCinema = (Button) findViewById(R.id.buttonCinema);
        buttonGenre = (Button) findViewById(R.id.buttonGenre);
        buttonMedia = (Button) findViewById(R.id.buttonMedia);
        buttonPays = (Button) findViewById(R.id.buttonPays);
        buttonVille = (Button) findViewById(R.id.buttonVille);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

    }

    @Override
    public void onClick(View v) {

        SQLiteDatabase bd = connectToSqlite();

        if (v == buttonGenre) {

            lsTable = "genre";

            new TAImportGenres(this, textViewMessage, "cinescope2017", lsTable).execute("http://192.168.1.39:8080/Cinescope2017Web/", "TableSQL2JSON", lsTable);

        }

    }

    private SQLiteDatabase connectToSqlite() {

        StringBuilder lsbMessage = new StringBuilder();
        SQLiteImportationGenres gos;
        SQLiteDatabase bd = null;
        try {
            // Connexion
            // --- GestionnaireOuvertureSQLite(Contexte, Fabrique de curseur);
            gos = new SQLiteImportationGenres(this, null);
            // --- Connexion à la BD ... en lecture/écriture
            bd = gos.getWritableDatabase();
            // Deconnexion
            gos.close();
            lsbMessage.append("Vous êtes déconnecté(e) !");
            lsbMessage.append("\n");
            //bd = null;

        } catch (Exception e) {
            lsbMessage.append("Aïe, aïe, aïe ! ");
            lsbMessage.append(e.getMessage());
            lsbMessage.append("\n");
        }

        Log.i("connectToSqlite", lsbMessage.toString());

        return bd;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
