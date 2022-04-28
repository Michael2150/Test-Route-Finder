package com.example.testroutefinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /*public static void main(String[] args) {
        Random random = new Random();
        GraphNodeAL2<String> a = new GraphNodeAL2<>("A");
        GraphNodeAL2<String> b = new GraphNodeAL2<>("B");
        GraphNodeAL2<String> c = new GraphNodeAL2<>("C");
        GraphNodeAL2<String> d = new GraphNodeAL2<>("D");
        GraphNodeAL2<String> e = new GraphNodeAL2<>("E");
        GraphNodeAL2<String> f = new GraphNodeAL2<>("F");
        GraphNodeAL2<String> g = new GraphNodeAL2<>("G");
        GraphNodeAL2<String> h = new GraphNodeAL2<>("H");

        a.connectToNodeUndirected(b, random.nextInt(5));
        a.connectToNodeUndirected(c, random.nextInt(5));
        b.connectToNodeUndirected(c, random.nextInt(5));
        b.connectToNodeUndirected(d, random.nextInt(5));
        c.connectToNodeUndirected(e, random.nextInt(5));
        d.connectToNodeUndirected(h, random.nextInt(5));
        d.connectToNodeUndirected(g, random.nextInt(5));
        e.connectToNodeUndirected(g, random.nextInt(5));
        e.connectToNodeUndirected(f, random.nextInt(5));
        f.connectToNodeUndirected(g, random.nextInt(5));
        g.connectToNodeUndirected(h, random.nextInt(5));

        System.out.println("Path from A to H");
        CostedPath cpa = Dijkstra.findCheapestPathDijkstra(a, "H");

        for (GraphNodeAL2<?> node : cpa.pathList) {
            System.out.println(node.data);
        }
        System.out.println("\nThe total path cost is: " + cpa.pathCost);
    }*/
}