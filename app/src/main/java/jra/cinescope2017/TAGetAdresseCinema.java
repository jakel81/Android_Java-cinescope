package jra.cinescope2017;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jo on 14/11/2017.
 */

public class TAGetAdresseCinema extends AsyncTask<String, Integer, JSONObject> {

    private Context contexte;
    private TextView tv;

    public TAGetAdresseCinema(Context contexte, TextView tv) {
        this.contexte = contexte;
        this.tv = tv;
    }

    @Override
    protected JSONObject doInBackground(String... asParametres) {
        JSONObject objet = null;

        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];
        String lsCodeCinema = asParametres[2];


        URL url;
        HttpURLConnection httpConnection = null;

        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource + "?code=" + lsCodeCinema);
            httpConnection = (HttpURLConnection) url.openConnection();

            // Connexion
            httpConnection.connect();
            // EXECUTION DE LA REQUETE ET RESPONSE
            InputStream is = httpConnection.getInputStream();
            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsContenu = br.readLine();
            objet = new JSONObject(lsContenu);
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
        return objet;
    }

    @Override
    protected void onPostExecute(JSONObject objet) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final

        String lsCode;
        String lsAdresse;
        try {
            lsCode = objet.get("code").toString();
            lsAdresse = objet.get("adresse").toString();
        } catch (Exception e) {
            lsCode = "";
            lsAdresse = e.getMessage();

        }

        tv.setText(lsAdresse);
        new TASetCoordCinema(this.contexte, tv).execute("http://172.26.10.23:8084/Cinescope2017Web/", "Cinema", lsCode, lsAdresse);
    }
}
