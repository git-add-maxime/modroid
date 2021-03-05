package com.example.modroid.motus;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.modroid.R;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<ViewHolder>  {

    private List<Propositions> propositionsList;
    private int compteurLettresBonnes=0;
    private MainGame mainGame;

    public RecycleViewAdapter(List<Propositions> propositionsList, MainGame mainGame){
        this.propositionsList=propositionsList;
        this.mainGame = mainGame;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        ViewHolder myViewHolder=new ViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        compteurLettresBonnes=0;
        Log.d("testFonction", "onBindViewHolder: passage dans la fonction");
        Propositions propositions =propositionsList.get(position);

        holder.lettreUn.setText(propositions.getLettreUn());

        holder.lettreUn.setBackgroundColor(verifierLettre(holder.lettreUn.getText().toString(),1));

        holder.lettreDeux.setText(propositions.getLettreDeux());

        holder.lettreDeux.setBackgroundColor(verifierLettre(holder.lettreDeux.getText().toString(),2));

        holder.lettreTrois.setText(propositions.getLettreTrois());

        holder.lettreTrois.setBackgroundColor(verifierLettre(holder.lettreTrois.getText().toString(),3));

        holder.lettreQuatre.setText(propositions.getLettreQuatre());

        holder.lettreQuatre.setBackgroundColor(verifierLettre(holder.lettreQuatre.getText().toString(),4));

        holder.lettreCinq.setText(propositions.getLettreCinq());

        holder.lettreCinq.setBackgroundColor(verifierLettre(holder.lettreCinq.getText().toString(),5));

        holder.lettreSix.setText(propositions.getLettreSix());

        holder.lettreSix.setBackgroundColor(verifierLettre(holder.lettreSix.getText().toString(),6));

        if(compteurLettresBonnes==6){
            //TODO Metre message de victoire et passer à une autre view
            Log.d("testVictoire", "VICTOIRE CAR TOUTES LES LETTRES DE TROUVEES");
            this.mainGame.alertFinJeuVictoire();
        }
    }

    @Override
    public int getItemCount(){
        return propositionsList.size();
    }

    public int verifierLettre(String lettre,int position){
        String motATrouver=MainGame.getMotMystere();
        Log.d("testFonction", "verifierLettre ");
        System.out.println(lettre.charAt(0)==motATrouver.charAt(position-1));

        if(lettre.charAt(0)==motATrouver.charAt(position-1)){
            compteurLettresBonnes+=1;
            return Color.GREEN;
        }
        else{
            compteurLettresBonnes=0;
            Log.d("testCouleur", "verifierLettre: "+motATrouver);
            Log.d("testCouleur", "lettre : "+lettre);
            if (motATrouver.indexOf(lettre)>0){
                return Color.YELLOW;
            }
            else{
                return Color.WHITE;
            }
        }
    }
}
