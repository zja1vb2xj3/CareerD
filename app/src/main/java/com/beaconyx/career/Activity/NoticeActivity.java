package com.beaconyx.career.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beaconyx.career.Adapter.NoticeListViewAdapter;
import com.beaconyx.career.Model.NoticeModel;
import com.beaconyx.career.R;

public class NoticeActivity extends Activity {
    private ListView noticeListView;
    private NoticeListViewAdapter noticeListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        titleInit();
        listviewInit();
    }

    private void listviewInit(){
        noticeListView = (ListView) findViewById(R.id.list);
        noticeListViewAdapter = new NoticeListViewAdapter(getApplicationContext());

        NoticeModel noticeModel0 = new NoticeModel();

        noticeModel0.setTitle("제목 0");
        noticeModel0.setTime("시간 0");
        noticeModel0.setText("내용 0");


        NoticeModel noticeModel1 = new NoticeModel();

        noticeModel1.setTitle("제목 1");
        noticeModel1.setTime("시간 1");
        noticeModel1.setText("내용 1");

        noticeListViewAdapter.addItem(noticeModel0);
        noticeListViewAdapter.addItem(noticeModel1);

        noticeListView.setAdapter(noticeListViewAdapter);
    }


    private void titleInit() {
        View top = findViewById(R.id.top);

        TextView title = top.findViewById(R.id.title);
        title.setText("공지/이벤트");

        LinearLayout backLayout = top.findViewById(R.id.backLayout);

        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
