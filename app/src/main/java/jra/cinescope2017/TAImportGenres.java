package jra.cinescope2017;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jo on 08/11/2017.
 */

public class TAImportGenres extends AsyncTask<String, Integer, String> {

    private Context contexte;
    private TextView tv;
    private String isBD;
    private String isTable;

    public TAImportGenres() {

    }

    public TAImportGenres(Context contexte, TextView tv, String psBD, String psTable) {

        this.contexte = contexte;
        this.tv = tv;
        this.isBD = psBD;
        this.isTable = psTable;
    }

    @Override
    protected String doInBackground(String... asParametres) {

        StringBuilder lsb = new StringBuilder();

        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];
        String lsTable = asParametres[2];

        URL url;
        HttpURLConnection httpConnection = null;

        try {
            // Instanciation de HttpURLConnection avec l'objet url
            url = new URL(lsURL + lsRessource + "?table=" + lsTable);
            Log.i("url", url.toString());
            httpConnection = (HttpURLConnection) url.openConnection();

            // Connexion
            httpConnection.connect();
            // EXECUTION DE LA REQUETE ET RESPONSE
            InputStream is = httpConnection.getInputStream();
            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String lsLigne;
            while ((lsLigne = br.readLine()) != null) {

                lsb.append(lsLigne);
                lsb.append("\n");
            }
            br.close();
            is.close();
        } catch (IOException e) {

            lsb.append(e.getMessage());

        } finally {

            httpConnection.disconnect();
        }

        return lsb.toString();

    }

    @Override
    protected void onPostExecute(String s) {

        tv.setText(s);

        DAOGenerique.insertGenres("cinescope2017","genre",s);


    }
}
