package ma.carpooli.carpooli.ui.home;

import ma.carpooli.carpooli.CarpooliApplication;
import ma.carpooli.carpooli.LiftData;
import ma.carpooli.carpooli.R;
import ma.carpooli.carpooli.ui.init.InitAppActivity;
import ma.carpooli.carpooli.ui.liftsearchresult.LiftSearchResult;
import ma.carpooli.carpooli.ui.signin.SignInActivity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFormActivity extends AppCompatActivity {

    Spinner dest_from, dest_to, passenger_num;
    EditText pick_date;

    String fromCity, toCity, passengerNum, date;

    Button search_carpoo;

    int fromCheck, toCheck, passCheck = 0;

    FirebaseDatabase database;
    DatabaseReference myRef;

    Calendar myCalendar;

    DatePickerDialog.OnDateSetListener dateDialog;

    CarpooliApplication carpooliApplication = (CarpooliApplication) getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_form);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("publish");

        dest_from = (Spinner) findViewById(R.id.dest_from);
        dest_to = (Spinner) findViewById(R.id.dest_to);

        pick_date = (EditText) findViewById(R.id.pick_date);
        passenger_num = (Spinner) findViewById(R.id.passenger_num);

        search_carpoo = (Button) findViewById(R.id.search_carpoo);

        ArrayAdapter<CharSequence> adapterCities = ArrayAdapter.createFromResource(this,
                R.array.cities, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterPassengers = ArrayAdapter.createFromResource(this,
                R.array.passengerNum, android.R.layout.simple_spinner_item);

        adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterPassengers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dest_from.setAdapter(adapterCities);
        dest_to.setAdapter(adapterCities);
        passenger_num.setAdapter(adapterPassengers);

        dest_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(++fromCheck > 1) {
                    fromCity = (String) parentView.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        dest_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(++toCheck > 1) {
                    toCity = (String) parentView.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        passenger_num.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(++passCheck > 1) {
                    passengerNum = (String) parentView.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        myCalendar = Calendar.getInstance();

        dateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        pick_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(HomeFormActivity.this, dateDialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        search_carpoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = pick_date.getText().toString();
                boolean cancel = false;

                // Check for a valid password, if the user entered one.
                if (TextUtils.isEmpty(passengerNum) || TextUtils.isEmpty(toCity) || TextUtils.isEmpty(fromCity) || TextUtils.isEmpty(date)) {
                    cancel = true;
                }

                if(!cancel){
                    myRef.orderByChild("pickupLocation").equalTo(fromCity).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            for (DataSnapshot lift: snapshot.getChildren()) {
                                String pickupLocation = lift.child("pickupLocation").getValue(String.class);
                                String dropOffLocation = lift.child("dropOffLocation").getValue(String.class);
                                Integer seats = lift.child("seats").getValue(Integer.class);
                                String date = lift.child("date").getValue(String.class);

                                carpooliApplication.ld.add(new LiftData(pickupLocation, dropOffLocation, seats, date));

                                Intent i = new Intent(HomeFormActivity.this, LiftSearchResult.class);
                                startActivity(i);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        pick_date.setText(sdf.format(myCalendar.getTime()));
    }
}
