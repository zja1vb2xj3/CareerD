package com.example.pdg.careerd.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pdg.careerd.DialogBuilder.NormalDialogBuilder;
import com.example.pdg.careerd.R;

public class MainActivity extends Activity {
    private String CLASSNAME = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        ImageView banner = (ImageView) findViewById(R.id.mainBanner);
        //메인banner 이미지 설정
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

    /**
     * 메인 엑티비티 뒤로가기 버튼 시 물음 다이얼로그
     */
    @Override
    public void onBackPressed() {
        NormalDialogBuilder normalDialogBuilder = new NormalDialogBuilder(MainActivity.this);

        normalDialogBuilder.setActivity(this);
        normalDialogBuilder.setNormalDialogBuilder("알림", "App을 종료하시겠습니까?", "종료", "취소");
    }


    /**
     * 다음 엑티비티 실행
     * @param activityClass 실행될 엑티비티 클래스명
     */
    private void startNextActivity(Class activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //region onClickListener 메인 엑티비티의 onClickListener
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int viewId = view.getId();

            switch (viewId) {
                case R.id.beaconServiceButton:
                    startNextActivity(NotBeaconResActivity.class);
                    break;
                case R.id.ainfoButton:
                    Toast.makeText(MainActivity.this, "ainfo클릭", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
    //endregion
}
