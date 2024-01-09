package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationPresenter {
    @FXML
    private TextField movesTextField;



    public void onSimulationStartClicked(){
       String[] moves = movesTextField.getText().split("\\s+");
        System.out.println("System wystartował");
        try {

            SimulationWindowPresenter newPresenter = createNewSimulationWindow();
            List<MoveDirection> directions = OptionsParser.parse(moves);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(6,3));
            List<Simulation> simulations = new ArrayList<>();
            GrassField grassField = new GrassField(10);
            newPresenter.setWorldMap(grassField);

            simulations.add(new Simulation(positions,directions,grassField));
            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsyncInThreadPool();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("System Zakończył działanie");
    }
    private SimulationWindowPresenter createNewSimulationWindow() throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("simulationwindow.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationWindowPresenter newPresenter = loader.getController();
        configureStage(viewRoot, newStage);
        newStage.show();
        return newPresenter;
    }

    private static void configureStage(BorderPane viewRoot, Stage newStage) {
        Image icon = new Image("/images/pig2.png");
        newStage.getIcons().add(icon);
        Scene scene = new Scene(viewRoot);
        newStage.setScene(scene);
        newStage.setTitle("Simulation Window");
    }


}
