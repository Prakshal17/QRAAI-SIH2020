package com.example.qraai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class Barcode extends AppCompatActivity {
    ImageView img;
    Button b;
    String val;
    TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        img=findViewById(R.id.img);
        b=findViewById(R.id.qr);
        data=findViewById(R.id.data);
        //get values
        Intent i=getIntent();
        val=i.getStringExtra("key");
        data.setText(val);
        data.setEnabled(false);
        data.setVisibility(View.INVISIBLE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String productId = data.getText().toString();
                    Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
                    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    Writer codeWriter;
                    codeWriter = new Code128Writer();
                    BitMatrix byteMatrix = codeWriter.encode(productId, BarcodeFormat.CODE_128,400, 200, hintMap);
                    int width = byteMatrix.getWidth();
                    int height = byteMatrix.getHeight();
                    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            bitmap.setPixel(i, j, byteMatrix.get(i, j) ? Color.BLACK : Color.WHITE);
                        }
                    }
                    img.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
//        Toast.makeText(this, ""+val, Toast.LENGTH_SHORT).show();
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
