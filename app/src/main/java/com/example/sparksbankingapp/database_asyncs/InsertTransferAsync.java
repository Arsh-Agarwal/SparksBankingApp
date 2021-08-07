package com.example.sparksbankingapp.database_asyncs;

import android.os.AsyncTask;

import com.example.sparksbankingapp.models.Transfer;
import com.example.sparksbankingapp.room_persistence.MyDao;

public class InsertTransferAsync extends AsyncTask<Transfer,Void,Void> {

    private static final String TAG = "InsertTransferAsync";

    private MyDao myDao;

    public InsertTransferAsync(MyDao myDao) {
        this.myDao = myDao;
    }

    @Override
    protected Void doInBackground(Transfer... transfers) {
        myDao.insertTransfer(transfers);
        return null;
    }
}
