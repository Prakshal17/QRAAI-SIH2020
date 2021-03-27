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

public class Consumable extends AppCompatActivity {
TextInputEditText srno,ename,date,price,qtyp,by;
Button qr,br;
int year,month,dayofmonth;
DatePickerDialog dp;
DatabaseReference ref;
Cdetails cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumable);
        srno=findViewById(R.id.srno);
        ename=findViewById(R.id.ename);
        date=findViewById(R.id.date);
        price=findViewById(R.id.price);
        qtyp=findViewById(R.id.qtyp);
        by=findViewById(R.id.by);
        qr=findViewById(R.id.qr);
        br=findViewById(R.id.br);
        cd=new Cdetails();
        ref= FirebaseDatabase.getInstance().getReference().child("Cdetails");
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                dayofmonth = c.get(Calendar.DAY_OF_MONTH);
                dp = new DatePickerDialog(Consumable.this, new DatePickerDialog.OnDateSetListener() {
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
                Integer sn=0,eqtyp=0,eprice=0;
                String edate="",eby="",name="";
                try {
                    sn = Integer.parseInt(srno.getText().toString().trim());
                    eqtyp = Integer.parseInt(qtyp.getText().toString().trim());
                    eprice = Integer.parseInt(price.getText().toString().trim());
                }catch (Exception e)
                {

                }
                name=ename.getText().toString().trim();
                edate=date.getText().toString().trim();
                eby=by.getText().toString().trim();
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
                else if(eqtyp.equals(0))
                {
                    qtyp.setError("Please enter Quantity");
                }
                else if(eby.equals(""))
                {
                    by.setError("Please enter Packed by");
                }
                else
                {
                    cd.setSn(sn);
                    cd.setName(name);
                    cd.setEdate(edate);
                    cd.setEprice(eprice);
                    cd.setEqtyp(eqtyp);
                    cd.setEby(eby);
                    ref.child("C"+String.valueOf(sn)).setValue(cd);
                    Toast.makeText(Consumable.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Consumable.this,QRcode.class);
                    i.putExtra("key","C"+srno.getText().toString().trim());
                    startActivity(i);

                }
                clearText();
            }
        });
        br.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer sn=0,eqtyp=0,eprice=0;
                String edate="",eby="",name="";
                try {
                    sn = Integer.parseInt(srno.getText().toString().trim());
                    eqtyp = Integer.parseInt(qtyp.getText().toString().trim());
                    eprice = Integer.parseInt(price.getText().toString().trim());
                }catch (Exception e)
                {

                }
                name=ename.getText().toString().trim();
                edate=date.getText().toString().trim();
                eby=by.getText().toString().trim();
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
                else if(eqtyp.equals(0))
                {
                    qtyp.setError("Please enter Quantity");
                }
                else if(eby.equals(""))
                {
                    by.setError("Please enter Packed by");
                }
                else
                {
                    cd.setSn(sn);
                    cd.setName(name);
                    cd.setEdate(edate);
                    cd.setEprice(eprice);
                    cd.setEqtyp(eqtyp);
                    cd.setEby(eby);
                    ref.child("C"+String.valueOf(sn)).setValue(cd);
                    Toast.makeText(Consumable.this, "data inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Consumable.this,Barcode.class);
                    i.putExtra("key","C"+srno.getText().toString().trim());
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
    qtyp.setText("");
    by.setText("");

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
