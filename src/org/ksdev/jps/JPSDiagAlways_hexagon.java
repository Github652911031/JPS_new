package org.ksdev.jps;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JPSDiagAlways_hexagon<T extends Node> extends JPS_hexagon<T> {
    public JPSDiagAlways_hexagon(Graph_hexagon<T> graph) { super(graph); }


    /**
     * 查找自然邻居和强迫邻居
     * @param node
     * @param parentMap
     * @return
     */
    @Override
    protected Set<T> findNeighbors(T node, Map<T, T> parentMap) {
        Set<T> neighbors = new HashSet<>();

        Node parent = parentMap.get(node);


        // directed pruning: can ignore most neighbors, unless forced.
        if (parent != null) {
            final int x = node.x;
            final int y = node.y;


            // get normalized direction of travel
            final int dx = x - parent.x;
            final int dy = y - parent.y;

            if (isDiagonal(node)){
                if(x == y){
                    if(graph.isWalkable(x+dx,y+dy)){
                        neighbors.add(graph.getNode(x+dx,y+dy));
                    }
                    if(graph.isWalkable(x+dx,y)){
                        neighbors.add(graph.getNode(x+dx,y));
                    }
                    if(!graph.isWalkable(x-dx,y)){
                        neighbors.add(graph.getNode(x,y+dy));
                    }

                }else if(x == 0){
                    if(graph.isWalkable(x,y+dy)){
                        neighbors.add(graph.getNode(x,y+dy));
                    }
                    if(y>0){
                        if(graph.isWalkable(x+1,y+1)){
                            neighbors.add(graph.getNode(x+1,y+1));
                        }
                        if(!graph.isWalkable(x-1,y-1)){
                            neighbors.add(graph.getNode(x-1,y));
                        }
                    }else{
                        if(graph.isWalkable(x-1,y-1)){
                            neighbors.add(graph.getNode(x-1,y-1));
                        }
                        if(!graph.isWalkable(x+1,y+1)){
                            neighbors.add(graph.getNode(x+1,y));
                        }
                    }

                }else if(y == 0){
                    if(graph.isWalkable(x+dx,y)){
                        neighbors.add(graph.getNode(x+dx,y));
                    }
                    if(x>0){
                        if(graph.isWalkable(x,y-1)){
                            neighbors.add(graph.getNode(x,y-1));
                        }
                        if(!graph.isWalkable(x,y+1)){
                            neighbors.add(graph.getNode(x+1,y+1));
                        }
                    }else{
                        if(graph.isWalkable(x,y+1)){
                            neighbors.add(graph.getNode(x,y+1));
                        }
                        if(!graph.isWalkable(x,y-1)){
                            neighbors.add(graph.getNode(x-1,y-1));
                        }
                    }
                }

            }else {
                if(graph.isWalkable(x+dx,y+dy)){
                    neighbors.add(graph.getNode(x+dx,y+dy));
                }
                if(dx==dy){
                    if(!graph.isWalkable(x,y-dy)){
                        neighbors.add(graph.getNode(x+dx,y));
                    }
                    if(!graph.isWalkable(x-dx,y)){
                        neighbors.add(graph.getNode(x,y+dy));
                    }
                }else if(dx == 0){
                    if(y>0){
                        if(!graph.isWalkable(x+1,y)){
                            neighbors.add(graph.getNode(x+1,y+1));
                        }
                        if(!graph.isWalkable(x-1,y-1)){
                            neighbors.add(graph.getNode(x-1,y));
                        }
                    }else{
                        if(!graph.isWalkable(x-1,y)){
                            neighbors.add(graph.getNode(x-1,y-1));
                        }
                        if(!graph.isWalkable(x+1,y+1)){
                            neighbors.add(graph.getNode(x+1,y));
                        }
                    }

                }else if(dy == 0){
                    if(x>0){
                        if(!graph.isWalkable(x,y+1)){
                            neighbors.add(graph.getNode(x+1,y+1));
                        }
                        if(!graph.isWalkable(x-1,y-1)){
                            neighbors.add(graph.getNode(x,y-1));
                        }
                    }else{
                        if(!graph.isWalkable(x,y-1)){
                            neighbors.add(graph.getNode(x-1,y-1));
                        }
                        if(!graph.isWalkable(x+1,y+1)){
                            neighbors.add(graph.getNode(x,y+1));
                        }
                    }

                }
            }

//            if( y % 2 == 0){
//                //水平，偶数行（第一行为0),一个自然邻居,两个强迫邻居
//                if( dx==1 && dy==0 ){
//                    if (graph.isWalkable(x+1, y ))
//                        neighbors.add(graph.getNode(x+1, y ));
//                    if (!graph.isWalkable(x-1,y-1)){
//                        neighbors.add(graph.getNode(x,y-1));
//                    }
//                    if (!graph.isWalkable(x-1,y+1)){
//                        neighbors.add(graph.getNode(x,y+1));
//                    }
//                }
//                else if( dx==0 && dy==-1 ){
//                    if (graph.isWalkable(x-1, y-1))
//                        neighbors.add(graph.getNode(x-1, y-1));
//                    if (!graph.isWalkable(x-1, y+1)){
//                        neighbors.add(graph.getNode(x-1,y));
//                    }
//                    if (!graph.isWalkable(x+1,y)){
//                        neighbors.add(graph.getNode(x,y-1));
//                    }
//                }
//                else if( dx==0 && dy==1 ){
//                    if (graph.isWalkable(x-1,y+1))
//                        neighbors.add(graph.getNode(x-1, y+1));
//                    if (!graph.isWalkable(x+1, y)){
//                        neighbors.add(graph.getNode(x,y+1));
//                    }
//                    if (!graph.isWalkable(x-1,y-1)){
//                        neighbors.add(graph.getNode(x-1,y));
//                    }
//                }
//                //对角,三个自然邻居
//                else if( dx==1 && dy==-1 ){
//                    if (graph.isWalkable(x,y-1)){
//                        neighbors.add(graph.getNode(x,y-1));
//                    }
//                    if (graph.isWalkable(x+1,y)){
//                        neighbors.add(graph.getNode(x+1,y));
//                    }
//                    if (graph.isWalkable(x-1,y-1)){
//                        neighbors.add(graph.getNode(x-1,y-1));
//                    }
//                }
//                else if( dx==-1 && dy==0){
//                    if (graph.isWalkable(x-1,y)){
//                        neighbors.add(graph.getNode(x-1,y));
//                    }
//                    if (graph.isWalkable(x-1,y-1)){
//                        neighbors.add(graph.getNode(x-1,y-1));
//                    }
//                    if (graph.isWalkable(x-1,y+1)){
//                        neighbors.add(graph.getNode(x-1,y+1));
//                    }
//                }
//                else if( dx==1 && dy==1 ){
//                    if (graph.isWalkable(x,y+1)){
//                        neighbors.add(graph.getNode(x,y+1));
//                    }
//                    if (graph.isWalkable(x+1,y)){
//                        neighbors.add(graph.getNode(x+1,y));
//                    }
//                    if (graph.isWalkable(x-1,y+1)){
//                        neighbors.add(graph.getNode(x-1,y+1));
//                    }
//                }
//            }else{
//                //水平,奇数行
//                if( dx==1 && dy==0 ){
//                    if (graph.isWalkable(x+1, y ))
//                        neighbors.add(graph.getNode(x+1, y ));
//                    if (!graph.isWalkable(x,y-1)){
//                        neighbors.add(graph.getNode(x+1,y-1));
//                    }
//                    if (!graph.isWalkable(x,y+1)){
//                        neighbors.add(graph.getNode(x+1,y+1));
//                    }
//                }
//                else if( dx==-1 && dy==-1 ){
//                    if (graph.isWalkable(x, y-1))
//                        neighbors.add(graph.getNode(x, y-1));
//                    if (!graph.isWalkable(x, y+1)){
//                        neighbors.add(graph.getNode(x-1,y));
//                    }
//                    if (!graph.isWalkable(x+1,y)){
//                        neighbors.add(graph.getNode(x+1,y-1));
//                    }
//                }
//                else if( dx==-1 && dy==1 ){
//                    if (graph.isWalkable(x,y+1))
//                        neighbors.add(graph.getNode(x, y+1));
//                    if (!graph.isWalkable(x+1, y)){
//                        neighbors.add(graph.getNode(x+1,y+1));
//                    }
//                    if (!graph.isWalkable(x,y-1)){
//                        neighbors.add(graph.getNode(x-1,y));
//                    }
//                }
//                //对角
//                else if( dx==0 && dy==-1 ){
//                    if (graph.isWalkable(x+1,y-1)){
//                        neighbors.add(graph.getNode(x+1,y-1));
//                    }
//                    if (graph.isWalkable(x+1,y)){
//                        neighbors.add(graph.getNode(x+1,y));
//                    }
//                    if (graph.isWalkable(x,y-1)){
//                        neighbors.add(graph.getNode(x,y-1));
//                    }
//                }
//                else if( dx==-1 && dy==0){
//                    if (graph.isWalkable(x-1,y)){
//                        neighbors.add(graph.getNode(x-1,y));
//                    }
//                    if (graph.isWalkable(x,y-1)){
//                        neighbors.add(graph.getNode(x,y-1));
//                    }
//                    if (graph.isWalkable(x,y+1)){
//                        neighbors.add(graph.getNode(x,y+1));
//                    }
//                }
//                else if( dx==0 && dy==1 ){
//                    if (graph.isWalkable(x+1,y+1)){
//                        neighbors.add(graph.getNode(x+1,y+1));
//                    }
//                    if (graph.isWalkable(x+1,y)){
//                        neighbors.add(graph.getNode(x+1,y));
//                    }
//                    if (graph.isWalkable(x,y+1)){
//                        neighbors.add(graph.getNode(x,y+1));
//                    }
//                }
//            }
            //is diag


        } else {
            // no parent, return all neighbors
            neighbors.addAll(graph.getNeighborsOf(node));
        }

        return neighbors;
    }

    @Override
    protected T jump(T neighbor, T current, Set<T> goals) {
        if (neighbor == null || !neighbor.walkable) return null;
        if (goals.contains(neighbor)) return neighbor;

        int dx = neighbor.x - current.x;
        int dy = neighbor.y - current.y;

        // check for forced neighbors
        // check along diagonal
        if (dx != 0 && dy != 0) {
            if ((graph.isWalkable(neighbor.x - dx, neighbor.y + dy) && !graph.isWalkable(neighbor.x - dx, neighbor.y)) ||
                    (graph.isWalkable(neighbor.x + dx, neighbor.y - dy) && !graph.isWalkable(neighbor.x, neighbor.y - dy))) {
                return neighbor;
            }
            // when moving diagonally, must check for vertical/horizontal jump points
            if (jump(graph.getNode(neighbor.x + dx, neighbor.y), neighbor, goals) != null ||
                    jump(graph.getNode(neighbor.x, neighbor.y + dy), neighbor, goals) != null) {
                return neighbor;
            }
        } else { // check horizontally/vertically
            if (dx != 0) {
                if ((graph.isWalkable(neighbor.x + dx, neighbor.y + 1) && !graph.isWalkable(neighbor.x, neighbor.y + 1)) ||
                        (graph.isWalkable(neighbor.x + dx, neighbor.y - 1) && !graph.isWalkable(neighbor.x, neighbor.y - 1))) {
                    return neighbor;
                }
            } else {
                if ((graph.isWalkable(neighbor.x + 1, neighbor.y + dy) && !graph.isWalkable(neighbor.x + 1, neighbor.y)) ||
                        (graph.isWalkable(neighbor.x - 1, neighbor.y + dy) && !graph.isWalkable(neighbor.x - 1, neighbor.y))) {
                    return neighbor;
                }
            }
        }

        // jump diagonally towards our goal
        return jump(graph.getNode(neighbor.x + dx, neighbor.y + dy), neighbor, goals);
    }

    private  boolean isDiagonal(T node){
        if(node.y == 0 || node.x == 0 || node.x == node.y){
            return true;
        }
        return false;
    }
}
