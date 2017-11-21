package com.beaconyx.career.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.beaconyx.career.DialogBuilder.NormalDialogBuilder;
import com.beaconyx.career.R;

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

        ImageView beaconService = (ImageView) findViewById(R.id.beaconService);

        ImageView ainfo = (ImageView) findViewById(R.id.ainfo);
        ImageView binfo = (ImageView) findViewById(R.id.binfo);
        ImageView cinfo = (ImageView) findViewById(R.id.cinfo);
        ImageView dinfo = (ImageView) findViewById(R.id.dinfo);

        beaconService.setImageResource(R.mipmap.bt_top_img);
        ainfo.setImageResource(R.mipmap.bt_a_img);
        binfo.setImageResource(R.mipmap.bt_b_img);
        cinfo.setImageResource(R.mipmap.bt_c_img);
        dinfo.setImageResource(R.mipmap.bt_d_img);

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
                case R.id.beaconService:
                    startNextActivity(NotBeaconResActivity.class);
                    break;
                case R.id.ainfo:
                    Toast.makeText(MainActivity.this, "ainfo클릭", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };
    //endregion
}
