package jra.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MonCompte extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private EditText editTextUser;
    private EditText editTextPwd;
    private EditText editTextEmail;
    private Button buttonModifier;
    private Button buttonSupprimer;
    private TextView textViewMessage;
    private TextView textViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mon_compte);

        initInterface();
        initEvents();

        Bundle params = this.getIntent().getExtras();
        String id = params.getString("id");
        String nom = params.getString("nom");
        String mdp = params.getString("mdp");
        String email = params.getString("email");

        textViewID.setText(id);
        editTextUser.setText(nom);
        editTextPwd.setText(mdp);
        editTextEmail.setText(email);
    }

    private void initEvents() {

        editTextEmail.setOnFocusChangeListener(this);
        editTextPwd.setOnFocusChangeListener(this);
        editTextEmail.setOnFocusChangeListener(this);
        buttonSupprimer.setOnClickListener(this);
        buttonModifier.setOnClickListener(this);

    }

    private void initInterface() {

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPwd = (EditText) findViewById(R.id.editTextPwd);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        buttonSupprimer = (Button) findViewById(R.id.buttonSupprimer);
        buttonModifier = (Button) findViewById(R.id.buttonModifier);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
        textViewID = (TextView) findViewById(R.id.textViewID);

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

        if (v == buttonSupprimer) {

            new TAExterneSuppressionUser(textViewMessage).execute("http://192.168.1.39:8080/Cinescope2017Web/", "UtilisateurDelete", Globale.getId());
        }

        if (v == buttonModifier) {

            new TAExterneModificationUser(textViewMessage).execute("http://192.168.1.39:8080/Cinescope2017Web/", "UtilisateurUpdate", Globale.getId());
        }


    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }
}
