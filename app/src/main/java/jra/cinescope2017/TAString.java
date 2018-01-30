package jra.cinescope2017;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jo on 08/11/2017.
 */

public class TAString extends AsyncTask<String, Integer, String> {

    private CBInterfaceString callback;

    public TAString(CBInterfaceString callback) {

        this.callback = callback;
    }

    @Override
    protected String doInBackground(String... asParametres) {

        StringBuilder lsb = new StringBuilder();

        String lsURL;
        String lsRessource;
        String lsTable;

        lsURL = asParametres[0];
        lsRessource = asParametres[1];
        lsTable = asParametres[2];

        URL url;
        HttpURLConnection httpConnection = null;

        try {

            url = new URL(lsURL + lsRessource + "?table=" + lsTable);
            httpConnection = (HttpURLConnection) url.openConnection();


            httpConnection.connect();

            InputStream is = httpConnection.getInputStream();

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
}
