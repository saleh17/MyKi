package com.example.myki.activities;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myki.R;
import com.example.myki.adapters.AccountsAdapter;
import com.example.myki.api.ApiService;
import com.example.myki.models.Account;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myki.api.ApiContract.BASE_URL;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    AccountsAdapter accountsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);
        getAccounts();
    }



    @Override
    public void onRefresh() {
        getAccounts();
    }

    public void getAccounts() {
        swipeRefreshLayout.setRefreshing(true);
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();
            ApiService service = retrofit.create(ApiService.class);
            Call<List<Account>> call = service.getAccounts();

            call.enqueue(new Callback<List<Account>>() {
                @Override
                public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    List<Account> accounts = response.body();
                    accountsAdapter = new AccountsAdapter(getApplicationContext(), accounts);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(accountsAdapter);
                }

                @Override
                public void onFailure(Call<List<Account>> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    showMessage("Something went wrong");
                }
            });
    }

    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton(getString(R.string.close),
                        (dialog, which) -> {}
                );
        builder.create().show();
    }
}
