package com.example.myki.api;

import com.example.myki.models.Account;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface  ApiService {
    @GET(ApiContract.Endpoint.GET_ACCOUNTS)
    Call<List<Account>> getAccounts();
}
