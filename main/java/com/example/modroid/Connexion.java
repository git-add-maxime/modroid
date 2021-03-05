package com.example.modroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modroid.BD.BDActivity;
import com.example.modroid.BD.DatabaseManager;
import com.example.modroid.motus.MainGame;

public class Connexion extends AppCompatActivity {

    private EditText pseudo,mdp;
    private static DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        databaseManager=new DatabaseManager(this);

        pseudo = findViewById(R.id.editTextPseudo);
        mdp = findViewById(R.id.editTextMotdepasse);



    }

    public void onConnexion(View view) {
        try{
            Intent intent = new Intent(this, MainGame.class);
            intent.putExtra("User",databaseManager.getUser(pseudo.getText().toString(),mdp.getText().toString()).getPseudo());
            intent.putExtra("mdp",mdp.getText().toString());
            startActivity(intent);
        }catch(Exception e){
            Toast toast = Toast.makeText(getApplicationContext(), "Le pseudo ou mot de passe est incorrect", Toast.LENGTH_SHORT);
            toast.show();
        }

    }



    public void onInscription(View view) {
        Intent intent = new Intent(this, Inscription.class);
        startActivity(intent);
        //todo mettre le layout d'inscription
    }

    public void onMdpo(View view) {
        //mettre le layout de mot de passe oublier
        Intent intent = new Intent(this, MotDePasseOublier.class);
        //test pseudo valide
        if (this.pseudo.length()!=0){
            //test pseudo vide
            intent.putExtra("editTextPseudo",this.pseudo.getText().toString());
            //todo: test pseudo dans la BD
            //todo: precharger la question secrete
            startActivity(intent);

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Veuiller entrer un pseudo", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onScore(View view) {
        Intent intent = new Intent(this, BDActivity.class);
        startActivity(intent);
    }


    public static DatabaseManager getDataManager(){
        return databaseManager;
    }
}
