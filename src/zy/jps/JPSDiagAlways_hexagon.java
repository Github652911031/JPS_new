package zy.jps;

import org.ksdev.jps.Node;
import zy.jps.Graph_hexagon;
import zy.jps.JPS_hexagon;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JPSDiagAlways_hexagon<T extends Node_hexagon> extends JPS_hexagon<T> {
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

        Node_hexagon parent = parentMap.get(node);


        // directed pruning: can ignore most neighbors, unless forced.
        if (parent != null) {
            final int x = node.x;
            final int y = node.y;


//            // get normalized direction of travel
//            final int dx = x - parent.x;
//            final int dy = y - parent.y;

            if (isDiagonal(node)){
                if(x == y && x > 0){
                    if(graph.isWalkable(x+1,y+1)){
                        neighbors.add(graph.getNode(x+1,y+1));
                    }
                    if(graph.isWalkable(x+1,y)){
                        neighbors.add(graph.getNode(x+1,y));
                    }
                    if(!graph.isWalkable(x-1,y)){
//                        graph.getNode(x,y+1).setForce(true);
                        neighbors.add(graph.getNode(x,y+1));
                    }

                }else if(x == y && x < 0){
                    if(graph.isWalkable(x-1,y-1)){
                        neighbors.add(graph.getNode(x-1,y-1));
                    }
                    if(graph.isWalkable(x-1,y)){
                        neighbors.add(graph.getNode(x-1,y));
                    }
                    if(!graph.isWalkable(x+1,y)){
//                        graph.getNode(x,y-1).setForce(true);
                        neighbors.add(graph.getNode(x,y-1));
                    }
                }else if(x == 0 && y > 0){
                    if(graph.isWalkable(x,y+1)){
                        neighbors.add(graph.getNode(x,y+1));
                    }

                    if(graph.isWalkable(x+1,y+1)){
                        neighbors.add(graph.getNode(x+1,y+1));
                    }
                    if(!graph.isWalkable(x-1,y-1)){
//                        graph.getNode(x-1,y).setForce(true);
                        neighbors.add(graph.getNode(x-1,y));
                    }
                }else if(x == 0 && y < 0){
                    if(graph.isWalkable(x,y-1)){
                        neighbors.add(graph.getNode(x,y-1));
                    }
                    if(graph.isWalkable(x-1,y-1)){
                        neighbors.add(graph.getNode(x-1,y-1));
                    }
                    if(!graph.isWalkable(x+1,y+1)){
//                        graph.getNode(x+1,y).setForce(true);
                        neighbors.add(graph.getNode(x+1,y));
                    }
                }else if(y == 0 && x < 0) {
                    if (graph.isWalkable(x - 1, y)) {
                        neighbors.add(graph.getNode(x - 1, y));
                    }
                    if (graph.isWalkable(x, y + 1)) {
                        neighbors.add(graph.getNode(x, y + 1));
                    }
                    if (!graph.isWalkable(x, y - 1)) {
//                        graph.getNode(x - 1, y - 1).setForce(true);
                        neighbors.add(graph.getNode(x - 1, y - 1));
                    }
                }else if(y == 0 && x > 0){
                    if(graph.isWalkable(x+1,y)){
                        neighbors.add(graph.getNode(x+1,y));
                    }
                    if(graph.isWalkable(x,y-1)){
                        neighbors.add(graph.getNode(x,y-1));
                    }
                    if(!graph.isWalkable(x,y+1)){
//                        graph.getNode(x+1,y+1).setForce(true);
                        neighbors.add(graph.getNode(x+1,y+1));
                    }
                }

            }else {
                if(x>0 && y>0 && x>y){
                    if(graph.isWalkable(x+1,y)){
                        neighbors.add(graph.getNode(x+1,y));
                    }
                    if(!graph.isWalkable(x-1,y-1)){
                        neighbors.add(graph.getNode(x,y-1));
                    }
                    if(!graph.isWalkable(x,y+1)){
                        neighbors.add(graph.getNode(x+1,y+1));
                    }
                }else if(x>0 && y>0 && x<y){
                    if(graph.isWalkable(x+1,y+1)){
                        neighbors.add(graph.getNode(x+1,y+1));
                    }
                    if(!graph.isWalkable(x,y-1)){
                        neighbors.add(graph.getNode(x+1,y));
                    }
                    if(!graph.isWalkable(x-1,y)){
                        neighbors.add(graph.getNode(x,y+1));
                    }
                }
                else if(x<0 && y>0 ){
                    if(graph.isWalkable(x,y+1)){
                        neighbors.add(graph.getNode(x,y+1));
                    }
                    if(!graph.isWalkable(x+1,y)){
                        neighbors.add(graph.getNode(x+1,y+1));
                    }
                    if(!graph.isWalkable(x-1,y-1)){
                        neighbors.add(graph.getNode(x-1,y));
                    }
                }else if(x<0 && y<0 && x<y){
                    if(graph.isWalkable(x-1,y)){
                        neighbors.add(graph.getNode(x-1,y));
                    }
                    if(!graph.isWalkable(x+1,y+1)){
                        neighbors.add(graph.getNode(x,y+1));
                    }
                    if(!graph.isWalkable(x,y-1)){
                        neighbors.add(graph.getNode(x-1,y-1));
                    }
                }else if(x<0 && y<0 && x>y){
                    if(graph.isWalkable(x-1,y-1)){
                        neighbors.add(graph.getNode(x-1,y-1));
                    }
                    if(!graph.isWalkable(x,y+1)){
                        neighbors.add(graph.getNode(x-1,y));
                    }
                    if(!graph.isWalkable(x+1,y)){
                        neighbors.add(graph.getNode(x,y-1));
                    }
                }else if(x>0 && y<0){
                    if(graph.isWalkable(x,y-1)){
                        neighbors.add(graph.getNode(x,y-1));
                    }
                    if(!graph.isWalkable(x-1,y)){
                        neighbors.add(graph.getNode(x-1,y-1));
                    }
                    if(!graph.isWalkable(x+1,y+1)){
                        neighbors.add(graph.getNode(x+1,y));
                    }
                }

            }



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
        int x = neighbor.x;
        int y = neighbor.y;
        int dx = neighbor.x - current.x;
        int dy = neighbor.y - current.y;

        // check for forced neighbors
        // check along diagonal
//        if (dx != 0 && dy != 0) {
//            if ((graph.isWalkable(neighbor.x - dx, neighbor.y + dy) && !graph.isWalkable(neighbor.x - dx, neighbor.y)) ||
//                    (graph.isWalkable(neighbor.x + dx, neighbor.y - dy) && !graph.isWalkable(neighbor.x, neighbor.y - dy))) {
//                return neighbor;
//            }
//            // when moving diagonally, must check for vertical/horizontal jump points
//            if (jump(graph.getNode(neighbor.x + dx, neighbor.y), neighbor, goals) != null ||
//                    jump(graph.getNode(neighbor.x, neighbor.y + dy), neighbor, goals) != null) {
//                return neighbor;
//            }
//        } else { // check horizontally/vertically
//            if (dx != 0) {
//                if ((graph.isWalkable(neighbor.x + dx, neighbor.y + 1) && !graph.isWalkable(neighbor.x, neighbor.y + 1)) ||
//                        (graph.isWalkable(neighbor.x + dx, neighbor.y - 1) && !graph.isWalkable(neighbor.x, neighbor.y - 1))) {
//                    return neighbor;
//                }
//            } else {
//                if ((graph.isWalkable(neighbor.x + 1, neighbor.y + dy) && !graph.isWalkable(neighbor.x + 1, neighbor.y)) ||
//                        (graph.isWalkable(neighbor.x - 1, neighbor.y + dy) && !graph.isWalkable(neighbor.x - 1, neighbor.y))) {
//                    return neighbor;
//                }
//            }
//        }

        if(isDiagonal(neighbor)){
            if(x == y && x > 0){
                if(!graph.isWalkable(x-1,y) && graph.isWalkable(x,y+1)){
                    return neighbor;
                }
                if(jump(graph.getNode(x+1,y),neighbor,goals)!=null){
                    return neighbor;
                }
                return jump(graph.getNode(x+1,y+1),neighbor,goals);
            }else if(x == y && x < 0){
                if(!graph.isWalkable(x+1,y) && graph.isWalkable(x,y-1)){
                    return neighbor;
                }
                if(jump(graph.getNode(x-1,y),neighbor,goals)!=null){
                    return neighbor;
                }
                return jump(graph.getNode(x-1,y-1),neighbor,goals);

            }else if(x == 0 && y > 0){
                if(!graph.isWalkable(x-1,y-1) && graph.isWalkable(x-1,y)){
                    return neighbor;
                }
                if(jump(graph.getNode(x+1,y+1),neighbor,goals)!=null){
                    return neighbor;
                }
                return jump(graph.getNode(x,y+1),neighbor,goals);
            }else if(x == 0 && y < 0){
                if(!graph.isWalkable(x+1,y+1) && graph.isWalkable(x+1,y)){
                    return neighbor;
                }
                if(jump(graph.getNode(x-1,y-1),neighbor,goals)!=null){
                    return neighbor;
                }
                return jump(graph.getNode(x,y-1),neighbor,goals);
            }else if(y == 0 && x < 0){
                if(!graph.isWalkable(x,y-1) && graph.isWalkable(x-1,y-1)){
                    return neighbor;
                }
                if(jump(graph.getNode(x,y+1),neighbor,goals)!=null){
                    return neighbor;
                }
                return jump(graph.getNode(x-1,y),neighbor,goals);

            }else if(y == 0 && x > 0){
                if(!graph.isWalkable(x,y+1) && graph.isWalkable(x+1,y+1)){
                    return neighbor;
                }
                if(jump(graph.getNode(x,y-1),neighbor,goals)!=null){
                    return neighbor;
                }
                return jump(graph.getNode(x+1,y),neighbor,goals);

            }

        }else{
            if(x>0 && y>0 && x>y){
                if(!graph.isWalkable(x,y+1) && graph.isWalkable(x+1,y+1)
                        ||!graph.isWalkable(x-1,y-1) && graph.isWalkable(x,y-1)){
                    return neighbor;
                }
                return jump(graph.getNode(x+1,y),neighbor,goals);
            }else if(x>0 && y>0 && x<y){
                if(!graph.isWalkable(x,y-1) && graph.isWalkable(x+1,y)
                        ||!graph.isWalkable(x-1,y) && graph.isWalkable(x,y+1)){
                    return neighbor;
                }
                return jump(graph.getNode(x+1,y+1),neighbor,goals);
            }else if(x<0 && y>0){
                if(!graph.isWalkable(x+1,y) && graph.isWalkable(x+1,y+1)
                        ||!graph.isWalkable(x-1,y-1) && graph.isWalkable(x-1,y)){
                    return neighbor;
                }
                return jump(graph.getNode(x,y+1),neighbor,goals);
            }else if(x<0 && y<0 && x<y){
                if(!graph.isWalkable(x,y+1) && graph.isWalkable(x-1,y+1)
                        ||!graph.isWalkable(x,y-1) && graph.isWalkable(x-1,y-1)){
                    return neighbor;
                }
                return jump(graph.getNode(x-1,y),neighbor,goals);

            }else if(x<0 && y<0 && x>y){
                if(!graph.isWalkable(x+1,y) && graph.isWalkable(x,y-1)
                        ||!graph.isWalkable(x,y+1) && graph.isWalkable(x-1,y)){
                    return neighbor;
                }
                return jump(graph.getNode(x-1,y-1),neighbor,goals);

            }else if(x>0 && y<0){
                if(!graph.isWalkable(x+1,y+1) && graph.isWalkable(x+1,y)
                        ||!graph.isWalkable(x-1,y) && graph.isWalkable(x-1,y-1)){
                    return neighbor;
                }
                return jump(graph.getNode(x,y-1),neighbor,goals);

            }
        }
        return null;

    }

    private  boolean isDiagonal(T node){
        if(node.y == 0 || node.x == 0 || node.x == node.y){
            return true;
        }
        return false;
    }
}
