package com.example.sparksbankingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparksbankingapp.adapters.TransferRecycelrAdapter;
import com.example.sparksbankingapp.models.Customer;
import com.example.sparksbankingapp.models.Transfer;
import com.example.sparksbankingapp.room_persistence.MyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TransferActivity extends AppCompatActivity implements RecyclerInterface{

    private static final String TAG = "TransferActivity";

    //consts
    public static final String KEY_TRANSFER_FROM_CUSTOMER = "transferFromCustomer";

    //vars
    private Customer transferFromCustomer;
    private TransferRecycelrAdapter adapter;
    private ArrayList<Customer> customers = new ArrayList<>();
    private MyRepository myRepository;

    //widgets
    private EditText amountET;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        getSupportActionBar().setTitle("Transfer money");
        transferFromCustomer = getIntent().getParcelableExtra(KEY_TRANSFER_FROM_CUSTOMER);
        myRepository = new MyRepository(this);
        bindViews();
        showSoftInput(amountET);
        setupData();
    }

    private void bindViews(){
        amountET = findViewById(R.id.transferAmount);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransferRecycelrAdapter(this,customers,this);
        recyclerView.setAdapter(adapter);
    }

    private void setupData(){
        myRepository.getAllCustomers().observe(this, new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> mCustomers) {
                customers.clear();
                customers.addAll(mCustomers);
                for (int i = 0 ; i < customers.size() ; i++){
                    if(customers.get(i).getCustomerId() == transferFromCustomer.getCustomerId()){
                        customers.remove(i);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    //interface methods
    @Override
    public void itemClicked(int position) {
        String amountString = amountET.getText().toString();
        if(amountString.equals("")){
            toastMessage("Enter the amount to be transferred");
            return;
        }

        Double amount = null;
        try {
            amount = Double.parseDouble(amountString);
        } catch (NumberFormatException e) {
            Log.d(TAG, "itemClicked: "+e.getMessage());
            toastMessage("Enter a valid amount");
            return;
        }
        Customer transferToCustomer = customers.get(position);

        transferFromCustomer.setCurrenctBalance(transferFromCustomer.getCurrenctBalance()-amount);
        transferToCustomer.setCurrenctBalance(transferToCustomer.getCurrenctBalance()+amount);

        myRepository.insertTransfer(new Transfer(transferFromCustomer.getCustomerId(),transferToCustomer.getCustomerId(),amount));
        myRepository.updateCustomer(transferFromCustomer);
        myRepository.updateCustomer(transferToCustomer);

        toastMessage("Transferred "+String.format(Locale.US,"%.2f",amount)+" to "+transferToCustomer.getCustomerName());
        exitActivity();
    }

    //utils
    private void exitActivity(){
        setResult(RESULT_OK);
        super.finish();
    }

    private void toastMessage(String message){
        Log.d(TAG, "toastMessage: "+message);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private void showSoftInput(View view){
        if(view.requestFocus()){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }
}
