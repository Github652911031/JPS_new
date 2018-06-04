package org.ksdev.jps;

/**
 * @author Kevin
 */
public abstract class Node {
    int x;
    int y;

    boolean walkable = true;

//    boolean force = false;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void setWalkable(boolean walkable) { this.walkable = walkable; }
    public boolean isWalkable() { return walkable; }
//    public boolean isForce() {
//        return force;
//    }
//
//    public void setForce(boolean force) {
//        this.force = force;
//    }
}
