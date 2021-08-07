package com.example.sparksbankingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "customers")
public class Customer implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int customerId;

    @ColumnInfo(name = "name")
    private String customerName;

    @ColumnInfo(name = "customerEmail")
    private String customerEmail;

    @ColumnInfo(name = "currentBalance")
    private double currenctBalance;

    @Ignore
    public Customer() {
    }

    public Customer(String customerName, String customerEmail, double currenctBalance) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.currenctBalance = currenctBalance;
    }

    protected Customer(Parcel in) {
        customerId = in.readInt();
        customerName = in.readString();
        customerEmail = in.readString();
        currenctBalance = in.readDouble();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", currenctBalance=" + currenctBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Double.compare(customer.currenctBalance, currenctBalance) == 0 &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerEmail, customer.customerEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerEmail, currenctBalance);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getCurrenctBalance() {
        return currenctBalance;
    }

    public void setCurrenctBalance(double currenctBalance) {
        this.currenctBalance = currenctBalance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(customerId);
        dest.writeString(customerName);
        dest.writeString(customerEmail);
        dest.writeDouble(currenctBalance);
    }
}
