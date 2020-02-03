package com.example.myki.adapters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myki.R;
import com.example.myki.models.Account;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.MyViewHolder> {
    Context context;
    private List<Account> accounts;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTv)
        TextView nameTv;
        @BindView(R.id.emailTv)
        TextView emailTv;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    public AccountsAdapter(Context context, List<Account> accounts) {
        this.accounts = accounts;
        this.context = context;

    }

    @Override
    public AccountsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_accounts, parent, false);

        return new AccountsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AccountsAdapter.MyViewHolder holder, final int position) {
        final Account account = accounts.get(position);

    holder.nameTv.setText(account.getName());
    holder.emailTv.setText(account.getEmail());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

}