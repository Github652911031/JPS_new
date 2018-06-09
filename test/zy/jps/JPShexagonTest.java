package zy.jps;

import org.junit.Before;
import org.junit.Test;
import org.ksdev.jps.Tile;
import zy.jps.JPShexagonBaseTest;

import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class JPShexagonTest extends JPShexagonBaseTest {
    @Before
    public void setup() {
        super.setup();
    }

    @Test
    public void twoObstaclesAndPath() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tileList.get(i).get(j).walkable = false;
            }
        }
        Tile_hexagon start = tileList.get(1).get(1);
        start.walkable = true;
        Tile_hexagon end = tileList.get(0).get(2);
        end.walkable = true;


        tileList.get(0).get(1).walkable = true;
//        tileList.get(1).get(4).walkable = false;

//        Future<Queue<Tile>> futurePath = jps.findPath(start, end);
//        Queue<Tile> path = futurePath.get();
        Queue<Tile_hexagon> path = jps.findPathSync(start, end);

        assert path != null;
        assert !path.isEmpty();
        for(Tile_hexagon p :path){
            System.out.println(p.x+","+p.y);
        }
    }

    @Test
    public void comingFromTopLeftThroughTwoObstacles() throws ExecutionException, InterruptedException {
        Tile_hexagon start = tileList.get(0).get(0);
        Tile_hexagon end = tileList.get(4).get(4);
        for (int i = 0; i < 4; i++) {
            tileList.get(2).get(i).walkable = false;
        }
        tileList.get(1).get(4).walkable = false;

        Future<Queue<Tile_hexagon>> futurePath = jps.findPath(start, end);
        Queue<Tile_hexagon> path = futurePath.get();

        assert path != null;
        assert !path.isEmpty();

        assert path.remove().equals(tileList.get(0).get(0));
        assert path.remove().equals(tileList.get(1).get(1));
        assert path.remove().equals(tileList.get(1).get(2));
        assert path.remove().equals(tileList.get(1).get(3));
        assert path.remove().equals(tileList.get(2).get(4));
        assert path.remove().equals(tileList.get(3).get(4));
        assert path.remove().equals(tileList.get(4).get(4));
    }

    @Test
    public void noPath() throws ExecutionException, InterruptedException {
        Tile_hexagon start = tileList.get(0).get(0);
        Tile_hexagon end = tileList.get(4).get(8);

        for (Tile_hexagon tile : tileList.get(2)) {
            tile.walkable = false;
        }

        Future<Queue<Tile_hexagon>> futurePath = jps.findPath(start, end);
        Queue<Tile_hexagon> path = futurePath.get();

        assert path == null;
    }

    @Test
    public void map2() throws ExecutionException, InterruptedException {
        Tile_hexagon start = tileList2.get(38).get(34);
        System.out.println(start.isWalkable());
        Tile_hexagon end = tileList2.get(38).get(33);
        System.out.println(end.isWalkable());

        //Future<Queue<Tile>> futurePath = jps2.findPath(start, end);
        Queue<Tile_hexagon> path = jps2.findPathSync(start, end);

        for (Tile_hexagon tile : path) {
            System.out.println("X: " + tile.x + ", Y: " + tile.y);
        }

        assert path != null;
    }
}
