package com.beaconyx.career.Push;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by pdg on 2017-11-21.
 */

public class MyFirebaseInstanceIDService  extends FirebaseInstanceIdService{

    private final String CLASSNAME = getClass().getSimpleName();
    @Override
    public void onTokenRefresh() {
        Log.i(CLASSNAME, "onTokenRefresh");
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();


        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
}
