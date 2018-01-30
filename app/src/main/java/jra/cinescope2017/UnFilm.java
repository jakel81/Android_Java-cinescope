package jra.cinescope2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class UnFilm extends AppCompatActivity {

    private ImageView imageViewFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_film);

        imageViewFilm = (ImageView) findViewById(R.id.imageViewFilm);

        new PhotoReadOneAsynchrone(this, imageViewFilm).execute("titanic.jpg");
    }
}
