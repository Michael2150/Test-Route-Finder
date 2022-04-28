package com.example.testroutefinder;

import java.util.LinkedList;
import java.util.Objects;

public class Exhibit {

    public int id;
    public String name;
    public String description;
    public Pixel position;
    public LinkedList<Integer> connections;
    public int cost;

    //Constructor
    public Exhibit() {

    }


    //Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Pixel getPosition() {
        return position;
    }

    public LinkedList<Integer> getConnections() {
        return connections;
    }

    public int getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPosition(Pixel position) {
        this.position = position;
    }

    public void setConnections(LinkedList<Integer> connections) {
        this.connections = connections;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    //toString
    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Exhibit)) return false;
        Exhibit exhibit = (Exhibit) obj;
        return Objects.equals(getId(), exhibit.getId());
    }
}
