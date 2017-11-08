package com.binar.academy.mygreendaosample;

import android.app.Application;

import com.binar.academy.mygreendaosample.database.entity.DaoMaster;
import com.binar.academy.mygreendaosample.database.entity.DaoSession;
import com.facebook.stetho.Stetho;

/**
 * Created by herisulistiyanto on 9/12/17.
 */

public class MainApp extends Application {

    private DaoSession daoSession; // <--- belum ada, auto generated

    @Override
    public void onCreate() {
        super.onCreate();

        daoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "nama_database.db").getWritableDb()
        ).newSession();

        Stetho.initializeWithDefaults(this);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
