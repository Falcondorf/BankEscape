package be.esi.devir5.gui;

import be.esi.devir5.exception.BankEscapeException;
import be.esi.devir5.model.Game;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author G40963
 */
public class ViewMirror extends Application implements Initializable, Observer {

    private Game g;
    private Stage stage;
    private Parent root;
    private AnchorPane anchor;
    private Scene scene;
    private Pane paneStatic;
    Pane paneDynamic;
    private StackPane stackPane;


    public ViewMirror(Game g) throws IOException {
        this.g = g;
        g.addObserver(ViewMirror.this);
        anchor = new AnchorPane();
        paneStatic = new Pane();
        paneDynamic = new Pane();
        stackPane = new StackPane();

        stackPane.getChildren().add(paneStatic);
        stackPane.getChildren().add(paneDynamic);
        anchor.getChildren().add(stackPane);
        root = anchor;
        scene = new Scene(root);
        stage = new Stage();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        insertImages();
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                    refreshPane();
                } catch (BankEscapeException ex) {
                    System.out.println("mirror error");;
                }
            }
        });

    }

    public void setDynamicImage(ImageView img, int j, int i) {
        img.setFitHeight(70);
        img.setFitWidth(70);
        img.setX(j * 70);
        img.setY(i * 70);
        this.paneDynamic.getChildren().add(img);
    }

    public void setStaticImage(ImageView img, int j, int i) {
        img.setFitHeight(70);
        img.setFitWidth(70);
        img.setX(j * 70);
        img.setY(i * 70);
        this.paneStatic.getChildren().add(img);
    }

    private void insertImages() throws IOException {

        for (int i = 0; i < g.getSquares().length; i++) {
            for (int j = 0; j < g.getSquares()[0].length; j++) {
                ImageView img;
                switch (g.getSquares()[i][j].getType()) {
                    case "wall":
                        img = new ImageView(ViewMirror.class.getResource("/images/wall2.png").toString());
                        setStaticImage(img, g.getSquares()[0].length - j, i);

                        break;
                    case "exit":
                        img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                        setStaticImage(img, g.getSquares()[0].length - j, i);
                        img = new ImageView(ViewMirror.class.getResource("/images/exit.png").toString());
                        setStaticImage(img, g.getSquares()[0].length - j, i);
                        break;
                    case "floor":
                        if (g.getSquares()[i][j].hasDrill()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);

                        } else if (g.getSquares()[i][j].hasEnemy()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);
                        } else if (g.getSquares()[i][j].hasKey()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);

                        } else if (g.getSquares()[i][j].hasPlayer()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);

                        } else {
                            img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);
                        }
                        break;
                    case "entry":
                        if (g.getSquares()[i][j].hasPlayer()) {

                            img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);
                        } else {

                            img = new ImageView(ViewMirror.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);
                            img = new ImageView(ViewMirror.class.getResource("/images/doorEntry.png").toString());
                            setStaticImage(img, g.getSquares()[0].length - j, i);
                        }
                        break;
                    case "vault":
                        img = new ImageView(ViewMirror.class.getResource("/images/vault.png").toString());
                        setStaticImage(img, g.getSquares()[0].length - j, i);
                        break;
                    default:
                        System.out.println("Error : invalid element read ");
                }

            }
        }
    }

    public void refreshPane() throws BankEscapeException  {
        paneDynamic.getChildren().clear();
        for (int i = 0; i < g.getSquares().length; i++) {
            for (int j = 0; j < g.getSquares()[0].length; j++) {
                ImageView img = new ImageView();
                switch (g.getSquares()[i][j].getType()) {
                    case "exit":
                        img = new ImageView(ViewMirror.class.getResource("/images/exit.png").toString());
                        setStaticImage(img, g.getSquares()[0].length - j, i);
                        break;
                    case "floor":
                        if (g.getSquares()[i][j].isLighted()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/floorLight.png").toString());
                            setDynamicImage(img, g.getSquares()[0].length - j, i);
                        }
                        if (g.getSquares()[i][j].hasDrill()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/drill.png").toString());
                            setDynamicImage(img, g.getSquares()[0].length - j, i);
                        }
                        if (g.getSquares()[i][j].hasEnemy()) {
                            switch (g.getEnemyDir(i, j)) {
                                case UP:
                                    img = new ImageView(ViewMirror.class.getResource("/images/guardN.gif").toString());
                                    setDynamicImage(img, g.getSquares()[0].length - j, i);
                                    break;
                                case DOWN:
                                    img = new ImageView(ViewMirror.class.getResource("/images/guardS.gif").toString());
                                    setDynamicImage(img, g.getSquares()[0].length - j, i);
//                                  
                                    break;
                                case LEFT:
                                    img = new ImageView(ViewMirror.class.getResource("/images/guardO.gif").toString());
                                    setDynamicImage(img, g.getSquares()[0].length - j, i);
//                                  
                                    break;
                                case RIGHT:
                                    img = new ImageView(ViewMirror.class.getResource("/images/guardE.gif").toString());
                                    setDynamicImage(img, g.getSquares()[0].length - j, i);
//                                   
                                    break;
                                default:
                            }

                        }
                        if (g.getSquares()[i][j].hasKey()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/key.png").toString());
                            setDynamicImage(img, g.getSquares()[0].length - j, i);
                        }
                        if (g.getSquares()[i][j].hasPlayer()) {
                            switch (g.getPlayerDir(i, j)) {
                                case UP:
                                    img = new ImageView(ViewMirror.class.getResource("/images/PlayerMovNHD.gif").toString());
                                    break;
                                case DOWN:
                                    img = new ImageView(ViewMirror.class.getResource("/images/PlayerMovSHD.gif").toString());
                                    break;
                                case LEFT:
                                    img = new ImageView(ViewMirror.class.getResource("/images/PlayerMovWHD.gif").toString());
                                    break;
                                case RIGHT:
                                    img = new ImageView(ViewMirror.class.getResource("/images/PlayerMovEHD.gif").toString());
                                    break;
                                default:
                            }

                            setDynamicImage(img, g.getSquares()[0].length - j, i);
                        }
                        break;
                    case "entry":
                        if (g.getSquares()[i][j].hasPlayer()) {
                            img = new ImageView(ViewMirror.class.getResource("/images/PlayerMovNHD.gif").toString());
                            setDynamicImage(img, g.getSquares()[0].length - j, i);
                        } else {

                        }
                        break;
                    default:
                        break;
                }
            }
        }

    }
}
