package jra.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Authentification extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private EditText editTextUser;
    private Button buttonValider;
    private EditText editTextPwd;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentification);

        initInterface();
        initEvents();
    }

    private void initEvents() {

        editTextUser.setOnFocusChangeListener(this);
        editTextPwd.setOnFocusChangeListener(this);
        buttonValider.setOnClickListener(this);
    }

    private void initInterface() {

        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPwd = (EditText) findViewById(R.id.editTextPwd);
        buttonValider = (Button) findViewById(R.id.buttonValider);
        textViewMessage = (TextView) findViewById(R.id.textViewMessage);
    }

    @Override
    public void onClick(View v) {

        if (v == buttonValider) {
            new TAExterneAuthentification(this, textViewMessage).execute("http://192.168.1.39:8080/Cinescope2017Web/", "Authentification", editTextUser.getText().toString(), editTextPwd.getText().toString());
        }

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (v == editTextUser) {
        }

        if (v == editTextPwd) {
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
