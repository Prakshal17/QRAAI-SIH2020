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

public class Non_Consumable extends AppCompatActivity {
    TextInputEditText srno,date,price,by,mins,maxs,ipack;
    Button qr,br;
    int year,month,dayofmonth;
    DatePickerDialog dp;
    DatabaseReference ref;
    Ndetails cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non__consumable);

        srno=findViewById(R.id.srno);
        mins=findViewById(R.id.mins);
        maxs=findViewById(R.id.maxs);
        date=findViewById(R.id.date);
        price=findViewById(R.id.price);
        ipack=findViewById(R.id.ipack);
        by=findViewById(R.id.by);

        qr=findViewById(R.id.qr);
        br=findViewById(R.id.br);
        cd=new Ndetails();
        ref= FirebaseDatabase.getInstance().getReference().child("Ndetails");
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                dayofmonth = c.get(Calendar.DAY_OF_MONTH);
                dp = new DatePickerDialog(Non_Consumable.this, new DatePickerDialog.OnDateSetListener() {
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
                Integer sn=0,emaxs=0,emins=0,eipack=0,eprice=0;
                String edate="",eby="";
                try {
                    sn = Integer.parseInt(srno.getText().toString().trim());
                    emaxs = Integer.parseInt(maxs.getText().toString().trim());
                    emins = Integer.parseInt(mins.getText().toString().trim());
                    eipack = Integer.parseInt(ipack.getText().toString().trim());
                    eprice = Integer.parseInt(price.getText().toString().trim());
                }catch (Exception e)
                {

                }
                edate=date.getText().toString().trim();
                eby=by.getText().toString().trim();
                if(sn.equals(0))
                {
                    srno.setError("Please enter serial number");
                }
                else if(emaxs.equals(0))
                {
                    maxs.setError("Please enter Maximum stock of equipments");
                }
                else if(emins.equals(0))
                {
                    mins.setError("Please enter Minimum stock of equipments");
                }
                else if(eipack.equals(0))
                {
                    ipack.setError("Please enter packed items ");
                }
                else if(eby.equals(""))
                {
                    by.setError("Please enter Packed by");
                }
                else if(eprice.equals(0))
                {
                    price.setError("Please enter price");
                }
                else if(edate.equals(""))
                {
                    date.setError("Please enter date");
                }
                else
                {
                    cd.setSn(sn);
                    cd.setMaxs(emaxs);
                    cd.setMins(emins);
                    cd.setIpack(eipack);
                    cd.setEby(eby);
                    cd.setPrice(eprice);
                    cd.setEdate(edate);
                    ref.child("N"+String.valueOf(sn)).setValue(cd);
                    Toast.makeText(Non_Consumable.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Non_Consumable.this,QRcode.class);
                    i.putExtra("key","N"+srno.getText().toString().trim());
                    startActivity(i);

                }
                clearText();
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer sn=0,emaxs=0,emins=0,eipack=0,eprice=0;
                String edate="",eby="";
                try {
                    sn = Integer.parseInt(srno.getText().toString().trim());
                    emaxs = Integer.parseInt(maxs.getText().toString().trim());
                    emins = Integer.parseInt(mins.getText().toString().trim());
                    eipack = Integer.parseInt(ipack.getText().toString().trim());
                    eprice = Integer.parseInt(price.getText().toString().trim());
                }catch (Exception e)
                {

                }
                edate=date.getText().toString().trim();
                eby=by.getText().toString().trim();
                if(sn.equals(0))
                {
                    srno.setError("Please enter serial number");
                }
                else if(emaxs.equals(0))
                {
                    maxs.setError("Please enter Maximum stock of equipments");
                }
                else if(emins.equals(0))
                {
                    mins.setError("Please enter Minimum stock of equipments");
                }
                else if(eipack.equals(0))
                {
                    ipack.setError("Please enter packed items ");
                }
                else if(eby.equals(""))
                {
                    by.setError("Please enter Packed by");
                }
                else if(eprice.equals(0))
                {
                    price.setError("Please enter price");
                }
                else if(edate.equals(""))
                {
                    date.setError("Please enter date");
                }
                else
                {
                    cd.setSn(sn);
                    cd.setMaxs(emaxs);
                    cd.setMins(emins);
                    cd.setIpack(eipack);
                    cd.setEby(eby);
                    cd.setPrice(eprice);
                    cd.setEdate(edate);
                    ref.child("N"+String.valueOf(sn)).setValue(cd);
                    Toast.makeText(Non_Consumable.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Non_Consumable.this,Barcode.class);
                    i.putExtra("key","N"+srno.getText().toString().trim());
                    startActivity(i);

                }
                clearText();
            }
        });

    }
    public  void clearText() {
        srno.setText("");
        maxs.setText("");
        mins.setText("");
        ipack.setText("");
        by.setText("");
        price.setText("");
        date.setText("");

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
