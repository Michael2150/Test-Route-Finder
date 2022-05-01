package com.example.testroutefinder;

public class GraphLinkAL <O>{

    public  GraphNodeAL2<O> destNode; //Could also store source node if required
    public int cost; //Other link attributes could be similarly stored
    public GraphLinkAL(GraphNodeAL2<O> destNode,int cost) {
        this.destNode=destNode;
        this.cost=cost;
    }
}
