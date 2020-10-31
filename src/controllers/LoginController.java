package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author allie
 */
public class LoginController implements Initializable {

    /**
     * ****************** @PANE ***********************************************
     */
    @FXML
    private AnchorPane anchor;
    @FXML
    private Pane login_scene;
    @FXML
    private Pane verify_pin_scene;
    @FXML
    private Pane request_pin_scene;

    /**
     * ****************** @TEXTFIELD ******************************************
     */
    @FXML
    private TextField usr;
    @FXML
    private TextField code_input;
    @FXML
    private TextField email_input;

    /**
     * ****************** @PASSWORDFIELD *************************************
     */
    @FXML
    private PasswordField pwd;

    /**
     * ****************** @LABEL *********************************************
     */
    @FXML
    private Label usrwarning;
    @FXML
    private Label pwdwarning;
    @FXML
    private Label warning;

    /**
     * ****************** @BUTTON ********************************************
     */
    @FXML
    private Button verify_btn;

    /**
     * ****************** @CLASSES ********************************************
     */
    private final Animation animation;
    private final GetScene scene;
    private final Function function;
    private final Authentication authentication;

    public LoginController() {
        this.animation = new Animation();
        this.scene = new GetScene();
        this.function = new Function();
        this.authentication = new Authentication();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animation.fadeOut(warning);
        animation.fadeOut(usrwarning);
        animation.fadeOut(pwdwarning);
    }

    @FXML
    private void exit(ActionEvent event) {
        function.close();
    }

    @FXML
    private void minimize(ActionEvent event) {
        function.minimize(anchor);
    }

    @FXML
    private void usrKeyTyped(KeyEvent event) {
        animation.fadeOut(usrwarning);
    }

    @FXML
    private void pwdKeyTyped(KeyEvent event) {
        animation.fadeOut(pwdwarning);
    }

    @FXML
    private void login(ActionEvent event) {
        // authentication.LoginAuthen(event, usr, pwd, warning, usrwarning, pwdwarning);
        scene.nextScene(event, "Dashboard");
    }

    @FXML
    private void backToRequestPinFromVerifyPin(ActionEvent event) {
        scene.getNextScene(request_pin_scene);
        scene.disposeCurrentScene(login_scene);
        scene.disposeCurrentScene(verify_pin_scene);
    }

    @FXML
    private void backToLoginFromRequestPinScene(ActionEvent event) {
        scene.getNextScene(login_scene);
        scene.disposeCurrentScene(request_pin_scene);
        scene.disposeCurrentScene(verify_pin_scene);
    }

    @FXML
    private void toRequestPinFromLogin(MouseEvent event) {
        scene.getNextScene(request_pin_scene);
        scene.disposeCurrentScene(login_scene);
        scene.disposeCurrentScene(verify_pin_scene);
    }

    @FXML
    private void requestCode(ActionEvent event) {
        //authentication.RequestCode(verify_pin_scene, login_scene, request_pin_scene, email_input, code_input, verify_btn);
        // Get next scene and dispose current scene
        scene.getNextScene(verify_pin_scene);
        scene.disposeCurrentScene(login_scene);
        scene.disposeCurrentScene(request_pin_scene);
    }

    @FXML
    private void check(KeyEvent event) {
        function.CheckOnKeyTyped(code_input, verify_btn);
    }

    @FXML
    private void verifyCode(ActionEvent event) throws IOException {
        //authentication.VerifyCodeInput(event, anchor, code_input, verify_btn);
        scene.nextScene(event, "Dashboard");
    }
}
