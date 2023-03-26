package com.nischalstha9.expensetracker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Login extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //EVENT LISTENERS
        Button button= (Button)findViewById(R.id.loginBtn);
        TextView registerHelper = (TextView)findViewById(R.id.registerHelper);
        button.setOnClickListener(Login.this);
        registerHelper.setOnClickListener(Login.this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginBtn) {

            EditText emailInput = (EditText)findViewById(R.id.email);
            String email = emailInput.getText().toString();

            EditText passwordInput = (EditText)findViewById(R.id.password);
            String password = passwordInput.getText().toString();

            if(email.equals("admin@admin.com") && password.equals("admin")){
                Intent i = new Intent(getApplicationContext(), AccountBooks.class);
                startActivity(i);
                Toast myToast = Toast.makeText(this, "Login Success!", Toast.LENGTH_LONG);
                myToast.show();
                emailInput.setText("");
                passwordInput.setText("");
            }else{
                Toast myToast = Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_LONG);
                myToast.show();
            }

        }
        else if(view.getId()==R.id.registerHelper) {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://nischalstha9.pythonanywhere.com/api/portfolio/about/?format=json";

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response.toString());
//                    textView.setText(response.toString());
//                    try {
//                        JSONObject object=new JSONObject(response);
//                        JSONArray array=object.getJSONArray("users");
//                        for(int i=0;i<array.length();i++) {
//                            JSONObject object1=array.getJSONObject(i);
//                            textView.setText(object1.toString());
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error.toString());
                }
            });


            MySingleton.getInstance(this).addToRequestQueue(request);
//            Intent i = new Intent(getApplicationContext(), Register.class);
//            startActivity(i);
        }
    }
}
