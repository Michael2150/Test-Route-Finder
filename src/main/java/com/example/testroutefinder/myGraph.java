package com.example.testroutefinder;

import java.util.LinkedList;

public class myGraph<O> {

    public LinkedList<GraphNodeAL2<O>> nodes;

    public myGraph() {
        nodes = new LinkedList<GraphNodeAL2<O>>();
    }

    public LinkedList<GraphNodeAL2<O>> getNodes() {
        return new LinkedList<GraphNodeAL2<O>>(this.nodes);
    }

    //get nodes of type GraphNodeAL2
    public GraphNodeAL2<O> getNode(O data){
        for(GraphNodeAL2<O> node : nodes){
            if(node.data.equals(data)){
                return node;
            }
        }
        return null;
    }

    public boolean addNode(O data){
        nodes.add(new GraphNodeAL2<O>(data));
        return true;
    }

    public boolean addLink(O source, O destination){
        return addCostedLink(source, destination, 3);
    }

    public boolean addCostedLink(O source, O destination, int cost){
        GraphNodeAL2<O> sourceNode = getNode(source);
        GraphNodeAL2<O> destinationNode = getNode(destination);
        sourceNode.connectToNodeUndirected(destinationNode, cost);
        return true;
    }


    public LinkedList<O> getNodeValue(){ //get the value of the node
        LinkedList<O> nodeValue = new LinkedList<>(); //create a new linked list
        for(GraphNodeAL2<O> node : nodes){ //for each node in the graph
            nodeValue.add(node.data); //add the value of the node to the linked list
        }
        return nodeValue; //return the linked list
    }
}
