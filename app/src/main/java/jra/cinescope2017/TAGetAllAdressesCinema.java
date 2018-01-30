package jra.cinescope2017;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jo on 14/11/2017.
 */

public class TAGetAllAdressesCinema extends AsyncTask<String, Integer, JSONArray> {

    private Context contexte;
    private TextView tv;

    public TAGetAllAdressesCinema(Context contexte, TextView tv) {

        this.contexte = contexte;
        this.tv = tv;
    }

    @Override
    protected JSONArray doInBackground(String... asParametres) {
        JSONArray tableauJSON = null;

        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];


        URL url;
        HttpURLConnection httpConnection = null;

        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource);
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

        return tableauJSON;
    }

    @Override
    protected void onPostExecute(JSONArray tableau) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final

        StringBuilder lsb = new StringBuilder();
        JSONObject objet;
        try {

            for (int i = 0; i < tableau.length(); i++) {
                objet = (JSONObject) tableau.get(i);
                lsb.append(objet.get("code").toString());
                lsb.append("\n");
            }
        } catch (Exception e) {

        }

        tv.setText(lsb.toString());

        String lsCode;
        String lsAdresse;
        JSONObject objetJSON;
        try {
            for (int i = 0; i < tableau.length(); i++) {
                objetJSON = (JSONObject) tableau.get(i);
                lsCode = objetJSON.get("code").toString();
                lsAdresse = objetJSON.get("adresse").toString();
                new TASetCoordCinema(this.contexte, tv).execute("http://172.26.10.144:8084/Cinescope2017Web/", "SetCoordsCinema", lsCode, lsAdresse);
            }
        } catch (Exception e) {

        }
    }
}
