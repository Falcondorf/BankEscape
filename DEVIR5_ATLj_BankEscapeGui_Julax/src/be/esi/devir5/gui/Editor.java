package be.esi.devir5.gui;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ConcurrentModificationException;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import be.esi.devir5.exception.BankEscapeException;
import be.esi.devir5.model.Direction;
import be.esi.devir5.model.Game;
import java.io.UnsupportedEncodingException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author jackd
 */
public class Editor extends Application implements Observer {

    private AnchorPane anchor;
    private GridPane gridPane;
    private Stage stage;
    private HBox root;
    private VBox info;
    private Button saveButton;
    private ImageView imgRb;
    private String type;
    private Game g;
    private TextField levelChoice;
    private double arg;

    public Editor() {
        this.g = new Game(10, 10);
        this.anchor = new AnchorPane();
        this.gridPane = new GridPane();
        this.stage = new Stage();
        this.root = new HBox();
        this.info = new VBox(10);
        this.saveButton = new Button("SAVE");
        this.levelChoice = new TextField("Niveau - ");
        this.arg = 500 / g.getWidth() + g.getHeight();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        VBox menuWindoEtc = new VBox();
        MenuBar mbMenu = new MenuBar();
        Menu menuOption = new Menu("Option");
        MenuItem opt1 = new MenuItem("Return to main menu");
        MenuItem opt2 = new MenuItem("Quit game");

        opt1.setOnAction((ActionEvent event) -> {
            try {
                Parent root1 = FXMLLoader.load(Editor.class.getResource("StartWindow.fxml"));
                Scene scene = new Scene(root1);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println(e.getCause());
            }
        });

        opt2.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });

        menuOption.getItems().addAll(opt1, opt2);
        mbMenu.getMenus().addAll(menuOption);

        initAddObserver();
        gridPane.setHgap(arg / 50);
        gridPane.setVgap(arg / 50);

        insertImages();

        ToggleGroup tg = new ToggleGroup();

        RadioButton rbWall = new RadioButton();
        rbWall.setToggleGroup(tg);
        RadioButton rbFloor = new RadioButton();
        rbFloor.setToggleGroup(tg);
        rbFloor.setSelected(true);
        RadioButton rbExit = new RadioButton();
        rbExit.setToggleGroup(tg);
        RadioButton rbDrill = new RadioButton();
        rbDrill.setToggleGroup(tg);
        RadioButton rbEntry = new RadioButton();
        rbEntry.setToggleGroup(tg);
        RadioButton rbPlayer = new RadioButton();
        rbPlayer.setToggleGroup(tg);
        RadioButton rbEnemy = new RadioButton();
        rbEnemy.setToggleGroup(tg);
        RadioButton rbVault = new RadioButton();
        rbVault.setToggleGroup(tg);
        RadioButton rbKey = new RadioButton();
        rbKey.setToggleGroup(tg);

        initRbImg(rbDrill, rbWall, rbPlayer, rbExit, rbEntry, rbFloor, rbEnemy, rbKey, rbVault);

        addToInfo(rbDrill, rbEnemy, rbEntry, rbExit, rbFloor, rbKey, rbPlayer, rbVault, rbWall);

        loadImgRb(rbDrill, rbEnemy, rbEntry, rbExit, rbFloor, rbKey, rbPlayer, rbVault, rbWall);

        gridPane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                try {
                    for (Node n : gridPane.getChildren()) {
                        if (n instanceof ImageView) {
                            if (n.getBoundsInParent().contains(event.getSceneX(), event.getSceneY())) {
                                ImageView img;
                                img = imgRb;
                                img.setFitHeight(arg);
                                img.setFitWidth(arg);
                                switch (type) {
                                    case "wall":
                                        g.addWall(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "floor":
                                        g.addFloor(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "vault":
                                        g.addVault(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "entry":
                                        g.addEntry(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "exit":
                                        g.addExit(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "player":
                                        g.putPlayer(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        g.addPlayer(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "enemy":
                                        g.putEnemy(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        g.addEnemy(Direction.UP, GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "drill":
                                        g.putDrill(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    case "key":
                                        g.putKey(GridPane.getRowIndex(n), GridPane.getColumnIndex(n));
                                        break;
                                    default:

                                }

                                gridPane.getChildren().remove(n);
                            }
                        }
                    }
                } catch (ConcurrentModificationException ex) {

                }

            }

        });

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if (!g.isValid()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Invalid Maze");
                        alert.setHeaderText("Seems like you've done something wrong...");
                        alert.setContentText("I must remind you that a maze must"
                                + " be surrounded by walls.\n"
                                + "Also you must have at least 1 player, 1 drill, "
                                + "1 vault and 1 entry.");

                        alert.showAndWait();
                    } else {
//                        try {
                           // writeLevel();
                           g.writeDB(levelChoice.getText(), "END");
                           //writeLevelDb();
//                        } catch (UnsupportedEncodingException ex) {
//                            System.out.println("alert incoding");
//                        }

                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Valid Maze");
                        alert.setHeaderText("Congrats you did well");
                        alert.setContentText("Your Maze is just puuuurfect");

                        alert.showAndWait();
                    }
//                } catch (FileNotFoundException ex) {
//                    System.out.println("Fichier introuvable");
                } catch (BankEscapeException ex2) {
                    System.out.println("Problème de validation");
                }
            }
        });

        GridPane gpDimension = new GridPane();
        Label lWidth = new Label("Hauteur");
        Label lHeight = new Label("Largeur");
        Button bWidthPlus = new Button("+");
        Button bWidthLess = new Button("-");
        Button bHeightPlus = new Button("+");
        Button bHeightLess = new Button("-");

        bWidthPlus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    gridPane.getChildren().clear();
                    // maze = new Maze(maze, 1, 0);
                    g.modifSize(1, 0);
                    initAddObserver();
                    insertImages();
                    //  stage.setMinHeight(stage.getMinHeight() + 70);
                    arg = arg / 1.1;
                    //stage.setHeight(stage.getHeight() + arg);
                } catch (BankEscapeException ex) {
                    System.out.println(ex.getCause());
                }
            }
        });
        bWidthLess.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    gridPane.getChildren().clear();
                    //   maze = new Maze(maze, -1, 0);
                    g.modifSize(-1, 0);
                    initAddObserver();
                    insertImages();
                    arg = arg * 1.1;
                    // stage.setHeight(stage.getHeight() - arg);
                } catch (BankEscapeException ex) {
                    System.out.println(ex.getCause());
                }
            }
        });

        bHeightPlus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    gridPane.getChildren().clear();
                    //maze = new Maze(maze, 0, 1);
                    g.modifSize(0, 1);

                    initAddObserver();
                    insertImages();
                    arg = arg / 1.1;
                    // stage.setWidth(stage.getWidth()+ arg);
                } catch (BankEscapeException ex) {
                    System.out.println(ex.getCause());
                }
            }
        });
        bHeightLess.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    gridPane.getChildren().clear();
                    // maze = new Maze(maze, 0, -1);
                    g.modifSize(0, -1);
                    initAddObserver();
                    insertImages();
                    arg = arg * 1.1;
                    // stage.setWidth(stage.getWidth()- arg);
                } catch (BankEscapeException ex) {
                    System.out.println(ex.getCause());
                }
            }
        });
        gpDimension.add(lWidth, 0, 0);
        gpDimension.add(lHeight, 0, 1);
        gpDimension.add(bWidthPlus, 2, 0);
        gpDimension.add(bWidthLess, 1, 0);
        gpDimension.add(bHeightPlus, 2, 1);
        gpDimension.add(bHeightLess, 1, 1);
        info.getChildren().add(gpDimension);

        root.setSpacing(50);

        root.getChildren().add(gridPane);
        root.getChildren().add(info);
        menuWindoEtc.getChildren().add(mbMenu);
        root.getChildren().add(menuWindoEtc);

        anchor.getChildren().add(root);
        Parent rootP = anchor;

        // menuWindoEtc.getChildren().addAll(mbMenu, rootP);
        Scene scene = new Scene(rootP);
