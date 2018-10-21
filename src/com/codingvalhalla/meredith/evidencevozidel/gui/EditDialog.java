package com.codingvalhalla.meredith.evidencevozidel.gui;

import com.codingvalhalla.meredith.evidencevozidel.automobily.Barva;
import com.codingvalhalla.meredith.evidencevozidel.automobily.Dodavka;
import com.codingvalhalla.meredith.evidencevozidel.automobily.NakladniAutomobil;
import com.codingvalhalla.meredith.evidencevozidel.automobily.OsobniAutomobil;
import com.codingvalhalla.meredith.evidencevozidel.automobily.Palivo;
import com.codingvalhalla.meredith.evidencevozidel.automobily.Vozidlo;
import com.codingvalhalla.meredith.evidencevozidel.automobily.VozidloEnum;
import com.codingvalhalla.meredith.evidencevozidel.utills.LoznaPlocha;
import com.codingvalhalla.meredith.evidencevozidel.utills.StaticAlerts;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author Meredith
 */
public class EditDialog extends Dialog<Vozidlo> {

    private final double buttonWidth = 75;
    private DialogPane dialogPane;
    private BorderPane rootLayout;
    private GridPane rootGrid;
    private Window window;
    private Stage dialogStage;
    private Scene dialogScene;

    private Vozidlo selectedItem;
    private Object result;

    private TextField znacka;
    private TextField spz;
    private TextField rokVyroby;
    private TextField vaha;
    private ComboBox<Palivo> palivo;
    private ComboBox<Barva> barva;
    private TextField vykon;
    private TextField loznaPlocha;
    private CheckBox tazneZarizeni;
    private TextField nosnost;
    private TextField pocetNaprav;

    private LoznaPlocha loznaPlochaOut;

    private Button buttonOK;
    private Button buttonCancel;

    EditDialog(Vozidlo selectedItem) {
        this.selectedItem = selectedItem;
        window = getDialogPane().getScene().getWindow();
        window.setOnCloseRequest((WindowEvent event) -> {
            if (StaticAlerts.confirmMessage("zavrit okno")) {
                window.hide();
            } else {
                event.consume();
            }
        });

        dialogPane = getDialogPane();
        init();

        initGrid();
        initProperties();
        addToLayout();
        initListers();
        addValues();

    }

    private void init() {
        this.rootLayout = new BorderPane();
        this.rootGrid = new GridPane();
        this.dialogScene = new Scene(rootLayout, 400, 450);
        this.buttonOK = new Button("OK");
        this.buttonCancel = new Button("Cancel");
        this.znacka = new TextField();
        this.spz = new TextField();
        this.rokVyroby = new TextField();
        this.vaha = new TextField();
        this.vykon = new TextField();
        this.loznaPlocha = new TextField();
        this.nosnost = new TextField();
        this.pocetNaprav = new TextField();
        this.palivo = new ComboBox<>();
        this.barva = new ComboBox<>();
        this.tazneZarizeni = new CheckBox();
        initLists();

    }

    private void addToLayout() {
        rootLayout.setTop(initTop());
        rootLayout.setBottom(initBottom());
        rootLayout.setCenter(rootGrid);
        dialogPane.setContent(rootLayout);
    }

    private VBox initTop() {
        VBox vbox = new VBox();
        vbox.getChildren().addAll(new Label("Pro upraveni vozidla vyplnte nasledujici udaje:"), new Label("Upravovane vozidlo: " + selectedItem.getDruh()));
        return vbox;
    }

