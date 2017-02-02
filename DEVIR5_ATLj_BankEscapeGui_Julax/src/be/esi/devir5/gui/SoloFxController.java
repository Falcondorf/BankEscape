package be.esi.devir5.gui;

import be.esi.devir5.business.AdminFacade;
import be.esi.devir5.dto.PlayerDto;
import be.esi.devir5.exception.BankEscapeDbException;
import be.esi.devir5.exception.BankEscapeException;
import be.esi.devir5.model.Game;
import be.esi.devir5.model.Direction;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jackd
 */
public class SoloFxController extends Application implements Initializable, Observer {

    private AnchorPane anchor;
    private Scene scene;
    private VBox menuWindoEtc;
    private HBox itemsView;
    private Pane paneStatic;
    private Pane paneDynamic;
    private StackPane stackPane;
    private Game g;
    private Stage stage;
    private String nameLevel;
    private ImageView imgvDrill;
    private ImageView imgvKey;
    private ImageView imgvMoney;

    public SoloFxController(String nameLevel) {
        this.anchor = new AnchorPane();
        this.paneStatic = new Pane();
        this.paneDynamic = new Pane();
        this.stackPane = new StackPane();
        this.nameLevel = nameLevel;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        try{
             initWindow(primaryStage);
        }catch(BankEscapeException | IOException ex){
            System.out.println("error initialization");
        }
       
    }

    private void initWindow(Stage primaryStage) throws BankEscapeException, IOException {
        this.menuWindoEtc = new VBox();
        this.itemsView = new HBox();
        MenuBar mbMenu = new MenuBar();

        ColorAdjust blackWhite = new ColorAdjust();
        blackWhite.setContrast(-15);

        Image img1 = new Image(SoloFxController.class.getResource("/images/drill.png").toString());
        imgvDrill = new ImageView(img1);
        imgvDrill.setFitWidth(50);
        imgvDrill.setPreserveRatio(true);
        imgvDrill.setEffect(blackWhite);
        Image img2 = new Image(SoloFxController.class.getResource("/images/key.png").toString());
        imgvKey = new ImageView(img2);
        imgvKey.setFitWidth(50);
        imgvKey.setPreserveRatio(true);
        imgvKey.setEffect(blackWhite);
        Image img3 = new Image(SoloFxController.class.getResource("/images/money.png").toString());
        imgvMoney = new ImageView(img3);
        imgvMoney.setFitWidth(50);
        imgvMoney.setPreserveRatio(true);
        imgvMoney.setEffect(blackWhite);

        itemsView.getChildren().addAll(imgvDrill, imgvKey, imgvMoney);

        Menu menuOption = new Menu("Option");
        Menu menuSpecial = new Menu("Special");
        Menu menuCheats = new Menu("Cheats");

        MenuItem mirror = new MenuItem("Mirror");
        MenuItem cheat1 = new MenuItem("Unlock all items");
        MenuItem cheat2 = new MenuItem("Ghost mode");
        MenuItem cheat3 = new MenuItem("Kill all enemies");

        MenuItem optSave = new MenuItem("Save");
        MenuItem opt1 = new MenuItem("Return to main menu");
        MenuItem opt2 = new MenuItem("Quit game");

        optSave.setOnAction((ActionEvent event) -> {
            Stage dialog = new Stage();
            dialog.setTitle("SAVE");
            Scene sceneSave;
            HBox bxSaveLayout = new HBox();
            TextField tfName = new TextField();
            tfName.setPromptText("Your name...");
            TextField tfNameSave = new TextField();
            tfNameSave.setPromptText("Save name...");
            Button bSave = new Button("Save");

            bSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        g.writeDB(tfNameSave.getText(), g.getNextLevelName());
                        AdminFacade.addPlayer(new PlayerDto(0,
                                tfName.getText(),
                                AdminFacade.findLevelByName(tfNameSave.getText()).getId(),
                                g.playerHasKey(),
                                g.playerHasDrill(),
                                g.playerHasMoney()));
                        returnToMainMenu();
                        dialog.close();
                    } catch (BankEscapeDbException ex) {
                        System.out.println("Error cannot save: " + ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println("Error cannot read file SoloFxController.class.getResource(\"StartWindow.fxml\")  " + ex.getMessage());
                    }
                }
            });

