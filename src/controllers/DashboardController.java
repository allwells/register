/*
 * Copyright (C) 2020 allie
 *
 *
 * @author allie
 * 
 */
package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import register.Connector;



public class DashboardController implements Initializable {
    
    LoginController log = new LoginController();
    FileInputStream fis;
    File file;
    
//********************  CONNECTION  ******************************************\\
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
//********************  DOUBLE  **********************************************\\
    private double xOffset;
    private double yOffset;
    
//********************  STRINGS  *********************************************\\
    private final String imgLabel = "Click here or drag and drop image here";
    private final String imgUpdate = "Click here to upload image";

//********************  PANES  ***********************************************\\
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
    
//********************  BUTTONS  *********************************************\\
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
    
//********************  TEXT FIELD  ******************************************\\
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
    
//********************  PASSWORD FIELD  **************************************\\
    @FXML
    private PasswordField admin_password;
    @FXML
    private PasswordField new_password;
    @FXML
    private PasswordField old_password;
    
//********************  COMBOBOX  ********************************************\\
    @FXML
    private ComboBox staff_position;
    @FXML
    private ComboBox update_position;
    
//********************  IMAGE VIEW  ******************************************\\
    @FXML
    private ImageView staff_image;
    @FXML
    private ImageView profileImage;
    @FXML
    private ImageView admin_image;
    @FXML
    private ImageView update_image;
    
//********************  LABEL  ***********************************************\\
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
    
//********************  TABLE VIEW  ******************************************\\
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
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SQLDB();
        active(dashboard_btn,
                register_btn,
                settings_btn,
                profile_btn,
                notify_btn,
                edit_btn,
                table_btn
        );
        onStart();
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
    
    private void SQLDB() {
        con = Connector.getConnection();
    }
    
    //Random Number Generation
    private void getRandomGen(int random_number, TextField id){
        Random random=new Random();
        random_number=random.nextInt(992467);
        id.setText(""+random_number);
    }
    
