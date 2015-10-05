package com.asrajarshi.myapplication;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by asrajarshi on 9/27/2015.
 */
public class GetAssets {
    Context appContext;
    GetAssets(Context context){
        this.appContext = context;
    }
    public String getFileAssets() throws IOException {
        InputStream is = null;
        try {
            is = appContext.getAssets().open("obj.json");
        }catch (IOException e) {
            e.printStackTrace();
        }
        int size = 0;
        size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String bufferString = new String(buffer);
        Log.d("bbuffer1", bufferString);
        return bufferString;
    }
}

