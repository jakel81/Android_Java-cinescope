package jra.cinescope2017;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jo on 21/11/2017.
 */

public class TAGetBeginningTitle extends AsyncTask<String, Integer, JSONArray> {

    private Context contexte;
    private ListView lv;

    public TAGetBeginningTitle(Context contexte, ListView lv) {
        this.contexte = contexte;
        this.lv = lv;
    }

    @Override
    protected JSONArray doInBackground(String... asParametres) {
        JSONArray tableauJSON = null;

        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];
        String lsDebutDeChaine = asParametres[2];


        URL url;
        HttpURLConnection httpConnection = null;

        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource + "?chaine=" + lsDebutDeChaine);
            httpConnection = (HttpURLConnection) url.openConnection();

            // Connexion
            httpConnection.connect();
            // EXECUTION DE LA REQUETE ET RESPONSE
            InputStream is = httpConnection.getInputStream();
            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsContenu = br.readLine();
            tableauJSON = new JSONArray(lsContenu);
            br.close();
            is.close();
        } catch (Exception e) {
            //lsb.append(e.getMessage());
            //tableauJSON = new JSONArray(e.getMessage());
        } finally {
            // Deconnexion
            httpConnection.disconnect();
        }
        // Renvoie la valeur a onPostExecute
        return tableauJSON;
    }

    @Override
    protected void onPostExecute(JSONArray tableauJSON) {
        JSONObject objet;
        int n = tableauJSON.length();
        String[] tTitreIDs = new String[n];

        try {
            for (int i = 0; i < tableauJSON.length(); i++) {
                objet = (JSONObject) tableauJSON.get(i);
                tTitreIDs[i] = objet.get("id") + "-" + objet.get("titre");
            }
        } catch (Exception e) {

        }

        ArrayAdapter<String> aaListe = new ArrayAdapter<>(contexte, android.R.layout.simple_list_item_1, tTitreIDs);
        lv.setAdapter(aaListe);
    }
}