//        stage.setMinWidth(1000);
//        stage.setMaxWidth(1000);
//        stage.setMinHeight(800);
//        stage.setMaxHeight(800);

        stage.setScene(scene);
        stage.show();
    }

    private void initAddObserver() {
        for (int i = 0; i < g.getWidth(); i++) {
            for (int j = 0; j < g.getHeight(); j++) {
                g.deleteObserver(Editor.this);
                g.addObserver(Editor.this);

            }

        }
    }

    private void initRbImg(RadioButton rbDrill, RadioButton rbWall, RadioButton rbPlayer, RadioButton rbExit, RadioButton rbEntry, RadioButton rbFloor, RadioButton rbEnemy, RadioButton rbKey, RadioButton rbVault) {
        ImageView img;
        img = new ImageView(Editor.class.getResource("/images/drill.png").toString());
        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbDrill.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/wall2.png").toString());

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbWall.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/PlayerMovNHD.gif").toString());

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbPlayer.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/exit.png").toString());

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbExit.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/doorEntry.png").toString());   //attention doorEntry.png

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbEntry.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/floor.png").toString());

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbFloor.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/guardN.gif").toString());

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbEnemy.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/key.png").toString());

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbKey.setGraphic(img);
        img = new ImageView(Editor.class.getResource("/images/vault.png").toString());

        img.setFitHeight(arg);
        img.setFitWidth(arg);
        rbVault.setGraphic(img);
    }

    private void addToInfo(RadioButton rbDrill, RadioButton rbEnemy, RadioButton rbEntry, RadioButton rbExit, RadioButton rbFloor, RadioButton rbKey, RadioButton rbPlayer, RadioButton rbVault, RadioButton rbWall) {
        info.getChildren().add(rbDrill);
        info.getChildren().add(rbEnemy);
        info.getChildren().add(rbEntry);
        info.getChildren().add(rbExit);
        info.getChildren().add(rbFloor);
        info.getChildren().add(rbKey);
        info.getChildren().add(rbPlayer);
        info.getChildren().add(rbVault);
        info.getChildren().add(rbWall);
        info.getChildren().add(levelChoice);
        info.getChildren().add(saveButton);

    }

    private void loadImgRb(RadioButton rbDrill, RadioButton rbEnemy, RadioButton rbEntry, RadioButton rbExit, RadioButton rbFloor, RadioButton rbKey, RadioButton rbPlayer, RadioButton rbVault, RadioButton rbWall) {
        rbDrill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/drill.png").toString());
                type = "drill";
            }

        });
        rbEnemy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/guardN.gif").toString());
                type = "enemy";
            }

        });
        rbEntry.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/doorEntry.png").toString()); //attention dorrEntry.png
                type = "entry";
            }

        });
        rbExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/exit.png").toString());

                type = "exit";
            }

        });
        rbFloor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                type = "floor";
            }

        });
        rbKey.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/key.png").toString());
                type = "key";
            }

        });
        rbPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/PlayerMovNHD.gif").toString());
                type = "player";
            }

        });
        rbVault.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/vault.png").toString());
                type = "vault";
            }

        });
        rbWall.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imgRb = new ImageView(Editor.class.getResource("/images/wall2.png").toString());
                type = "wall";
            }

        });
    }

    private void writeLevel() throws FileNotFoundException, UnsupportedEncodingException {

        try (PrintWriter writer = new PrintWriter("levels/" + levelChoice.getText(), "UTF-8")) {
            writer.print(g.getWidth() + "/" + g.getHeight() + "/END");
            writer.println("");
            for (int i = 0; i < g.getWidth(); i++) {
                for (int j = 0; j < g.getHeight(); j++) {
                    switch (g.getSquare(i, j).getType()) {
                        case "wall":
                            writer.print("W");
                            break;
                        case "vault":
                            writer.print("V");
                            break;
                        case "exit":
                            writer.print("S");
                            break;
                        case "entry":
                            writer.print("I");
                            break;
                        case "floor":
                            if (g.getSquare(i, j).hasDrill()) {
                                writer.print("D");
                            } else if (g.getSquare(i, j).hasEnemy()) {
                                writer.print("E");
                            } else if (g.getSquare(i, j).hasKey()) {
                                writer.print("K");
                            } else if (g.getSquare(i, j).hasPlayer()) {
                                writer.print("P");
                            } else {
                                writer.print(" ");
                            }
                            break;
                        default:
                    }
                }
                writer.println("");
            }

        }
    }

    private void setStaticImage(ImageView img, int j, int i) {
        img.setFitHeight(arg);
        img.setFitWidth(arg);
        img.setX(j * arg);
        img.setY(i * arg);
        this.gridPane.add(img, i, j);
    }

    private void insertImages() {
        for (int i = 0; i < g.getHeight(); i++) {
            for (int j = 0; j < g.getWidth(); j++) {
                ImageView img;
                switch (g.getSquare(j, i).getType()) {
                    case "wall":
                        // img = new ImageView(new Image(StartWindowController.class.getResourceAsStream(".\\src\\images\\sis.jpg")));
                        img = new ImageView(Editor.class.getResource("/images/wall2.png").toString());
                        setStaticImage(img, j, i);
                        break;
                    case "exit":
                        img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                        setStaticImage(img, j, i);
                        img = new ImageView(Editor.class.getResource("/images/exit.png").toString());
                        setStaticImage(img, j, i);
                        break;
                    case "floor":
                        if (g.getSquare(j, i).hasDrill()) {
                            img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                            img = new ImageView(Editor.class.getResource("/images/drill.png").toString());
                            setStaticImage(img, j, i);
                        } else if (g.getSquare(j, i).hasEnemy()) {
                            img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                            img = new ImageView(Editor.class.getResource("/images/guardN.gif").toString());
                            setStaticImage(img, j, i);
                        } else if (g.getSquare(j, i).hasKey()) {
                            img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                            img = new ImageView(Editor.class.getResource("/images/key.png").toString());
                            setStaticImage(img, j, i);

                        } else if (g.getSquare(j, i).hasPlayer()) {
                            img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                            img = new ImageView(Editor.class.getResource("/images/PlayerMovNHD.gif").toString());
                            setStaticImage(img, j, i);
                        } else {
                            img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                        }
                        break;
                    case "entry":
                        if (g.getSquare(j, i).hasPlayer()) {

                            img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                        } else {
                            img = new ImageView(Editor.class.getResource("/images/floor.png").toString());
                            setStaticImage(img, j, i);
                            img = new ImageView(Editor.class.getResource("/images/doorEntry.png").toString()); //attention doorEntry.png
                            setStaticImage(img, j, i);
                        }
                        break;
                    case "vault":
                        img = new ImageView(Editor.class.getResource("/images/vault.png").toString());
                        setStaticImage(img, j, i);
                        break;
                    default:
                        System.out.println("Error : invalid element read ");
                }
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                insertImages();
            }
        });

    }

}
