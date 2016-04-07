package com.apps.dayornight;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.apps.utils.SharedPreferenceUtils;

/**
 * Created by Kira on 2016/4/7.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        changeTheme();
        super.onCreate(savedInstanceState);
    }

    protected void changeTheme(){
        boolean isNight = SharedPreferenceUtils.get(this);
        if (isNight){
            setTheme(R.style.AppNightTheme);
        }else{
            setTheme(R.style.AppDayTheme);
        }
    }

    protected void recreateUI(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = getIntent();
//                overridePendingTransition(0, 0);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                finish();
//                overridePendingTransition(0, 0);
//                startActivity(intent);
             recreate();
            }
        },100);
    }
}
