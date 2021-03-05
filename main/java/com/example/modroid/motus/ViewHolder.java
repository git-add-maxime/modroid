package com.example.modroid.motus;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.modroid.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView lettreUn,lettreDeux,lettreTrois,lettreQuatre,lettreCinq,lettreSix;

    public ViewHolder(View view){
        super(view);
        lettreUn=view.findViewById(R.id.textView1);
        lettreDeux=view.findViewById(R.id.textView2);
        lettreTrois=view.findViewById(R.id.textView3);
        lettreQuatre=view.findViewById(R.id.textView4);
        lettreCinq=view.findViewById(R.id.textView5);
        lettreSix=view.findViewById(R.id.textView6);
    }
}
