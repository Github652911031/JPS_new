package zy.jps;

import org.ksdev.jps.Graph;
import org.ksdev.jps.Node;

import java.util.*;
import java.util.function.BiFunction;

public class Graph_hexagon<T extends Node_hexagon>  {
    private List<T> nodes;
    private int x_start;
    private int x_end;
    private int y_start;
    private int y_end;


//    private BiFunction<Node, Node, Double> distance = (a, b) -> Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    private BiFunction<Node_hexagon, Node_hexagon, Double> distance = (a, b) -> Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y,2)*3/4);
    private BiFunction<Node_hexagon, Node_hexagon, Double> heuristic = (a, b) -> Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y,2)*3/4);
    /**
     * By default, will use EUCLIDEAN for distance and heuristic calculations. You can set your own algorithm
     * if you would like to change this.
     */
    public Graph_hexagon(List<List<T>> map, int x_start, int x_end, int y_start, int y_end) {
        this.x_start = x_start; //起始x坐标
        this.x_end = x_end; //终止x坐标
        this.y_start = y_start; //起始y坐标
        this.y_end = y_end;  //终止y坐标
        nodes = new ArrayList<>((x_end - x_start + 1)* (y_end - y_start + 1));
        map.forEach(nodes::addAll);
    }


    /**
     * @return List of all nodes in the graph.
     */
    public Collection<T> getNodes() { return nodes; }

    public T getNode(int x, int y) {
        if (x < x_start || x > x_end || y < y_start || y > y_end) {
            return null;
        }

        return nodes.get((y-y_start)*(x_end - x_start + 1) + x - x_start);
    }

    /**
     * Given two adjacent nodes, returns the distance between them.
     * @return The distance between the two nodes given.
     */
    public double getDistance(Node_hexagon a, Node_hexagon b) { return distance.apply(a, b); }

    /**
     * Given two nodes, returns the estimated distance between them. Optimizing this is the best way to improve
     * performance of your search time.
     * @return Estimated distance between the two given nodes.
     */
    public double getHeuristicDistance(Node_hexagon a, Node_hexagon b) { return heuristic.apply(a, b); }

    /**
     * By default, we return all reachable diagonal neighbors that have no obstacles blocking us.
     * i.e.
     * O O G
     * O C X
     * O O O
     *
     * In this above example, we could not go diagonally from our (C)urrent position to our (G)oal due to the obstacle (X).
     *
     * Please use {@link #getNeighborsOf(Node, Graph.Diagonal)} method if you would like to specify different diagonal functionality.
     *
     * @return All reachable neighboring nodes of the given node.
     */


    /**
     * @return All reachable neighboring nodes of the given node.
     */
    public Set<T> getNeighborsOf(T node) {
        int x = node.x;
        int y = node.y;
        Set<T> neighbors = new HashSet<>();

//        if( y % 2 == 0) {
//            //
//            if(isWalkable(x-1,y)) {
//                neighbors.add(getNode(x-1, y));
//            }
//            //
//            if(isWalkable(x+1, y)) {
//                neighbors.add(getNode(x+1, y));
//            }
//            //
//            if(isWalkable(x,y-1)) {
//                neighbors.add(getNode(x, y-1));
//            }
//            //
//            if(isWalkable(x-1, y+1)) {
//                neighbors.add(getNode(x-1, y+1));
//            }
//            //
//            if(isWalkable(x-1,y-1)) {
//                neighbors.add(getNode(x-1, y-1));
//            }
//            //
//            if(isWalkable(x, y+1)) {
//                neighbors.add(getNode(x, y+1));
//            }
//        } else {
//            //
//            if(isWalkable(x-1,y)) {
//                neighbors.add(getNode(x-1, y));
//            }
//            //
//            if(isWalkable(x+1, y)) {
//                neighbors.add(getNode(x+1, y));
//            }
//            //
//            if(isWalkable(x+1,y-1)) {
//                neighbors.add(getNode(x+1, y-1));
//            }
//            //
//            if(isWalkable(x, y+1)) {
//                neighbors.add(getNode(x, y+1));
//            }
//            //
//            if(isWalkable(x,y-1)) {
//                neighbors.add(getNode(x, y-1));
//            }
//            //
//            if(isWalkable(x+1, y+1)) {
//                neighbors.add(getNode(x+1, y+1));
//            }
//        }
        if(isWalkable(x+1,y+1)){
            neighbors.add(getNode(x+1,y+1));
        }
        if(isWalkable(x+1,y)){
            neighbors.add(getNode(x+1,y));
        }
        if(isWalkable(x,y-1)){
            neighbors.add(getNode(x,y-1));
        }
        if(isWalkable(x-1,y-1)){
            neighbors.add(getNode(x-1,y-1));
        }
        if(isWalkable(x-1,y)){
            neighbors.add((getNode(x-1,y)));
        }
        if(isWalkable(x,y+1)){
            neighbors.add(getNode(x,y+1));
        }
        return neighbors;

    }

    public boolean isWalkable(int x, int y) {
        return x >= x_start && x <= x_end && y >= y_start && y <= y_end && getNode(x, y).walkable;
    }





}
