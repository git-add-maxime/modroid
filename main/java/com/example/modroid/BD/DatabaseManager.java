package com.example.modroid.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Game.db";
    private static final int DATABASE_VERSION=10;

    public DatabaseManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql="create table T_User ("
                +"   pseudo text primary key,"
                +"   mdp text not null,"
                +"   question text not null,"
                +"   reponse text not null,"
                +"   points integer"
                +")";
        db.execSQL(strSql);
        Log.d("DATABASE","onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String strSql = "alter table T_User add column ...";
        String strSql="drop table if exists T_User";
        db.execSQL(strSql);
        this.onCreate(db);
        Log.d("DATABASE", "onUpgrade invoked ");

    }

    public void insertUser(String pseudo,String mdp, String question,String reponse,Integer points){
        pseudo=pseudo.replace("'","''");
        mdp=mdp.replace("'","''");
        question=question.replace("'","''");
        reponse.replace("'","''");
        try {
            String strSql="insert into T_User (pseudo,mdp,question,reponse,points) values ('"
                    + pseudo+"','"+mdp + "', '" + question + "', '" + reponse+"','"+points+"')";
            this.getWritableDatabase().execSQL(strSql);
        }catch (Exception e){
            System.out.println("Pseudo deja utilisé");
        }
        Log.d("DATABASE", "insertScore invoked ");
    }

    public List<User> readTop10(){
        List<User> Users=new ArrayList<>();

        // Utiliser à l'aide d'un ordre sql
        String strSql= "select * from T_User order by points desc limit 10";
        Cursor cursor = this.getWritableDatabase().rawQuery(strSql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            Users.add(user);
            cursor.moveToNext();
        }
        cursor.close();

        return Users;
    }

    public boolean pseudoPris(String pseudo){
        List<User> Users=new ArrayList<>();
        String strSql= "select * from T_User where pseudo='"+pseudo+"'";
        Cursor cursor = this.getWritableDatabase().rawQuery(strSql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            Users.add(user);
            cursor.moveToNext();
        }
        cursor.close();

        return Users.size()>0;
    }

    public User getUser(String pseudo,String mdp){
        List<User> Users=new ArrayList<>();
        String strSql= "select * from T_User where pseudo='"+pseudo+"' and mdp='"+mdp+"'";
        Cursor cursor = this.getWritableDatabase().rawQuery(strSql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            User user = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
            Users.add(user);
            cursor.moveToNext();
        }
        cursor.close();

        return Users.get(0);
    }


    public void upDatePoints(User user,Integer points) {
        try{
            String sqlSuppression = "delete from T_User where pseudo='"+user.getPseudo()+"'";
            this.getWritableDatabase().execSQL(sqlSuppression);
            this.insertUser(user.getPseudo(),user.getMdp(),user.getQuestion(),user.getReponse(),user.getPoints());

        }catch(Exception e){
            Log.d("testUpdate", "upDatePoints: erreur lors de la modification des points");
        }


    }

}
