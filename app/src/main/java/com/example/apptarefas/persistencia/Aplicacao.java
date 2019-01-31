package com.example.apptarefas.persistencia;

import android.app.Application;
import android.util.Log;

import com.example.apptarefas.BuildConfig;
import com.example.apptarefas.models.*;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

import static com.example.apptarefas.models.MyObjectBox.builder;

public class Aplicacao extends Application {

    public static final String TAG = "ObjectBoxExample";
    public static final boolean EXTERNAL_DIR = false;
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();

        boxStore =  MyObjectBox.builder().androidContext(this).build();

        if (BuildConfig.DEBUG){
            new AndroidObjectBrowser(boxStore).start(this);
        }

        Log.d("App", "Using ObjectBox " + BoxStore.getVersion() + " (" + BoxStore.getVersionNative() + ")");

    }

    public BoxStore getBoxStore(){

        return boxStore;
    }
}
