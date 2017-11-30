package com.beaconyx.career.Parse;

/**
 * Created by pdg on 2017-11-29.
 */

public class LoadParseThread extends Thread {
    private LoadParseController loadParseController;

    public LoadParseThread(LoadParseController loadParseController) {
        this.loadParseController = loadParseController;
    }

    @Override
    public void run() {
        super.run();

        loadParseController.load();
    }
}
