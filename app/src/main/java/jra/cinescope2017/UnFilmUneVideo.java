package jra.cinescope2017;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class UnFilmUneVideo extends AppCompatActivity {

    private VideoView videoView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.un_film_une_video);

        videoView1 = (VideoView) findViewById(R.id.videoView1);

        String lsCheminComplet = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/" + "tout_l_argent_du_monde.3gpp";
        try {
            videoView1.setVideoURI(Uri.parse("file:///" + lsCheminComplet));

        } catch (Exception e) {
        }
    }
}
