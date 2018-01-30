package jra.cinescope2017;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class TAResultatRechercheSDP extends AsyncTask<String, Integer, JSONArray> {

    private Context contexte;
    private ListView lv;
    private TextView tv;

    public TAResultatRechercheSDP(Context contexte, ListView lv, TextView tv) {
        this.contexte = contexte;
        this.lv = lv;
        this.tv = tv;
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
        String[] tNomCinema = new String[n];
        String nomArrondissement = "";

        try {

            if (tableauJSON.length() == 0) {
                nomArrondissement = "Aucun cinéma trouvé";
            } else {
                for (int i = 0; i < tableauJSON.length(); i++) {
                    objet = (JSONObject) tableauJSON.get(i);
                    tNomCinema[i] = objet.get("nom_cinema") + "\n" + objet.get("adresse_cinema") + "\n" + objet.get("nom_station") + "\n" + objet.get("titre_film");
                    nomArrondissement = objet.get("code_arrondissement") + "e" + "\n" + objet.get("nom_arrondissement");
                    //Log.i("TTTTTTT: ", nomArrondissement);
                }
            }
        } catch (Exception e) {

        }

        tv.setText(nomArrondissement);
        ArrayAdapter<String> aaListe = new ArrayAdapter<>(contexte, android.R.layout.simple_list_item_1, tNomCinema);
        lv.setAdapter(aaListe);

    }
}
