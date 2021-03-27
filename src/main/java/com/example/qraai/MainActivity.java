package com.example.qraai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  {
    TextInputEditText username,password;
    Button signin;
    TextView signup;
    String URL_REGI="http://192.168.43.72/codegenerators/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        getSupportActionBar().hide();
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signin=findViewById(R.id.btn_login);
        signup=findViewById(R.id.signup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Home.class));
            }
        });

    }
    public void signin(View v) {
        final String name, pas;
        name = username.getText().toString().trim();
        pas = password.getText().toString().trim();
        if (name.length() == 0 && pas.length() == 0) {
            username.setError("Enter Username");
            password.setError("Enter Password");
        } else if (name.length() == 0) {
            username.setError("Enter UserName");
        } else if (pas.length() == 0) {
            password.setError("Enter Password");
        } else {

            if(name.equals("groupadmin@gmail.com") && pas.equals("kaveri007")){
                startActivity(new Intent(this,Home.class));
            }
//            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGI, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                Log.e("res",response);
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                        String success=jsonObject.getString("success");
//                        JSONArray jsonArray=jsonObject.getJSONArray("login");
//                        if(success.equals("1")){
//
//                            for(int i=0;i<jsonArray.length();i++){
//                                JSONObject object=jsonArray.getJSONObject(i);
//                                String name=object.getString("fullname").trim();
//                                String username=object.getString("username").trim();
//                                Toast.makeText(MainActivity.this, "Login successfull"+name+username, Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Toast.makeText(MainActivity.this, "login error"+e.toString(), Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//            },
//            new Response.ErrorListener() {
//            @Override
//             public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "login error"+error.toString(), Toast.LENGTH_SHORT).show();
//             }
//            })
//            {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String,String> param=new HashMap<>();
//                    param.put("username",name);
//                    param.put("password",pas);
//                        return param;
//                }
//            };
//            RequestQueue requestQueue= Volley.newRequestQueue(this);
//            requestQueue.add(stringRequest);
//        }

        }
    }
    public void signup(View v){

        startActivity(new Intent(this,registration.class));
    }
}
