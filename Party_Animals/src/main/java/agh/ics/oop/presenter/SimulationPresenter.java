package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulationPresenter implements MapChangeListener {
    @FXML
    private Label infoLabel;
    @FXML
    private TextField movesTextField;
    @FXML
    private GridPane mapGrid;
    private  WorldMap map;
    private final  static double CELL_WIDTH=35;
    private final  static double CELL_HEIGHT=35;
    public void setWorldMap(WorldMap map){
        this.map=map;
    }
    private void createGrid(Boundary boundary){
        Vector2d lhvector=boundary.rightUpper().subtract(boundary.leftLower());
        int x = boundary.leftLower().getX();
        int y = boundary.rightUpper().getY();
        for(int i=0;i<=lhvector.getX()+1;i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int i=0;i<=lhvector.getY()+1;i++){
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
        for (int i=1;i<=lhvector.getX()+1;i++){
            Label label = new Label(""+x);
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label,i,0);
            x+=1;
        }
        for (int i=1;i<=lhvector.getY()+1;i++){
            Label label = new Label(""+y);
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label,0,i);
            y-=1;
        }
        Label label = new Label("x/y");
        GridPane.setHalignment(label, HPos.CENTER);
        mapGrid.add(label, 0, 0);
    }
    public void drawMap(WorldMap worldMap){
        clearGrid();
        Boundary boundary =  worldMap.getCurrentBounds();
        createGrid(boundary);
        drawWorldElements(worldMap,boundary);
        mapGrid.setAlignment(Pos.CENTER);

    }
    private void drawWorldElements(WorldMap worldMap,Boundary boundary){
        int x = boundary.leftLower().getX();
        int y = boundary.leftLower().getY();
        Vector2d lhvector=boundary.rightUpper().subtract(boundary.leftLower());
        int height=lhvector.getY();
        Map<Vector2d,WorldElement> elements= worldMap.getElements();
        elements.forEach((key,value)->{
            System.out.println(key.toString());
            Label label = new Label(value.toString());
            GridPane.setHalignment(label, HPos.CENTER);
            mapGrid.add(label,key.getX()-x+1,height-(key.getY()-y)+1);
        } );
    }
    public void onSimulationStartClicked(){
       String[] moves = movesTextField.getText().split("\\s+");
        System.out.println("System wystartował");
        try {
            List<MoveDirection> directions = OptionsParser.parse(moves);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(6,3));
            ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();

            List<Simulation> simulations = new ArrayList<>();

            GrassField grassField = new GrassField(10);
            this.setWorldMap(grassField);
            grassField.addObserver(this);
            simulations.add(new Simulation(positions,directions,grassField));
            SimulationEngine engine = new SimulationEngine(simulations);
            engine.runAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("System Zakończył działanie");
    }
    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0)); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(()-> {
            drawMap(worldMap);
            System.out.println(worldMap.toString());
            infoLabel.setText(message);
        });

    }
}