            bxSaveLayout.getChildren().addAll(tfName, tfNameSave, bSave);
            sceneSave = new Scene(bxSaveLayout);
            dialog.setScene(sceneSave);
            dialog.show();
        });

        opt1.setOnAction((ActionEvent event) -> {
            try {
                returnToMainMenu();
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        });

        opt2.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        mirror.setOnAction((ActionEvent event) -> {
            ViewMirror mirror1;
            try {
                mirror1 = new ViewMirror(g);
                mirror1.start(stage);
            } catch (IOException ex) {
            } catch (Exception ex) {
            }
        });
        cheat1.setOnAction((ActionEvent event) -> {
            g.giveAllToPlayer();
        });
        cheat2.setOnAction((ActionEvent event) -> {
            g.goGhost();
        });
        cheat3.setOnAction((ActionEvent event) -> {
            try {
                g.killAllEnemies();
                refreshPane();
            } catch (BankEscapeException ex) {
                System.out.println(ex);
            }
        });
        menuOption.getItems().addAll(optSave, opt1, opt2);
        menuSpecial.getItems().add(mirror);
        menuCheats.getItems().addAll(cheat1, cheat2, cheat3);
        mbMenu.getMenus().addAll(menuOption, menuSpecial, menuCheats);

        g = new Game(nameLevel);
        g.addObserver(SoloFxController.this);

        stage = primaryStage;

        insertImages();
        refreshPane();

        stackPane.getChildren().add(paneStatic);
        stackPane.getChildren().add(paneDynamic);

        anchor.getChildren().add(mbMenu);
        anchor.getChildren().addAll(stackPane);
        menuWindoEtc.getChildren().addAll(mbMenu, stackPane, itemsView);

        scene = new Scene(menuWindoEtc);
        stage.setScene(scene);
        stage.show();

        g.startThreadEnnemy();

        setKeysMovement();
    }

    private void returnToMainMenu() throws IOException {
        g.enGame();
        Parent root = FXMLLoader.load(SoloFxController.class.getResource("StartWindow.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

    private void setKeysMovement() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        g.movePlayer(Direction.UP);
                        break;
                    case DOWN:
                        g.movePlayer(Direction.DOWN);
                        break;
                    case LEFT:
                        g.movePlayer(Direction.LEFT);
                        break;
                    case RIGHT:
                        g.movePlayer(Direction.RIGHT);
                        break;
                    default:
                }
            }
        });
    }

    private void insertImages() throws IOException {

        for (int i = 0; i < g.getWidth(); i++) {
            for (int j = 0; j < g.getHeight(); j++) {
                ImageView img;
                switch (g.getSquare(i, j).getType()) {
                    case "wall":
                        img = new ImageView(SoloFxController.class.getResource("/images/wall2.png").toString());
                        setStaticImage(img, j, i);
                        break;
                    case "exit":
                        img = new ImageView(SoloFxController.class.getResource("/images/floor.png").toString());
                        setStaticImage(img, j, i);
                        img = new ImageView(SoloFxController.class.getResource("/images/exit.png").toString());
                        setStaticImage(img, j, i);
                        break;
                    case "floor":
                        img = new ImageView(SoloFxController.class.getResource("/images/floor.png").toString());
                        setStaticImage(img, j, i);
                        break;
                    case "entry":
                        if (g.getSquare(i, j).hasPlayer()) {
                            img = new ImageView(SoloFxController.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                        } else {
                            img = new ImageView(SoloFxController.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                            img = new ImageView(SoloFxController.class.getResource("/images/doorEntry.png").toString());
                            setStaticImage(img, j, i);
                        }
                        break;
                    case "vault":
                        img = new ImageView(SoloFxController.class.getResource("/images/vault.png").toString());
                        setStaticImage(img, j, i);
                        break;
                    default:
                        System.out.println("Error : invalid element read ");
                }

            }
        }
    }

    private void setDynamicImage(ImageView img, int j, int i) {
        double arg = 500 / (double) g.getWidth();
        img.setFitHeight(arg);
        img.setFitWidth(arg);
        img.setX(j * arg);
        img.setY(i * arg);
        this.paneDynamic.getChildren().add(img);
    }

    private void setStaticImage(ImageView img, int j, int i) {
        double arg = 500 / (double) g.getWidth();
        img.setFitHeight(arg);
        img.setFitWidth(arg);
        img.setX(j * arg);
        img.setY(i * arg);
        this.paneStatic.getChildren().add(img);
    }

    @Override
    public void update(Observable o, Object arg) {

        Platform.runLater(() -> {
            try {
                refreshPane();
                if (g.endLevel()) {

                    if (g.getNextLevelName().equals("END")) {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Vous avez terminé le Jeu");
                        alert2.setHeaderText("Dernier niveau achevé");
                        alert2.setContentText("Bravo, vous êtes un voleur ... Content ?");
                        alert2.showAndWait();
                        returnToMainMenu();
                    } else {
                        resetSoloNodes();
                        nameLevel = g.getNextLevelName();

                        try {
                            initWindow(stage);
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
                    }

                } else if (g.isLost()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Game Over");
                    alert.setHeaderText("GAME OVER !!!");
                    alert.setContentText("Vous avez perdu... Dommage.");

                    alert.showAndWait();
                    resetSoloNodes();
                    initWindow(stage);
                    //Platform.exit();
                }
                stage.show();
            } catch (BankEscapeException | IllegalStateException ex) {
                System.out.println(ex);
            } catch (IOException ex) {
                System.out.println("Error reading file: " + ex.getMessage());
            }

            //verif items of player and change second view...
            ColorAdjust normalColor = new ColorAdjust();
            if (g.playerHasMoney()) {
                imgvMoney.setEffect(normalColor);
            }
            if (g.playerHasDrill()) {
                imgvDrill.setEffect(normalColor);
            }
            if (g.playerHasKey()) {
                imgvKey.setEffect(normalColor);
            }
        });

    }

    private void resetSoloNodes() {
        //TODO restart level
        anchor.getChildren().clear();
        stackPane.getChildren().clear();
        anchor = new AnchorPane();
        paneStatic = new Pane();
        paneDynamic = new Pane();
        stackPane = new StackPane();
    }

    private void refreshPane() throws BankEscapeException {
        paneDynamic.getChildren().clear();
        for (int i = 0; i < g.getWidth(); i++) {
            for (int j = 0; j < g.getHeight(); j++) {
                ImageView img = new ImageView();
                switch (g.getSquare(i, j).getType()) {
                    case "exit":

                    case "floor":
                        if (g.getSquare(i, j).isLighted()) {
                            if (!g.getSquare(i, j).hasEnemy()) {
                                img = new ImageView(SoloFxController.class.getResource("/images/floorLight.png").toString());
                                setDynamicImage(img, j, i);
                            }

                        }
                        if (g.getSquare(i, j).hasDrill()) {
                            img = new ImageView(SoloFxController.class.getResource("/images/drill.png").toString());
                            setDynamicImage(img, j, i);
                        }
                        if (g.getSquare(i, j).hasEnemy()) {
                            switch (g.getEnemyDir(i, j)) {
                                case UP:
                                    img = new ImageView(SoloFxController.class.getResource("/images/guardN.gif").toString());
                                    setDynamicImage(img, j, i);
                                    break;
                                case DOWN:
                                    img = new ImageView(SoloFxController.class.getResource("/images/guardS.gif").toString());
                                    setDynamicImage(img, j, i);
//                                  
                                    break;
                                case LEFT:
                                    img = new ImageView(SoloFxController.class.getResource("/images/guardO.gif").toString());
                                    setDynamicImage(img, j, i);
//                                  
                                    break;
                                case RIGHT:
                                    img = new ImageView(SoloFxController.class.getResource("/images/guardE.gif").toString());
                                    setDynamicImage(img, j, i);
//                                   
                                    break;
                                default:
                            }

                        }
                        if (g.getSquare(i, j).hasKey()) {
                            img = new ImageView(SoloFxController.class.getResource("/images/key.png").toString());
                            setDynamicImage(img, j, i);
                        }
                        if (g.getSquare(i, j).hasPlayer()) {
                            switch (g.getPlayerDir(i, j)) {
                                case UP:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovNHD.gif").toString());
                                    break;
                                case DOWN:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovSHD.gif").toString());
                                    break;
                                case LEFT:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovWHD.gif").toString());
                                    break;
                                case RIGHT:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovEHD.gif").toString());
                                    break;
                                default:
                            }

                            setDynamicImage(img, j, i);
                        }
                        break;
                    case "entry":
                        if (g.getSquare(i, j).hasPlayer()) {
                            switch (g.getPlayerDir(i, j)) {
                                case UP:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovNHD.gif").toString());
                                    break;
                                case DOWN:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovSHD.gif").toString());
                                    break;
                                case LEFT:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovWHD.gif").toString());
                                    break;
                                case RIGHT:
                                    img = new ImageView(SoloFxController.class.getResource("/images/PlayerMovEHD.gif").toString());
                                    break;
                                default:
                            }
                            setDynamicImage(img, j, i);
                        }
                        break;
                    default:
                        break;
                }
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
