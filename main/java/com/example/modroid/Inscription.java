package com.example.modroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modroid.BD.DatabaseManager;

import static com.example.modroid.Connexion.getDataManager;

public class Inscription extends AppCompatActivity {

    private Spinner spinner;
    private CheckBox homme,femme;
    private  DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        this.spinner = (Spinner) findViewById(R.id.spinnerQuestionSecret);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerQuestionSecret, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        this.homme=findViewById(R.id.checkBoxSexehomme);
        this.femme=findViewById(R.id.checkBoxSexefemme);
    }

    public void onCheckboxClicked(View view) {
        switch(view.getId()) { //TODO à reformuler
            case R.id.checkBoxSexehomme:
                this.homme.setChecked(true);
                this.femme.setChecked(false);

                break;
            case R.id.checkBoxSexefemme:
                this.homme.setChecked(false);
                this.femme.setChecked(true);

                break;
        }
    }

    public void onConnexion(View view) {
//        Log.d("close","je ferme l'intent inscription");
        finish();

    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        super.finish();
    }


    public void onValidation(View view) {
        //verif pseudo dispo dans bd
        //verif mdp corect
        //confirmation mdp
        //verif QS non vide
        //sexe choisie
        //creer user in db
        //log use

        databaseManager=getDataManager();
        EditText EditTextPseudo=findViewById(R.id.editTextPseudo);
        String pseudo = EditTextPseudo.getText().toString();
        if(databaseManager.pseudoPris(pseudo)){
            Toast toast = Toast.makeText(getApplicationContext(), "Le pseudo est déjà utilisé", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{

            EditText EditTextMdp=findViewById(R.id.editTextMotdepasse);
            String mdp = EditTextMdp.getText().toString();

            EditText EditTextMdpc=findViewById(R.id.editTextMotdepasseC);
            String mdpV = EditTextMdpc.getText().toString();
            if(!mdp.equals(mdpV)){
                Toast toast = Toast.makeText(getApplicationContext(), "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT);
                toast.show();
            }
            else {

                Spinner monSpinner = findViewById(R.id.spinnerQuestionSecret);
                String question = monSpinner.getSelectedItem().toString();
                EditText EditTextReponse = findViewById(R.id.editTextQuestionSecret);
                String reponse = EditTextReponse.getText().toString();
                databaseManager.insertUser(pseudo, mdp, question, reponse,0);
                databaseManager.close();

                Toast toast = Toast.makeText(getApplicationContext(), "Votre compte a bien été crée, merci de vous connecter", Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }
        }



    }


}
