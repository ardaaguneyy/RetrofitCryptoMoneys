package com.example.retrofitproject.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitproject.R;
import com.example.retrofitproject.model.Moneys;

import java.util.ArrayList;

public class Recyclerviewadapter extends RecyclerView.Adapter<Recyclerviewadapter.Moneyholder> {
    private ArrayList<Moneys> cryptolist;
    private String[] colors = {"#a3ff00","#ff00aa","#b4a7d6","#a4c2f4","#8ee5ee","#cd950c","#f5f5f5","#f47932"};
    public Recyclerviewadapter(ArrayList<Moneys> cryptolist) {
        this.cryptolist = cryptolist;
    }

    @NonNull
    @Override
    public Moneyholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerlayout,parent,false);
        return new Moneyholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Moneyholder holder, int position) {
        holder.bind(cryptolist.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptolist.size();
    }

    public class Moneyholder extends RecyclerView.ViewHolder {
        TextView moneynametext, moneypricetext;
        public Moneyholder(@NonNull View itemView) {
            super(itemView);

        }
        public void bind(Moneys moneys,String[] colors, Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position%8]));
            moneynametext = itemView.findViewById(R.id.moneynamestext);
            moneypricetext = itemView.findViewById(R.id.moneypricestext2);
            moneynametext.setText(moneys.currency);
            moneypricetext.setText(moneys.price);
        }
    }
}
