package com.example.retrofitproject.service;

import com.example.retrofitproject.model.Moneys;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    @GET("prices?key=a01fec7202073d1ef5bb5cab7f15071c")
    Call<List<Moneys>> getdata();

}
