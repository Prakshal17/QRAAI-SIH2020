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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRcode extends AppCompatActivity {
ImageView img;
Button b,share;
String val;
TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        img=findViewById(R.id.img);
        b=findViewById(R.id.qr);
        share=findViewById(R.id.print);
        data=findViewById(R.id.data);
        //get value from previous intent
        Intent i=getIntent();
        val=i.getStringExtra("key");
        data.setText(val);
        data.setEnabled(false);
        data.setVisibility(View.INVISIBLE);

        //Toast.makeText(this, ""+val, Toast.LENGTH_SHORT).show();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                try {
                        Toast.makeText(QRcode.this, ""+data.getText(), Toast.LENGTH_SHORT).show();
                        BitMatrix bitMatrix = qrCodeWriter.encode(data.getText().toString().trim(), BarcodeFormat.QR_CODE, 200, 200);

                        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);
                        for (int x = 0; x < 200; x++) {
                            for (int y = 0; y < 200; y++) {
                                bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                            }
                        }
                        img.setImageBitmap(bitmap);

                    } catch(Exception e){
                        e.printStackTrace();
                    }

                }

        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
