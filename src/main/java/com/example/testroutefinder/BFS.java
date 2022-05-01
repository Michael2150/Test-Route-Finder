package com.example.testroutefinder;

import java.util.HashMap;
import java.util.LinkedList;

public class BFS {

    public static <O> LinkedList<O> bfs(myGraph<O> graph, O start, O end) { // O is the type of the objects in the graph
        var startPoint = graph.getNode(start); // get the node with the start object
        var endPoint = graph.getNode(end); // get the node with the end object

        if (startPoint == null || endPoint == null) { // if the start or end point is not in the graph
            throw new IllegalArgumentException("Start or end point not found or does not exist in the graph"); // throw an exception
        }

        HashMap<GraphNodeAL2<O>, Boolean> visited = new HashMap<>(); // create a hashmap to keep track of visited nodes
        HashMap<GraphNodeAL2<O>, GraphNodeAL2<O>> previous = new HashMap<>(); // create a hashmap to keep track of previous nodes
        LinkedList<GraphNodeAL2<O>> queue = new LinkedList<>(); // create a queue to keep track of nodes to visit

        for (GraphNodeAL2<O> node : graph.getNodes()) { // for each node in the graph
            visited.put(node, false); // set the node to not visited
            previous.put(node, null); // set the node to not visited
        }

        visited.put(startPoint, true); // set the start point to visited
        queue.add(startPoint); // add the start point to the queue

        GraphNodeAL2<O> current; // create a current node
        while (!queue.isEmpty() && !visited.get(endPoint)) { // while the queue is not empty and the end point is not visited
            current = queue.removeFirst(); // remove the first node in the queue
            for(var adjacent : current.adjList){ // for each adjacent node
                var adjacentNode = adjacent.destNode; // get the adjacent node
                if(!visited.get(adjacentNode)){ // if the adjacent node is not visited
                    visited.put(adjacentNode, true); // set the adjacent node to visited
                    previous.put(adjacentNode, current); // set the previous node to the current node
                    queue.add(adjacentNode); // add the adjacent node to the queue
                }
            }
        }

        LinkedList<O> path = new LinkedList<>(); // create a path to return
        current = endPoint; // set the current node to the end point
        while (previous.get(current) != null) { // while the previous node is not null
            path.addFirst(current.data); // add the current node to the path
            current = previous.get(current); // set the current node to the previous node
        }
        path.addFirst(current.data); // add the current node to the path
        return path; // return the path
    }
}
