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
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card, parent, false);
        }

        AccountBookModel abm = getItem(position);
        TextView accountBookTitleTV = listitemView.findViewById(R.id.TitleTVAccountBook);
        TextView accountBookBalanceTV = listitemView.findViewById(R.id.BalanceTVAccountBook);

        accountBookTitleTV.setText(abm.getAccountBookName());
        accountBookBalanceTV.setText("Rs. "+String.valueOf(abm.getAccountBookBalance())+"/-");
        return listitemView;
    }
}
