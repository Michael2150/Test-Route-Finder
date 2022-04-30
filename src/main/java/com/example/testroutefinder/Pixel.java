package com.example.testroutefinder;

import javafx.geometry.Point2D;

import java.io.Serializable;

public class Pixel  {

    public double x, y;

    public Pixel(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Get distance between 2 pixels
    public double distance(Pixel p) {
        return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
    }

    //multiply with an integer
    public Pixel multiply(int i) {
        return new Pixel(x * i, y * i);
    }

    //getters and setters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
