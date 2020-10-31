package register;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import views.splashscreen;

/**
 *
 * @author allie
 */
public class Register extends Application
{
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception
    {
        try
        {
            splashscreen splash = new splashscreen();
            splash.setVisible(true);
                
            for (int i=0; i<=100; i++)
            {
                Thread.sleep(50);
//                splash.progress_indicator.setText("Loading database. . . "+Integer.toString(i)+"%");
                splash.progress_bar.setValue(i);
                    
                if (i == 1)
                {
                    splash.progress_indicator.setText("Starting. . . ");
                }
                    
                if (i == 52)
                {
                    splash.progress_indicator.setText("Setting up environment. . . ");
                }
                    
                if (i == 70)
                {
                    splash.progress_indicator.setText("Loading database. . . ");
                }
                    
                if (i == 100)
                {
                    splash.setVisible(false);
                    Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
                        
                    setPosition(root, stage);
                        
                    Scene newScene = new Scene(root);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(newScene);
                    stage.show();
                }
            }
        }
        catch (InterruptedException e)
        {
            System.out.println(e.toString());
        }
    }
    
    private void setPosition(Parent root, Stage stage) {
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}