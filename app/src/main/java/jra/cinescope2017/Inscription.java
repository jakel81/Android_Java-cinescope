package jra.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Inscription extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    // Attributs pour les widgets
    private EditText editTextUser;
    private Button buttonValider;
    private EditText editTextPwd;
    private EditText editTextEmail;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        initInterface();
        initEvents();
    }

    private void initEvents() {

        // Liaison widget <--> Events
        buttonValider.setOnClickListener(this);

    }

    private void initInterface() {

        // Liaison widget <--> Attribut
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPwd = (EditText) findViewById(R.id.editTextPwd);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);

    }

    @Override
    public void onClick(View v) {

        if (v == buttonValider) {
            new TAExterneInsert(textViewMessage).execute("http://192.168.1.39:8080/Cinescope2017Web/", "UtilisateurInsert", editTextUser.getText().toString(), editTextPwd.getText().toString(), editTextEmail.getText().toString());
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (v == editTextUser) {
        }

        if (v == editTextPwd) {
        }

        if (v == editTextEmail) {
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } // onOptionsItemSelected


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
