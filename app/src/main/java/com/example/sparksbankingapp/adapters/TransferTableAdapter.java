package com.example.sparksbankingapp.adapters;

import android.content.Context;
import android.os.LocaleList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sparksbankingapp.R;
import com.example.sparksbankingapp.models.Transfer;

import java.util.ArrayList;
import java.util.Locale;

public class TransferTableAdapter extends RecyclerView.Adapter<TransferTableAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Transfer> transfers;

    public TransferTableAdapter(Context context, ArrayList<Transfer> transfers) {
        this.context = context;
        this.transfers = transfers;
    }

    @NonNull
    @Override
    public TransferTableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_transfer_table,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransferTableAdapter.ViewHolder holder, int position) {
        Transfer temp = transfers.get(position);
        holder.from.setText(temp.getTransferFromCustomerName());
        holder.to.setText(temp.getTransferToCustomerName());
        holder.amount.setText(String.format(Locale.UK,"%.2f",temp.getAmount()));
    }

    @Override
    public int getItemCount() {
        return transfers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView from,to,amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            from = itemView.findViewById(R.id.fromAccount);
            to = itemView.findViewById(R.id.toAccount);
            amount = itemView.findViewById(R.id.transferAmount);
        }
    }
}
