package com.example.modroid.motus;

public class Propositions {
    private String lettreUn,lettreDeux,lettreTrois,lettreQuatre,lettreCinq,lettreSix;

    public Propositions(){
        this.lettreUn="";
        this.lettreDeux="";
        this.lettreTrois="";
        this.lettreQuatre="";
        this.lettreCinq="";
        this.lettreSix="";
    }

    public Propositions(String lettreUn,String lettreDeux,String lettreTrois,String lettreQuatre,String lettreCinq,String lettreSix){
        this.lettreUn=lettreUn;
        this.lettreDeux=lettreDeux;
        this.lettreTrois=lettreTrois;
        this.lettreQuatre=lettreQuatre;
        this.lettreCinq=lettreCinq;
        this.lettreSix=lettreSix;
    }

    public String getLettreUn() {
        return lettreUn;
    }

    public void setLettreUn(String lettreUn) {
        this.lettreUn = lettreUn;
    }

    public String getLettreDeux() {
        return lettreDeux;
    }

    public void setLettreDeux(String lettreDeux) {
        this.lettreDeux = lettreDeux;
    }

    public String getLettreTrois() {return lettreTrois;}

    public void setLettreTrois(String lettreTrois) {this.lettreTrois = lettreTrois;}

    public String getLettreQuatre() {return lettreQuatre;}

    public void setLettreQuatre(String lettreQuatre) {this.lettreQuatre = lettreQuatre;}

    public String getLettreCinq() {
        return lettreCinq;
    }

    public void setLettreCinq(String lettreCinq) {
        this.lettreCinq = lettreCinq;
    }

    public String getLettreSix() {
        return lettreSix;
    }

    public void setLettreSix(String lettreSix) {
        this.lettreSix = lettreSix;
    }
}
