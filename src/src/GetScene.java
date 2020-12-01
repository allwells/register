package src;

/*
 * Copyright (C) 2020 allie
 *
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author allie
 */
public class GetScene {

    /**
     * ****************** @CLASSES ********************************************
     */
    private final Function function;
    private final Animation animation;

    public GetScene() {
        this.function = new Function();
        this.animation = new Animation();
    }

    public void nextScene(ActionEvent event, String sceneName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/" + sceneName + ".fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            //Change position on drag and drop
            this.function.changePositionOnDrag(root, stage);

            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getNextScene(Pane pane) {
        pane.setVisible(true);
        this.animation.makeFadeInTransition(pane);
    }

    public void disposeCurrentScene(Pane pane) {
        pane.setVisible(false);
    }

}
