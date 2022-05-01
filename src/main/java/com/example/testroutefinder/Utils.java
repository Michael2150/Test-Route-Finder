package com.example.testroutefinder;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class Utils {

    public static LinkedList<Pixel> getPixelsBetween(myGraph<Pixel> pixels, LinkedList<Exhibit> exhibits){
        if (pixels == null || exhibits == null){
            return null;
        }

        var path = new LinkedList<Pixel>();
        for (int i = 0; i < exhibits.size() - 1; i++){
            var startPoint = exhibits.get(i).getPosition();
            var endPoint = exhibits.get(i + 1).getPosition();
            var links = BFS.bfs(pixels, startPoint, endPoint);
            path.addAll(links);
        }
        return path;
    }

    public static Image createPath(LinkedList<LinkedList<Pixel>> pixels, int width, int height){
        if (pixels == null){
            return null;
        }

        WritableImage image = new WritableImage(width, height);
        PixelWriter pixelWriter = image.getPixelWriter();


        for (var pxls : pixels){
            Color color = new Color(1, 0, 0, 1);
            for (var pxl : pxls){
                var radius = 5;
                for (int x = -radius; x <= radius; x++){
                    for (int y = -radius; y <= radius; y++){
                        if (pxl.getX() + x >= 0 && pxl.getX() + x < width && pxl.getY() + y >= 0 && pxl.getY() + y < height){
                            pixelWriter.setColor((int) (pxl.getX() + x), (int) (pxl.getY() + y), color);
                        }
                    }
                }
            }
        }
        return image;
    }

    public static Image createPath(int width, int height, LinkedList<Pixel> pixels){
        if (pixels == null){
            return null;
        }

        var pixelList = new LinkedList<LinkedList<Pixel>>();
        pixelList.add(pixels);
        return createPath(pixelList, width, height);
    }
}
