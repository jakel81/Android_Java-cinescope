package jra.cinescope2017;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

/**
 * Created by Jo on 02/11/2017.
 */

public class TAExterneBO extends AsyncTask<String, Integer, String> {

    private GridView gv1;
    private GridView gv2;
    private Context contexte;


    public TAExterneBO(Context contexte, GridView gv1, GridView gv2) {
        this.gv1 = gv1;
        this.gv2 = gv2;
        this.contexte = contexte;
    }

    @Override
    protected String doInBackground(String... asParameters) {
        return null;
    }
}