    private HBox initBottom() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buttonOK, buttonCancel);
        return hbox;
    }

    private void initProperties() {
        buttonOK.setPrefWidth(buttonWidth);
        buttonCancel.setPrefWidth(buttonWidth);
        znacka.setPrefWidth(250);
        znacka.setPromptText("Zadejte znacku vozidla");
        spz.setPromptText("Zadejte SPZ vozidla");
        rokVyroby.setPromptText("Zadejte rok vyroby (4 mistny)");
        vaha.setPromptText("Zadejte vahu v tunach (t)");
        nosnost.setPromptText("Zadejte maximalni nostnost v tunach (t)");
        pocetNaprav.setPromptText("Zadejte pocet naprav");
        loznaPlocha.setPromptText("Zadejte plochu ve tvaru \"100x100x100\" v milimetrech (mm)");
        vykon.setPromptText("Zadejte vykon v kilowattech (kW)");

        Tooltip loznaPlochaTooltip = new Tooltip(loznaPlocha.getPromptText());
        loznaPlocha.setTooltip(loznaPlochaTooltip);

        rootGrid.setHgap(10.0);
        rootGrid.setVgap(5.0);
        rootGrid.setPadding(new Insets(5, 15, 5, 15));

        for (int i = 0; i < rootGrid.getChildren().size(); i++) {
            if (i % 2 == 0) {
                GridPane.setHalignment(rootGrid.getChildren().get(i), HPos.RIGHT);
            } else {
                GridPane.setHalignment(rootGrid.getChildren().get(i), HPos.LEFT);
                GridPane.setFillWidth(rootGrid.getChildren().get(i), true);
                GridPane.setHgrow(rootGrid.getChildren().get(i), Priority.ALWAYS);
            }

        }

        barva.setMaxWidth(Double.MAX_VALUE);
        palivo.setMaxWidth(Double.MAX_VALUE);

    }

    private void initLists() {
        barva.getItems().addAll(Barva.values());
        palivo.getItems().addAll(Palivo.values());
        barva.getSelectionModel().selectFirst();
        palivo.getSelectionModel().selectFirst();

        rokVyroby.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                rokVyroby.setText(newValue.replaceAll("[^0-9]", ""));
            }
            if (newValue.length() > 4) {
                rokVyroby.setText(newValue.substring(0, 4));
            }
        });
        vaha.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String firstChar = null;
            if (newValue.length() > 0) {
                firstChar = "" + newValue.charAt(0);
                if (!firstChar.matches("[0-9]")) {
                    vaha.setText("");
                } else {
                    String output = new String(newValue);
                    int xCount = 0;
                    for (int i = 0; i < output.length(); i++) {
                        if (output.charAt(i) == ',') {
                            xCount++;
                        }
                        if (xCount == 2) {
                            output = output.substring(0, output.lastIndexOf(','));
                        }
                    }
                    if (!output.matches("[0-9]+,?[0-9]*")) {
                        output = output.replaceAll("[^0-9,]", "");
                    }

                    vaha.setText(output);
                }

            }
        });
        vykon.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String firstChar = null;
            if (newValue.length() > 0) {
                firstChar = "" + newValue.charAt(0);
                if (!firstChar.matches("[0-9]")) {
                    vykon.setText("");
                } else {
                    String output = new String(newValue);
                    int xCount = 0;
                    for (int i = 0; i < output.length(); i++) {
                        if (output.charAt(i) == ',') {
                            xCount++;
                        }
                        if (xCount == 2) {
                            output = output.substring(0, output.lastIndexOf(','));
                        }
                    }
                    if (!output.matches("[0-9]+,?[0-9]*")) {
                        output = output.replaceAll("[^0-9,]", "");
                    }

                    vykon.setText(output);
                }

            }
        });
        nosnost.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String firstChar = null;
            if (newValue.length() > 0) {
                firstChar = "" + newValue.charAt(0);
                if (!firstChar.matches("[0-9]")) {
                    nosnost.setText("");
                } else {
                    String output = new String(newValue);
                    int xCount = 0;
                    for (int i = 0; i < output.length(); i++) {
                        if (output.charAt(i) == ',') {
                            xCount++;
                        }
                        if (xCount == 2) {
                            output = output.substring(0, output.lastIndexOf(','));
                        }
                    }
                    if (!output.matches("[0-9]+,?[0-9]*")) {
                        output = output.replaceAll("[^0-9,]", "");
                    }

                    nosnost.setText(output);
                }

            }
        });
        pocetNaprav.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                pocetNaprav.setText(newValue.replaceAll("[^0-9]", ""));
            }
        });
        loznaPlocha.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            String firstChar = null;
            if (newValue.length() > 0) {
                firstChar = "" + newValue.charAt(0);
                if (!firstChar.matches("[0-9]")) {
                    loznaPlocha.setText("");
                } else {
                    String output = new String(newValue);
                    int xCount = 0;
                    for (int i = 0; i < output.length(); i++) {
                        if (output.charAt(i) == 'x') {
                            xCount++;
                        }
                        if (xCount == 2) {
                            if (output.charAt(output.lastIndexOf('x') - 1) == 'x') {
                                output = output.substring(0, output.lastIndexOf('x'));
                            }
                        }
                        if (xCount == 3) {
                            output = output.substring(0, output.lastIndexOf('x'));
                        }
                    }
                    if (!output.matches("^[0-9]+x?[0-9]*x?[0-9]*$")) {
                        output = output.replaceAll("[^0-9x]", "");
                    }

                    loznaPlocha.setText(output);
                }

            }
        });

        barva.setCellFactory((ListView<Barva> p) -> new ListCell<Barva>() {
            private final Rectangle rectangle;

            {
                setContentDisplay(ContentDisplay.LEFT);
                rectangle = new Rectangle(30, 15);
                rectangle.setStroke(Color.BLACK);
            }

            @Override
            protected void updateItem(Barva item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    Color color;
                    switch (item) {
                        case BLACK:
                            color = Color.BLACK;
                            break;
                        case WHITE:
                            color = Color.WHITE;
                            break;
                        case RED:
                            color = Color.RED;
                            break;
                        case GRAY:
                            color = Color.GRAY;
                            break;
                        case GREEN:
                            color = Color.GREEN;
                            break;
                        case YELLOW:
                            color = Color.YELLOW;
                            break;
                        case SILVER:
                            color = Color.SILVER;
                            break;
                        case BLUE:
                            color = Color.BLUE;
                            break;
                        default:
                            color = null;
                    }

                    rectangle.setFill(color);
                    setText(item.toString());
                    setGraphic(rectangle);
                }
            }
        });

    }

    private void initGrid() {
        rootGrid.addRow(0, new Label("Znacka:"), znacka);
        rootGrid.addRow(1, new Label("SPZ:"), spz);
        rootGrid.addRow(2, new Label("Rok vyroby:"), rokVyroby);
        rootGrid.addRow(3, new Label("Vaha:"), vaha);
        rootGrid.addRow(4, new Label("Palivo:"), palivo);
        if (selectedItem.getDruh() == VozidloEnum.OSOBNI_AUTOMOBIL) {
            rootGrid.addRow(5, new Label("Barva:"), barva);
            rootGrid.addRow(6, new Label("Vykon:"), vykon);
        }
        if (selectedItem.getDruh() == VozidloEnum.DODAVKA) {
            rootGrid.addRow(5, new Label("Lozna plocha:"), loznaPlocha);
            rootGrid.addRow(6, new Label("Tazne zarizeni:"), tazneZarizeni);
        }
        if (selectedItem.getDruh() == VozidloEnum.NAKLADNI_AUTOMOBIL) {
            rootGrid.addRow(5, new Label("Nostnost:"), nosnost);
            rootGrid.addRow(6, new Label("Pocet naprav:"), pocetNaprav);
        }
        znacka.setDisable(true);
        rokVyroby.setDisable(true);

    }

    private boolean isValid() {
        String errorMessage = "";

        if (znacka.getText() == null || znacka.getText().length() == 0) {
            errorMessage += "Neni zadana znacka.\n";
        }

        if (spz.getText() == null || spz.getText().length() == 0) {
            errorMessage += "Neni zadana SPZ.\n";
        }

        if (rokVyroby.getText() == null || rokVyroby.getText().length() != 4) {
            errorMessage += "Neni zadan rok vyroby nebo je neplatny\n";
        }

        if (vaha.getText() == null || vaha.getText().length() == 0) {
            errorMessage += "Neni zadana vaha.\n";
        }
        if (palivo.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Neni zvoleno palivo.\n";
        }
        if (selectedItem.getDruh() == VozidloEnum.OSOBNI_AUTOMOBIL) {
            if (barva.getSelectionModel().getSelectedItem() == null) {
                errorMessage += "Neni zvolena barva.\n";
            }
            if (vykon.getText() == null || vykon.getText().length() == 0) {
                errorMessage += "Neni zadan vykon.\n";
            }

        } else if (selectedItem.getDruh() == VozidloEnum.NAKLADNI_AUTOMOBIL) {
            if (nosnost.getText() == null || nosnost.getText().length() == 0) {
                errorMessage += "Neni zadana nostnost.\n";
            }
            if (pocetNaprav.getText() == null || pocetNaprav.getText().length() == 0) {
                errorMessage += "Neni zadan pocet naprav.\n";
            }
        } else if (selectedItem.getDruh() == VozidloEnum.DODAVKA) {
            if (loznaPlocha.getText() == null || loznaPlocha.getText().length() == 0) {
                errorMessage += "Neni zadana lozna plocha.\n";
            } else {
                try {
                    int xCount = 0;
                    for (int i = 0; i < loznaPlocha.getText().length(); i++) {
                        if (loznaPlocha.getText().charAt(i) == 'x') {
                            xCount++;
                        }
                    }
                    if (xCount != 2) {
                        throw new NumberFormatException();
                    }

                    int[] temp1 = new int[3];
                    String[] temp2 = loznaPlocha.getText().split("x");
                    for (int i = 0; i < 3; i++) {
                        temp1[i] = Integer.parseInt(temp2[i]);
                    }
                    loznaPlochaOut = new LoznaPlocha(temp1);
                } catch (NumberFormatException e) {
                    errorMessage += "Chyba zadani lozne plochy. - Tvar \"100x100x100\"\n";
                }
            }

        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            StaticAlerts.errorWithMessege(errorMessage, "Opravte zadané údaje", dialogStage);
            return false;
        }

    }
    private EventHandler<ActionEvent> dialogOKEvent = (ActionEvent event) -> {
        if (isValid()) {
            selectedItem.setSPZ(spz.getText());
            selectedItem.setVAHA(Double.parseDouble(vaha.getText().replace(",", ".")));
            selectedItem.setPalivo(palivo.getValue());

            if (selectedItem.getDruh() == VozidloEnum.OSOBNI_AUTOMOBIL) {
                OsobniAutomobil selectedItemOA = (OsobniAutomobil) selectedItem;
                selectedItemOA.setBarva(barva.getValue());
                selectedItemOA.setVykon(Double.parseDouble(vykon.getText().replace(",", ".")));
            } else if (selectedItem.getDruh() == VozidloEnum.NAKLADNI_AUTOMOBIL) {
                NakladniAutomobil selectedItemNA = (NakladniAutomobil) selectedItem;
                selectedItemNA.setNosnost(Double.parseDouble(nosnost.getText().replace(",", ".")));
                selectedItemNA.setPocetNaprav(Integer.parseInt(pocetNaprav.getText()));

            } else if (selectedItem.getDruh() == VozidloEnum.DODAVKA) {
                Dodavka selectedItemD = (Dodavka) selectedItem;
                selectedItemD.setLoznaPlocha(loznaPlochaOut);
                selectedItemD.setTazneZarizeni(tazneZarizeni.isSelected());
            }

        }
        setResult(selectedItem);

    };

    private EventHandler<ActionEvent> dialogCancelEvent = (ActionEvent event) -> {
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    };

    private void initListers() {

        buttonOK.setOnAction(dialogOKEvent);
        buttonCancel.setOnAction(dialogCancelEvent);
    }

    private void addValues() {
        znacka.setText(selectedItem.getZNACKA());
        spz.setText(selectedItem.getSPZ());
        rokVyroby.setText(Integer.toString(selectedItem.getROK_VYROBY()));
        vaha.setText(Double.toString(selectedItem.getVAHA()).replace(".", ","));
        palivo.getSelectionModel().select(selectedItem.getPalivo());
        if (selectedItem.getDruh() == VozidloEnum.OSOBNI_AUTOMOBIL) {
            OsobniAutomobil selectedItemOA = (OsobniAutomobil) selectedItem;
            barva.getSelectionModel().select(selectedItemOA.getBarva());;
            vykon.setText(Double.toString(selectedItemOA.getVykon()).replace(".", ","));
        }
        if (selectedItem.getDruh() == VozidloEnum.DODAVKA) {
            Dodavka selectedItemD = (Dodavka) selectedItem;
            int[] temp = selectedItemD.getLoznaPlocha().getRozmery();
            loznaPlocha.setText(temp[0] + "x" + temp[1] + "x" + temp[2]);
            tazneZarizeni.setSelected(selectedItemD.isTazneZarizeni());
        }
        if (selectedItem.getDruh() == VozidloEnum.NAKLADNI_AUTOMOBIL) {
            NakladniAutomobil selectedItemNA = (NakladniAutomobil) selectedItem;
            nosnost.setText(Double.toString(selectedItemNA.getNosnost()).replace(".", ","));
            pocetNaprav.setText(Integer.toString(selectedItemNA.getPocetNaprav()));
        }
    }
}
