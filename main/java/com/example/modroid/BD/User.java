package com.example.modroid.BD;

public class User {

    private String pseudo,mdp,question,reponse;
    private Integer points;


    public User(String pseudo, String mdp, String question, String reponse,Integer points) {
        this.setPseudo(pseudo);
        this.setMdp(mdp);
        this.setQuestion(question);
        this.setReponse(reponse);
        this.setPoints(points);
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Integer getPoints() { return points;}

    public void setPoints(int points) {this.points = points;}

    @Override
    public String toString() {
        return "Joueur: "+ this.getPseudo()+":  -> "+this.getPoints()+" mots Trouvés ";
    }
}
