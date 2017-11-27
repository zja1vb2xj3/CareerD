package com.beaconyx.career.Application;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseInstallation;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by pdg on 2017-11-20.
 */

public class AppDelegate extends Application implements BeaconConsumer {

    private BeaconManager beaconManager;
    private Region region;


    @Override
    public void onCreate() {
        super.onCreate();

        parseInit();
        beaconInit();
    }


    private void parseInit() {
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("YesdexaAZx4r86eeoyIwwGfdfOLeT2CnQKFcQ1")
                .clientKey("YesdexbeaconyxSwvy38GBFH6i1MZ2JGxfYkt2j4gaROGxy")
                .server("http://www.beaconyx.co.kr:1337/parse")   // '/' important after 'parse'
                .build());
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    private void beaconInit() {
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.setAndroidLScanningDisabled(true);
        beaconManager.setBackgroundScanPeriod(1500);
        beaconManager.setBackgroundBetweenScanPeriod(1500);

        beaconManager.setForegroundScanPeriod(1000);
        beaconManager.setForegroundBetweenScanPeriod(1000);

        beaconManager.bind(AppDelegate.this);


        region = new Region("myRangingUniqueId", Identifier.parse("a0fabefc-b1f5-4836-8328-7c5412fff9c4"), null, null);

    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() != 0) {
                    ArrayList<Beacon> beaconArrayList = new ArrayList<>(beacons);
                    Collections.sort(beaconArrayList, new NoDescCompare());

                    int beaconRssi = beaconArrayList.get(0).getRssi();

                    String beaconMajorStr = beaconArrayList.get(0).getId2().toString();
                    String beaconMinorStr = beaconArrayList.get(0).getId3().toString();
                    String beaconId = "m" + beaconMajorStr + "_" + beaconMinorStr;


                    Log.i("beaconid : ", beaconId);
                }
            }
        });
    }

    public synchronized void startBeaconThread() {
        try {
            beaconManager.startRangingBeaconsInRegion(region);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void stopBeaconThread() {
        try {
            beaconManager.stopRangingBeaconsInRegion(region);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class NoDescCompare implements Comparator<Beacon> {

        /**
         * 내림차순(DESC)
         */
        @Override
        public int compare(Beacon arg0, Beacon arg1) {
            // TODO Auto-generated method stub
            return arg0.getRssi() > arg1.getRssi() ? -1 : arg0.getRssi() < arg1.getRssi() ? 1 : 0;
        }

    }
}
