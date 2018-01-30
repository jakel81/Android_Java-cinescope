package jra.cinescope2017;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jo on 14/11/2017.
 */

public class SQLiteImportationDepartements extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "cinescope2017.db";

    private static final String DROP_TABLE_DEPARTEMENT = "DROP TABLE IF EXISTS departement";
    private static final String CREATE_TABLE_DEPARTEMENT = "CREATE TABLE IF NOT EXISTS departement(id_departement INTEGER PRIMARY KEY AUTOINCREMENT, code_departement CHAR(3) NOT NULL UNIQUE, nom_departement VARCHAR(50) NOT NULL UNIQUE)";

    public SQLiteImportationDepartements(Context contexte, SQLiteDatabase.CursorFactory fabrique) {

        super(contexte, DB_NAME, fabrique, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DEPARTEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_DEPARTEMENT);
        onCreate(db);
    }
}
