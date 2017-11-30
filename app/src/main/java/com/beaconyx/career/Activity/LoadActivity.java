package com.beaconyx.career.Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

import com.beaconyx.career.Constant.IntentKeyConstant;
import com.beaconyx.career.DialogBuilder.NormalDialogBuilder;
import com.beaconyx.career.Model.NoticeModel;
import com.beaconyx.career.Parse.LoadParseController;
import com.beaconyx.career.Parse.LoadParseThread;
import com.beaconyx.career.R;

import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends Activity {
    private final String CLASSNAME = getClass().getSimpleName();
    private LoadParseController loadParseController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        loadParseController = new LoadParseController();
        loadParseController.setOnLoadCallBack(onLoadCallBack);

        checkPushData();

    }

    LoadParseController.OnLoadCallBack onLoadCallBack = new LoadParseController.OnLoadCallBack() {
        @Override
        public void onLoadNotice(ArrayList<NoticeModel> noticeModels) {
            for(NoticeModel model : noticeModels){
                Log.i("content", model.getContent());
            }
        }
        //            startNextActivity(MainActivity.class);
    };


    private void checkPushData() {
        final String pushMessage = getIntent().getStringExtra(IntentKeyConstant.AppPushIntentKey.PUSH_DATA_KEY);

        if (pushMessage != null) {
            NormalDialogBuilder normalDialogBuilder = new NormalDialogBuilder(LoadActivity.this);

            normalDialogBuilder.appPushNotiNormalDialogBuilder(
                    "이 메세지는 공지사항에서 확인 하실 수 있습니다."
                    , pushMessage
                    , "확인"
            );

            normalDialogBuilder.setOnPushDialogCancelListener(new NormalDialogBuilder.OnPushDialogCancelListener() {
                @Override
                public void onCancel() {
                    getParseData();
                }
            });

        } else {
            Log.i(CLASSNAME, "push 데이터 없음");
            getParseData();
        }
    }

    private void getParseData() {

        LoadParseThread thread = new LoadParseThread(loadParseController);
        thread.start();
    }

    private void startNextActivity(Class nextActivityName) {
        Intent intent = new Intent(LoadActivity.this, nextActivityName);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }
}
