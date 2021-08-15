package com.example.sparksbankingapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "transfers")
public class Transfer {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int transferId;

    @ColumnInfo(name = "transferFromCustomerId")
    private String transferFromCustomerName;

    @ColumnInfo(name = "transferToCustomerId")
    private String transferToCustomerName;

    @ColumnInfo(name = "amount")
    private double amount;


    @Ignore
    public Transfer() {
    }

    public Transfer(String transferFromCustomerName, String transferToCustomerName, double amount) {
        this.transferFromCustomerName = transferFromCustomerName;
        this.transferToCustomerName = transferToCustomerName;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId=" + transferId +
                ", transferFromCustomerName='" + transferFromCustomerName + '\'' +
                ", transferToCustomerName='" + transferToCustomerName + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return transferId == transfer.transferId &&
                Double.compare(transfer.amount, amount) == 0 &&
                Objects.equals(transferFromCustomerName, transfer.transferFromCustomerName) &&
                Objects.equals(transferToCustomerName, transfer.transferToCustomerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transferId, transferFromCustomerName, transferToCustomerName, amount);
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getTransferFromCustomerName() {
        return transferFromCustomerName;
    }

    public void setTransferFromCustomerName(String transferFromCustomerName) {
        this.transferFromCustomerName = transferFromCustomerName;
    }

    public String getTransferToCustomerName() {
        return transferToCustomerName;
    }

    public void setTransferToCustomerName(String transferToCustomerName) {
        this.transferToCustomerName = transferToCustomerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
