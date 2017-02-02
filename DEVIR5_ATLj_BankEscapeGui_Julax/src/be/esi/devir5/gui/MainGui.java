
package be.esi.devir5.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jackd
 */
public class MainGui extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(MainGui.class.getResource("StartWindow.fxml"));        
        Scene scene = new Scene(root);       
        stage.setScene(scene);
        //stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args parametre de lancement de la fonction principale
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
