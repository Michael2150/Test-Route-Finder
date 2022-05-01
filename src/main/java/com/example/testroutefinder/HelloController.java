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
import java.util.LinkedList;

public class HelloController implements Initializable {
    public ImageView overlayImage;
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
            Globals.pixelGraph = ScanData.readInGraph(blueImage);
            for (Exhibit e : Globals.exhibits) {
                e.setPosition(Globals.getNearestPixel(Globals.pixelGraph, e.getPosition()));
            }
            System.out.println("Graph loaded " + Globals.pixelGraph.getNodes().size());
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
        Exhibit source = sourceBox.getSelectionModel().getSelectedItem(); //get the source
        Exhibit destination =  destinationBox.getSelectionModel().getSelectedItem(); //get the destination
        LinkedList<Exhibit> exhibitList = new LinkedList<>(); //create a list to store the path

        GraphNodeAL2<Exhibit> sourceNode = Globals.exhibitGraph.getNode(source); //get the source node

        var res = Dijkstra.findCheapestPathDijkstra(sourceNode, destination); //find the cheapest path
        exhibitList.addAll(res); //add the path to the list
        var cost = Dijkstra.findPathCost(res, sourceNode); //find the cost of the path

        loadPath(exhibitList); //load the path
        assert res != null;
        for (var n : res) { //for each node in the path
            System.out.println(n);
        }
        System.out.println("Total cost: " + cost + "\n Path:" + res); //print the cost and path
    }

    public void loadPath(LinkedList<Exhibit> exhibits) { //load the path
        var pixels = Utils.getPixelsBetween(Globals.pixelGraph, exhibits); //get the pixels between the path
        var imagePath = Utils.createPath((int) image.getFitWidth(), (int) image.getFitHeight(), pixels); //create the image of the path
        overlayImage.setImage(imagePath); //set the image of the path to the overlay image
    }

}