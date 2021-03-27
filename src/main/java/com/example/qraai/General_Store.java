package com.example.qraai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class General_Store extends AppCompatActivity {
    TextInputEditText srno,ename,date,price,qi,qrc;
    Button qr,br;
    int year,month,dayofmonth;
    DatePickerDialog dp;
    DatabaseReference ref;
    Gdetails cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general__store);
        srno=findViewById(R.id.srno);
        ename=findViewById(R.id.ename);
        date=findViewById(R.id.date);
        price=findViewById(R.id.price);
        qi=findViewById(R.id.qi);
        qrc=findViewById(R.id.qrc);
        qr=findViewById(R.id.qr);
        br=findViewById(R.id.br);
        cd=new Gdetails();
        ref= FirebaseDatabase.getInstance().getReference().child("Gdetails");
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                dayofmonth = c.get(Calendar.DAY_OF_MONTH);
                dp = new DatePickerDialog(General_Store.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date.setText(day + "-" + (month + 1) + "-" + year);
                    }
                }, 0, 0, 0);
                dp.show();
            }
        });
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer sn=0,eqi=0,eprice=0,eqrc=0;
                String edate="",name="";
                try {
                    sn = Integer.parseInt(srno.getText().toString().trim());
                    eqi = Integer.parseInt(qi.getText().toString().trim());
                    eqrc = Integer.parseInt(qrc.getText().toString().trim());
                    eprice = Integer.parseInt(price.getText().toString().trim());
                }catch (Exception e)
                {

                }
                name=ename.getText().toString().trim();
                edate=date.getText().toString().trim();
                if(sn.equals(0))
                {
                    srno.setError("Please enter serial number");
                }
                else if(name.equals(""))
                {
                    ename.setError("Please enter name of equipment");
                }
                else if(edate.equals(""))
                {
                    date.setError("Please select date");
                }
                else if(eprice.equals(0))
                {
                    price.setError("Please enter purchase price");
                }
                else if(eqi.equals(0))
                {
                    qi.setError("Please enter Quantities Issued");
                }
                else if(eqrc.equals(""))
                {
                    qrc.setError("Please enter Quantities Received");
                }
                else
                {
                    cd.setSn(sn);
                    cd.setName(name);
                    cd.setDate(edate);
                    cd.setPrice(eprice);
                    cd.setQi(eqi);
                    cd.setQrc(eqrc);
                    ref.child("G"+String.valueOf(sn)).setValue(cd);
                    Toast.makeText(General_Store.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(General_Store.this,QRcode.class);
                    i.putExtra("key","G"+srno.getText().toString().trim());
                    startActivity(i);

                }
                clearText();
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer sn=0,eqi=0,eprice=0,eqrc=0;
                String edate="",name="";
                try {
                    sn = Integer.parseInt(srno.getText().toString().trim());
                    eqi = Integer.parseInt(qi.getText().toString().trim());
                    eqrc = Integer.parseInt(qrc.getText().toString().trim());
                    eprice = Integer.parseInt(price.getText().toString().trim());
                }catch (Exception e)
                {

                }
                name=ename.getText().toString().trim();
                edate=date.getText().toString().trim();
                if(sn.equals(0))
                {
                    srno.setError("Please enter serial number");
                }
                else if(name.equals(""))
                {
                    ename.setError("Please enter name of equipment");
                }
                else if(edate.equals(""))
                {
                    date.setError("Please select date");
                }
                else if(eprice.equals(0))
                {
                    price.setError("Please enter purchase price");
                }
                else if(eqi.equals(0))
                {
                    qi.setError("Please enter Quantities Issued");
                }
                else if(eqrc.equals(""))
                {
                    qrc.setError("Please enter Quantities Received");
                }
                else
                {
                    cd.setSn(sn);
                    cd.setName(name);
                    cd.setDate(edate);
                    cd.setPrice(eprice);
                    cd.setQi(eqi);
                    cd.setQrc(eqrc);
                    ref.child("G"+String.valueOf(sn)).setValue(cd);
                    Toast.makeText(General_Store.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(General_Store.this,Barcode.class);
                    i.putExtra("key","G"+srno.getText().toString().trim());
                    startActivity(i);

                }
                clearText();
            }
        });
    }
    public  void clearText() {
        srno.setText("");
        ename.setText("");
        date.setText("");
        price.setText("");
        qi.setText("");
        qrc.setText("");

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.logout:
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }
}
