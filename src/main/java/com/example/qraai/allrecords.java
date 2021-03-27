package com.example.qraai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class allrecords extends AppCompatActivity {
    private static final String URL_PRODUCTS = "http://192.168.43.72/codegenerators/fetchrecords.php";

    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allrecords);
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }
    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("res",response);
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            Integer cnt = 0;
                            //traversing through all the object
                            if(array.length()==0){
                                final AlertDialog.Builder builder = new AlertDialog.Builder(allrecords.this);
                                builder.setCancelable(true);
                                builder.setTitle("Error");
                                builder.setMessage("No Service History Found....!");
                                builder.setIcon(R.drawable.updateicon);
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(allrecords.this, Home.class));
                                    }
                                });
                                builder.show();
                            }else{
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(

                                        product.getString("serial"),
                                        product.getString("date"),
                                        product.getString("description")
                                ));
                            }

                        }                            //creating adapter object and setting it to recyclerview
                            ProductAdapter adapter = new ProductAdapter(allrecords.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);

    }
}
