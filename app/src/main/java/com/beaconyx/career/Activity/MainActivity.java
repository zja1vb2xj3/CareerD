package com.beaconyx.career.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beaconyx.career.Application.AppDelegate;
import com.beaconyx.career.DialogBuilder.NormalDialogBuilder;
import com.beaconyx.career.R;
import com.beaconyx.career.Toast.ToastMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    private String CLASSNAME = getClass().getSimpleName();

    @BindView(R.id.mainBanner)
    ImageView mainBanner;

    @BindView(R.id.beaconService)
    ImageView beaconService;

    @BindViews({R.id.ainfo, R.id.binfo, R.id.cinfo, R.id.dinfo})
    List<ImageView> infoViews;

    @BindView(R.id.megaPhoneLayout)
    FrameLayout megaPhoneLayout;

    @BindView(R.id.megaPhone)
    ImageView megaPhone;

    @BindView(R.id.megaPhoneCount)
    TextView megaPhoneCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();

        megaPhoneLayout.setOnClickListener(v -> megaPhoneLayoutClick());

        megaPhoneLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                megaPhoneLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                System.out.println(megaPhoneLayout.getWidth());
                System.out.println(megaPhoneLayout.getHeight());

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(megaPhoneLayout.getWidth() + 20, megaPhoneLayout.getHeight() + 20);
                params.gravity = Gravity.RIGHT | Gravity.BOTTOM;

                megaPhoneLayout.setLayoutParams(params);

            }
        });
    }

    private void megaPhoneLayoutClick() {
        ToastMessage toastMessage = new ToastMessage(this);
        toastMessage.printToast("메가폰 레이아웃 클릭");
    }


    private void initView() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Drawable newCountBackground = getResources().getDrawable(R.drawable.red_oval);
                megaPhoneCount.setBackground(newCountBackground);
                megaPhoneCount.setTextColor(Color.parseColor("#FFFFFF"));
                megaPhoneCount.setText("1");
                megaPhoneCount.setTextSize(10);

                Drawable icon = getResources().getDrawable(R.mipmap.megaphone_icon);
                ColorFilter blackFilter = new LightingColorFilter(Color.BLACK, Color.BLACK);
                icon.setColorFilter(blackFilter);

                megaPhone.setImageDrawable(icon);

//                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(megaPhone.getWidth()+10, megaPhone.getHeight()+10);
//                megaPhoneLayout.setLayoutParams(layoutParams);

                //메인banner 이미지 설정
                mainBanner.setBackgroundResource(R.mipmap.main_banner);

                beaconService.setImageResource(R.mipmap.bt_top_img);
                infoViews.get(0).setImageResource(R.mipmap.bt_a_img);
                infoViews.get(1).setImageResource(R.mipmap.bt_b_img);
                infoViews.get(2).setImageResource(R.mipmap.bt_c_img);
                infoViews.get(3).setImageResource(R.mipmap.bt_d_img);
            }
        });

    }




    /**
     * 메인 엑티비티 뒤로가기 버튼 시 물음 다이얼로그
     */
    @Override
    public void onBackPressed() {
        NormalDialogBuilder normalDialogBuilder = new NormalDialogBuilder(MainActivity.this);
        normalDialogBuilder.finishActivityNormalDialogBuilder("알림", "App을 종료하시겠습니까?", "종료", "취소");
        normalDialogBuilder.setOnFinishActivityDialogCancelListener(new NormalDialogBuilder.OnFinishActivityDialogCancelListener() {
            @Override
            public void onCancel() {
                finish();
            }
        });
    }


    /**
     * 다음 엑티비티 실행
     *
     * @param activityClass 실행될 엑티비티 클래스명
     */
    private void startNextActivity(Class activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

//    //region onClickListener 메인 엑티비티의 onClickListener
//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            int viewId = view.getId();
//            ToastMessage toastMessage = new ToastMessage(MainActivity.this);
//
//            switch (viewId) {
//                case R.id.megaPhoneLayout:
//                    toastMessage.printToast("메가폰 클릭");
//                    break;
//
//                case R.id.beaconService:
//                    startNextActivity(NotBeaconResActivity.class);
//                    break;
//                case R.id.ainfo:
//                    toastMessage.printToast("행사장 안내 버튼 클릭");
//                    break;
//
//            }
//        }
//    };
//    //endregion
}
