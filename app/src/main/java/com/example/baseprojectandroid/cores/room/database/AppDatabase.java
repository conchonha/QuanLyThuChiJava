package com.example.baseprojectandroid.cores.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.baseprojectandroid.cores.room.dao.EevenueExpenditureDao;
import com.example.baseprojectandroid.cores.room.table.RevenueExpenditureTable;
import com.example.baseprojectandroid.utils.Constain;

//đây là annotation sẽ cho mình biết là sẽ có bao nhiêu bảng được tạo trong sqlite
@Database(entities = {RevenueExpenditureTable.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //thuộc tính với hàm này giúp mình lấy được instance của class này
    private static AppDatabase mInstance;

    //đây là các abstract giúp cho công việc truy vấn trở lên dễ dàng hơn
    public abstract EevenueExpenditureDao taskEvenueExpenditureDao();

    //get instance của database
    public static synchronized AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, Constain.appNameDatabase).fallbackToDestructiveMigration().build();
        }
        return mInstance;
    }
}
