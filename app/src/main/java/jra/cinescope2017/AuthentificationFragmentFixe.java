package jra.cinescope2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class AuthentificationFragmentFixe extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification_fragment_fixe);
    }

    public void retourAccueil(View vue) {
        Intent intention = new Intent(this, MainFragmentFixe.class);
        startActivity(intention);
    }
}
