package com.example.testroutefinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {

    public static <T> LinkedList<T> findCheapestPathDijkstra(GraphNodeAL2<T> startNode, T lookingfor){
        LinkedList<T> path = new LinkedList<>();//Create result object for cheapest path
        List<GraphNodeAL2<T>> encountered=new ArrayList<>(), unencountered=new ArrayList<>(); //Create encountered/unencountered lists
        startNode.nodeValue=0; //Set the starting node value to zero
        unencountered.add(startNode); //Add the start node as the only value in the unencountered list to start
        GraphNodeAL2<T> currentNode;
        do{ //Loop until unencountered list is empty
            currentNode=unencountered.remove(0); //Get the first unencountered node (sorted list, so will have lowest value)
            encountered.add(currentNode); //Record current node in encountered list
            if(currentNode.data.equals(lookingfor)){ //Found goal - assemble path list back to start and return it
                path.add(currentNode.data); //Add the current (goal) node to the result list (only element)
                while(currentNode!=startNode) { //While we're not back to the start node...
                    boolean foundPrevPathNode=false; //Use a flag to identify when the previous path node is identified
                    for(GraphNodeAL2<T> n : encountered) { //For each node in the encountered list...
                        for(GraphLinkAL e : n.adjList) //For each edge from that node...
                            if(e.destNode==currentNode && currentNode.nodeValue-e.cost==n.nodeValue){ //If that edge links to the
//current node and the difference in node values is the cost of the edge -> found path node!
                                path.add(0,n.data); //Add the identified path node to the front of the result list
                                currentNode=n; //Move the currentNode reference back to the identified path node
                                foundPrevPathNode=true; //Set the flag to break the outer loop
                                break; //We've found the correct previous path node and moved the currentNode reference
//back to it so break the inner loop
                            }
                        if(foundPrevPathNode) break; //We've identified the previous path node, so break the inner loop to continue
                    }
                }
//Reset the node values for all nodes to (effectively) infinity so we can search again (leave no footprint!)
                for(GraphNodeAL2<T> n : encountered) n.nodeValue=Integer.MAX_VALUE;
                for(GraphNodeAL2<T> n : unencountered) n.nodeValue=Integer.MAX_VALUE;
                return path; //The costed (cheapest) path has been assembled, so return it!
            }
//We're not at the goal node yet, so...
            for(GraphLinkAL e : currentNode.adjList) //For each edge/link from the current node...
                if(!encountered.contains(e.destNode)) { //If the node it leads to has not yet been encountered (i.e. processed)
                    e.destNode.nodeValue=Integer.min(e.destNode.nodeValue, currentNode.nodeValue+e.cost); //Update the node value at the end
                    //of the edge to the minimum of its current value or the total of the current node's value plus the cost of the edge
                    unencountered.add((GraphNodeAL2<T>) e.destNode);
                }
            unencountered.sort((n1, n2) -> n1.nodeValue - n2.nodeValue); //Sort in ascending node value order
        }while(!unencountered.isEmpty());
        return null; //No path found, so return null
    }

    public static <T> int findPathCost(LinkedList<T> path, GraphNodeAL2<T> startNode){
        if (path==null) return 0;

        var curNode = startNode;
        var cost = 0;
        while (!path.isEmpty()) {
            var nextVal = path.removeFirst();
            for (var edge : curNode.adjList) {
                if (edge.destNode.data.equals(nextVal)) {
                    cost += edge.cost;
                    curNode = (GraphNodeAL2<T>) edge.destNode;
                    break;
                }
            }
        }
        return cost;
    }

}
