package com.example.sparksbankingapp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparksbankingapp.adapters.TransferTableAdapter;
import com.example.sparksbankingapp.models.Transfer;
import com.example.sparksbankingapp.room_persistence.MyRepository;

import java.util.ArrayList;
import java.util.List;

public class TransfersTableActivity extends AppCompatActivity {

    private static final String TAG = "TransfersTableActivity";

    //vars
    private TransferTableAdapter adapter;
    private ArrayList<Transfer> transfers = new ArrayList<>();
    private MyRepository myRepository;

    //widgets
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers_table);

        getSupportActionBar().setTitle("Transfers");
        myRepository = new MyRepository(this);

        bindViews();
    }

    private void bindViews(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransferTableAdapter(this,transfers);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();

                if(transfers.size() == 0){
                    recyclerView.setVisibility(View.GONE);
                }else{
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                retrieveTransfers();
            }
        });
    }

    private void retrieveTransfers(){
        myRepository.getAllTransfers().observe(this, new Observer<List<Transfer>>() {
            @Override
            public void onChanged(List<Transfer> mTransfers) {
                transfers.clear();
                transfers.addAll(mTransfers);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
