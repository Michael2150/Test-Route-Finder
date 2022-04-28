package com.example.testroutefinder;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeAL2<O> {

    public O data;
    public int nodeValue = Integer.MAX_VALUE;
    public List<GraphLinkAL> adjList=new ArrayList<>(); //Adjacency list now contains link objects = key!
    //Could use any concrete List implementation
    public GraphNodeAL2(O data) {
        this.data=data;
    }

    public GraphNodeAL2(){

    }

    public void connectToNodeDirected(GraphNodeAL2<O> destNode, int cost) {
        adjList.add( new GraphLinkAL(destNode,cost) ); //Add new link object to source adjacency list
    }

    public void connectToNodeUndirected(GraphNodeAL2<O> destNode, int cost) {
        adjList.add( new GraphLinkAL(destNode,cost) ); //Add new link object to source adjacency list
        destNode.adjList.add( new GraphLinkAL(this, cost) ); //Add new link object to destination adjacency list
    }

    //get node data
    public O getData() {
        return data;
    }
}
