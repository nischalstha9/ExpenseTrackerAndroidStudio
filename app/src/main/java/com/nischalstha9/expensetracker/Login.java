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
            Intent i = new Intent(getApplicationContext(), Register.class);
            startActivity(i);
        }
    }
}
