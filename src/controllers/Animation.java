/*
 * Copyright (C) 2020 allie
 *
 */
package controllers;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author allie
 */
public class Animation {

    protected void makeFadeInTransition(Pane scene) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(.3));
        fadeTransition.setNode(scene);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    protected void fadeIn(Label msg) {
        FadeTransition ft = new FadeTransition(Duration.seconds(.5), msg);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }

    protected void fadeOut(Label msg) {
        FadeTransition ft = new FadeTransition(Duration.seconds(.5), msg);
        ft.setFromValue(1);
        ft.setToValue(0.0);
        ft.play();
    }

}
