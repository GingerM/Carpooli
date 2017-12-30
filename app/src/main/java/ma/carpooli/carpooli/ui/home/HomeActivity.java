package ma.carpooli.carpooli.ui.home;

import ma.carpooli.carpooli.R;
import ma.carpooli.carpooli.ui.init.InitAppActivity;
import ma.carpooli.carpooli.ui.signin.SignInActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class HomeActivity extends AppCompatActivity {

    String email;
    LinearLayout carpo_specs;
    Animation slideDownAnimation;
    Spinner spinner;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        email = intent.getStringExtra("EMAIL");

        spinner = (Spinner) findViewById(R.id.country_spinner);

        carpo_specs = (LinearLayout) findViewById(R.id.carpo_specs);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(++check > 1) {
                    String city = (String) parentView.getSelectedItem();
                    Intent i = new Intent(HomeActivity.this, HomeFormActivity.class);
                    i.putExtra("CITY", city);
                    i.putExtra("EMAIL", email);
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
