package com.idrisso4.money;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBadapter extends SQLiteOpenHelper {
    public DBadapter (Context context){
        super(context,"mymoney",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String creatTable="create table money(id integer primary key,achat text,prix text,date text )";
        db.execSQL(creatTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deleteTable=("drop table if exists money ");
        db.execSQL(deleteTable);
        onCreate(db);
    }
    public void ajoutmoney(NewMoney m){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("achat",m.getNom());
        contentValues.put("prix",m.getprix());
        contentValues.put("date",m.getdate());
        db.insert("money",null,contentValues);
    }
    public ArrayList<NewMoney> afficher(){
        SQLiteDatabase db=getReadableDatabase();
        String selectall="SELECT * FROM money";
        Cursor cursor= db.rawQuery(selectall,null);
        ArrayList<NewMoney> money=new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                NewMoney m=new NewMoney(1,cursor.getString(1),cursor.getString(2),cursor.getString(3));
                m.setid(cursor.getInt(0));
                money.add(m);
            }
            while(cursor.moveToNext());
        }
        return money;
    }
    public void delete (int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("money","id="+Integer.toString(id),null);
    }

    public String total(){
        String pr;
        Float l=0f;
        SQLiteDatabase db=getReadableDatabase();
        String select="SELECT * FROM money";
        Cursor cursor= db.rawQuery(select,null);
        if (cursor.moveToFirst()){
            do{
               String p=cursor.getString(2);
               l+=Float.parseFloat(p.substring(0,p.length()-3));
            }
            while(cursor.moveToNext());
        }
        pr=String.valueOf(l)+" DT";
        return pr;
    }
}
