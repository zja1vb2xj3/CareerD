package com.beaconyx.career.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beaconyx.career.R;

public class NotBeaconResActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_beacon_res);

        titleInit();
        viewInit();
    }

    private void viewInit(){
        ImageView mapImage = (ImageView) findViewById(R.id.mapImage);
        mapImage.setImageResource(R.mipmap.map1);

        mapImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void titleInit() {
        View top = findViewById(R.id.top);

        TextView firstTitle = top.findViewById(R.id.firstTitle);

        firstTitle.setText("2018 현대·기아자동차");

        TextView secondTitle = top.findViewById(R.id.secondTitle);
        secondTitle.setText("협력사 채용박람회");

        LinearLayout backLayout = top.findViewById(R.id.backLayout);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayout homeLayout = top.findViewById(R.id.homeLayout);

        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void startNextActivityAfterThisFinish(Class activityClass) {
        Intent intent = new Intent(NotBeaconResActivity.this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
