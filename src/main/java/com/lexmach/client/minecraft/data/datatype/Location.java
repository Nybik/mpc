package com.lexmach.client.minecraft.data.datatype;

/**
 * A class, that defines location on the 3D map
 * implemented vector-like functions
 */
public class Location {
    private double x;
    private double y;
    private double z;

    public Location() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location mult(double k) {
        return new Location(x * k, y * k, z * k);
    }

    public Location add(Location other) {
        return new Location(x + other.getX(), y + other.getY(), z + other.getZ());
    }

    public Location sub(Location other) {
        return this.add(other.mult(-1));
    }

    public double len() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Location normalize() {
        return this.mult(1. / len());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "X=" + x +
                ", Y=" + y +
                ", Z=" + z +
                '}';
    }
}
