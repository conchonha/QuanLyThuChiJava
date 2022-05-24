package com.example.baseprojectandroid.src.repositories.revenue_expenditure_repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baseprojectandroid.async.DeleteEvenueExpenditureAsyncTask;
import com.example.baseprojectandroid.async.InsertEvenueExpenditureAsyncTask;
import com.example.baseprojectandroid.async.UpdateEvenueExpenditureAsyncTask;
import com.example.baseprojectandroid.cores.room.dao.EevenueExpenditureDao;
import com.example.baseprojectandroid.cores.room.database.AppDatabase;
import com.example.baseprojectandroid.cores.room.table.RevenueExpenditureTable;
import com.example.baseprojectandroid.models.spinner_model.SpinnerModel;

import java.util.ArrayList;
import java.util.List;

public class RevenueExpenditureRepositories {
    private List<SpinnerModel> mListSpiner = new ArrayList<>();
    private EevenueExpenditureDao mEvenueExpenditureDao;

    public RevenueExpenditureRepositories(Application application) {
        mEvenueExpenditureDao = AppDatabase.getInstance(application).taskEvenueExpenditureDao();
    }

    public void insert(RevenueExpenditureTable revenueExpenditureTable) {
        new InsertEvenueExpenditureAsyncTask(revenueExpenditureTable, mEvenueExpenditureDao).execute();
    }

    public void delete(RevenueExpenditureTable revenueExpenditureTable) {
        new DeleteEvenueExpenditureAsyncTask(revenueExpenditureTable, mEvenueExpenditureDao).execute();
    }

    public void update(RevenueExpenditureTable revenueExpenditureTable) {
        new UpdateEvenueExpenditureAsyncTask(revenueExpenditureTable, mEvenueExpenditureDao).execute();
    }

    public LiveData<List<RevenueExpenditureTable>> getListEevenueExpenditure(String type) {
        return mEvenueExpenditureDao.getListRevenueExpenditure(type);
    }

    public LiveData<List<RevenueExpenditureTable>> getmListAllEevenueExpenditure() {
        return mEvenueExpenditureDao.getAllListRevenueExpenditure();
    }

    public LiveData<List<SpinnerModel>> getListSpiner() {
        MutableLiveData<List<SpinnerModel>> listTmt = new MutableLiveData<>();
        addData();
        listTmt.setValue(mListSpiner);
        return listTmt;
    }

    public LiveData<List<String>> getListYear() {
        MutableLiveData<List<String>> listMutableLiveData = new MutableLiveData<>();
        List<String> list = new ArrayList<>();
        for (int i = 16; i <= 30; i++) {
            list.add("20" + i);
        }
        listMutableLiveData.setValue(list);
        return listMutableLiveData;
    }

    public LiveData<List<String>> getListMounth() {
        MutableLiveData<List<String>> listMutableLiveData = new MutableLiveData<>();
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i + "");
        }
        listMutableLiveData.setValue(list);
        return listMutableLiveData;
    }

    private void addData() {
        mListSpiner.clear();
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS10aPi7W9xsHqOq2g3orJHs-9DaRv9UgQYlQ&usqp=CAU", "Khác"));
        mListSpiner.add(new SpinnerModel("https://e7.pngegg.com/pngimages/1004/46/png-clipart-real-estate-building-property-house-business-office-building-angle-building.png", "Cho thuê nhà"));
        mListSpiner.add(new SpinnerModel("https://www.vhv.rs/dpng/d/440-4409798_gift-vector-icon-icon-gift-vector-png-transparent.png", "Quà tặng"));
        mListSpiner.add(new SpinnerModel("https://e7.pngegg.com/pngimages/396/516/png-clipart-computer-icons-commerce-discount-icon-logo-desktop-wallpaper.png", "Trợ cấp"));
        mListSpiner.add(new SpinnerModel("https://w7.pngwing.com/pngs/824/347/png-transparent-computer-icons-black-and-white-graphic-design-teamwork-icon-text-photography-logo.png", "Việc làm"));
        mListSpiner.add(new SpinnerModel("https://img95.699pic.com/element/40137/9272.png_300.png", "Buôn bán"));
        mListSpiner.add(new SpinnerModel("https://www.pngfind.com/pngs/m/140-1407971_back-payment-payment-icon-png-transparent-png-download.png", "Thu nhập chính"));
        mListSpiner.add(new SpinnerModel("https://cdn.iconscout.com/icon/premium/png-512-thumb/money-raise-986677.png", "Lãi suất"));
    }

    public LiveData<List<SpinnerModel>> getListSpinerType2() {
        MutableLiveData<List<SpinnerModel>> listTmt = new MutableLiveData<>();
        mListSpiner.clear();
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS10aPi7W9xsHqOq2g3orJHs-9DaRv9UgQYlQ&usqp=CAU", "Khác"));
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8QJw9de-vF0YTgFZyJPeqtfOeCfotny8p1RWcwMuj4kMxuMptgOix_E6YerrkE_tUTnQ&usqp=CAU", "Grap Uber"));
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSE2Fg7mwzRylHj3i29quri1SAZ-vhFNYpO10cIK98FgmExeRPZgYy3R5sGO2ZpBdy6yYI&usqp=CAU", "Mua Sắm"));
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcDlI3j7Khg7KX7oxsqow-wtg6tgjFKawGRaWXFD4DZiyJdkCL-9IT8wzjVx9z9maJs1w&usqp=CAU", "Sức Khỏe"));
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS37wDSvz9IFWoT7aZ5Oh__6O4gcYHI3T38QzzAfx1buzqmjuzXHFEQVtlQKshnJG7ufbA&usqp=CAU", "Xe bus"));
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2RDrLjca-hGyI9H73V2WjRg-kxH17TjasFWruZNhsZuIsNodDvsFzCkrt69_aas08vPQ&usqp=CAU", "Vé Máy Bay"));
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkAP7Xl-d2dUHnIZvq4qQJOCshKvlqeV9zX9g6rwaBk2AI1EAhOcOyeoXnDIU1ERvW7eM&usqp=CAU", "Cho Vay"));
        mListSpiner.add(new SpinnerModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR8mV_By7iFx3b9eakzPKpSHD4pArh90VT495iXmpPpjEdIyflgsVVquGNxGoc-8U7Lzmg&usqp=CAU", "Điện Nước"));
        listTmt.setValue(mListSpiner);
        return listTmt;
    }
}
