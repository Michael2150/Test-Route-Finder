package com.example.testroutefinder;

import java.util.LinkedList;

public class Globals {

    public static LinkedList<Exhibit> exhibits = new LinkedList<>();
    public static myGraph<Pixel> pixelGraph = new myGraph<>();
    public static myGraph<Exhibit> exhibitGraph = new myGraph<>();

    public static myGraph<Exhibit> makeGraph(LinkedList<Exhibit> exhibits) {
        myGraph<Exhibit> graph = new myGraph<>();

        for (Exhibit exhibit : exhibits) {
            graph.addNode(exhibit);
        }

        for (Exhibit exhibit : exhibits) {
            for (Integer link : exhibit.getConnections()) {
                for (Exhibit otherExhibit : exhibits) {
                    if (otherExhibit.getId() == link) {
                        graph.addLink(exhibit, otherExhibit);
                    }
                }
            }
        }
        return graph;
    }

    public static Pixel getNearestPixel(myGraph<Pixel> graph, Pixel pixel) {
        GraphNodeAL2<Pixel> node = graph.getNode(pixel);
        Pixel nearestPixel = null;

        if (node == null) {
            double minDistance = Double.POSITIVE_INFINITY;
            for(Pixel pixelNode : graph.getNodeValue()) {
                double distance = pixelNode.distance(pixel);

                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPixel = pixelNode;
                }
            }
        }
        else {
            nearestPixel = node.getData();
        }
        return nearestPixel;
    }
}
