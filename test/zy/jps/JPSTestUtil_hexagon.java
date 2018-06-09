package zy.jps;

import org.ksdev.jps.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kevin
 */
public class JPSTestUtil_hexagon {
    public static List<List<Tile_hexagon>> arraysToLists(Tile_hexagon[][] tiles) {
        List<List<Tile_hexagon>> tileList = new ArrayList<>();

        for (Tile_hexagon[] tileRow : tiles) {
            tileList.add(new ArrayList<>(Arrays.asList(tileRow)));
        }

        return tileList;
    }
}
