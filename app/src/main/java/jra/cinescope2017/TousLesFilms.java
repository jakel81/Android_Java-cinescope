package jra.cinescope2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TousLesFilms extends AppCompatActivity implements View.OnClickListener {

    private Button buttonVideo;
    private Button buttonPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tous_les_films);

        buttonVideo = (Button) findViewById(R.id.buttonVideo);
        buttonPhoto = (Button) findViewById(R.id.buttonPhoto);

        buttonVideo.setOnClickListener(this);
        buttonPhoto.setOnClickListener(this);

        Bundle params = this.getIntent().getExtras();
//        String valeur = params.getString("motRecherche");
//        Toast.makeText(this, valeur, Toast.LENGTH_SHORT).show();

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

        if (v == buttonPhoto) {
//            setResult(RESULT_OK);
//            finish();

            Intent intention = new Intent(this, UnFilm.class);
            startActivity(intention);
        }
        if (v == buttonVideo) {
//            setResult(RESULT_CANCELED);
//            finish();

            Intent intention = new Intent(this, UnFilmUneVideo.class);
            startActivity(intention);
        }

    }
}
