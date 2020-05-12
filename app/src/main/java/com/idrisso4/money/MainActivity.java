package com.idrisso4.money;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    ListView list;
    public ArrayList <NewMoney> arrayList;
    public DBadapter dbAabter;
    public  MoneyAdapter moneyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.listeview);
        arrayList=new ArrayList<>();
        dbAabter=new DBadapter(this);
        arrayList=dbAabter.afficher();
        moneyAdapter=new MoneyAdapter(this,R.layout.flous,arrayList);
        list.setAdapter(moneyAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("supprimer achat")
                        .setMessage("Êtes vous sûr de suprimer "+arrayList.get(position).getNom()+" ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
            dbAabter.delete(arrayList.get(position).getid());
            arrayList=dbAabter.afficher();
            moneyAdapter=new MoneyAdapter(MainActivity.this,R.layout.flous,arrayList);
            list.setAdapter(moneyAdapter);
            Toast.makeText(MainActivity.this, "supprimé", Toast.LENGTH_SHORT).show();}})
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        arrayList=dbAabter.afficher();
        moneyAdapter=new MoneyAdapter(this,R.layout.flous,arrayList);
        list.setAdapter(moneyAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.add_flous,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId()==R.id.addm)
        {
            Intent intent = new Intent(MainActivity.this, add_flous.class); startActivity(intent);
        }

        if( item.getItemId()==R.id.total)
        {
            Intent intent = new Intent(MainActivity.this, total.class); startActivity(intent);
        }
        return true ;
    }

    public static long FirstInstallTime(Context context){
        PackageInfo packageInfo;
        try {
            if(Build.VERSION.SDK_INT>8 ){
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                return packageInfo.firstInstallTime;
            }else{
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                String sAppFile = appInfo.sourceDir;
                return new File(sAppFile).lastModified();
            }
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }

    public static String getdate(long milliSeconds, String dateFormat) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

}