package com.nischalstha9.expensetracker;


import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountBooks extends AppCompatActivity {

    GridView accountBooksGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        StringRequest fetchAccountBookRequest = new StringRequest(Request.Method.GET, GlobalVariable.FETCH_ACCOUNT_BOOKS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                accountBooksGV = findViewById(R.id.accountBookGV);
                ArrayList<AccountBookModel> AccountBookModelArrayList = new ArrayList<AccountBookModel>();
                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray array=object.getJSONArray("results");
                        for(int i=0;i<array.length();i++) {
                            JSONObject book=array.getJSONObject(i);
                            AccountBookModelArrayList.add(new AccountBookModel(book.getString("title"), R.drawable.ic_launcher_foreground, book.getInt("balance")));
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    AccountBookGVAdapter adapter = new AccountBookGVAdapter(AccountBooks.this, AccountBookModelArrayList);
                    accountBooksGV.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String statusCode = String.valueOf(error.networkResponse.statusCode);
                if (new Integer(statusCode).equals(403)) {
                    Toast.makeText(AccountBooks.this, "An issue occoured! Bad Request!", Toast.LENGTH_LONG).show();
                }
                //get response body and parse with appropriate encoding
                else if (error.networkResponse.data != null) {
                    String body = null;
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("ERROR======>"+body);
                    JSONObject obj = null;
                    Toast.makeText(AccountBooks.this, "An issue occoured!", Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("Authorization","JWT "+GlobalVariable.AccessToken);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(fetchAccountBookRequest);
    }
}
