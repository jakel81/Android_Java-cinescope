package jra.cinescope2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class CatalogueFragmentFixe extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue_fragment_fixe);

        GridView gridView1 = findViewById(R.id.gridView1);
        gridView1.setAdapter(new ImageAdapter(this));

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(CatalogueFragmentFixe.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retourAccueil(View vue) {
        Intent intention = new Intent(this, MainFragmentFixe.class);
        startActivity(intention);
    }
}
