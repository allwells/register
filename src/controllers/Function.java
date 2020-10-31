/*
 * Copyright (C) 2020 allie
 *
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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

    /**
     * ****************** @FILE ***********************************************
     */
    private FileInputStream fis = null;
    private File file;

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

    protected void resetTableScene(TextField search_field, TextField update_finame, TextField update_lname, TextField update_email, TextField update_phone, ComboBox update_position, ToggleGroup gender2, RadioButton update_male) {
        search_field.setText("");
        update_finame.setText("");
        update_lname.setText("");
        update_email.setText("");
        update_phone.setText("");
        update_position.setValue(null);
        gender2.selectToggle(update_male);

    }

    protected void getProfile(Label tab_name, Button profile_button, Button deactivate1, Button deactivate2, Button deactivate3, Button deactivate4, Button deactivate5, Button deactivate6) {
        final String activateBgColor = "-fx-background-color: #2F2B43;"
                + "-fx-border-color: #9C71A9;"
                + "-fx-border-width: 0px 0px 5px 0px;";

        tab_name.setText(profile_button.getText());
        profile_button.setStyle(activateBgColor);
        deactivate1.setStyle("");
        deactivate2.setStyle("");
        deactivate3.setStyle("");
        deactivate4.setStyle("");
        deactivate5.setStyle("");
        deactivate6.setStyle("");

    }

    protected void active(Label tab_name, Button activate0, Button deactivate1, Button deactivate2, Button deactivate3, Button deactivate4, Button deactivate5, Button deactivate6) {
        final String activateBgColor = "-fx-background-color: #2F2B43;"
                + "-fx-border-color: #9C71A9;"
                + "-fx-border-width: 0px 0px 0px 5px;";

        tab_name.setText(activate0.getText());
        activate0.setStyle(activateBgColor);
        deactivate1.setStyle("");
        deactivate2.setStyle("");
        deactivate3.setStyle("");
        deactivate4.setStyle("");
        deactivate5.setStyle("");
        deactivate6.setStyle("");
    }

    //Random Number Generation
    protected void getRandomGen(int random_number, TextField id) {
        Random random = new Random();
        random_number = random.nextInt(992467);
        id.setText("" + random_number);
    }

    protected void onDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    protected void onDragDropped(DragEvent event, ImageView image) throws FileNotFoundException {
        List<File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        image.setImage(img);
    }

    protected void setEnabled(Label label, TextField fname_txt_field, TextField lname_txt_field, TextField email_txt_field, TextField phone_txt_field, ImageView img_view, Button save_btn, Button change_btn, Button edit_btn) {
        label.setDisable(false);
        fname_txt_field.setDisable(false);
        lname_txt_field.setDisable(false);
        email_txt_field.setDisable(false);
        phone_txt_field.setDisable(false);
        img_view.setDisable(false);
        save_btn.setDisable(false);
        change_btn.setDisable(false);
        edit_btn.setDisable(true);
    }

    protected void setDisabled(Label label, TextField fname_txt_field, TextField lname_txt_field, TextField email_txt_field, TextField phone_txt_field, ImageView img_view, Button save_btn, Button change_btn, Button edit_btn) {
        label.setDisable(true);
        fname_txt_field.setDisable(true);
        lname_txt_field.setDisable(true);
        email_txt_field.setDisable(true);
        phone_txt_field.setDisable(true);
        img_view.setDisable(true);
        save_btn.setDisable(true);
        change_btn.setDisable(true);
        edit_btn.setDisable(false);
    }

    protected void onStart(Label tab_name, Button dashboard_btn, Button register_btn, Button settings_btn, Button profile_btn, Button notify_btn, Button edit_btn, Button table_btn, Pane dashboard_pane, Pane register_pane, Pane settings_pane, Pane profile_pane, Pane notify_pane, Pane table_pane) {
        active(tab_name, dashboard_btn, register_btn, settings_btn, profile_btn, notify_btn, edit_btn, table_btn);
        dashboard_pane.setVisible(true);
        register_pane.setVisible(false);
        settings_pane.setVisible(false);
        profile_pane.setVisible(false);
        notify_pane.setVisible(false);
        table_pane.setVisible(false);
    }

    protected void getRegForms(Label tab_name, Pane pane01, Pane pane02, Button user_reg_btn, Button register_btn) {
        pane01.setVisible(true);
        pane02.setVisible(false);
        tab_name.setText(register_btn.getText() + "/" + user_reg_btn.getText().toLowerCase());
    }

    //Import Image
    protected void importImage(ImageView imageView, Label labelView, String str) throws IOException {
        FileChooser chooser = new FileChooser();

//        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG Files (*.jpg)", "*.JPG");
//        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG Files (*.png)", "*.PNG");
//        FileChooser.ExtensionFilter extFilterGIF = new FileChooser.ExtensionFilter("GIF Files (*.gif)", "*.GIF");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("*.IMAGE", "*.jpg", "*.jpeg", "*.png", "*.gif");

//        chooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterGIF);
        chooser.getExtensionFilters().addAll(filter);

        file = chooser.showOpenDialog(null);

        BufferedImage bimg = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bimg, null);
        imageView.setImage(image);

        if (imageView != null) {
            labelView.setText("");
        } else {
            labelView.setText(str);
        }
    }

}
