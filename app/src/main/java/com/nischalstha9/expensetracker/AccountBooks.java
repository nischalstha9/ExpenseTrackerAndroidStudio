package com.nischalstha9.expensetracker;


import android.os.Bundle;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AccountBooks extends AppCompatActivity {

    GridView coursesGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        coursesGV = findViewById(R.id.idGVcourses);
        ArrayList<AccountBookModel> courseModelArrayList = new ArrayList<AccountBookModel>();

        courseModelArrayList.add(new AccountBookModel("DSA", R.drawable.ic_launcher_foreground));
        courseModelArrayList.add(new AccountBookModel("JAVA", R.drawable.ic_launcher_foreground));
        courseModelArrayList.add(new AccountBookModel("C++", R.drawable.ic_launcher_foreground));
        courseModelArrayList.add(new AccountBookModel("Python", R.drawable.ic_launcher_foreground));
        courseModelArrayList.add(new AccountBookModel("Javascript", R.drawable.ic_launcher_foreground));
        courseModelArrayList.add(new AccountBookModel("DSA", R.drawable.ic_launcher_foreground));

        AccountBookGVAdapter adapter = new AccountBookGVAdapter(this, courseModelArrayList);
        coursesGV.setAdapter(adapter);
    }
}
