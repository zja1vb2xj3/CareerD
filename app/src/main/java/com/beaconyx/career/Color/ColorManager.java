package com.beaconyx.career.Color;

import android.content.res.Resources;

import com.beaconyx.career.R;

/**
 * Created by pdg on 2017-11-27.
 */

public class ColorManager {
    private Resources res;

    private int represent;
    private int red;
    private int white;
    private int black;
    private int lightGrey;
    private int rightArrow;
    public ColorManager(Resources resources) {
        res = resources;
    }

    public int getRightArrow() {
        rightArrow = res.getColor(R.color.colorRightArrow);
        return rightArrow;
    }

    public int getBlack() {
        black = res.getColor(R.color.colorBlack);
        return black;
    }

    public int getWhite() {
        white = res.getColor(R.color.colorWhite);
        return white;
    }

    public int getRed() {
        red = res.getColor(R.color.colorRed);
        return red;
    }

    public int getRepresent() {
        represent = res.getColor(R.color.colorRepresent);
        return represent;
    }

    public int getLightGrey() {
        represent = res.getColor(R.color.colorLightGrey);
        return lightGrey;
    }
}
