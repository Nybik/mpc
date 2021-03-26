package com.lexmach.client.minecraft.data.datatype;

public class Location {
    private double X;
    private double Y;
    private double Z;

    public Location() {
        X = 0;
        Y = 0;
        Z = 0;
    }

    public Location(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }

    public Location mult(double k) {
        return new Location(X * k, Y * k, Z * k);
    }

    public Location add(Location other) {
        return new Location(X + other.getX(), Y + other.getY(), Z + other.getZ());
    }

    public Location sub(Location other) {
        return this.add(other.mult(-1));
    }

    public double len() {
        return Math.sqrt(X * X + Y * Y + Z * Z);
    }

    public Location normalize() {
        return this.mult(1. / len());
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getZ() {
        return Z;
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    public void setZ(double z) {
        Z = z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "X=" + X +
                ", Y=" + Y +
                ", Z=" + Z +
                '}';
    }
}
