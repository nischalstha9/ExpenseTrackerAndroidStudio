package com.nischalstha9.expensetracker;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Login extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(GlobalVariable.AccessToken!=null){
            Intent i = new Intent(getApplicationContext(), AccountBooks.class);
            startActivity(i);
        }else {
            setContentView(R.layout.login);
            //EVENT LISTENERS
            Button button = (Button) findViewById(R.id.loginBtn);
            TextView registerHelper = (TextView) findViewById(R.id.registerHelper);
            button.setOnClickListener(Login.this);
            registerHelper.setOnClickListener(Login.this);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginBtn) {

            EditText emailInput = (EditText) findViewById(R.id.email);
            String email = emailInput.getText().toString();

            EditText passwordInput = (EditText) findViewById(R.id.password);
            String password = passwordInput.getText().toString();
            StringRequest loginRequest = new StringRequest(Request.Method.POST, GlobalVariable.LOGIN_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response.toString());
                        String access = new String(object.getString("access"));
                        System.out.println("ACCESS=>" + access);
                        GlobalVariable.AccessToken = access;
                        //JSONArray array=object.getJSONArray("access");
//                        for(int i=0;i<array.length();i++) {
//                            JSONObject object1=array.getJSONObject(i);
//                            System.out.println(object1.toString());
//                        }
                        Intent i = new Intent(getApplicationContext(), AccountBooks.class);
                        startActivity(i);
                        Toast myToast = Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_LONG);
                        myToast.show();
                        emailInput.setText("");
                        passwordInput.setText("");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String body = "";
                    //get status code here
                    String statusCode = String.valueOf(error.networkResponse.statusCode);
                    if (new Integer(statusCode).equals(400)) {
                        Toast.makeText(Login.this, "Invalid Credentials!", Toast.LENGTH_LONG).show();
                    }
                    //get response body and parse with appropriate encoding
                    else if (error.networkResponse.data != null) {
                        JSONObject obj = null;
                        try {
                            body = new String(error.networkResponse.data, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } finally {
                            Toast.makeText(Login.this, body, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("email", email);
                    params.put("password", password);

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            MySingleton.getInstance(this).addToRequestQueue(loginRequest);
        } else if (view.getId() == R.id.registerHelper) {
            Intent i = new Intent(getApplicationContext(), Register.class);
            startActivity(i);
        }
    }
}
