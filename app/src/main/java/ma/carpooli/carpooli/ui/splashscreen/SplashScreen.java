package ma.carpooli.carpooli.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import ma.carpooli.carpooli.R;
import ma.carpooli.carpooli.ui.init.InitAppActivity;
import ma.carpooli.carpooli.utils.PreferencesHelper;

/**
 * Created by MouadS on 19/11/2017.
 */

public class SplashScreen extends AppCompatActivity {


    boolean isDissmissed = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity);
        PreferencesHelper.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                if(!isDissmissed) {
                    if(PreferencesHelper.isHasLaunchedOnce()){
                        startActivity(new Intent(SplashScreen.this, InitAppActivity.class));
                    }
                    else
                        startActivity(new Intent(SplashScreen.this, InitAppActivity.class));
                    SplashScreen.this.finish();
                }
            }
        }, 3000);
    }

    @Override public void onBackPressed() {
        super.onBackPressed();
        isDissmissed = true;
    }
}
