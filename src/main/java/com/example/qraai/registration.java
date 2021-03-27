package com.example.qraai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registration extends AppCompatActivity {
    EditText name,username,password,cpassword;
    Button signup;
    String uname,user,upass,ucpass;
    String URL_REGI="http://192.168.43.72/codegenerators/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        name=findViewById(R.id.name);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);
        signup=findViewById(R.id.signup);
    }
    public void register(View v){
        uname=name.getText().toString().trim();
        user=username.getText().toString().trim();
        upass=password.getText().toString().trim();
        ucpass=cpassword.getText().toString().trim();
        if(uname.equals("")){
            name.setError("Provide Full Name");
        }else if(user.equals("")){
            username.setError("Provide Username");
        }else if(upass.equals("")){
            password.setError("Provide Password");
        }else if(ucpass.equals("")){
            cpassword.setError("Provide Confirm Password");
        }else if(!(ucpass.equals(upass))){
            cpassword.setError("Password And Confirm Password Must Be same");
        }else{
            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGI, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.e("res",response);
                    try{
                        JSONObject jsonObject=new JSONObject(response);
                        String success =jsonObject.getString("success");
                        if(success.equals("1")){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(registration.this);
                            builder.setCancelable(true);
                            builder.setTitle("Registration");
                            builder.setMessage("Registration done Successfully..!");
                            builder.setIcon(R.drawable.updateicon);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(registration.this, MainActivity.class));
                                }
                            });
                            builder.show();
                            clearText();
                        }
                        else if (success.equals("0")){
                            final AlertDialog.Builder builder = new AlertDialog.Builder(registration.this);
                            builder.setCancelable(true);
                            builder.setTitle("Registration");
                            builder.setMessage("Username Already Exists..!");
                            builder.setIcon(R.drawable.erroricon);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(registration.this, registration.class));
                                }
                            });
                            builder.show();
                            clearText();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(registration.this, "Registration Error..!"+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(registration.this, "Registration Error..!"+error.toString(), Toast.LENGTH_SHORT).show();
                  }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    params.put("name",uname);
                    params.put("username",user);
                    params.put("password",upass);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
    public void signin(View v){
        startActivity(new Intent(this,MainActivity.class));
    }
    public void clearText(){
        name.setText("");
        username.setText("");
        password.setText("");
        cpassword.setText("");
    }
}
