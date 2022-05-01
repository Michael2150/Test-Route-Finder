package com.example.testroutefinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GraphNodeAL2<O> {

    public O data;
    public int nodeValue = Integer.MAX_VALUE;
    public List<GraphLinkAL<O>> adjList=new ArrayList<>(); //Adjacency list now contains link objects = key!
    //Could use any concrete List implementation
    public GraphNodeAL2(O data) {
        this.data=data;
    }

    public GraphNodeAL2(){

    }

    public void connectToNodeDirected(GraphNodeAL2<O> destNode, int cost) {
        adjList.add( new GraphLinkAL<O>(destNode,cost) ); //Add new link object to source adjacency list
    }

    public void connectToNodeUndirected(GraphNodeAL2<O> destNode, int cost) {
        adjList.add( new GraphLinkAL<O>(destNode,cost) ); //Add new link object to source adjacency list
        destNode.adjList.add( new GraphLinkAL<O>(this, cost) ); //Add new link object to destination adjacency list
    }

    //get node data
    public O getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GraphNodeAL2)) return false;
        GraphNodeAL2<O> that = (GraphNodeAL2<O>) o;
        return Objects.equals(getData(), that.getData());
    }
}
