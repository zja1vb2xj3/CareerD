package com.example.pdg.careerd.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pdg.careerd.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        ImageView banner = (ImageView) findViewById(R.id.mainBanner);
        banner.setImageResource(R.mipmap.main_banner);

        Button beaconService = (Button) findViewById(R.id.beaconServiceButton);

        Button ainfo = (Button) findViewById(R.id.ainfoButton);
        Button binfo = (Button) findViewById(R.id.binfoButton);
        Button cinfo = (Button) findViewById(R.id.cinfoButton);
        Button dinfo = (Button) findViewById(R.id.dinfoButton);

        beaconService.setOnClickListener(onClickListener);
        ainfo.setOnClickListener(onClickListener);
        binfo.setOnClickListener(onClickListener);
        cinfo.setOnClickListener(onClickListener);
        dinfo.setOnClickListener(onClickListener);

    }

    @Override
    public void onBackPressed() {

    }

    private void startNextActivity(Class activityClass){
        Intent intent = new Intent(MainActivity.this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int viewId = view.getId();

            switch (viewId){
                case R.id.beaconServiceButton:
                    startNextActivity(BeaconServiceActivity.class);
                    break;
                case R.id.ainfoButton:
                    Toast.makeText(MainActivity.this, "ainfo클릭", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
}
