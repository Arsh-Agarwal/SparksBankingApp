package com.example.sparksbankingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparksbankingapp.R;
import com.example.sparksbankingapp.RecyclerInterface;
import com.example.sparksbankingapp.models.Customer;

import java.util.ArrayList;
import java.util.Locale;

public class CustomerRecyclerAdapter extends RecyclerView.Adapter<CustomerRecyclerAdapter.ViewHolder> {

    private static final String TAG = "CustomerRecyclerAdapter";

    private Context context;
    private ArrayList<Customer> customers;
    private RecyclerInterface recyclerInterface;

    public CustomerRecyclerAdapter(Context context, ArrayList<Customer> customers, RecyclerInterface recyclerInterface) {
        this.context = context;
        this.customers = customers;
        this.recyclerInterface = recyclerInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Customer temp = customers.get(position);
        holder.name.setText(temp.getCustomerName());
        holder.email.setText(temp.getCustomerEmail());
        holder.currentBalance.setText(String.format(Locale.US,"%.2f",temp.getCurrenctBalance()));
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name,currentBalance,email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.customerName);
            email = itemView.findViewById(R.id.customerEmail);
            currentBalance = itemView.findViewById(R.id.currentBalance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerInterface.itemClicked(getAdapterPosition());
                }
            });
        }
    }
}
