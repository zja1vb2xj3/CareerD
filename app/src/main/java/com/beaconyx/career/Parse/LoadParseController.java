package com.beaconyx.career.Parse;

import android.util.Log;

import com.beaconyx.career.Constant.ParseConstant;
import com.beaconyx.career.Model.NoticeModel;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdg on 2017-11-29.
 */

public class LoadParseController {
    private final String CLASSNAME = getClass().getSimpleName();

    public interface OnLoadCallBack {
        void onLoadNotice(ArrayList<NoticeModel> noticeModels);
    }

    private OnLoadCallBack onLoadCallBack;

    public void setOnLoadCallBack(OnLoadCallBack onLoadCallBack) {
        this.onLoadCallBack = onLoadCallBack;
    }

    public synchronized void load() {
        loadNotice();

    }

    private synchronized void loadNotice() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseConstant.TB_Notice.TABLENAME);

        boolean isCache = query.hasCachedResult();
        if (isCache == true) {
            query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        }

        ArrayList<NoticeModel> noticeModels = new ArrayList<>();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {

                if (e == null) {
                    for (ParseObject object : objects) {
                        String title = object.getString(ParseConstant.TB_Notice.title);
                        String content = object.getString(ParseConstant.TB_Notice.content);

                        //object.getString(ParseConstant.TB_Notice.time);

                        NoticeModel noticeModel = new NoticeModel();
                        noticeModel.setTitle(title);
                        noticeModel.setContent(content);

                        noticeModels.add(noticeModel);
                    }

                    onLoadCallBack.onLoadNotice(noticeModels);
                } else {
                    Log.i(CLASSNAME, "error");
                    e.printStackTrace();
                }
            }
        });
    }

    private synchronized void getCompany() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TB_Company_Ko");
        query.orderByAscending("CPY_IDX");
        query.setLimit(1000);

        boolean isCache = query.hasCachedResult();
        if (isCache == true) {
            query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        }

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int i = 0;
                for (ParseObject object : objects) {
                    String title = object.getString("CPY_TITLE");
                    Log.i("title", title);

                    i++;
                    Log.i("count = ", String.valueOf(i));

                }
            }
        });

    }

    private synchronized void getBeacon() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TB_Beacon_Contents_Ko");
        query.orderByAscending("BCS_IDX");
        query.setLimit(1000);

        boolean isCache = query.hasCachedResult();
        if (isCache == true) {
            query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
        }

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                int i = 0;
                for (ParseObject object : objects) {
                    String beaconId = object.getString("BCS_BEACON_ID");
                    Log.i("beaconId", beaconId);

                    i++;
                    Log.i("count = ", String.valueOf(i));

                }
            }
        });

    }


}
