/*
 * Copyright (C) 2020 allie
 *
 *
 * @author allie
 *
 */
package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DashboardController implements Initializable {

    /**
     * ****************** @STRINGS ********************************************
     */
    private final String imgLabel = "Click here or drag and drop image here";
    private final String imgUpdate = "Click here to upload image";

    /**
     * ****************** @PANES **********************************************
     */
    @FXML
    public AnchorPane anchor;
    @FXML
    private Pane reg_type_pane;
    @FXML
    private Pane profile_pane;
    @FXML
    private Pane settings_pane;
    @FXML
    private Pane register_pane;
    @FXML
    private Pane dashboard_pane;
    @FXML
    private Pane notify_pane;
    @FXML
    private Pane staff_reg_pane;
    @FXML
    private Pane admin_reg_pane;
    @FXML
    private Pane password_pane;
    @FXML
    private Pane table_pane;
    @FXML
    private Pane edit_pane;

    /**
     * ****************** @BUTTONS ********************************************
     */
    @FXML
    private Button dashboard_btn;
    @FXML
    private Button register_btn;
    @FXML
    private Button settings_btn;
    @FXML
    private Button profile_btn;
    @FXML
    private Button notify_btn;
    @FXML
    private Button admin_reg_btn;
    @FXML
    private Button staff_reg_btn;
    @FXML
    private Button edit_profile_btn;
    @FXML
    private Button save_profile_btn;
    @FXML
    private Button change_password_btn;
    @FXML
    private Button table_btn;
    @FXML
    private Button edit_btn;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ToggleGroup gender2;
    @FXML
    private RadioButton staff_gender_male;
    @FXML
    private RadioButton staff_gender_female;
    @FXML
    private RadioButton update_male;
    @FXML
    private RadioButton update_female;

    /**
     * ****************** @TEXTFIELD *****************************************
     */
    @FXML
    private TextField staff_fname;
    @FXML
    private TextField staff_phone;
    @FXML
    private TextField staff_email;
    @FXML
    private TextField staff_lname;
    @FXML
    private TextField admin_lname;
    @FXML
    private TextField admin_fname;
    @FXML
    private TextField admin_email;
    @FXML
    private TextField admin_profile_fname;
    @FXML
    private TextField admin_profile_lname;
    @FXML
    private TextField admin_profile_email;
    @FXML
    private TextField admin_username;
    @FXML
    private TextField admin_profile_username;
    @FXML
    private TextField update_finame;
    @FXML
    private TextField update_lname;
    @FXML
    private TextField update_phone;
    @FXML
    private TextField update_email;
    @FXML
    private TextField search_field;
    @FXML
    private TextField search_bar;

    /**
     * ****************** @PASSWORDFIELD **************************************
     */
    @FXML
    private PasswordField admin_password;
    @FXML
    private PasswordField new_password;
    @FXML
    private PasswordField old_password;

    /**
     * ****************** @COMBOBOX *******************************************
     */
    @FXML
    private ComboBox staff_position;
    @FXML
    private ComboBox update_position;

    /**
     * ****************** @IMAGEVIEW *****************************************
     */
    @FXML
    private ImageView staff_image;
    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView admin_image;
    @FXML
    private ImageView update_image;

    /**
     * ****************** @LABEL **********************************************
     */
    @FXML
    private Label tab_name;
    @FXML
    private Label image_label;
    @FXML
    private Label image_label_update;
    @FXML
    private Label staff_image_label;
    @FXML
    private Label admin_image_label;

    /**
     * ****************** @TABLEVIEW *****************************************
     */
    @FXML
    private TableView<ModelTable> table_view;
    @FXML
    private TableColumn<ModelTable, String> col_id;
    @FXML
    private TableColumn<ModelTable, String> col_fname;
    @FXML
    private TableColumn<ModelTable, String> col_lname;
    @FXML
    private TableColumn<ModelTable, String> col_email;
    @FXML
    private TableColumn<ModelTable, String> col_position;
    @FXML
    private TableColumn<ModelTable, String> col_gender;
    @FXML
    private TableColumn<?, ?> col_phone;

    /**
     * ****************** @CLASSES *******************************************
     */
    private final Animation animation;
    private final Authentication authentication;
    private final Function function;
    private final GetScene scene;

    public DashboardController() {
        this.animation = new Animation();
        this.authentication = new Authentication();
        this.function = new Function();
        this.scene = new GetScene();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        function.active(tab_name, dashboard_btn, register_btn, settings_btn, profile_btn, notify_btn, edit_btn, table_btn);
        function.onStart(tab_name, dashboard_btn, register_btn, settings_btn, profile_btn, notify_btn, edit_btn, table_btn, dashboard_pane, register_pane, settings_pane, profile_pane, notify_pane, table_pane);

        staff_position.getItems().add("Accountant");
        staff_position.getItems().add("Environmental Manager");
        staff_position.getItems().add("Marketing Manager");
        staff_position.getItems().add("Office Manager");
        staff_position.getItems().add("Operations Manager");
        staff_position.getItems().add("Professional Staff");
        staff_position.getItems().add("Receptionist");

        update_position.getItems().add("Accountant");
        update_position.getItems().add("Environmental Manager");
        update_position.getItems().add("Marketing Manager");
        update_position.getItems().add("Office Manager");
        update_position.getItems().add("Operations Manager");
        update_position.getItems().add("Professional Staff");
        update_position.getItems().add("Receptionist");
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void logout(ActionEvent event) {
        scene.nextScene(event, "login");
    }

    @FXML
    private void getProfile(ActionEvent event) {
        function.getProfile(tab_name, profile_btn, dashboard_btn, register_btn, settings_btn, notify_btn, edit_btn, table_btn);
        scene.disposeCurrentScene(dashboard_pane);
        scene.disposeCurrentScene(settings_pane);
        scene.disposeCurrentScene(register_pane);
        scene.disposeCurrentScene(notify_pane);
        scene.disposeCurrentScene(edit_pane);
        scene.disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        profileImage.setDisable(true);
        scene.getNextScene(profile_pane);
        function.resetTableScene(search_field, update_finame, update_lname, update_email, update_phone, update_position, gender2, update_male);
//        authentication.showProfileDetails(admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, old_password, profileImage);
    }

    @FXML
    private void getNotiifications(ActionEvent event) {
        function.getProfile(tab_name, notify_btn, dashboard_btn, register_btn, settings_btn, profile_btn, edit_btn, table_btn);
        scene.disposeCurrentScene(dashboard_pane);
        scene.disposeCurrentScene(settings_pane);
        scene.disposeCurrentScene(register_pane);
        scene.disposeCurrentScene(profile_pane);
        scene.disposeCurrentScene(edit_pane);
        scene.disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        scene.getNextScene(notify_pane);

        function.setDisabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
//        authentication.onLoadTable(table_view, col_id, col_fname, col_lname, col_email, col_phone, col_position, col_gender);
    }

    @FXML
    private void getDashboardPane(ActionEvent event) {
        function.active(tab_name, dashboard_btn, register_btn, settings_btn, profile_btn, notify_btn, edit_btn, table_btn);
        scene.disposeCurrentScene(profile_pane);
        scene.disposeCurrentScene(settings_pane);
        scene.disposeCurrentScene(register_pane);
        scene.disposeCurrentScene(notify_pane);
        scene.disposeCurrentScene(edit_pane);
        scene.disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        scene.getNextScene(dashboard_pane);

        function.setDisabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
//        authentication.onLoadTable(table_view, col_id, col_fname, col_lname, col_email, col_phone, col_position, col_gender);
    }

    @FXML
    private void getEditScene(ActionEvent event) {
        function.active(tab_name, edit_btn, dashboard_btn, register_btn, profile_btn, notify_btn, table_btn, settings_btn);
        scene.disposeCurrentScene(profile_pane);
        scene.disposeCurrentScene(dashboard_pane);
        scene.disposeCurrentScene(register_pane);
        scene.disposeCurrentScene(notify_pane);
        scene.disposeCurrentScene(table_pane);
        scene.disposeCurrentScene(settings_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        scene.getNextScene(edit_pane);

        function.setDisabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
    }

    @FXML
    private void getTable(ActionEvent event) {
//        authentication.onLoadTable(table_view, col_id, col_fname, col_lname, col_email, col_phone, col_position, col_gender);
        function.active(tab_name, table_btn, dashboard_btn, register_btn, profile_btn, notify_btn, edit_btn, settings_btn);
        scene.disposeCurrentScene(profile_pane);
        scene.disposeCurrentScene(dashboard_pane);
        scene.disposeCurrentScene(register_pane);
        scene.disposeCurrentScene(notify_pane);
        scene.disposeCurrentScene(edit_pane);
        scene.disposeCurrentScene(settings_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        scene.getNextScene(table_pane);

        function.setDisabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
    }

    @FXML
    private void getRegisterPane(ActionEvent event) {
        function.active(tab_name, register_btn, settings_btn, dashboard_btn, profile_btn, notify_btn, edit_btn, table_btn);
        scene.disposeCurrentScene(profile_pane);
        scene.disposeCurrentScene(settings_pane);
        scene.disposeCurrentScene(dashboard_pane);
        scene.disposeCurrentScene(notify_pane);
        scene.disposeCurrentScene(edit_pane);
        scene.disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        scene.getNextScene(register_pane);

        function.setDisabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
//        authentication.onLoadTable(table_view, col_id, col_fname, col_lname, col_email, col_phone, col_position, col_gender);
    }

    @FXML
    private void getSettingPane(ActionEvent event) {
        function.active(tab_name, settings_btn, dashboard_btn, register_btn, profile_btn, notify_btn, edit_btn, table_btn);
        scene.disposeCurrentScene(profile_pane);
        scene.disposeCurrentScene(dashboard_pane);
        scene.disposeCurrentScene(register_pane);
        scene.disposeCurrentScene(notify_pane);
        scene.disposeCurrentScene(edit_pane);
        scene.disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        scene.getNextScene(settings_pane);

        function.setDisabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
//        authentication.onLoadTable(table_view, col_id, col_fname, col_lname, col_email, col_phone, col_position, col_gender);
    }

    @FXML
    private void register(ActionEvent event) {
        /**
         * ****************** @ADMIN-REGISTRATION *****************************
         */
//        authentication.AdminRegisteration(tab_name, register_btn, admin_reg_btn, admin_fname, admin_lname, admin_email, admin_username, admin_password, admin_image);

        /**
         * ****************** @STAFF-REGISTRATION *****************************
         */
//        authentication.StaffRegistration(tab_name, register_btn, staff_reg_btn, staff_fname, staff_lname, staff_email, staff_phone, staff_position, gender, staff_image, staff_gender_male);
    }

    @FXML
    private void getAdminRegForm(ActionEvent event) {
        function.getRegForms(tab_name, admin_reg_pane, reg_type_pane, admin_reg_btn, register_btn);
    }

    @FXML
    private void getStaffRegForm(ActionEvent event) {
        function.getRegForms(tab_name, staff_reg_pane, reg_type_pane, staff_reg_btn, register_btn);
    }

    @FXML
    private void staffBackToRegType(ActionEvent event) {
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        tab_name.setText(register_btn.getText());
        reg_type_pane.setVisible(true);

        staff_fname.setText("");
        staff_lname.setText("");
        staff_email.setText("");
        staff_phone.setText("");
        staff_position.setValue(null);
        gender.selectToggle(staff_gender_male);
        staff_image.setImage(null);
    }

    @FXML
    private void adminBackToRegType(ActionEvent event) {
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        tab_name.setText(register_btn.getText());
        reg_type_pane.setVisible(true);

        admin_fname.setText("");
        admin_lname.setText("");
        admin_email.setText("");
        admin_username.setText("");
        admin_password.setText("");
        admin_image.setImage(null);
    }

    @FXML
    private void editProfile(ActionEvent event) {
        function.setEnabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
        old_password.setDisable(false);
        new_password.setDisable(false);
    }

    @FXML
    private void saveProfile(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Saved!", "Save success!", 1);
        System.out.println("Saved!");
        function.setDisabled(image_label, admin_profile_fname, admin_profile_lname, admin_profile_email, admin_profile_username, profileImage, save_profile_btn, change_password_btn, edit_profile_btn);
        old_password.setDisable(true);
        new_password.setDisable(true);
        animation.returnBackToNormalOnSave(change_password_btn, save_profile_btn, edit_profile_btn, password_pane);
    }

    @FXML
    private void getAdminImage(MouseEvent event) throws IOException {
        function.importImage(admin_image, tab_name, imgLabel);
    }

    @FXML
    private void onDragAdminImageOver(DragEvent event) {
        function.onDragOver(event);
    }

    @FXML
    private void onDragAdminImageDropped(DragEvent event) throws FileNotFoundException {
        function.onDragDropped(event, admin_image);
    }

    @FXML
    private void getStaffImage(MouseEvent event) throws IOException {
        function.importImage(staff_image, staff_image_label, imgLabel);
    }

    @FXML
    private void onDragStaffImageOver(DragEvent event) {
        function.onDragOver(event);
    }

    @FXML
    private void onDragStaffImageDropped(DragEvent event) throws FileNotFoundException {
        function.onDragDropped(event, staff_image);
    }

    @FXML
    private void onDragProfileImageOver(DragEvent event) {
        function.onDragOver(event);
    }

    @FXML
    private void onDragProfileImageDropped(DragEvent event) throws FileNotFoundException {
        function.onDragDropped(event, profileImage);
    }

    @FXML
    private void getProfileImage(MouseEvent event) throws IOException {
        if (!image_label.isDisabled() && !profileImage.isDisabled()) {
            function.importImage(profileImage, image_label, imgLabel);
        }
    }

    @FXML
    private void showPasswordField(ActionEvent event) {
        animation.changePasswordAnimation(change_password_btn, save_profile_btn, edit_profile_btn, password_pane);
    }

    @FXML
    private void onUpdate(ActionEvent event) {
//        authentication.Update(search_field, update_finame, update_lname, update_email, update_phone, update_position, gender2, update_image);
        search_field.setText("");
        update_finame.setText("");
        update_lname.setText("");
        update_email.setText("");
        update_phone.setText("");
        update_position.setValue(null);
        gender2.selectToggle(update_male);
        update_image.setImage(null);
    }

    @FXML
    private void onDelete(ActionEvent event) {
//        authentication.Delete(search_field, update_email, update_phone);
        search_field.setText("");
        update_finame.setText("");
        update_lname.setText("");
        update_email.setText("");
        update_phone.setText("");
        update_position.setValue(null);
        gender2.selectToggle(update_male);
        update_image.setImage(null);
    }

    @FXML
    private void searchTable(ActionEvent event) {
//        authentication.onLoadTable(table_view, col_id, col_fname, col_lname, col_email, col_phone, col_position, col_gender);
    }

    @FXML
    private void getUpdateImage(MouseEvent event) throws IOException {
        function.importImage(update_image, image_label_update, imgUpdate);
    }

    @FXML
    private void uploadData(ActionEvent event) {
//        authentication.UploadData(search_field, update_finame, update_lname, update_email, update_phone, update_position, gender2, update_image);
    }

    @FXML
    private void onReset(ActionEvent event) {
        search_field.setDisable(false);
        search_field.setText("");
        update_finame.setText("");
        update_lname.setText("");
        update_email.setText("");
        update_phone.setText("");
        update_position.setValue(null);
        gender2.selectToggle(update_male);
        update_image.setImage(null);
    }

}


/*
*************   TO-DO List   **************
    -Search bar
    -Retrive image
 */
