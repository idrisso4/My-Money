package com.idrisso4.money;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.security.PublicKey;
import java.util.ArrayList;


public class MoneyAdapter extends ArrayAdapter <NewMoney> {
    private ArrayList <NewMoney> arrayliste;
    private Context ctx;
    private int item;
    public MoneyAdapter(Context context, int ressource, ArrayList <NewMoney> mylist) {
        super(context, ressource, mylist);
        this.arrayliste=mylist;
        this.ctx=context;
        this.item=ressource;

    }

    @NonNull
    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(ctx).inflate(item,parent,false);
        TextView achat=convertView.findViewById(R.id.achat);
        TextView prix=convertView.findViewById(R.id.prix);
        TextView date=convertView.findViewById(R.id.date);
        achat.setText(arrayliste.get(position).getNom());
        prix.setText(arrayliste.get(position).getprix());
        date.setText(arrayliste.get(position).getdate());
        return convertView;
    }
}
