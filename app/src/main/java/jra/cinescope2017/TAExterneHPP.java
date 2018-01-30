package jra.cinescope2017;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jo on 31/10/2017.
 */

public class TAExterneHPP extends AsyncTask<String, Integer, String> {

    //    private TextView tv;
    private GridView gv1;
    private GridView gv2;
    private Context contexte;

//    public TAExterne(TextView tv) {
//
//        this.tv = tv;
//    }

    public TAExterneHPP(Context contexte, GridView gv1, GridView gv2) {
        this.gv1 = gv1;
        this.gv2 = gv2;
        this.contexte = contexte;
    }

    @Override
    protected String doInBackground(String... asParameters) {

        StringBuilder lsb = new StringBuilder();

        String lsURL = asParameters[0];
        String lsRessource = asParameters[1];

        URL url;
        HttpURLConnection httpConnection = null;

        try {
            url = new URL(lsURL + lsRessource);
            httpConnection = (HttpURLConnection) url.openConnection();

            httpConnection.connect();

            InputStream is = httpConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String lsLigne;

            int i = 0;

            while ((lsLigne = br.readLine()) != null) {
                lsb.append(lsLigne);
                lsb.append("\n");
                i++;
                publishProgress(i);
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

//    @Override
//    protected void onProgressUpdate(Integer... aiProgression) {
//        tv.setText(Integer.toString(aiProgression[0]));
//    }

//    @Override
//    protected void onPostExecute(String s) {
//
//        tv.setText(s);
//    }

    @Override
    protected void onPostExecute(String lsb) {

        List<String> items1 = new ArrayList<>();
        List<String> items2 = new ArrayList<>();
        String[] tLignes = lsb.split("\n");
        String lsLigne;
        String[] tChamps;
        String champs;

        for (int i = 0; i < tLignes.length; i++) {
            lsLigne = tLignes[i];
            tChamps = lsLigne.split(";");
            items1.add(tChamps[0]);
            for (int j = 1; j < tChamps.length; j++) {
                champs = tChamps[j];
                items2.add(champs);
            }
        }

        gv1.setAdapter(new ArrayAdapter<>(contexte, R.layout.cellule_text, items1));
        gv2.setAdapter((new ArrayAdapter<>(contexte, R.layout.cellule_text, items2)));
    }
}
