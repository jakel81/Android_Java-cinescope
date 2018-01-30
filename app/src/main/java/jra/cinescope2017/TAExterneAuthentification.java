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
 * Created by Jo on 06/11/2017.
 */

public class TAExterneAuthentification extends AsyncTask<String, Integer, JSONObject> {

    private Context contexte;
    private TextView tv;

    public TAExterneAuthentification() {
    }

    public TAExterneAuthentification(Context contexte, TextView tv) {
        this.contexte = contexte;
        this.tv = tv;
    }

    @Override
    protected JSONObject doInBackground(String... asParametres) {

        JSONObject objet = null;

        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];
        String lsNom = asParametres[2];
        String lsMDP = asParametres[3];

        URL url = null;
        HttpURLConnection httpConnection = null;

        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource + "?nom=" + lsNom + "&mdp=" + lsMDP);
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

        String lsID;
        String lsNom;
        String lsMDP;
        String lsEmail;
        try {
            lsID = objet.get("id").toString();
            lsNom = objet.get("nom").toString();
            lsMDP = objet.get("mdp").toString();
            lsEmail = objet.get("email").toString();
        } catch (Exception e) {
            lsID = "";
            lsNom = e.getMessage();
            lsMDP = "";
            lsEmail = "";
        }

        tv.setText(lsID + ":" + lsNom + "-" + lsMDP + "-" + lsEmail);
        Globale.setId(lsID);
        Globale.setNom(lsNom);
        Globale.setMdp(lsMDP);
        Globale.setEmail(lsEmail);
    }
}
