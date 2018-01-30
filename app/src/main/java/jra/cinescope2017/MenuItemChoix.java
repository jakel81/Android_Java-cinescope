package jra.cinescope2017;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Jo on 06/11/2017.
 */

public class MenuItemChoix {

    public static boolean menuItemChoix(Activity activite, int choix) {

        Intent intention;

        // Détermine quelle entrée a été sélectionnée.
        switch (choix) {

            // Aiguille
            case (R.id.itemAccueil):
                intention = new Intent(activite, Main.class);
                activite.startActivityForResult(intention, 1);
                return true;

            case (R.id.itemFilms):
                intention = new Intent(activite, TousLesFilms.class);
                activite.startActivityForResult(intention, 2);
                return true;

            case (R.id.itemSDP):
                intention = new Intent(activite, TousLesFilms.class);
                activite.startActivityForResult(intention, 3);
                return true;

            case (R.id.itemBO):
                intention = new Intent(activite, BoxOffice.class);
                activite.startActivityForResult(intention, 4);
                return true;

            case (R.id.itemHPP):
                intention = new Intent(activite, HitParadeDuPublic.class);
                activite.startActivityForResult(intention, 5);
                return true;

            case (R.id.itemADC):
                intention = new Intent(activite, AvisDesCritiques.class);
                activite.startActivityForResult(intention, 6);
                return true;

            case (R.id.itemRA):
                intention = new Intent(activite, RechercheAvancee.class);
                activite.startActivityForResult(intention, 7);
                return true;

            case (R.id.itemInscription):
                intention = new Intent(activite, Inscription.class);
                activite.startActivityForResult(intention, 8);
                return true;

            case (R.id.itemAuthentification):
                intention = new Intent(activite, Authentification.class);
                activite.startActivityForResult(intention, 9);
                return true;

            case (R.id.itemCompte):
                intention = new Intent(activite, MonCompte.class);
                activite.startActivityForResult(intention, 10);
                return true;

            case (R.id.itemImportation):
                intention = new Intent(activite, Importations.class);
                activite.startActivityForResult(intention, 11);
                return true;

            case (R.id.action_settings):
                Toast.makeText(activite, "Configuration", Toast.LENGTH_SHORT).show();
                return true;

            case (R.id.itemGeocodage):
                intention = new Intent(activite, Geocodage.class);
                activite.startActivityForResult(intention, 12);
                return true;

            case (R.id.itemAide):
                // Une activite avec une WebView et l'aide en HTML/CSS
                intention = new Intent(activite, Aide.class);
                activite.startActivityForResult(intention, 13);
                return true;

            case (R.id.itemAPropos):
                /*
                 * LA BOITE DE DIALOGUE
                 */
                // --- L'écouteur pour le clic
                // Le code peut être en dehors de la méthode utilisatrice
                DialogInterface.OnClickListener ecouteurClicBD = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int codeBouton) {

                    }
                };

                String lsTitre = "A propos";
                String lsMessage = "Android Contacts\nNovembre 2017\nVersion 0.9\nAuthor : PB & Co";
                String lsOK = "OK";

                AlertDialog.Builder ad = new AlertDialog.Builder(activite);
                ad.setTitle(lsTitre);
                ad.setMessage(lsMessage);
                ad.setNeutralButton(lsOK, ecouteurClicBD);
                ad.show();
                return true;

            default:
                // Renvoie false si les entrées n’ont pas été gérées.
                return false;
        }
    } /// menuItemChoix
}
