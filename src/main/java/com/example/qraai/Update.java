package com.example.qraai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Update extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String value;
    DatabaseReference reff;
    Cdetails cdetails;
    Ndetails ndetails;
    Gdetails gdetails;
    TextInputLayout desp;
    TextView name,price,qtyp,by,date,serial,n,p,q,b,d,a,all;
    String data;
    EditText description;
    Spinner sp;
    Button update;


    private static String URL_Store="http://192.168.43.72/codegenerators/pastrecords.php";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        serial=findViewById(R.id.serial);
        description=findViewById(R.id.description);
        name=findViewById(R.id.name);
        price=findViewById(R.id.price);
        qtyp=findViewById(R.id.qtyp);
        by=findViewById(R.id.by);
        date=findViewById(R.id.date);

        n=findViewById(R.id.n);
        p=findViewById(R.id.p);
        q=findViewById(R.id.q);
        b=findViewById(R.id.b);
        d=findViewById(R.id.d);
        a=findViewById(R.id.a);
        all=findViewById(R.id.all);

        sp=findViewById(R.id.updatespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.up, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);

        update=findViewById(R.id.update);
        desp=findViewById(R.id.desp);
        desp.setVisibility(View.INVISIBLE);
        update.setVisibility(View.INVISIBLE);

        //value from intent
        Intent ii=getIntent();
        value=ii.getStringExtra("key");
        //Toast.makeText(this, "value = "+value, Toast.LENGTH_SHORT).show();

        if(value.charAt(0)=='C') {
            cdetails = new Cdetails();
            reff = FirebaseDatabase.getInstance().getReference().child("Cdetails").child(value);
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String ename, eby, eqty, eprice, edate;
                    //esr=dataSnapshot.child("srno").getValue().toString();
                    ename = dataSnapshot.child("name").getValue().toString();
                    eby = dataSnapshot.child("eby").getValue().toString();
                    eqty = dataSnapshot.child("eqtyp").getValue().toString();
                    eprice = dataSnapshot.child("eprice").getValue().toString();
                    edate = dataSnapshot.child("edate").getValue().toString();

//                    serial.setText(val);
                    name.setText(ename);
                    price.setText(eprice);
                    qtyp.setText(eqty);
                    by.setText(eby);
                    date.setText(edate);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else if(value.charAt(0)=='N') {
                ndetails = new Ndetails();
                reff = FirebaseDatabase.getInstance().getReference().child("Ndetails").child(value);
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String emaxs,emins, eby, eqty, eprice, edate,eipack;
                        //esr=dataSnapshot.child("srno").getValue().toString();
                        emaxs = dataSnapshot.child("maxs").getValue().toString();
                        emins = dataSnapshot.child("mins").getValue().toString();
                        eby = dataSnapshot.child("eby").getValue().toString();
                        eipack = dataSnapshot.child("ipack").getValue().toString();
                        eprice = dataSnapshot.child("price").getValue().toString();
                        edate = dataSnapshot.child("edate").getValue().toString();

                        n.setText("Maximum Stock :");
                        p.setText("Minimum Stock :");
                        q.setText("Items Packed :");
                        b.setText("Packed by :");
                        d.setText("Date of Installation :");
                        a.setText("Cost : ");

                        name.setText(emaxs);
                        price.setText(emins);
                        qtyp.setText(eipack);
                        by.setText(eby);
                        date.setText(edate);
                        all.setText(eprice);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        }else if(value.charAt(0)=='G') {
            gdetails = new Gdetails();
            reff = FirebaseDatabase.getInstance().getReference().child("Gdetails").child(value);
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String ename,eqi, eqrc, eprice, edate;
                    //esr=dataSnapshot.child("srno").getValue().toString();
                    ename = dataSnapshot.child("name").getValue().toString();
                    eqi = dataSnapshot.child("qi").getValue().toString();
                    eqrc = dataSnapshot.child("qrc").getValue().toString();
                    eprice = dataSnapshot.child("price").getValue().toString();
                    edate = dataSnapshot.child("date").getValue().toString();


                    //n.setHint("Maximum Stock ");
                   n.setText("Equipment Name :");
                   p.setText("Cost Of Equipment :");
                   q.setText("Quantity Issued :");
                   b.setText("Quantity Received :");
                   d.setText("Date of Installation :");

                    name.setText(ename);
                    price.setText(eprice);
                    qtyp.setText(eqi);
                    by.setText(eqrc);
                    date.setText(edate);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String sSelected=parent.getItemAtPosition(position).toString();
        if(sSelected.equals("Repair_Equipment"))
        {
            desp.setVisibility(View.VISIBLE);
            update.setVisibility(View.VISIBLE);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    submit();
                }
            });
        }
        else if(sSelected.equals("Replace_Equipment"))
        {
            startActivity(new Intent(this,generateqrbr.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void submit(){
        data=description.getText().toString().trim();
        //Toast.makeText(this, "data ="+data, Toast.LENGTH_SHORT).show();
        if(data.equals("")){
            description.setError("Provide Description");
        }else{
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_Store, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("res",response);
                    try{
                        JSONObject jsonObject=new JSONObject(response);
                        String success =jsonObject.getString("success");
                        if(success.equals("1")){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(Update.this);
                            builder.setCancelable(true);
                            builder.setTitle("Update");
                            builder.setMessage("Records Updated Successfully..!");
                            builder.setIcon(R.drawable.updateicon);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(Update.this, scanner.class));
                                }
                            });
                            builder.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Update.this, "Update Error"+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError e) {
                    Toast.makeText(Update.this, "Update Error"+e.toString(), Toast.LENGTH_SHORT).show();
                    System.out.println(e.toString());
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("serial",value);
                    params.put("description",data);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(Update.this);
            requestQueue.add(stringRequest);
            description.setText("");
        }
    }
}
