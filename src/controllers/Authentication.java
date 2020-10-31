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
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import register.Connector;

/**
 *
 * @author allie
 */
public class Authentication {

    /**
     * ****************** @STATIC ********************************************
     */
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    /**
     * ****************** @CLASSES ********************************************
     */
    private final GetScene scene;
    private final Animation animation;

    public Authentication() {
        this.scene = new GetScene();
        this.animation = new Animation();
    }

    private void SQLDB() {
        con = Connector.getConnection();
    }

    protected void LoginAuthen(TextField username, PasswordField password, Label warning, Label usrwarning, Label pwdwarning) {
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
                login(username, password, warning);
            } else {
                this.animation.fadeIn(pwdwarning);
            }
        }
    }

    protected void login(TextField username, PasswordField password, Label warning) {
        final String user = username.getText();
        final String pass = password.getText();
        final String query = "SELECT * FROM `admin` WHERE `username` = '" + user + "' and `password` = '" + pass + "'";

        try {
            SQLDB();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if (rs.next()) {
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
                con.close();
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

}
