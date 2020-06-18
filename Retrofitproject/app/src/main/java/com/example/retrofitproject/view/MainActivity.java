package com.example.retrofitproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitproject.R;
import com.example.retrofitproject.adapter.Recyclerviewadapter;
import com.example.retrofitproject.model.Moneys;
import com.example.retrofitproject.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
ArrayList<Moneys> cryptomodels;
private String baseurl = "https://api.nomics.com/v1/";
Retrofit retrofit ;
RecyclerView recyclerView;
Recyclerviewadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        //https://api.nomics.com/v1/prices?key=a01fec7202073d1ef5bb5cab7f15071c
        //Retrofit and JSON
         Gson gson = new GsonBuilder().setLenient().create();
         retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
         loaddata();




    }
    public  void loaddata(){
        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);
        Call<List<Moneys>> call = cryptoAPI.getdata();
        call.enqueue(new Callback<List<Moneys>>() {
            @Override
            public void onResponse(Call<List<Moneys>> call, Response<List<Moneys>> response) {
                if (response.isSuccessful()){
                    List<Moneys> responselist = response.body();
                    cryptomodels = new ArrayList<>(responselist);
                    //Recycler
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    adapter = new Recyclerviewadapter(cryptomodels);
                    recyclerView.setAdapter(adapter);
                   /* for (Moneys moneys : cryptomodels){
                        System.out.println(moneys.price);
                    }*/

                }
            }

            @Override
            public void onFailure(Call<List<Moneys>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "HATA", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
