package com.example.baseprojectandroid.models.callback;

import com.example.baseprojectandroid.cores.room.table.RevenueExpenditureTable;

public interface CallbackToRevenueExpenditure<T> {
    public T getTimePickerDialog(String time);
    public T getTitleDialog(String title);
    public T getRevenueExpenditureObject(RevenueExpenditureTable revenueExpenditureTable);
}
