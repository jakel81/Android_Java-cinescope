package jra.cinescope2017;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jo on 14/11/2017.
 */

public class PermissionsCheck extends AppCompatActivity implements View.OnClickListener {

        private final int PERMISSION_REQUEST_ALL = 99;

        private Switch switchCamera;
        private Switch switchMicro;
        private Switch switchEnr;
        private Button buttonValider;

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.permissions_check);

            switchCamera = (Switch) findViewById(R.id.switchCamera);
            switchMicro = (Switch) findViewById(R.id.switchMicro);
            switchEnr = (Switch) findViewById(R.id.switchEnr);
            buttonValider = (Button) findViewById(R.id.buttonValider);

        }

        @Override
        public void onRequestPermissionsResult ( int requestCode, String permissions[],
        int[] grantResults){
            Log.i("Granted", Integer.toString(grantResults.length));

        /*
        Cette méthode est appelée par ActivityCompat.requestPermissions()
        */
            switch (requestCode) {
                case PERMISSION_REQUEST_ALL: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0) {
                        for (int i = 0; i < permissions.length; i++) {
                            Toast.makeText(this, permissions[i], Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "La requête a été annulée", Toast.LENGTH_SHORT).show();

                    }
                    return;
                }
            } /// switch
        } /// onRequestPermissionsResult

        @Override
        public void onClick (View v){
//        boolean lbEtatCamera = switchCamera.isChecked();
//        boolean lbEtatRecordAudio = switchMicro.isChecked();
//        boolean lbEtatStorage = switchEnr.isChecked();

        /*Toutes les permissions en une seule fois avec une seule boite de dialogue et un phenomeme de "diapo"*/
            List<String> listePermissions = new ArrayList();
            if (switchCamera.isChecked()) {
                listePermissions.add(Manifest.permission.CAMERA);
            }
            if (switchMicro.isChecked()) {
                listePermissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (switchEnr.isChecked()) {
                listePermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (listePermissions.size() > 0) {
                String[] tPermissions = listePermissions.toArray(new String[listePermissions.size()]);
                ActivityCompat.requestPermissions(this, tPermissions, PERMISSION_REQUEST_ALL);
            }

        }
    }
