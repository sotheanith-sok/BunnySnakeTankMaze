package com.agilewhisperers.bunnysnaketankmaze.entities;

import com.agilewhisperers.bunnysnaketankmaze.Utilities.Utils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public class AmmoBar extends ProgressBar {
    public AmmoBar(int width, int height) {
        super(0f, 1f, 0.001f, false, new ProgressBarStyle());
        getStyle().background = Utils.getColoredDrawable(width, height, Color.GRAY);
        getStyle().knob = Utils.getColoredDrawable(0, height, Color.YELLOW);
        getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.YELLOW);
        setWidth(width);
        setHeight(height);
        setAnimateDuration(0.0f);
        setValue(1f);
        setAnimateDuration(0.25f);
    }
}
