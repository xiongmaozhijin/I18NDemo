package cn.example.i18ndemo;

import android.util.Log;

import com.applanga.android.ApplangaApplication;

public class App extends ApplangaApplication {

    public static final String TAG = "I18NDemo";

    @Override
    public void onLocalizeFinished(boolean success) {
        //do something on finished loacalization
        Log.e(TAG, "onLocalizeFinished(). " + success);
    }

}
