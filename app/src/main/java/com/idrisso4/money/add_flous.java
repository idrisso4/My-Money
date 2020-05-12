package com.idrisso4.money;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_flous extends AppCompatActivity {
    EditText achat;
    EditText prix;
    Button bout1;
    DateFormat df = new SimpleDateFormat("d/MM/y");
    String date = df.format(Calendar.getInstance().getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flous);
        bout1=findViewById(R.id.bout);
        achat=findViewById(R.id.achat1);
        prix=findViewById(R.id.prix1);
        bout1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(add_flous.this, "enregistré",Toast.LENGTH_SHORT).show();
                Intent intent2= new Intent(add_flous.this,MainActivity.class);
                String ach=achat.getText().toString();
                String pr=prix.getText().toString();
                NewMoney money=new  NewMoney(1,achat.getText().toString(),prix.getText().toString()+" DT",date);
                DBadapter dpadapter=new DBadapter(add_flous.this);
                dpadapter.ajoutmoney(money);
                startActivity(intent2);
            }
        });
    }
}
