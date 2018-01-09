package ma.carpooli.carpooli.ui.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ma.carpooli.carpooli.R;

public class UserEmailSignupActivity extends AppCompatActivity {

    private static final String TAG = "UserEmailSignupActivity";

    Button signup_btn;
    EditText lNameField, fNameField, emailField, passwordField;

    private FirebaseAuth mAuth;

    AuthCredential credential;

    Task<AuthResult> authResultTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_email_signup);

        signup_btn = (Button)findViewById(R.id.signup_btn);

        lNameField = (EditText) findViewById(R.id.lName_signup);
        fNameField = (EditText) findViewById(R.id.fName_signup);
        emailField = (EditText) findViewById(R.id.email_signup);
        passwordField = (EditText) findViewById(R.id.password_signup);

        mAuth = FirebaseAuth.getInstance();

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Store values at the time of the login attempt.
                String fName = fNameField.getText().toString();
                String lName = lNameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                boolean cancel = false;

                // Check for a valid password, if the user entered one.
                if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                    emailField.setError(getString(R.string.error_invalid_password));
                    cancel = true;
                }

                // Check for a valid email address.
                if (TextUtils.isEmpty(email)) {
                    emailField.setError(getString(R.string.error_field_required));
                    cancel = true;
                } else if (!isEmailValid(email)) {
                    emailField.setError(getString(R.string.error_invalid_email));
                    cancel = true;
                }

                if(!cancel){
                    AuthCredential credential = EmailAuthProvider.getCredential(email, password);

                    

                }

            }
        });
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@aui");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}
