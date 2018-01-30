package jra.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

//public class HitParadeDuPublic extends AppCompatActivity implements View.OnClickListener {
public class HitParadeDuPublic extends AppCompatActivity {

    //    private TextView textViewContenu;
//    private Button buttonValider;
//    private Button buttonAnnuler;
    private GridView gridView1;
    private GridView gridView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_office);

        gridView1 = (GridView) findViewById(R.id.gridView1);
        gridView2 = (GridView) findViewById(R.id.gridView2);

//        textViewContenu = (TextView) findViewById(R.id.textViewContenu);
//
//        buttonValider = (Button) findViewById(R.id.buttonValider);
//        buttonAnnuler = (Button) findViewById(R.id.buttonAnnuler);
//
//        buttonValider.setOnClickListener(this);
//        buttonAnnuler.setOnClickListener(this);

        String lsURL = "http://192.168.1.39:8080/Cinescope2017Web/";
        String lsRessource = "HitParadeDuPublic";
//        TAExterneHPP tae = new TAExterneHPP(textViewContenu);
        TAExterneHPP tae = new TAExterneHPP(this, gridView1, gridView2);
        tae.execute(lsURL, lsRessource);
    }

//    @Override
//    public void onClick(View v) {
//        if (v == buttonValider) {
//            finish();
//        }
//        if (v == buttonAnnuler) {
//            finish();
//        }
//    }
}
