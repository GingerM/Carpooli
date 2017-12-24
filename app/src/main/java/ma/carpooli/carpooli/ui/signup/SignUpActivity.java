package ma.carpooli.carpooli.ui.signup;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import ma.carpooli.carpooli.R;
import ma.carpooli.carpooli.ui.base.BaseActivity;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViewById(R.id.phone_input).requestFocus();
        ((EditText) findViewById(R.id.phone_input)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void sendMessage() {
        EditText text= findViewById(R.id.phone_input);
        Drawable drawable = getResources().getDrawable(R.drawable.number_icon);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.error));
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        text.setCompoundDrawables(drawable,drawable,drawable,drawable);
        //text.backgroundTint
        text.requestFocus();

    }
}
