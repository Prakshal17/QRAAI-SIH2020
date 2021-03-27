package com.example.qraai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.icon);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_home);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
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

    public void qrbrgenerate(View v){
        startActivity(new Intent(this,generateqrbr.class));
    }
    public void scanner(View v){
       startActivity(new Intent(this,scanner.class));
    }
    public  void pastrecords(View v){ startActivity(new Intent(this,allrecords.class));  }
}
