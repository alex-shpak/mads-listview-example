package com.example.madslistviewexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.madsadview.MadsInlineAdView;


public class MainActivity extends AppCompatActivity {

    private MadsViewAdapter<String> adapter = new MadsViewAdapter<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAds();

        ListView listView = (ListView) findViewById(R.id.listView);

        adapter.setGroupSize(3, 10);
        adapter.setPlacement(getString(R.string.mads_dev_placement));

        listView.setAdapter(adapter);

        for (int i = 0; i < 50; i++) {
            adapter.getList().add(String.format("Item %s", i));
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * It takes some time to init WebView first time. Call this method during splash screen
     */
    private void initAds() {
        MadsInlineAdView ads = new MadsInlineAdView(this, null);
    }

}
