package jra.cinescope2017;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Bandeau extends Fragment implements View.OnClickListener {

    private Button buttonAuthentification;
    private Button buttonCatalogue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        // Ligne « équivalente » à setContentView(vue)
        // View = inflate(int resource, ViewGroup root, boolean attachToRoot)
        View vue = inflater.inflate(R.layout.bandeau, container, false);

        buttonAuthentification = vue.findViewById(R.id.buttonAuthentification);
        buttonAuthentification.setOnClickListener(this);

        buttonCatalogue = vue.findViewById(R.id.buttonCatalogue);
        buttonCatalogue.setOnClickListener(this);
        return vue;
    }

    @Override
    public void onClick(View v) {
        if (v == buttonAuthentification) {
            Intent intention = new Intent(this.getActivity(), AuthentificationFragmentFixe.class);
            startActivity(intention);
        } /// onClick

        if (v == buttonCatalogue) {
            Intent intention = new Intent(this.getActivity(), CatalogueFragmentFixe.class);
            startActivity(intention);
        } /// buttonCatalogue
    }
}
