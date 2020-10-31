/*
 * Copyright (C) 2020 allie
 *
 */
package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import register.sqlite_db_connection;

/**
 *
 * @author allie
 */
public class Authentication {

    /**
     * ****************** @STATIC *********************************************
     */
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    /**
     * ****************** @CLASSES ********************************************
     */
    private final GetScene scene;
    private final Animation animation;

    /**
     * ****************** @LIST ***********************************************
     */
    private final ObservableList<ModelTable> oblist;

    public Authentication() {
        this.scene = new GetScene();
        this.animation = new Animation();
        this.oblist = FXCollections.observableArrayList();
    }

    private void SQLDB() {
        con = sqlite_db_connection.connection();
    }

    protected void LoginAuthen(ActionEvent event, TextField username, PasswordField password, Label warning, Label usrwarning, Label pwdwarning) {
        final String user = username.getText();
        final String pass = password.getText();

        if (user.length() == 0) {
            this.animation.fadeIn(usrwarning);
        }
        if (pass.length() == 0) {
            this.animation.fadeIn(pwdwarning);
        }
        if (user.length() > 0 && pass.length() > 0) {
            if (pass.length() > 6) {
                login(event, username, password, warning);
            } else {
                this.animation.fadeIn(pwdwarning);
            }
        }
    }

