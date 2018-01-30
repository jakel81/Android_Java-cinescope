package jra.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

//public class AvisDesCritiques extends AppCompatActivity implements View.OnClickListener {
public class AvisDesCritiques extends AppCompatActivity {

//    private TextView textViewContenu;
//    private Button buttonValider;
//    private Button buttonAnnuler;
    private GridView gridViewContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avis_des_critiques);

//        textViewContenu = (TextView) findViewById(R.id.textViewContenu);

        gridViewContenu = (GridView) findViewById(R.id.gridViewContenu);

//        buttonValider = (Button) findViewById(R.id.buttonValider);
//        buttonAnnuler = (Button) findViewById(R.id.buttonAnnuler);
//
//        buttonValider.setOnClickListener(this);
//        buttonAnnuler.setOnClickListener(this);

        String lsURL = "http://192.168.1.39:8080/Cinescope2017Web/";
        String lsRessource = "AvisDesCritiques";
//        TAExterneHPP tae = new TAExterneHPP(textViewContenu);
//        TAExterneHPP tae = new TAExterneHPP(this, gridViewContenu);
//        tae.execute(lsURL, lsRessource);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuItemChoix.menuItemChoix(this, item.getItemId());
    } // onOptionsItemSelected


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

//    @Override
//    public void onClick(View v) {
//        if (v == buttonValider) {
//            setResult(RESULT_OK);
//            finish();
//        }
//        if (v == buttonAnnuler) {
//            setResult(RESULT_CANCELED);
//            finish();
//        }
//    }
}
