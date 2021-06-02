package com.example.kiemtrathuchanh1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Infomation extends AppCompatActivity {
    String jsonstr = null;
        TextView tv_products;
        Button btn_back, btn_getall;
                ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        tv_products = findViewById(R.id.tv_products);
        btn_getall = findViewById(R.id.btn_getAll);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Infomation.this, Manager.class);
                startActivity(intent);
            }
        });
        btn_getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_products.setText(jsonstr);
                getData();
            }
        });

    }
    public void getData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://60b6892117d1dc0017b8801c.mockapi.io/products";
        JsonArrayRequest rq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for ( int i = 0; i < response.length(); i++){
                    try {
                        JSONObject user = response.getJSONObject(i);
                        String type = user.getString("type");
                        float Price = (float) user.getDouble("price");
                        String country = user.getString("country");
                        jsonstr += "type:" +type+ "\n\n";
                        jsonstr += "price:" +Price+"\n\n";
                        jsonstr += "country:" +Price+"\n\n";

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Infomation.this, "Loi Load Data", Toast.LENGTH_SHORT).show();
            }


        });
        requestQueue.add(rq);
    }
}