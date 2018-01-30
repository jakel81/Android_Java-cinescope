package jra.cinescope2017;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Jo on 08/11/2017.
 */

public class DAOGenerique {

    public static void insertVilles(String psBD, String psTable, String psContenu) {

        StringBuilder lsb = new StringBuilder();
        long llNum = 0;
        String lsChemin = "/data/data/jra.cinescope2017/databases/cinescope.db";
        SQLiteDatabase bd = SQLiteDatabase.openDatabase(lsChemin, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues hmValeurs;


        // --- Insertion
        try {
            JSONArray tableauJSON = new JSONArray(psContenu);
            JSONObject objetJSON;
            for (int i = 0; i < tableauJSON.length(); i++) {
                objetJSON = (JSONObject) tableauJSON.get(i);
                hmValeurs = new ContentValues();
                hmValeurs.put("cp", objetJSON.get("CP").toString());
                hmValeurs.put("nom_ville", objetJSON.get("NOM_VILLE").toString());
                llNum = bd.insert("ville", null, hmValeurs);

            }

            //lsb.append(String.valueOf(llNum) + " enregistrement ajouté");
            bd.close();
        } catch (Exception e) {
            lsb.append(e.getMessage());
        }
        Log.i("", lsb.toString());

    } /// insertVilles

    public static void insertPays(String psBD, String psTable, String psContenu) {

        StringBuilder lsb = new StringBuilder();
        long llNum = 0;
        String lsChemin = "/data/data/jra.cinescope2017/databases/cinescope.db";
        SQLiteDatabase bd = SQLiteDatabase.openDatabase(lsChemin, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues hmValeurs;

        // --- Insertion
        try {
            JSONArray tableauJSON = new JSONArray(psContenu);
            JSONObject objetJSON;
            for (int i = 0; i < tableauJSON.length(); i++) {
                objetJSON = (JSONObject) tableauJSON.get(i);
                hmValeurs = new ContentValues();
                hmValeurs.put("cp", objetJSON.get("ID_PAYS").toString());
                hmValeurs.put("nom_pays", objetJSON.get("NOM_PAYS").toString());
                llNum = bd.insert("pays", null, hmValeurs);

            }

            //lsb.append(String.valueOf(llNum) + " enregistrement ajouté");
            bd.close();
        } catch (Exception e) {
            lsb.append(e.getMessage());
        }
        Log.i("", lsb.toString());

    } /// insertPays

    /**
     * @param psBD
     * @param psTable
     * @param psContenu
     */
    public static void insertArrondissements(String psBD, String psTable, String psContenu) {

        StringBuilder lsb = new StringBuilder();
        long llNum = 0;
        String lsChemin = "/data/data/jra.cinescope2017/databases/cinescope.db";
        SQLiteDatabase bd = SQLiteDatabase.openDatabase(lsChemin, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues hmValeurs;

        // --- Insertion
        try {

            /*
            Suppression préalable
             */
            long llAffectes = bd.delete("arrondissement", null, null);
            Log.e("liAffectes sup", Long.toString(llAffectes));

            bd.beginTransaction();

            JSONArray tableauJSON = new JSONArray(psContenu);
            JSONObject objetJSON;
            long llNums = 0;
            for (int i = 0; i < tableauJSON.length(); i++) {
                objetJSON = (JSONObject) tableauJSON.get(i);
                hmValeurs = new ContentValues();
                hmValeurs.put("id_arrondissement", objetJSON.get("ID_ARRONDISSEMENT").toString());
                hmValeurs.put("code_arrondissement", objetJSON.get("CODE_ARRONDISSEMENT").toString());
                hmValeurs.put("nom_arrondissement", objetJSON.get("NOM_ARRONDISSEMENT").toString());
                llNum = bd.insert("arrondissement", null, hmValeurs);
                Log.i("llNum", Long.toString(llNum));
                llNums++;
            }
            Log.i("llNumSSS", Long.toString(llNums));
            //lsb.append(String.valueOf(llNum) + " enregistrement ajouté");
            bd.setTransactionSuccessful();
            bd.endTransaction();
            bd.close();
        } catch (Exception e) {
            lsb.append(e.getMessage());
        }
        Log.i("", lsb.toString());

    } /// insertArrondissements


    public static void insertGenres(String psBD, String psTable, String psContenu) {

        StringBuilder lsb = new StringBuilder();
        long llNum = 0;
        String lsChemin = "/data/data/jl.fr.cinescope2017/databases/cinescope2017.db";
        SQLiteDatabase bd = SQLiteDatabase.openDatabase(lsChemin, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues hmValeurs;

        // --- Insertion
        try {
            JSONArray tableauJSON = new JSONArray(psContenu);
            JSONObject objetJSON;
            for (int i = 0; i < tableauJSON.length(); i++) {
                objetJSON = (JSONObject) tableauJSON.get(i);
                hmValeurs = new ContentValues();
                hmValeurs.put("id_genre", objetJSON.get("ID_GENRE").toString());
                hmValeurs.put("code_genre", objetJSON.get("CODE_GENRE").toString());
                hmValeurs.put("libelle_genre", objetJSON.get("LIBELLE_GENRE").toString());
                hmValeurs.put("genre_grammatical", objetJSON.get("GENRE_GRAMMATICAL").toString());
                llNum = bd.insert("genre", null, hmValeurs);

            }

            //lsb.append(String.valueOf(llNum) + " enregistrement ajouté");
            bd.close();
        } catch (Exception e) {
            lsb.append(e.getMessage());
        }
        Log.i("", lsb.toString());

    } /// insertGenres

    public static void insertDepartements(String psBD, String psTable, String psContenu) {

        StringBuilder lsb = new StringBuilder();
        long llNum = 0;
        String lsChemin = "/data/data/jra.cinescope2017/databases/cinescope2017.db";
        SQLiteDatabase bd = SQLiteDatabase.openDatabase(lsChemin, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues hmValeurs;

        // --- Insertion
        try {
            JSONArray tableauJSON = new JSONArray(psContenu);
            JSONObject objetJSON;
            for (int i = 0; i < tableauJSON.length(); i++) {
                objetJSON = (JSONObject) tableauJSON.get(i);
                hmValeurs = new ContentValues();
                hmValeurs.put("id_departement", objetJSON.get("ID_DEPARTEMENT").toString());
                hmValeurs.put("code_departement", objetJSON.get("CODE_DEPARTEMENT").toString());
                hmValeurs.put("nom_departement", objetJSON.get("NOM_DEPARTEMENT").toString());
                llNum = bd.insert("departement", null, hmValeurs);

            }

            //lsb.append(String.valueOf(llNum) + " enregistrement ajouté");
            bd.close();
        } catch (Exception e) {
            lsb.append(e.getMessage());
        }
        Log.i("", lsb.toString());

    } /// insertDepartements

}
