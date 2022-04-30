package com.example.testroutefinder;

import java.util.HashMap;
import java.util.LinkedList;

public class BFS {

    public static <O> LinkedList<O> bfs(myGraph<O> graph, O start, O end) {
        var startPoint = graph.getNode(start);
        var endPoint = graph.getNode(end);

        if (startPoint == null || endPoint == null) {
            throw new IllegalArgumentException("Start or end point not found or does not exist in the graph");
        }

        HashMap<GraphNodeAL2<O>, Boolean> visited = new HashMap<>();
        HashMap<GraphNodeAL2<O>, GraphNodeAL2<O>> previous = new HashMap<>();
        LinkedList<GraphNodeAL2<O>> queue = new LinkedList<>();

        for (GraphNodeAL2<O> node : graph.getNodes()) {
            visited.put(node, false);
            previous.put(node, null);
        }

        visited.put(startPoint, true);
        queue.add(startPoint);

        GraphNodeAL2<O> current;
        while (!queue.isEmpty() && !visited.get(endPoint)) {
            current = queue.removeFirst();
            for(var adjacent : current.adjList){
                var adjacentNode = adjacent.destNode;
                if(!visited.get(adjacentNode)){
                    visited.put((GraphNodeAL2<O>) adjacentNode, true);
                    previous.put((GraphNodeAL2<O>) adjacentNode, current);
                    queue.add((GraphNodeAL2<O>) adjacentNode);
                }
            }
        }

        LinkedList<O> path = new LinkedList<>();
        current = endPoint;
        while (previous.get(current) != null) {
            path.addFirst(current.data);
            current = previous.get(current);
        }
        path.addFirst(current.data);
        return path;
    }
}
