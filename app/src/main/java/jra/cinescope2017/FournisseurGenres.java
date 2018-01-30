package jra.cinescope2017;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Jo on 08/11/2017.
 */

public class FournisseurGenres extends ContentProvider {

    private SQLiteDatabase db;

    public static final Uri CONTENT_URI = Uri.parse("content://jra.cinescope2017.FournisseurGenres");

    @Override
    public boolean onCreate() {
        Context context = getContext();
        // Connexion à la BD
        SQLiteImportationGenres dbHelper = new SQLiteImportationGenres(context, null);
        this.db = dbHelper.getWritableDatabase();
        return (this.db == null) ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor curseur = null;
        // --- Tous les enregistrements
        try {
            // --- query(table, tColonnes, sWhere, tParamsWhere, sGroupBy, sHaving, sOrderBy)
            curseur = this.db.query("genre", projection, selection, selectionArgs, null, null, sortOrder);
        } catch (SQLiteException e) {
            curseur = null;
        }
        return curseur;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
