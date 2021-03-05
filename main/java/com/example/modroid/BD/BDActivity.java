package com.example.modroid.BD;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modroid.R;

import java.util.List;

import static com.example.modroid.Connexion.getDataManager;

public class BDActivity extends AppCompatActivity {

    private TextView scoresView;
    private  DatabaseManager databaseManager;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd);

        scoresView=(TextView)findViewById(R.id.scoresView);
        databaseManager=getDataManager();


        List<User> users =databaseManager.readTop10();
        for (User user: users){
            scoresView.append(user.toString()+"\n\n");
        }
        databaseManager.close();
    }
}
