package ma.carpooli.carpooli.ui.ride;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ma.carpooli.carpooli.CarpooliApplication;
import ma.carpooli.carpooli.LiftData;
import ma.carpooli.carpooli.R;
import ma.carpooli.carpooli.ui.home.HomeFormActivity;
import ma.carpooli.carpooli.ui.liftsearchresult.LiftSearchResult;

public class ShareRideActivity extends AppCompatActivity {

    Spinner dest_from, dest_to, passenger_num;
    EditText pick_date, ride_price;
    static EditText pick_time;

    String fromCity, toCity, passengerNum, date, time;

    Button search_ride;

    Double price;

    int fromCheck, toCheck, passCheck = 0;

    FirebaseDatabase database;
    DatabaseReference myRef;

    Calendar myCalendar;

    DatePickerDialog.OnDateSetListener dateDialog;

    CarpooliApplication carpooliApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_ride);

        carpooliApplication = (CarpooliApplication) getApplication();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("lifts");

        dest_from = (Spinner) findViewById(R.id.dest_from);
        dest_to = (Spinner) findViewById(R.id.dest_to);

        ride_price = (EditText) findViewById(R.id.ride_price);
        pick_time = (EditText) findViewById(R.id.pick_time);
        pick_date = (EditText) findViewById(R.id.pick_date);
        passenger_num = (Spinner) findViewById(R.id.passenger_num);

        search_ride = (Button) findViewById(R.id.search_ride);

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
                new DatePickerDialog(ShareRideActivity.this, dateDialog, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        search_ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = pick_date.getText().toString();
                time = pick_time.getText().toString();
                price = Double.parseDouble(ride_price.getText().toString());

                boolean cancel = false;

                // Check for a valid password, if the user entered one.
                if (TextUtils.isEmpty(passengerNum) || TextUtils.isEmpty(toCity) || TextUtils.isEmpty(fromCity) || TextUtils.isEmpty(date) || TextUtils.isEmpty(time) || TextUtils.isEmpty(ride_price.getText().toString())){
                    cancel = true;
                }

                if(!cancel){
                    LiftData liftData = new LiftData(fromCity, "-KwfQmBUTthfTocTuair", price, time, toCity, Integer.parseInt(passengerNum), date);
                    myRef.push().setValue(liftData);
                }
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        pick_date.setText(sdf.format(myCalendar.getTime()));
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            pick_time.setText(hourOfDay + ":" + minute);
        }
    }
}
