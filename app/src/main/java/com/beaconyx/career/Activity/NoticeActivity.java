package com.beaconyx.career.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beaconyx.career.R;

public class NoticeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        titleInit();
    }

    private void titleInit() {
        View top = findViewById(R.id.top);

        TextView title = top.findViewById(R.id.title);
        title.setText("공지/이벤트");

        ImageView back = top.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
