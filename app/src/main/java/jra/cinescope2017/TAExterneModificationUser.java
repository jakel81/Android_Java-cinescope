package jra.cinescope2017;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Jo on 06/11/2017.
 */

public class TAExterneModificationUser extends AsyncTask<String, Integer, String> {

    private TextView textViewMessage;

    public TAExterneModificationUser(TextView textViewMessage) {
        super();
        this.textViewMessage = textViewMessage;
    } /// Constructeur

    @Override
    protected String doInBackground(String... asParametres) {

        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
        StringBuilder lsbResultat = new StringBuilder();
        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];
        URL url;
        HttpURLConnection httpConnection = null;
        boolean lbErreur = false;

        try {
            url = new URL(lsURL + lsRessource);
            httpConnection = (HttpURLConnection) url.openConnection();

            // Choix de la methode get ou post
            // Il faudra passer en POST
            httpConnection.setRequestMethod("POST");

            // Autorise l'envoi de donnees
            // Sets the flag indicating whether this URLConnection allows input.
            httpConnection.setDoInput(true);

            // Connexion
            httpConnection.connect();

            String params = "";
            params += "id=" + URLEncoder.encode(asParametres[2], "UTF-8");

            // Execution de la requete parametree
            OutputStreamWriter osw = new OutputStreamWriter(httpConnection.getOutputStream());
            osw.write(params);
            osw.flush();
            osw.close();

            // Execution de la requete parametree
            // Lecture du flux de la REPONSE
            InputStream inputStream = httpConnection.getInputStream();

            // Comme l'on recoit un flux Text ASCII
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String lsLigne;
            while ((lsLigne = br.readLine()) != null) {
                if (!lsLigne.trim().equals("")) {
                    lsbResultat.append(lsLigne);
                    lsbResultat.append("\n");
                }
            }
            br.close();
            inputStream.close();

        } catch (UnsupportedEncodingException e) {
            lsbResultat.append(e.getMessage());
            lbErreur = true;
        } catch (IOException e) {
            lsbResultat.append(e.getMessage());
            lbErreur = true;
        } finally {
            // Deconnexion
            httpConnection.disconnect();
        }

        if (lbErreur) {
            lsbResultat.setLength(0);
            lsbResultat.append("Erreur réseau");
        }

        // Renvoie la valeur a onPostExecute
        return lsbResultat.toString();

    }
}
