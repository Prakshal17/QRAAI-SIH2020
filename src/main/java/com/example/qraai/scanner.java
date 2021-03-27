package com.example.qraai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog.Builder;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    DatabaseReference reff;
    Details det;
    String sr="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_scanner);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.logout:
            startActivity(new Intent(this,MainActivity.class));
            return(true);
    }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    public void handleResult(final Result result) {
        //sr = Integer.parseInt(String.valueOf(result));
        //Toast.makeText(this, "scanner page:" + result, Toast.LENGTH_SHORT).show();
        if (String.valueOf(result).charAt(0) == 'C') {
            sr=String.valueOf(result);
            det = new Details();
            reff = FirebaseDatabase.getInstance().getReference().child("Cdetails").child(String.valueOf(result));
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String esr, ename,eby, eqty, eprice, edate;
                    esr = dataSnapshot.child("sn").getValue().toString();
                    ename = dataSnapshot.child("name").getValue().toString();
                    eby = dataSnapshot.child("eby").getValue().toString();
                    eqty = dataSnapshot.child("eqtyp").getValue().toString();
                    eprice = dataSnapshot.child("eprice").getValue().toString();
                    edate = dataSnapshot.child("edate").getValue().toString();

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("\nS/N : " + esr + "\n\n");
                    buffer.append("Name of Equipment: " + ename + "\n\n");
                    buffer.append("Date of Purchase : " + edate + "\n\n");
                    buffer.append("Purchase Price : " + eprice + "\n\n");
                    buffer.append("Quantity Packed: " + eqty + "\n\n");
                    buffer.append("Packed By : " + eby + "\n\n");
                    showScanDetail("Scanned Equipment Details", buffer.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(String.valueOf(result).charAt(0) == 'N')
        {
            sr=String.valueOf(result);
            det = new Details();
            reff = FirebaseDatabase.getInstance().getReference().child("Ndetails").child(String.valueOf(result));
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String esr,eby, eqty, eprice, edate,eipack,emaxs,emins;
                    esr = dataSnapshot.child("sn").getValue().toString();
                    eprice = dataSnapshot.child("price").getValue().toString();
                    eby = dataSnapshot.child("eby").getValue().toString();
                    emaxs = dataSnapshot.child("maxs").getValue().toString();
                    emins = dataSnapshot.child("mins").getValue().toString();
                    edate = dataSnapshot.child("edate").getValue().toString();
                    eipack = dataSnapshot.child("ipack").getValue().toString();


                    StringBuffer buffer = new StringBuffer();
                    buffer.append("\nS/N : " + esr + "\n\n");
                    buffer.append("Maximum Stock " + emaxs + "\n\n");
                    buffer.append("Minimum Stock : " + emins + "\n\n");
                    buffer.append("Purchase Price : " + eprice + "\n\n");
                    buffer.append("Items Packed: " + eipack + "\n\n");
                    buffer.append("Packed By : " + eby + "\n\n");
                    buffer.append("Date of Purchase : " + edate + "\n\n");

                    showScanDetail("Scanned Equipment Details", buffer.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if (String.valueOf(result).charAt(0) == 'G') {
            sr=String.valueOf(result);
            det = new Details();
            reff = FirebaseDatabase.getInstance().getReference().child("Gdetails").child(String.valueOf(result));
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String esr, ename,eqi, eqrc, eprice, edate;
                    esr = dataSnapshot.child("sn").getValue().toString();
                    ename = dataSnapshot.child("name").getValue().toString();
                    eqi = dataSnapshot.child("qi").getValue().toString();
                    eqrc = dataSnapshot.child("qrc").getValue().toString();
                    eprice = dataSnapshot.child("price").getValue().toString();
                    edate = dataSnapshot.child("date").getValue().toString();

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("\nS/N : " + esr + "\n\n");
                    buffer.append("Name of Equipment: " + ename + "\n\n");
                    buffer.append("Date of Purchase : " + edate + "\n\n");
                    buffer.append("Purchase Price : " + eprice + "\n\n");
                    buffer.append("Quantity Issued: " + eqi + "\n\n");
                    buffer.append("Quantity Received : " + eqrc + "\n\n");
                    showScanDetail("Scanned Equipment Details", buffer.toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else{
            final Builder builder=new Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Error");
            builder.setIcon(R.drawable.erroricon);
            builder.setMessage("QR Code/Barcode Not Exist....!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
    }
     void showScanDetail(String title,String message){

         final Builder builder=new Builder(this);
         builder.setCancelable(true);
         builder.setTitle(title);
         builder.setMessage(message);
         builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mScannerView.resumeCameraPreview(scanner.this);
            }
        });
         builder.setNeutralButton("Past Service Records", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int which) {
                 Intent i = new Intent(scanner.this,Pastrecords.class);
                 i.putExtra("key",sr);
                 startActivity(i);
             }
         });
         builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent i = new Intent(scanner.this,Update.class);
                i.putExtra("key",sr);
                startActivity(i);
            }
        });
         builder.show();
     }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
}
