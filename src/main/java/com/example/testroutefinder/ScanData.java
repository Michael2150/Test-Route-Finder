package com.example.testroutefinder;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class ScanData {

    public static LinkedList<Exhibit> readInData(){
        LinkedList<Exhibit> exhibits = new LinkedList<>();
        File filepath = new File("src/main/java/com/example/testroutefinder/Data/data.txt");
        String delimiter = "\t";

        final int EXHIBIT_ID = 0;
        final int EXHIBIT_NAME = 1;
        final int EXHIBIT_DESCRIPTION = 2;
        final int EXHIBIT_X = 3;
        final int EXHIBIT_Y = 4;
        final int EXHIBIT_CONNECTIONS = 5;
        final int EXHIBIT_COST = 6;


        try  (FileReader fr = new FileReader(filepath)) {
            try (BufferedReader br = new BufferedReader(fr)) {
                String currLine;
                int lineNumber = 0;

                while ((currLine = br.readLine()) != null) {
                    if(lineNumber == 0) {
                        lineNumber++;
                        continue;
                    }

                    String[] data = currLine.split(delimiter);

                    //remove quotations from the data
                    for (int i = 0; i < data.length; i++) {
                        data[i] = data[i].replaceAll("\"", "");
                    }


                    Exhibit exhibit = new Exhibit();
                    exhibit.setId(Integer.parseInt(data[EXHIBIT_ID]));
                    exhibit.setName(data[EXHIBIT_NAME]);
                    exhibit.setDescription(data[EXHIBIT_DESCRIPTION]);
                    exhibit.setCost(Integer.parseInt(data[EXHIBIT_COST]));

                    int x = Integer.parseInt(data[EXHIBIT_X]);
                    int y = Integer.parseInt(data[EXHIBIT_Y]);
                    exhibit.setPosition(new Pixel(x, y));

                    String[] connections = data[EXHIBIT_CONNECTIONS].split(",");
                    LinkedList<Integer> connectionList = new LinkedList<>();
                    List.of(connections).forEach(connection -> connectionList.add(Integer.parseInt(connection)));
                    exhibit.setConnections(connectionList);

                    exhibits.add(exhibit);

                    //Utils.save();
                }



            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exhibits;
    }

    public static myGraph<Pixel> readInGraph(Image graphImage) {
        myGraph<Pixel> graph = new myGraph<>();

        int width = (int) graphImage.getWidth();
        int height = (int) graphImage.getHeight();

        PixelReader pr = graphImage.getPixelReader();

        Color c = pr.getColor(0, 0);

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color color = pr.getColor(x, y);

                if(color.equals(c)) {
                    Pixel newNode = new Pixel(x, y).multiply(2);
                    graph.addNode(newNode);

                    Color above = (y - 1 >= 0) ? pr.getColor(x, y - 1) : Color.WHITE;
                    Color toLeft = (x - 1 >= 0) ? pr.getColor(x - 1, y) : Color.WHITE;

                    if(above.equals(c)){
                        Pixel newNode1 = new Pixel(x, y - 1).multiply(2);
                        graph.addNode(newNode1);
                        graph.addLink(newNode, newNode1);
                    }
                    if (toLeft.equals(c)) {
                        Pixel newNode1 = new Pixel(x - 1, y).multiply(2);
                        graph.addNode(newNode1);
                        graph.addLink(newNode, newNode1);
                    }
                }
            }
        }
        return graph;
    }
}