    //Import Image
    private void importImage(ImageView imageView, Label labelView, String str) throws IOException {
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
        
        if (null != imageView) {
            labelView.setText("");
        } else {
            labelView.setText(str);
        }
    }
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    private void onLoadTable() {
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM `staff`");
            
            while (rs.next()) {
                oblist.add(new ModelTable(rs.getString("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("position"),
                        rs.getString("gender")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        table_view.setItems(oblist);
    }
    
    private void onDragOver(DragEvent event) {
        if(event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    
    private void onDragDropped(DragEvent event, ImageView image) throws FileNotFoundException {
        List <File> files = event.getDragboard().getFiles();
        Image img = new Image(new FileInputStream(files.get(0)));
        image.setImage(img);
    }
    
    private void setEnabled(Label label,
            TextField fname_txt_field,
            TextField lname_txt_field,
            TextField email_txt_field,
            TextField phone_txt_field,
            ImageView img_view,
            Button save_btn,
            Button change_btn,
            Button edit_btn
    ) {
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
    
    private void setDisabled(Label label,
            TextField fname_txt_field,
            TextField lname_txt_field,
            TextField email_txt_field,
            TextField phone_txt_field,
            ImageView img_view,
            Button save_btn,
            Button change_btn,
            Button edit_btn
    ) {
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
    
    private void active(Button activate0,
            Button deactivate1,
            Button deactivate2,
            Button deactivate3,
            Button deactivate4,
            Button deactivate5,
            Button deactivate6
    ) {
        String activateBgColor = "-fx-background-color: #2F2B43;"
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
    
    private void getProfile(Button profile_button,
            Button deactivate1,
            Button deactivate2,
            Button deactivate3,
            Button deactivate4,
            Button deactivate5,
            Button deactivate6
    ) {
        String activateBgColor = "-fx-background-color: #2F2B43;"
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
    
    private void getNextScene(Pane pane) {
        pane.setVisible(true);
        makeFadeInTransitionPane(pane);
    }
    
    private void disposeCurrentScene(Pane pane) {
        pane.setVisible(false);
    }
    
    private void makeFadeInTransitionPane(Pane scene) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(.3));
        fadeTransition.setNode(scene);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    
    private void makeFadeOutTransitionPane(Pane scene) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(.6));
        fadeTransition.setNode(scene);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        
        fadeTransition.setOnFinished((ActionEvent event) -> {
            getNextScene(scene);
            scene.setVisible(false);
        });
        
        fadeTransition.play();
    }
    
    private void changePasswordAnimation( Button chng_btn, Button save,
            Button edit,
            Pane pane)
    {
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
            makeFadeInTransitionPane(pane);
        });
        
        timeline.play();
    }
    
    private void returnBackToNormalOnSave( Button chng_btn, Button save,
            Button edit,
            Pane pane)
    {
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
    
    private void resetTableScene() {
        search_field.setText("");
        update_finame.setText("");
        update_lname.setText("");
        update_email.setText("");
        update_phone.setText("");
        update_position.setValue(null);
        gender2.selectToggle(update_male);
        
    }
    
    private void onStart() {
        active(dashboard_btn,
                register_btn,
                settings_btn,
                profile_btn,
                notify_btn,
                edit_btn,
                table_btn
        );
        dashboard_pane.setVisible(true);
        register_pane.setVisible(false);
        settings_pane.setVisible(false);
        profile_pane.setVisible(false);
        notify_pane.setVisible(false);
        table_pane.setVisible(false);
    }
    
    private void getRegForms(Pane pane01, Pane pane02, Button btn) {
        pane01.setVisible(true);
        pane02.setVisible(false);
        tab_name.setText(register_btn.getText()+"/"+btn.getText().toLowerCase());
    }
    
    private void changePositionOnDrag(Parent root, Stage stage) {
        root.setOnMousePressed((MouseEvent evt) -> 
        {
            xOffset = evt.getSceneX();
            yOffset = evt.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent evt) -> 
        {
            stage.setX(evt.getScreenX() - xOffset);
            stage.setY(evt.getScreenY() - yOffset);
        });
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            changePositionOnDrag(root, stage);
            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getProfile(ActionEvent event) {
        String name = "newadmin";
        String pass = "newadmin";
        String sql = "SELECT * FROM admin WHERE username = '"+name+"' and password = '"+pass+"'";
        getProfile(profile_btn,
                dashboard_btn,
                register_btn,
                settings_btn,
                notify_btn,
                edit_btn,
                table_btn
        );
        disposeCurrentScene(dashboard_pane);
        disposeCurrentScene(settings_pane);
        disposeCurrentScene(register_pane);
        disposeCurrentScene(notify_pane);
        disposeCurrentScene(edit_pane);
        disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        profileImage.setDisable(true);
        getNextScene(profile_pane);
        resetTableScene();
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                admin_profile_fname.setText(rs.getString("fname"));
                admin_profile_lname.setText(rs.getString("lname"));
                admin_profile_email.setText(rs.getString("email"));
                admin_profile_username.setText(rs.getString("username"));
                old_password.setText(rs.getString("password"));
//                profileImage.setImage((Image) rs.getBlob("image"));
            } else {
                JOptionPane.showMessageDialog(null, "Error 449: Database connection error.", "Error Message", 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getNotiifications(ActionEvent event) {
        getProfile(notify_btn,
                dashboard_btn,
                register_btn,
                settings_btn,
                profile_btn,
                edit_btn,
                table_btn
        );
        disposeCurrentScene(dashboard_pane);
        disposeCurrentScene(settings_pane);
        disposeCurrentScene(register_pane);
        disposeCurrentScene(profile_pane);
        disposeCurrentScene(edit_pane);
        disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        getNextScene(notify_pane);
        
        setDisabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
        resetTableScene();
    }

    @FXML
    private void getDashboardPane(ActionEvent event) {
        active(dashboard_btn,
                register_btn,
                settings_btn,
                profile_btn,
                notify_btn,
                edit_btn,
                table_btn
        );
        disposeCurrentScene(profile_pane);
        disposeCurrentScene(settings_pane);
        disposeCurrentScene(register_pane);
        disposeCurrentScene(notify_pane);
        disposeCurrentScene(edit_pane);
        disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        getNextScene(dashboard_pane);
        
        setDisabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
        resetTableScene();
    }
    
    @FXML
    private void getEditScene(ActionEvent event) {
        active(edit_btn,
                dashboard_btn,
                register_btn,
                profile_btn,
                notify_btn,
                table_btn,
                settings_btn
        );
        disposeCurrentScene(profile_pane);
        disposeCurrentScene(dashboard_pane);
        disposeCurrentScene(register_pane);
        disposeCurrentScene(notify_pane);
        disposeCurrentScene(table_pane);
        disposeCurrentScene(settings_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        getNextScene(edit_pane);
        
        setDisabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
    }
    
    @FXML
    private void getTable(ActionEvent event) {
        onLoadTable();
        active(table_btn,
                dashboard_btn,
                register_btn,
                profile_btn,
                notify_btn,
                edit_btn,
                settings_btn
        );
        disposeCurrentScene(profile_pane);
        disposeCurrentScene(dashboard_pane);
        disposeCurrentScene(register_pane);
        disposeCurrentScene(notify_pane);
        disposeCurrentScene(edit_pane);
        disposeCurrentScene(settings_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        getNextScene(table_pane);
        
        setDisabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
    }

    @FXML
    private void getRegisterPane(ActionEvent event) {
        active(register_btn,
                settings_btn,
                dashboard_btn,
                profile_btn,
                notify_btn,
                edit_btn,
                table_btn
        );
        disposeCurrentScene(profile_pane);
        disposeCurrentScene(settings_pane);
        disposeCurrentScene(dashboard_pane);
        disposeCurrentScene(notify_pane);
        disposeCurrentScene(edit_pane);
        disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        getNextScene(register_pane);
        
        setDisabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
        resetTableScene();
    }

    @FXML
    private void getSettingPane(ActionEvent event) {
        active(settings_btn,
                dashboard_btn,
                register_btn,
                profile_btn,
                notify_btn,
                edit_btn,
                table_btn
        );
        disposeCurrentScene(profile_pane);
        disposeCurrentScene(dashboard_pane);
        disposeCurrentScene(register_pane);
        disposeCurrentScene(notify_pane);
        disposeCurrentScene(edit_pane);
        disposeCurrentScene(table_pane);
        reg_type_pane.setVisible(true);
        staff_reg_pane.setVisible(false);
        admin_reg_pane.setVisible(false);
        getNextScene(settings_pane);
        
        setDisabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
        resetTableScene();
    }

    @FXML
    private void register(ActionEvent event) {
//********************  ADMIN REGISTRATION  ******************************************\\
        if (tab_name.getText().equals(register_btn.getText()+"/"+admin_reg_btn.getText().toLowerCase())) {
            String query = "INSERT INTO `admin`(`fname`, `lname`, `email`, `username`, `password`, `image`)"
                    + "VALUES('"+admin_fname.getText()+"', '"+admin_lname.getText()+"',"
                    + "'"+admin_email.getText()+"', '"+admin_username.getText()+"',"
                    + "'"+admin_password.getText()+"', '"+admin_image.getImage()+"')";
            
            try {
                stmt = con.createStatement();
                int check = stmt.executeUpdate(query);
                
                if (check == 1) {
                    JOptionPane.showMessageDialog(null, admin_fname.getText() + " "
                            + admin_lname.getText() + " registration is successful!", "Message", 1);
                    System.out.println("1 admin registered successfully!\n");
                    admin_fname.setText("");
                    admin_lname.setText("");
                    admin_email.setText("");
                    admin_username.setText("");
                    admin_password.setText("");
                    admin_image.setImage(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Registration is unsuccessful!", "Error", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//********************  STAFF REGISTRATION  ******************************************\\
        if (tab_name.getText().equals(register_btn.getText()+"/"+staff_reg_btn.getText().toLowerCase())) {
            String query = "INSERT INTO `staff`(`fname`, `lname`, `email`, `phone`, `position`, `gender`, `image`)"
                    + "VALUES('"+staff_fname.getText()+"', '"+staff_lname.getText()+"',"
                    + "'"+staff_email.getText()+"', '"+staff_phone.getText()+"',"
                    + "'"+staff_position.getValue()+"', '"+gender.getSelectedToggle().getUserData()+"', '"+staff_image.getImage()+"')";

            try {
                stmt = con.createStatement();
                int check = stmt.executeUpdate(query);
                
                if (check == 1) {
                    JOptionPane.showMessageDialog(null, staff_fname.getText() + " "
                            + staff_lname.getText() + " registration is successful!", "Message", 1);
                    System.out.println("1 staff registered successfully!");
                    staff_fname.setText("");
                    staff_lname.setText("");
                    staff_email.setText("");
                    staff_phone.setText("");
                    staff_position.setValue(null);
                    gender.selectToggle(staff_gender_male);
                    staff_image.setImage(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Registration is unsuccessful!", "Error", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void getAdminRegForm(ActionEvent event) {
        getRegForms(admin_reg_pane,
                reg_type_pane,
                admin_reg_btn
        );
    }

    @FXML
    private void getStaffRegForm(ActionEvent event) {
        getRegForms(staff_reg_pane,
                reg_type_pane,
                staff_reg_btn
        );
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
        setEnabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
        old_password.setDisable(false);
        new_password.setDisable(false);
    }

    @FXML
    private void saveProfile(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Saved!", "Save success!",1);
        System.out.println("Saved!");
        setDisabled(image_label,
                admin_profile_fname,
                admin_profile_lname,
                admin_profile_email,
                admin_profile_username,
                profileImage,
                save_profile_btn,
                change_password_btn,
                edit_profile_btn
        );
        old_password.setDisable(true);
        new_password.setDisable(true);
        returnBackToNormalOnSave(change_password_btn, save_profile_btn, edit_profile_btn, password_pane);
    }

    @FXML
    private void getAdminImage(MouseEvent event) throws IOException { importImage( admin_image, admin_image_label, imgLabel ); }

    @FXML
    private void onDragAdminImageOver(DragEvent event) { onDragOver( event ); }
    
    @FXML
    private void onDragAdminImageDropped(DragEvent event) throws FileNotFoundException { onDragDropped( event, admin_image ); }

    @FXML
    private void getStaffImage(MouseEvent event) throws IOException { importImage( staff_image, staff_image_label, imgLabel ); }
    
    @FXML
    private void onDragStaffImageOver(DragEvent event) { onDragOver( event ); }

    @FXML
    private void onDragStaffImageDropped(DragEvent event) throws FileNotFoundException { onDragDropped( event, staff_image ); }

    @FXML
    private void onDragProfileImageOver(DragEvent event) { onDragOver( event ); }

    @FXML
    private void onDragProfileImageDropped(DragEvent event) throws FileNotFoundException { onDragDropped( event, profileImage ); }

    @FXML
    private void getProfileImage(MouseEvent event) throws IOException { 
        if (!image_label.isDisabled() && !profileImage.isDisabled()) {
            importImage( profileImage, image_label, imgLabel );
        }
    }

    @FXML
    private void showPasswordField(ActionEvent event) { changePasswordAnimation(change_password_btn, save_profile_btn, edit_profile_btn, password_pane); }

    @FXML
    private void onUpdate(ActionEvent event) {
        String query = "UPDATE `staff` SET `fname`='"+update_finame.getText()+
                "',`lname`='"+update_lname.getText()+"',`email`='"+update_email.getText()+
                "',`phone`='"+update_phone.getText()+"',`position`='"+update_position.getValue()+
                "',`gender`='"+gender2.getSelectedToggle().getUserData()+"',`image`='"+update_image.getImage()+
                "' WHERE `email`='"+update_email.getText()+"' and `phone`='"+update_phone.getText()+"'";
        try {
            stmt = con.createStatement();
            int check = stmt.executeUpdate(query);
            
            if(check == 1) {
                JOptionPane.showMessageDialog(null, "Updated successfully!", "Success", 1);
                search_field.setDisable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Update is unsuccessful!", "Error", 0);
                search_field.setDisable(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        String query = "DELETE FROM `staff` WHERE email='"+update_email.getText()+"' and phone='"+update_phone.getText()+"'";
        try {
            stmt = con.createStatement();
            int check = stmt.executeUpdate(query);
            
            if(check == 1) {
                JOptionPane.showMessageDialog(null, "Deleted successfully!", "Success", 1);
                search_field.setDisable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Not deleted!", "Error", 0);
                search_field.setDisable(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        String query = "SELECT * FROM `staff` WHERE 1";
        
        ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
        
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM `staff`");
            
            while (rs.next()) {
                oblist.add(new ModelTable(rs.getString("id"),
                        rs.getString("fname"),
                        rs.getString("lname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("position"),
                        rs.getString("gender")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        col_lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        
        table_view.setItems(oblist);
    }

    @FXML
    private void getUpdateImage(MouseEvent event) throws IOException {
        importImage(update_image, image_label_update, imgUpdate);
    }

    @FXML
    private void uploadData(ActionEvent event) {
        search_field.setDisable(true);
        String query = "SELECT * FROM `staff` WHERE id='"+search_field.getText()+"'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                search_field.setText(rs.getString("id"));
                update_finame.setText(rs.getString("fname"));
                update_lname.setText(rs.getString("lname"));
                update_email.setText(rs.getString("email"));
                update_phone.setText(rs.getString("phone"));
                update_position.setValue(rs.getString("position"));
                gender2.setUserData(rs.getString("gender"));
//                update_image.setImage((Image) rs.getBlob("image"));
            } else {
                JOptionPane.showMessageDialog(null, "Data not found!", "Message", 1);
                search_field.setDisable(false);
                search_field.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
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