package com.beaconyx.career.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.beaconyx.career.R;

public class LoadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        startNextActivity(MainActivity.class);
    }

    private void startNextActivity(Class activityClass){
        Intent intent = new Intent(LoadActivity.this, activityClass);
        startActivity(intent);
        finish();
    }
}
