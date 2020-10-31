/*
 * Copyright (C) 2020 allie
 *
 */
package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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

    protected void makeFadeOutTransition(Pane pane, GetScene scene) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(.6));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished((ActionEvent event) -> {
            scene.getNextScene(pane);
            pane.setVisible(false);
        });

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

    protected void changePasswordAnimation(Button chng_btn, Button save, Button edit, Pane pane) {
        chng_btn.setDisable(true);

        Timeline timeline = new Timeline();

        KeyValue saveKV = new KeyValue(save.translateYProperty(), 109, Interpolator.EASE_IN);
        KeyFrame saveKF = new KeyFrame(Duration.seconds(1), saveKV);
        timeline.getKeyFrames().add(saveKF);

        KeyValue editKV = new KeyValue(edit.translateYProperty(), 109, Interpolator.EASE_IN);
        KeyFrame editKF = new KeyFrame(Duration.seconds(1), editKV);
        timeline.getKeyFrames().add(editKF);

        timeline.setOnFinished(evt -> {
            pane.setVisible(true);
            this.makeFadeInTransition(pane);
        });

        timeline.play();
    }

    protected void returnBackToNormalOnSave(Button chng_btn, Button save, Button edit, Pane pane) {
        pane.setVisible(false);
        Timeline timeline = new Timeline();

        KeyValue saveKV = new KeyValue(save.translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame saveKF = new KeyFrame(Duration.seconds(1), saveKV);
        timeline.getKeyFrames().add(saveKF);

        KeyValue editKV = new KeyValue(edit.translateYProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame editKF = new KeyFrame(Duration.seconds(1), editKV);
        timeline.getKeyFrames().add(editKF);

        timeline.play();

        chng_btn.setDisable(true);
    }

}
