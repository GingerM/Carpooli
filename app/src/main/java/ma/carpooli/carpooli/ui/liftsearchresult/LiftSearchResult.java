package ma.carpooli.carpooli.ui.liftsearchresult;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import ma.carpooli.carpooli.CarpooliApplication;
import ma.carpooli.carpooli.LiftData;
import ma.carpooli.carpooli.R;
import ma.carpooli.carpooli.utils.CardArrayAdapter;

public class LiftSearchResult extends AppCompatActivity {

    private static final String TAG = "LiftSearchResult";
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;

    CarpooliApplication carpooliApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lift_search_result);

        carpooliApplication = (CarpooliApplication) getApplication();

        listView = (ListView) findViewById(R.id.card_listView);

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.list_item_card);


        for (LiftData lift : carpooliApplication.ld) {
            cardArrayAdapter.add(lift);
        }
        listView.setAdapter(cardArrayAdapter);
    }
}
