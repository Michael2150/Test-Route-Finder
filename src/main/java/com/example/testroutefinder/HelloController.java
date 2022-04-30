package com.example.testroutefinder;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class HelloController implements Initializable {
    @FXML
    private Pane imagePane;
    @FXML
    private ComboBox<Exhibit> sourceBox;
    @FXML
    private ComboBox<Exhibit> destinationBox;
    @FXML
    private Button runDijkstra;
    @FXML
    private ImageView image;
    public Image inputImage, blueImage;

    //implement Initializable
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        image.setImage(new Image("https://i.imgur.com/iI1fqoV.png", 674, 460, false, false));
        blueImage = new Image("https://i.imgur.com/LVRj3Jr.png", 337, 230, false, false);
        inputImage = image.getImage();

        Globals.exhibits = ScanData.readInData();
        Globals.exhibitGraph = Globals.makeGraph(Globals.exhibits);

        Thread loadThread;
        loadThread = new Thread(() -> {
            //start timer
            long startTime = System.currentTimeMillis();
            ScanData.readInGraph(blueImage);
            for (Exhibit e : Globals.exhibits) {
                e.setPosition(Globals.getNearestPixel(Globals.pixelGraph, e.getPosition()));
            }
            System.out.println("Graph loaded");
            //print time taken
            long endTime = System.currentTimeMillis();
            System.out.println("Time taken: " + (endTime - startTime) + "ms");
        });
        loadThread.start();

        fillComboBoxes();
    }

    public void fillComboBoxes() {
        sourceBox.getItems().addAll(Globals.exhibits);
        destinationBox.getItems().addAll(Globals.exhibits);
    }


    @FXML
    private void runDijkstra(MouseEvent mouseEvent) {
        Exhibit source = sourceBox.getSelectionModel().getSelectedItem();
        Exhibit destination =  destinationBox.getSelectionModel().getSelectedItem();

        GraphNodeAL2<Exhibit> sourceNode = Globals.exhibitGraph.getNode(source);

        var res = Dijkstra.findCheapestPathDijkstra(sourceNode, destination);
        var cost = Dijkstra.findPathCost(res, sourceNode);

        assert res != null;
        for (var n : res) {
            System.out.println(n);
        }
        System.out.println("Total cost: " + cost + "\n Path:" + res);
    }

    public void createPath(ArrayList<Integer> paths, int index) {
        Paint paint;
        if (index == 0) {
            paint = Color.RED;
        } else if (index == 1) {
            paint = Color.GREEN;
        } else {
            paint = Color.BLUE;
        }
        for (int path : paths) {
            double x = path % image.getImage().getWidth();
            double y = path / image.getImage().getWidth() + 1;

            Circle circle = new Circle();
            circle.setLayoutX(x);
            circle.setLayoutY(y);
            circle.setRadius(1);
            circle.setFill(paint);
            imagePane.getChildren().add(circle);
        }
    }

}