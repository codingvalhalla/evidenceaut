/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingvalhalla.meredith.evidencevozidel;

import com.codingvalhalla.meredith.evidencevozidel.gui.GraphicUserInterface;
import com.codingvalhalla.meredith.evidencevozidel.utills.StaticAlerts;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Meredith
 */
public class FXMain extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage stage) {
        mainStage = stage;

        GraphicUserInterface gui = null;
        try {
            gui = new GraphicUserInterface(mainStage);
        } catch (IllegalAccessException e) {
            StaticAlerts.exceptionDialog(e, e.getLocalizedMessage(), mainStage);
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
