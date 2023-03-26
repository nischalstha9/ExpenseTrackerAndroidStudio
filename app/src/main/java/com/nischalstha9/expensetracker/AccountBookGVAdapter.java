package com.nischalstha9.expensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class AccountBookGVAdapter extends ArrayAdapter<AccountBookModel> {

    public AccountBookGVAdapter(@NonNull Context context, ArrayList<AccountBookModel> AccountBookModelArrayList) {
        super(context, 0, AccountBookModelArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card, parent, false);
        }

        AccountBookModel courseModel = getItem(position);
        TextView accountBookTV = listitemView.findViewById(R.id.idTVCourse);
        ImageView accountBookIV = listitemView.findViewById(R.id.idIVcourse);

        accountBookTV.setText(courseModel.getAccountBookName());
        accountBookIV.setImageResource(courseModel.getAccountBookId());
        return listitemView;
    }
}
