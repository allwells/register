/*
 * Copyright (C) 2020 allie
 *
 */
package controllers;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author allie
 */
public class Function {

    /**
     * ****************** @DOUBLE *********************************************
     */
    private double xOffset;
    private double yOffset;

    protected void close() {
        System.exit(0);
    }

    protected void minimize(AnchorPane anchor) {
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    protected void changePositionOnDrag(Parent root, Stage stage) {
        root.setOnMousePressed((MouseEvent evt)
                -> {
            xOffset = evt.getSceneX();
            yOffset = evt.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent evt)
                -> {
            stage.setX(evt.getScreenX() - xOffset);
            stage.setY(evt.getScreenY() - yOffset);
        });
    }

    protected void CheckOnKeyTyped(TextField code_input, Button verify_btn) {
        if (code_input.getText().length() == 6) {
            verify_btn.setDisable(false);
        } else {
            verify_btn.setDisable(true);
        }
    }

}
