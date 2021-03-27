package com.example.qraai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class generateqrbr extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextInputEditText sr,name,model,date,qty,cost;
    TextView t;
    Button qr,br;
    DatabaseReference reff;
    Details details;
    DatePickerDialog datePickerDialog;
    int year,dayOfMonth,month;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generateqrbr);
        spinner=findViewById(R.id.category);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
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
        if(sSelected.equals("Consumable"))
        {
            startActivity(new Intent(this,Consumable.class));
        }
        else if(sSelected.equals("Non-Consumable"))
        {
            startActivity(new Intent(this,Non_Consumable.class));
        }
        else if(sSelected.equals("General_Store"))
        {
            startActivity(new Intent(this,General_Store.class));
        }
        else if(sSelected.equals("MT_Workshop_store"))
        {
            startActivity(new Intent(this,MT_Workshop_store.class));
        }
        else if(sSelected.equals("File_Station_Store"))
        {
            startActivity(new Intent(this,File_Station_Store.class));
        }
        else if(sSelected.equals("Furniture_and_Facilitation_Equipment"))
        {
            startActivity(new Intent(this,Furnitutre_Facilitation_Equipment.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
