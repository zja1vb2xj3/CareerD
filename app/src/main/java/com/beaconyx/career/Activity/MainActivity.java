package com.beaconyx.career.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
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
import com.beaconyx.career.Color.ColorManager;
import com.beaconyx.career.DialogBuilder.NormalDialogBuilder;
import com.beaconyx.career.R;
import com.beaconyx.career.Toast.ToastMessage;

import java.util.List;

import butterknife.BindColor;
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

    @BindView(R.id.countText)
    TextView countText;

    private ColorManager colorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        colorManager = new ColorManager(getResources());

        initView();

        megaPhoneLayout.setOnClickListener(v -> megaPhoneLayoutClick());
        beaconService.setOnClickListener(v -> beaconServiceButtonClick());
    }

    private void beaconServiceButtonClick() {
        startNextActivity(NotBeaconResActivity.class);
    }

    private void megaPhoneLayoutClick() {
        startNextActivity(NoticeActivity.class);
    }

    private void initMegaphoneLayout(int count){
        //region countTextView
        final int countTextViewSize = 40;
        FrameLayout.LayoutParams countParams = new FrameLayout.LayoutParams(countTextViewSize, countTextViewSize);
        countParams.gravity = Gravity.RIGHT;
        countParams.setMargins(0,10,0, 0);
        countText.setLayoutParams(countParams);

        Drawable countDrawable = getResources().getDrawable(R.drawable.red_oval);
        countText.setBackground(countDrawable);
        countText.setTextColor(colorManager.getWhite());
        countText.setText(String.valueOf(count));
        countText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        countText.setTypeface(countText.getTypeface(), Typeface.BOLD);
        countText.setTextSize(11);
    //endregion

        //region megaPhone
        final int megaPhoneSize = 80;
        FrameLayout.LayoutParams megaPhoneParams = new FrameLayout.LayoutParams(megaPhoneSize, megaPhoneSize);
        megaPhoneParams.gravity = Gravity.BOTTOM | Gravity.CENTER;
        megaPhone.setLayoutParams(megaPhoneParams);

        Drawable icon = getResources().getDrawable(R.mipmap.megaphone_icon);
        ColorFilter iconColor = new LightingColorFilter(colorManager.getRepresent(), colorManager.getRepresent());
        icon.setColorFilter(iconColor);

        megaPhone.setImageDrawable(icon);

        //endregion

        //region layout
        megaPhoneLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                megaPhoneLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                final int megaPhoneLayoutPlusValue = 20;
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(megaPhoneLayout.getWidth() + megaPhoneLayoutPlusValue, megaPhoneLayout.getHeight() + megaPhoneLayoutPlusValue);
                layoutParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
                layoutParams.setMargins(0,0,20,20);
                megaPhoneLayout.setLayoutParams(layoutParams);
            }
        });

        //endregion
    }


    private void initView() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                initMegaphoneLayout(2);

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


}
