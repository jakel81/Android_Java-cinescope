package jra.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SallesDeParis extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextRechercher;
    private Button buttonRechercher;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salles_de_paris);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        editTextRechercher = (EditText) findViewById(R.id.editTextRechercher);
        buttonRechercher = (Button) findViewById(R.id.buttonRechercher);

        buttonRechercher.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {

        if (v == buttonRechercher) {
            if (editTextRechercher.getText().toString().trim().equals("")) {
                textViewMessage.setText("Veuillez saisir un code arrondissement");
            } else {

                String lsCodeArrondissement = editTextRechercher.getText().toString();
                Intent intention = new Intent(this, ResultatRechercheSDP.class);
                intention.putExtra("code_arrondissement", lsCodeArrondissement);
                startActivity(intention);
            }
        }

    }
}
