package com.apeelingtech.worldcraft.util;

/**
 * Created by music on 9/23/2015.
 */
public class Vector2 {

    private int x, y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*public Vector Copy() {
        return new Vector(this.x, this.y);
    }*/

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void add(Vector2 v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    public void subtract(Vector2 v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    public void multiply(Vector2 v) {
        this.x *= v.getX();
        this.y *= v.getY();
    }

    public void divide(Vector2 v) {
        this.x /= v.getX();
        this.y /= v.getY();
    }

    public void modulus(Vector2 v) {
        this.x %= v.getX();
        this.y %= v.getY();
    }

    public void scale(int before, int after) {
        this.x = (after * this.x) / before;
        this.y = (after * this.y) / before;
    }

}
