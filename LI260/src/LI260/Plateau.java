package LI260;

import java.util.ArrayList;

/**
 *
 * @author 2900735
 */
public class Plateau {

    private int extL, extR;
    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    /* ---  Constructors    --- */
    /**
     * Constructor
     */
    public Plateau() {
        extL = 0;
        extR = 0;
        tiles.clear();
    }

    /**
     * Constructor with first tile /redundant/
     *
     * @param t First tile.
     */
    public Plateau(Tile t) {
        extL = t.getNb1();
        extR = t.getNb2();
        tiles.add(t);
    }

    /**
     * Appends t to the end of list, updates extR.
     *
     * @param t Tile to set in array.
     */
    public void addRight(Tile t) {
        tiles.add(t);
        extR = t.getNb2();
    }

    /**
     * Appends t to the beginning of list, updates extL.
     *
     * @param t Tile to set in array.
     */
    public void addLeft(Tile t) {
        tiles.add(0, t);
        extL = t.getNb1();
    }

    /**
     * Determines whether Tile t fits on the board.
     *
     * @param t Tile to check.
     * @return Boolean true if fits, false otherwise.
     */
    public boolean tileFits(Tile t) {
        boolean fits = false;
        if (t.getNb1() == this.getLeft() || t.getNb1() == this.getRight() || t.getNb2() == this.getLeft() || t.getNb2() == this.getRight()) {
            fits = true;
        }
        return fits;
    }

    /**
     * Checks if tile fits on left
     * @param t Tile 
     * @return True if does, false otherwise.
     */
    public boolean tileLeft(Tile t) {
        boolean fits = false;
        if (t.getNb1() == this.getLeft() || t.getNb2() == this.getLeft()) {
            fits = true;
        }
        return fits;

    }

    /**
     * Checks if tile fits on right
     * @param t Tile
     * @return True if does, false otherwise.
     */
    public boolean tileRight(Tile t) {
        boolean fits = false;
        if (t.getNb1() == this.getRight() || t.getNb2() == this.getRight()) {
            fits = true;
        }
        return fits;
    }

    /**
     * Checks whether the tile at hand can be played, inverses it if necessary.
     *
     * @param t Tile at hand
     */
    public void playTile(Tile t) {
        // Tile fits on the board.
        if (tileFits(t)) {
            // Inverse the numbers on the tile if needed.

            if (t.getNb1() == extR) {
                System.out.println("Adding " + t.toString() + " on the right.");
                addRight(t);
            } else if (t.getNb2() == extL) {
                System.out.println("Adding " + t.toString() + " on the left.");
                addLeft(t);
            } else if (t.getNb1() == extL) {
                System.out.print("The tile fits, but needs to be inversed: " + t.toString() + " ---> ");
                t.inverse();
                addLeft(t);
                System.out.println(t.toString());
            } else if (t.getNb2() == extR) {
                System.out.print("The tile fits, but needs to be inversed: " + t.toString() + " ---> ");
                t.inverse();
                addRight(t);
                System.out.println(t.toString());
            }
        } else {
            System.out.println("Warning the tile " + t.toString() + " des not fit on the board.");
        }
    }

    /**
     * Places the first tile on the board
     *
     * @param t Tile
     */
    public void placeFirstTile(Tile t) {
        tiles.add(t);
        extR = t.getNb1();
        extL = t.getNb2();
    }

    /*  --- Getters ---   */
    /**
     * Gets left-most digit.
     *
     * @return int
     */
    public int getLeft() {
        return extL;
    }

    /**
     * Gets right-most digit.
     *
     * @return int
     */
    public int getRight() {
        return extR;
    }

    /**
     * Gets the tiles at board.
     *
     * @return ArrayList<Tile>
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    /**
     * Returns the board's size.
     *
     * @return int size
     */
    public int getSize() {
        return tiles.size();
    }

    /**
     * Yet. Another. To. String. That. Prints. Shit.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "The left edge is: " + extL + ", the right edge is: " + extR + " there are now " + tiles.size() + " tiles on the board: " + this.printTiles(tiles);
    }

    /**
     * Prints all the tiles
     *
     * @param ar ArrayList<Tile> tiles.
     * @return String concatenated from all the tiles.
     */
    private String printTiles(ArrayList<Tile> ar) {
        String s = "\n";
        for (int i = 0; i < ar.size(); i++) {
            s += ar.get(i).toString() + "\n";
        }
        return s;
    }
}