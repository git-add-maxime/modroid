package com.example.modroid.motus;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modroid.BD.DatabaseManager;
import com.example.modroid.BD.User;
import com.example.modroid.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.modroid.Connexion.getDataManager;


public class MainGame extends AppCompatActivity {

    private RecyclerView myRecycleView;
    private RecycleViewAdapter myAdapter;
    private List<Propositions> propositionsList = new ArrayList<>();
    private TextView test;
    static String motMystere;
    private Vibrator Levibreur;
    private User user;
    private DatabaseManager databaseManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.motus);
        Intent intent = this.getIntent();
        Log.d("testCoueur", "onCreate: ");
        databaseManager=getDataManager();
        Log.d("testDBACTIVE", "onCreate: "+databaseManager.toString());


        Log.d("testFonction", "onCreate: entree");
        savedInstanceState=getIntent().getExtras();
        Log.d("testdatabase", "onCreate: "+savedInstanceState.getString("mdp")+" pseudo: "+savedInstanceState.getString("User"));

        user=databaseManager.getUser(savedInstanceState.getString("User"),savedInstanceState.getString("mdp"));
        relanceJeu(user);

    }

    public void deconnexion(View view) {
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        super.finish();
    }

    private void initialiseData() {
        Log.d("testFonction", "initialiseData");
        Propositions propositionUn = new Propositions("e", "p", "i", "c", "e", "s");
        propositionsList.add(propositionUn);

    }


    public void gestionJeu(View view){
        Log.d("testFonction", "gestionJeu ");
        validerMot(view);
    }
    public void validerMot(View view) {


        Propositions propositionActuelle= new Propositions();
        test = (TextView) findViewById(R.id.editTextTest);
        String chaine = test.getText().toString();
        Log.d("test", "recupèreLeMot: " + chaine);


        if(test.length()==6) {
            //Partie verifier mot
            for (int i = 0; i < test.length(); i++) {
                Log.d("test", "lettre : " + test.getText().toString().charAt(i));
                switch (i) {
                    case 0:
                        propositionActuelle.setLettreUn(test.getText().charAt(i) + "");
                        break;
                    case 1:
                        propositionActuelle.setLettreDeux(test.getText().charAt(i) + "");
                        break;
                    case 2:
                        propositionActuelle.setLettreTrois(test.getText().charAt(i) + "");
                        break;
                    case 3:
                        propositionActuelle.setLettreQuatre(test.getText().charAt(i) + "");
                        break;
                    case 4:
                        propositionActuelle.setLettreCinq(test.getText().charAt(i) + "");
                        break;
                    case 5:
                        propositionActuelle.setLettreSix(test.getText().charAt(i) + "");
                        break;
                    default:
                        Log.d("erreur", "on ignore la suite");
                        break;
                }
            }

            //Propositions propositionDeux = new Propositions("s", "e", "i", "s", "m", "e");
            propositionsList.add(propositionActuelle);

            myRecycleView = findViewById(R.id.RecycleView);


            myAdapter = new RecycleViewAdapter(propositionsList, this);
            RecyclerView.LayoutManager myLayoutManager = new LinearLayoutManager(getApplicationContext());
            myRecycleView.setLayoutManager(myLayoutManager);
            myRecycleView.setItemAnimator(new DefaultItemAnimator());
            myRecycleView.setAdapter(myAdapter);
            Log.d("testFonction", "validerMot: fin Boucle si mot correct");
        }
        else{
            //TODO creer message avec ALERT
            //System.out.println("un mot de 6 lettres!!!");
            Levibreur = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            int letemp=100;
            Toast toast = Toast.makeText(getApplicationContext(), "Veuiller entrer un mot de 6 lettres", Toast.LENGTH_SHORT);
            toast.show();
            //on fait vibrer le téléphone
            Levibreur.vibrate(letemp);
        }
        test.setText("");
        Log.d("test", "validerMot: VIFREFOELFKRSJFK");
    }

    private void setMotMystere() {
        Log.d("test", "setMotMystere ");
        Resources res = getResources();
        String[] planets = res.getStringArray(R.array.motsSixLettres);


        motMystere =planets[(int)Math.round(Math.random()*(planets.length-1))];
        Log.d("test", "Le mot mystère est : "+this.motMystere);
    }

    static String getMotMystere() {
        return motMystere;
    }


    public void alertFinJeuVictoire(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        user.setPoints(user.getPoints()+1);
        databaseManager.upDatePoints(user,user.getPoints());
        Log.d("testdatabase", "alertFinJeuVictoire: "+user.getPoints());
        alertDialogBuilder.setMessage("Vous avez gagné !");
        alertDialogBuilder.setPositiveButton("Rejouer",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(),"Une nouvelle partie commence",Toast.LENGTH_LONG).show();
                        setContentView(R.layout.motus);
                        relanceJeu(user);
                    }
                });

        alertDialogBuilder.setNegativeButton("Arreter",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void relanceJeu(User user){
        propositionsList.clear();
        TextView nomUser=findViewById(R.id.textViewNomUser);
        nomUser.setText(user.getPseudo()+user.getPoints());
        setMotMystere();

    }


}