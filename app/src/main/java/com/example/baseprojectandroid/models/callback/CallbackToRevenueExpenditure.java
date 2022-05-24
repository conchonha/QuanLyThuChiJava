package com.example.baseprojectandroid.models.callback;

import com.example.baseprojectandroid.cores.room.table.RevenueExpenditureTable;
import com.example.baseprojectandroid.src.dialog.FragmentDialogRevenueExpenditure;

public interface CallbackToRevenueExpenditure<T> {
    public T getTimePickerDialog(String time);
    public T getTitleDialog(String title);
    public T getRevenueExpenditureObject(RevenueExpenditureTable revenueExpenditureTable);
    T setTitleCategory(int title);
    T setTitleDate(int title);
    T setTitlePrice(int title);
    T setTitleNote(int title);
    T setTitleSave(int title);
    T setTitleCancel(int title);
}