    protected void login(ActionEvent event, TextField username, PasswordField password, Label warning) {
        final String user = username.getText();
        final String pass = password.getText();
        final String query = "SELECT * FROM `admin` WHERE `username` = '" + user + "' and `password` = '" + pass + "'";

        try {
            SQLDB();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                scene.nextScene(event, "Dashboard");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password!", "Wrong inputs!", 0);
                username.setText("");
                password.setText("");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void RequestCode(Pane verify_pin_scene, Pane login_scene, Pane request_pin_scene, TextField email_input, TextField code_input, Button verify_btn) {
        final String query = "SELECT * FROM admin WHERE email = '" + email_input + "'";
        final String empty = "";
        try {
            SQLDB();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "A 6 digit code has been sent to " + email_input.getText() + ".", "Success!", 1);

                // Get next scene and dispose current scene
                this.scene.getNextScene(verify_pin_scene);
                this.scene.disposeCurrentScene(login_scene);
                this.scene.disposeCurrentScene(request_pin_scene);

                if (empty.equals(code_input.getText()) || code_input.getText().length() < 6) {
                    verify_btn.setDisable(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email does not exist in our database!", "Error Message", 0);
                email_input.setText("");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void VerifyCodeInput(ActionEvent event, AnchorPane anchor, TextField code_input, Button verify_btn) {
        final String empty = "";

        if (code_input.getText().matches("196755")) {
            JOptionPane.showMessageDialog(null, "Confirmed!", "Success!", 1);
            code_input.setText("");
            anchor.setOpacity(0);
            this.scene.nextScene(event, "dashboard");

        } else {
            JOptionPane.showMessageDialog(null, "Invalid code!", "Invalid", 0);
            code_input.setText("");

            // Disable button if input is empty or less than 6 digits
            if (empty.equals(code_input.getText()) || code_input.getText().length() < 6) {
                verify_btn.setDisable(true);
            }

            // Check wether input is equal to 6 digits
            if (code_input.getText().length() == 6) {
                verify_btn.setDisable(false);
            } else {
                verify_btn.setDisable(true);
            }
        }
    }

    protected void onLoadTable(TableView table_view, TableColumn col_id, TableColumn col_fname, TableColumn col_lname, TableColumn col_email, TableColumn col_phone, TableColumn col_position, TableColumn col_gender) {
        try {
            SQLDB();
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
            con.close();
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

    protected void showProfileDetails(TextField admin_profile_fname, TextField admin_profile_lname, TextField admin_profile_email, TextField admin_profile_username, PasswordField old_password, ImageView profileImage) {
        String name = "newadmin";
        String pass = "newadmin";
        String sql = "SELECT * FROM admin WHERE username = '" + name + "' and password = '" + pass + "'";

        try {
            SQLDB();
            rs = con.createStatement().executeQuery(sql);

            if (rs.next()) {
                admin_profile_fname.setText(rs.getString("fname"));
                admin_profile_lname.setText(rs.getString("lname"));
                admin_profile_email.setText(rs.getString("email"));
                admin_profile_username.setText(rs.getString("username"));
                old_password.setText(rs.getString("password"));
                profileImage.setImage((Image) rs.getBlob("image"));
            } else {
                JOptionPane.showMessageDialog(null, "Error 449: Database connection error.", "Error Message", 0);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void AdminRegisteration(Label tab_name, Button register_btn, Button admin_reg_btn, TextField admin_fname, TextField admin_lname, TextField admin_email, TextField admin_username, PasswordField admin_password, ImageView admin_image) {
        if (tab_name.getText().equals(register_btn.getText() + "/" + admin_reg_btn.getText().toLowerCase())) {
            final String query = "INSERT INTO `admin`(`fname`, `lname`, `email`, `username`, `password`, `image`)"
                    + "VALUES('" + admin_fname.getText() + "', '" + admin_lname.getText() + "',"
                    + "'" + admin_email.getText() + "', '" + admin_username.getText() + "',"
                    + "'" + admin_password.getText() + "', '" + admin_image.getImage() + "')";

            try {
                SQLDB();
                stmt = con.createStatement();
                final int check = stmt.executeUpdate(query);

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
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void StaffRegistration(Label tab_name, Button register_btn, Button staff_reg_btn, TextField staff_fname, TextField staff_lname, TextField staff_email, TextField staff_phone, ComboBox staff_position, ToggleGroup gender, ImageView staff_image, RadioButton staff_gender_male) {
        if (tab_name.getText().equals(register_btn.getText() + "/" + staff_reg_btn.getText().toLowerCase())) {
            final String query = "INSERT INTO `staff`(`fname`, `lname`, `email`, `phone`, `position`, `gender`, `image`)"
                    + "VALUES('" + staff_fname.getText() + "', '" + staff_lname.getText() + "',"
                    + "'" + staff_email.getText() + "', '" + staff_phone.getText() + "',"
                    + "'" + staff_position.getValue() + "', '" + gender.getSelectedToggle().getUserData() + "', '" + staff_image.getImage() + "')";

            try {
                SQLDB();
                stmt = con.createStatement();
                final int check = stmt.executeUpdate(query);

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
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void Update(TextField search_field, TextField update_finame, TextField update_lname, TextField update_email, TextField update_phone, ComboBox update_position, ToggleGroup gender2, ImageView update_image) {
        final String query = "UPDATE `staff` SET `fname`='" + update_finame.getText()
                + "',`lname`='" + update_lname.getText() + "',`email`='" + update_email.getText()
                + "',`phone`='" + update_phone.getText() + "',`position`='" + update_position.getValue()
                + "',`gender`='" + gender2.getSelectedToggle().getUserData() + "',`image`='" + update_image.getImage()
                + "' WHERE `email`='" + update_email.getText() + "' and `phone`='" + update_phone.getText() + "'";

        try {
            SQLDB();
            stmt = con.createStatement();
            final int check = stmt.executeUpdate(query);

            if (check == 1) {
                JOptionPane.showMessageDialog(null, "Updated successfully!", "Success", 1);
                search_field.setDisable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Update is unsuccessful!", "Error", 0);
                search_field.setDisable(false);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void Delete(TextField search_field, TextField update_email, TextField update_phone) {
        final String query = "DELETE FROM `staff` WHERE email='" + update_email.getText() + "' and phone='" + update_phone.getText() + "'";
        try {
            SQLDB();
            stmt = con.createStatement();
            final int check = stmt.executeUpdate(query);

            if (check == 1) {
                JOptionPane.showMessageDialog(null, "Deleted successfully!", "Success", 1);
                search_field.setDisable(false);
            } else {
                JOptionPane.showMessageDialog(null, "Not deleted!", "Error", 0);
                search_field.setDisable(false);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void UploadData(TextField search_field, TextField update_finame, TextField update_lname, TextField update_email, TextField update_phone, ComboBox update_position, ToggleGroup gender2, ImageView update_image) {
        search_field.setDisable(true);
        String query = "SELECT * FROM `staff` WHERE id='" + search_field.getText() + "'";
        try {
            SQLDB();
            rs = con.createStatement().executeQuery(query);

            if (rs.next()) {
                search_field.setText(rs.getString("id"));
                update_finame.setText(rs.getString("fname"));
                update_lname.setText(rs.getString("lname"));
                update_email.setText(rs.getString("email"));
                update_phone.setText(rs.getString("phone"));
                update_position.setValue(rs.getString("position"));
                gender2.setUserData(rs.getString("gender"));
                update_image.setImage((Image) rs.getBlob("image"));
            } else {
                JOptionPane.showMessageDialog(null, "Data not found!", "Message", 1);
                search_field.setDisable(false);
                search_field.setText("");
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
