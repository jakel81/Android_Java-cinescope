package jra.cinescope2017;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
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
import java.util.List;
import java.util.Locale;

/**
 * Created by Jo on 14/11/2017.
 */

public class TASetCoordCinema extends AsyncTask<String, Integer, String> {

    private TextView textViewMessage;
    private Context contexte;

    public TASetCoordCinema(Context contexte, TextView textViewMessage) {
        super();
        this.contexte = contexte;
        this.textViewMessage = textViewMessage;
    } /// Constructeur

    /**
     * getLatLongFromAdresse : les coordonnees d'une adresse, géocodage
     *
     * @param adresse
     * @return
     */
    private double[] getLatLongFromAdresse(Geocoder geocodeur, String adresse) {
        // Fonction perso pour le géocodage
        double tLatLng[] = {0.0, 0.0};

        try {
            List<Address> adresses = geocodeur.getFromLocationName(adresse, 1);
            if (adresses.size() > 0) {
                tLatLng[0] = adresses.get(0).getLatitude();
                tLatLng[1] = adresses.get(0).getLongitude();
            }
        } catch (IOException e) {
        }

        return tLatLng;

    } // / getLatLongFromAdresse

    @Override
    protected String doInBackground(String... asParametres) {
        // String... parametre : nombre variable d'arguments
        // Se deplace dans un thread d'arriere-plan
        StringBuilder lsbResultat = new StringBuilder();
        String lsURL = asParametres[0];
        String lsRessource = asParametres[1];
        String lsAdresse = asParametres[2];
        String lsCodeCinema = asParametres[3];
        URL url;
        HttpURLConnection httpConnection = null;
        boolean lbErreur = false;

        try {

            Geocoder geocodeur = new Geocoder(contexte, Locale.FRANCE);
            double[] coord = getLatLongFromAdresse(geocodeur, lsAdresse);

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
            params += "code=" + URLEncoder.encode(lsCodeCinema, "UTF-8");
            params += "&lat=" + URLEncoder.encode(Double.toString(coord[0]), "UTF-8");
            params += "&lng=" + URLEncoder.encode(Double.toString(coord[1]), "UTF-8");

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
    } /// doInBackground

    @Override
    protected void onPostExecute(String asResultat) {
        // Synchronisation avec le thread de l'UI
        // Affiche le resultat final

        if (asResultat.equalsIgnoreCase("Erreur réseau")) {
            textViewMessage.setText(asResultat);
        } else {
            textViewMessage.setText("Etat de l'insertion : " + asResultat.trim());
        } /// if
    } /// onPostExecute

}
