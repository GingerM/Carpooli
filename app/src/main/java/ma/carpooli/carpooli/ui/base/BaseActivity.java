package ma.carpooli.carpooli.ui.base;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import ma.carpooli.carpooli.R;

/**
 * Created by MouadS on 11/04/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public String TAG=getClass().getSimpleName();
    public SharedPreferences mPrefs;
    public SharedPreferences.Editor prefsEditor;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mPrefs = this.getSharedPreferences("SHARED_PREFERENCES", 0);
        prefsEditor = mPrefs.edit();
    }
    public void goBack(View v){onBackPressed();}

    @Override
    public void setTitle(CharSequence title) {
        ((TextView)findViewById(R.id.nav_header_title)).setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        ((TextView)findViewById(R.id.nav_header_title)).setText(titleId);
    }
    protected void removeWait() {
        if(progressDialog!=null)
            progressDialog.dismiss();
    }

    protected void showWait(String title, String message) {
        removeWait();
        progressDialog = ProgressDialog.show(this, title,message);
    }
}
