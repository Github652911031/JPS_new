package org.ksdev.jps;

import java.util.*;
import java.util.function.BiFunction;

public class Graph_hexagon<T extends Node>  {
    private List<T> nodes;
    private int width;


    private BiFunction<Node, Node, Double> distance = (a, b) -> Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    private BiFunction<Node, Node, Double> heuristic = (a, b) -> Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    /**
     * By default, will use EUCLIDEAN for distance and heuristic calculations. You can set your own algorithm
     * if you would like to change this.
     */
    public Graph_hexagon(List<List<T>> map) {
        width = map.get(0).size();
        nodes = new ArrayList<>(map.size() * map.get(0).size());

        map.forEach(nodes::addAll);
    }

    /**
     * By default, will use EUCLIDEAN for distance and heuristic calculations. You can set your own algorithm
     * if you would like to change this.
     */
    public Graph_hexagon(T[][] map) {
        width = map[0].length;
        nodes = new ArrayList<>(map.length * map[0].length);

        for (T[] row : map) {
            Collections.addAll(nodes, row);
        }
    }

    /**
     * @return List of all nodes in the graph.
     */
    public Collection<T> getNodes() { return nodes; }

    public T getNode(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= nodes.size() / width) {
            return null;
        }
        return nodes.get(x + y*width);
    }

    /**
     * Given two adjacent nodes, returns the distance between them.
     * @return The distance between the two nodes given.
     */
    public double getDistance(Node a, Node b) { return distance.apply(a, b); }

    /**
     * Given two nodes, returns the estimated distance between them. Optimizing this is the best way to improve
     * performance of your search time.
     * @return Estimated distance between the two given nodes.
     */
    public double getHeuristicDistance(Node a, Node b) { return heuristic.apply(a, b); }

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
        return x >= 0 && x < width && y >= 0 && y < nodes.size() / width && getNode(x, y).walkable;
    }

    /**
     * If you would like to define your own Distance Algorithm not included.
     */
    public void setDistanceAlgo(BiFunction<Node, Node, Double> distance) {
        this.distance = distance;
    }

    /**
     * If you would like to define your own Heuristic Algorithm not included.
     */
    public void setHeuristicAlgo(BiFunction<Node, Node, Double> heuristic) {
        this.heuristic = heuristic;
    }



}
