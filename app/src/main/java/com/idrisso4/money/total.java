package com.idrisso4.money;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.TextView;
import static com.idrisso4.money.MainActivity.FirstInstallTime;
import static com.idrisso4.money.MainActivity.getdate;

public class total extends AppCompatActivity {
    TextView tot;
    TextView time;
    String total;
    Long date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);
        DBadapter dbabter=new DBadapter(total.this);
        total=dbabter.total();
        tot=findViewById(R.id.tot);
        time=findViewById(R.id.time);
        tot.setText(total);
        date= FirstInstallTime(this);
        String d = getdate(date, "dd/MM/yyyy");
        time.setText("depuis le: "+d);
    }

    @Override
    public void onResume() {
        super.onResume();
        tot=findViewById(R.id.tot);
        tot.setText(total);
    }
}
