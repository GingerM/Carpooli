package ma.carpooli.carpooli.ui.init;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import ma.carpooli.carpooli.R;
import ma.carpooli.carpooli.ui.base.BaseActivity;
import ma.carpooli.carpooli.ui.signin.SignInActivity;
import ma.carpooli.carpooli.ui.signup.PhoneNumberActivity;

/**
 * Created by MouadS on 19/11/2017.
 */

public class InitAppActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_app);

        findViewById(R.id.get_started).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitAppActivity.this.setContentView(R.layout.activity_welcome);
                findViewById(R.id.create_account).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(InitAppActivity.this, PhoneNumberActivity.class));
                    }
                });
                findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(InitAppActivity.this, SignInActivity.class));
                    }
                });
            }
        });
    }
}
