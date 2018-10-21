package com.codingvalhalla.meredith.evidencevozidel.gui;

import com.codingvalhalla.meredith.evidencevozidel.FillData;
import com.codingvalhalla.meredith.evidencevozidel.kolekce.DataHandler;
import com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException;
import com.codingvalhalla.meredith.evidencevozidel.automobily.Vozidlo;
import com.codingvalhalla.meredith.evidencevozidel.automobily.VozidloEnum;
import com.codingvalhalla.meredith.evidencevozidel.utills.StaticAlerts;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Meredith
 */
public class GraphicUserInterface {

    private final String title = "Evidence automobilů";
    private final static String RESOURCES = "/com/codingvalhalla/meredith/evidencevozidel/resources/";
    private ListView<Vozidlo> listVozidelLW;
    private BorderPane rootLayout;
    private Stage mainStage;
    private Scene scene;
    private static boolean alreadyExecuted = false;
    private Button addButton;
    private Button removeButton;
    private Button testButton;
    private Button editButton;
    private Button saveButton;
    private Button restoreButton;
    private Button zrusFiltrButton;

    private ToggleGroup groupVozidla;
    private RadioButton buttonOA;
    private RadioButton buttonNA;
    private RadioButton buttonD;
    private RadioButton buttonNone;

    private DataHandler data;
    private Vozidlo editovaneVozidlo;

    public GraphicUserInterface(Stage stage) throws IllegalAccessException {
        if (!alreadyExecuted) {
            mainStage = stage;
            data = new DataHandler();
            mainStage.setTitle(title);
            initRootLayout();
            createListerers();
            alreadyExecuted = true;
        } else {
            throw new IllegalAccessException("Cannot make more than one GUI");
        }
    }

    public static String getResources() {
        return RESOURCES;
    }

    private void initRootLayout() {
        rootLayout = new BorderPane();
        scene = new Scene(rootLayout, 900, 400);
        rootLayout.setTop(initMenu());
        rootLayout.setLeft(initLeftStage());
        rootLayout.setBottom(initBottomStage());
        rootLayout.setRight(initRightStage());
        mainStage.setScene(scene);
        mainStage.show();

    }

