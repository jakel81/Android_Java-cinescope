package jra.cinescope2017;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jo on 06/11/2017.
 */

public class SQLiteImportationGenres extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "cinescope2017.db";

    private static final String DROP_TABLE_GENRE = "DROP TABLE IF EXISTS genre";
    private static final String CREATE_TABLE_GENRE = "CREATE TABLE IF NOT EXISTS genre(id_genre INTEGER(11) PRIMARY KEY AUTOINCREMENT, code_genre CHAR(2) NOT NULL UNIQUE, libelle_genre VARCHAR(50) NOT NULL UNIQUE, genre_grammatical CHAR(1) NOT NULL)";

    public SQLiteImportationGenres(Context contexte, SQLiteDatabase.CursorFactory fabrique) {
        // --- Cree la BD si elle n'existe pas
        // --- et de ce fait execute le code de onCreate()
        // --- Se connecte si elle existe
        super(contexte, DB_NAME, fabrique, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_GENRE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_TABLE_GENRE);
        onCreate(db);

    }
}
