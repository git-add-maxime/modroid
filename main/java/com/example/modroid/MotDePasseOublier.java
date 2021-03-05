package com.example.modroid;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MotDePasseOublier extends AppCompatActivity {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mot_de_passe_oublier);


        //partie intent de récupération de pseudo
        Intent intent = this.getIntent();

        String pseudo = intent.getStringExtra("editTextPseudo");

        TextView tPseudo = findViewById(R.id.textViewPseudo);

        tPseudo.setText("Votre pseudo est : "+pseudo);

        //Partie spinner
        this.spinner = (Spinner) findViewById(R.id.spinnerQuestionSecreteMDP);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerQuestionSecret, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    public void onChangemdp(View view) {
        Intent intent = new Intent(this, NouveauMDP.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        super.finish();
    }

}
