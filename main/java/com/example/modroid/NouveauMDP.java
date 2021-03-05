package com.example.modroid;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NouveauMDP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouveau_mdp);

    }

    public void onAnnuler(View view) {
        finish();
    }

    @Override
    public void finish(){
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK,intent);
        super.finish();
    }

    public void onValidationmdp(View view) {
        // mdp = mdpconfirmation
        // verification de la validiter du mdp
        // changement du mdp en db
        Toast toast = Toast.makeText(getApplicationContext(), "Votre mot de passe a bien été modifié", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}