    private MenuBar initMenu() {
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(mainStage.widthProperty());
        Menu fileMenu = new Menu("_File");
        fileMenu.setMnemonicParsing(true);
        MenuItem closeMenuItem = new MenuItem("Close");
        closeMenuItem.setOnAction((ActionEvent event) -> {
            mainStage.fireEvent(new WindowEvent(mainStage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
        fileMenu.getItems().add(closeMenuItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }

    private AnchorPane initRightStage() {
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(mainStage.widthProperty().add(-160));
        listVozidelLW = new ListView<>();
        listVozidelLW.getStylesheets().add(GraphicUserInterface.class.getResource("ListView_CSS.css").toExternalForm());
        AnchorPane.setTopAnchor(listVozidelLW, 0.0);
        AnchorPane.setLeftAnchor(listVozidelLW, 0.0);
        AnchorPane.setRightAnchor(listVozidelLW, 0.0);
        AnchorPane.setBottomAnchor(listVozidelLW, 0.0);
        pane.getChildren().addAll(listVozidelLW);
        return pane;
    }

    private HBox initBottomStage() {
        double buttonWidth = 85;
        HBox result = new HBox();
        result.setPadding(new Insets(15, 12, 15, 12));
        result.setSpacing(10);
        result.setPrefHeight(50);
        addButton = new Button("Novy");
        removeButton = new Button("Zrus");
        testButton = new Button("Test");
        editButton = new Button("Zmen");
        saveButton = new Button("Uloz");
        restoreButton = new Button("Obnov");
        zrusFiltrButton = new Button("Zrus filtraci");
        addButton.setPrefWidth(buttonWidth);
        removeButton.setPrefWidth(buttonWidth);
        result.getChildren().addAll(addButton, editButton, removeButton, saveButton, restoreButton, testButton, zrusFiltrButton);
        for (int i = 0; i < result.getChildren().size(); i++) {
            Button temp = new Button();
            temp = (Button) result.getChildren().get(i);
            temp.setPrefWidth(buttonWidth);
        }
        return result;
    }

    private void createListerers() {
        mainStage.setOnCloseRequest((WindowEvent event) -> {
            if (StaticAlerts.confirmMessage("vypnout program")) {
                System.exit(0);
            } else {
                event.consume();
            }
        });

        listVozidelLW.setOnMouseClicked((event) -> {
            if (event.getClickCount() == 2) {
                editButton.fire();
            }
        });

        addButton.setOnAction(handlerAddButton);
        editButton.setOnAction(handlerEditButton);
        editButton.disableProperty().bind(listVozidelLW.getSelectionModel().selectedItemProperty().isNull());
        removeButton.setOnAction(handlerRemoveButton);
        removeButton.disableProperty().bind(listVozidelLW.getSelectionModel().selectedItemProperty().isNull());
        saveButton.setOnAction(handlerSaveButton);
        restoreButton.setOnAction(handlerRestoreButton);
        testButton.setOnAction(handlerTestButton);
        zrusFiltrButton.setOnAction(handlerZrusFiltraciButton);

    }

    public ListView<Vozidlo> getListVozidelLW() {
        return listVozidelLW;
    }

    private VBox initLeftStage() {
        this.groupVozidla = new ToggleGroup();
        this.buttonOA = new RadioButton("Osobni automobil");
        this.buttonNA = new RadioButton("Nakladni automobil");
        this.buttonD = new RadioButton("Dodavka");
        this.buttonNone = new RadioButton("Bez filtrace");
        initRadioButtons();
        VBox result = new VBox();
        result.setMinWidth(150);
        result.setMaxWidth(150);
        result.setAlignment(Pos.BASELINE_LEFT);
        result.setSpacing(5);
        result.setPadding(new Insets(10, 0, 0, 10));
        result.getChildren().addAll(buttonOA, buttonD, buttonNA, buttonNone);
        return result;
    }

    private void initRadioButtons() {
        buttonOA.setToggleGroup(groupVozidla);
        buttonNA.setToggleGroup(groupVozidla);
        buttonD.setToggleGroup(groupVozidla);
        buttonNone.setToggleGroup(groupVozidla);
        buttonNone.setSelected(true);

        buttonOA.setOnAction((event) -> {
            refreshListView();
        });
        buttonNA.setOnAction((event) -> {
            refreshListView();
        });
        buttonD.setOnAction((event) -> {
            refreshListView();
        });
        buttonNone.setOnAction(event -> {
            refreshListView();
        });

    }

    private EventHandler<ActionEvent> handlerAddButton = (ActionEvent event) -> {
        DialogVozidlo add = new DialogVozidlo();
        add.setTitle("Pridej vozidlo");
        add.show();
        add.resultProperty().addListener((observable, oldValue, newValue) -> {
            try {
                data.getSeznam().pridej(newValue);
                refreshListView();
            } catch (KolekceException ex) {
                StaticAlerts.exceptionDialog(ex, ex.getLocalizedMessage(), mainStage);
            }
        });

    };

    private EventHandler<ActionEvent> handlerEditButton = (ActionEvent event) -> {
        editovaneVozidlo = data.getSeznam().najdi(listVozidelLW.getSelectionModel().getSelectedItem());
        DialogVozidlo edit = new DialogVozidlo(editovaneVozidlo);
        edit.setTitle("Uprav vozidlo");
        edit.show();
        edit.resultProperty().addListener((observable, oldValue, newValue) -> {
            try {
                editovaneVozidlo = newValue;
                refreshListView();
            } catch (Exception ex) {
                StaticAlerts.exceptionDialog(ex, ex.getLocalizedMessage(), mainStage);
            }
        });
        
    };
    private EventHandler<ActionEvent> handlerRemoveButton = (ActionEvent event) -> {
        data.getSeznam().odeber(listVozidelLW.getSelectionModel().getSelectedItem());
        refreshListView();
    };

    private EventHandler<ActionEvent> handlerSaveButton = (ActionEvent event) -> {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;

        try {
            fileOut = new FileOutputStream("zaloha.bin");
            out = new ObjectOutputStream(fileOut);
            Function<Integer, Vozidlo[]> s = Vozidlo[]::new;
            Vozidlo[] array = data.getSeznam().toArray(s);
            out.writeInt(array.length);
            for (int i = 0; i < array.length; i++) {
                out.writeObject(array[i]);
            }

        } catch (Exception ex) {
            StaticAlerts.exceptionDialog(ex, ex.getLocalizedMessage(), mainStage);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileOut != null) {
                    fileOut.close();
                }

            } catch (Exception ex) {
                StaticAlerts.exceptionDialog(ex, ex.getLocalizedMessage(), mainStage);
            }
        }

    };

    private EventHandler<ActionEvent> handlerRestoreButton = (ActionEvent event) -> {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream("zaloha.bin");
            in = new ObjectInputStream(fileIn);
            data.getSeznam().zrus();
            int iMax = in.readInt();
            for (int i = 0; i < iMax; i++) {
                Vozidlo temp = (Vozidlo) in.readObject();
                data.getSeznam().pridej(temp);
            }

        } catch (Exception ex) {
            StaticAlerts.exceptionDialog(ex, ex.getLocalizedMessage(), mainStage);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (fileIn != null) {
                    fileIn.close();
                }

            } catch (Exception ex) {
                StaticAlerts.exceptionDialog(ex, ex.getLocalizedMessage(), mainStage);
            }
        }

        refreshListView();
    };

    private EventHandler<ActionEvent> handlerTestButton = (ActionEvent event) -> {
        if (data.getSeznam().jePrazdny()) {
            try {
                data.getSeznam().pridej(FillData.listData);
            } catch (KolekceException ex) {
                StaticAlerts.exceptionDialog(ex, ex.getLocalizedMessage(), mainStage);
            }
        } else {
            StaticAlerts.infoMessage("Test", "Nepovedlo se přidat položky, protože seznam není prázdný");
        }
        refreshListView();
    };

    private EventHandler<ActionEvent> handlerZrusFiltraciButton = (ActionEvent event) -> {
        buttonNone.setSelected(true);
        refreshListView();
    };

    private void refreshListView() {
        listVozidelLW.getItems().clear();
        Function<Integer, Vozidlo[]> s = Vozidlo[]::new;
        Vozidlo[] array = data.getSeznam().toArray(s);
        Stream<Vozidlo> stream = Stream.of(array);
        Consumer<? super Vozidlo> addVozidlo = (t -> listVozidelLW.getItems().add(t));

        if (buttonNone.isSelected()) {
            stream.forEach(addVozidlo);
        } else if (buttonD.isSelected()) {
            stream.filter(t -> t.getDruh() == VozidloEnum.DODAVKA).forEach(addVozidlo);
        } else if (buttonNA.isSelected()) {
            stream.filter(t -> t.getDruh() == VozidloEnum.NAKLADNI_AUTOMOBIL).forEach(addVozidlo);
        } else if (buttonOA.isSelected()) {
            stream.filter(t -> t.getDruh() == VozidloEnum.OSOBNI_AUTOMOBIL).forEach(addVozidlo);
        }
    }
}
