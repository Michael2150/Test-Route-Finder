package com.example.testroutefinder;

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
}
