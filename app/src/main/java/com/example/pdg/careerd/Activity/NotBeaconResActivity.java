package com.example.pdg.careerd.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pdg.careerd.R;

public class NotBeaconResActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_beacon_res);

        titleInit();
    }

    private void titleInit() {
        View top = findViewById(R.id.top);

        TextView firstTitle = top.findViewById(R.id.firstTitle);

        firstTitle.setText("2018 현대·기아자동차");

        TextView secondTitle = top.findViewById(R.id.secondTitle);
        secondTitle.setTextSize(20);
        secondTitle.setText("협력사 채용박람회");

        ImageView back = top.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView home = top.findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
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
