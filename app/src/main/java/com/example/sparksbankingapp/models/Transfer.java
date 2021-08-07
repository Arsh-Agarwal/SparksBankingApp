package com.example.sparksbankingapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "transfers")
public class Transfer {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int transferId;

    @ColumnInfo(name = "transferFromCustomerId")
    private int transferFromCustomerId;

    @ColumnInfo(name = "transferToCustomerId")
    private int transferToCustomerId;

    @ColumnInfo(name = "amount")
    private double amount;


    public Transfer() {
    }

    public Transfer(int transferFromCustomerId, int transferToCustomerId, double amount) {
        this.transferFromCustomerId = transferFromCustomerId;
        this.transferToCustomerId = transferToCustomerId;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return transferId == transfer.transferId &&
                transferFromCustomerId == transfer.transferFromCustomerId &&
                transferToCustomerId == transfer.transferToCustomerId &&
                Double.compare(transfer.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferId, transferFromCustomerId, transferToCustomerId, amount);
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferFromCustomerId=" + transferFromCustomerId +
                ", transferToCustomerId=" + transferToCustomerId +
                ", amount=" + amount +
                '}';
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getTransferFromCustomerId() {
        return transferFromCustomerId;
    }

    public void setTransferFromCustomerId(int transferFromCustomerId) {
        this.transferFromCustomerId = transferFromCustomerId;
    }

    public int getTransferToCustomerId() {
        return transferToCustomerId;
    }

    public void setTransferToCustomerId(int transferToCustomerId) {
        this.transferToCustomerId = transferToCustomerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
