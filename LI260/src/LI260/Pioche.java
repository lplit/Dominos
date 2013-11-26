package LI260;

import java.util.ArrayList;

/**
 *
 * @author 2900735
 */
public class Pioche {

    private final int size = 28;
    private int drawn = 0;
    private ArrayList<Tile> bag = new ArrayList<Tile>(size);

    /**
     * Fuckin' constructor mate.
     */
    public Pioche() {
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= i; j++) {
                Tile t = new Tile(i, j);
                bag.add(t);
            }
        }
    }

    
    /**
     * Draws random tile from the bag, identical tiles will not be drawn.
     *
     * @return A randomly picked object Tile.
     */
    public Tile drawTile() {
        int rnd = (int) (Math.random() * bag.size());
        Tile t = new Tile(bag.get(rnd));

        bag.remove(rnd);
        drawn++;
        return t;

    }

    
    /**
     * Calculates size of the bag.
     * @return int 
     */
    public int getSize() {
        return bag.size();
    }

    
    /**
     * Checks if bag is empty /I do realize this is completely redundant/
     * @return True if empty, else false.
     */
    public boolean isEmpty() {
        if (bag.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    
    /**
     * Regular toString, just like all the toStrings on the planet.
     * @return SURPRISE SURPRISE! A STRING! AMG!!!!!!11111oneoneone
     */
    @Override
    public String toString() {
        return "Size: " + size + " tiles left: " + (size - drawn);
    }
}