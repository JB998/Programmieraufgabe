package Labyrinth;

public class Koordinate {
    private Koordinate parent;
    private int x;
    private int y;
    private int z;
    Koordinate(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;

        parent = null;

    }

    Koordinate(int x, int y, int z, Koordinate parent){
        this.x = x;
        this.y = y;
        this.z = z;

        this.parent = parent;

    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
    int getZ(){
        return z;
    }

    public Koordinate getParent() {
        return parent;
    }
}