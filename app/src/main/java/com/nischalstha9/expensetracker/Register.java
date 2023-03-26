package com.nischalstha9.expensetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Register extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //EVENT LISTENERS
        Button button= (Button)findViewById(R.id.registerBtn);
        TextView registerHelper = (TextView)findViewById(R.id.loginHelper);
        button.setOnClickListener(Register.this);
        registerHelper.setOnClickListener(Register.this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.registerBtn) {
            Intent i = new Intent(getApplicationContext(), AccountBooks.class);
            startActivity(i);
        }
        else if(view.getId()==R.id.loginHelper) {
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }
    }
}
