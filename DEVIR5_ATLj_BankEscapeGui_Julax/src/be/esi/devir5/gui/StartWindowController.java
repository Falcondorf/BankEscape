package be.esi.devir5.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author jackd
 */
public class StartWindowController implements Initializable {

    @FXML
    private TextField txtFieldLevel;

    @FXML
    void handleButtonSolo(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        SoloFxController sfc = new SoloFxController(txtFieldLevel.getText());
        sfc.start(stage);

    }

    @FXML
    void handleButtonEdit(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Editor ec = new Editor();
            ec.start(stage);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void quitWindowEvent(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
