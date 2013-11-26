package engine;

/**
 *
 * @author 2900735
 */
public class Tile {

    private int nb1, nb2;

    
    /**
     * Tile constructor
     * @param a Digit 1
     * @param b Digit 2
     */
    public Tile(int a, int b) {
        nb1 = a;
        nb2 = b;
    }

    
    /**
     * By copy constructor.
     * @param t
     */
    public Tile(Tile t) {
        this(t.getNb1(), t.getNb2());

    }

    /**
     * Inverses the digits on the tile. 
     */
    public void inverse() {
        int temp;
        temp = nb1;
        nb1 = nb2;
        nb2 = temp;
    }

    /*  --- Getters --- */
    
    /**
     * Getter first number (L).
     * @return int
     */
    public int getNb1() {
        return nb1;
    }

    /**
     * Getter second number (R).
     * @return int
     */
    public int getNb2() {
        return nb2;
    }

    /**
     * Returns sum of left and right tile values.
     * @return int
     */
    public int sum() {
        return (nb1 + nb2);
    }

    
    /**
     * Checks if the tile is a double.
     * @return Returns true if tile is a double, false otherwise. 
     */
    public boolean isDouble() {
        return (this.nb1 == this.nb2);
    }

    /**
     * Will choose the tile with highest single point.
     * @param t2 Tile to compare to
     * @return True if tile1 has higher single point, false otherwise.
     */
    public boolean isHigher(Tile t2) {
        if ((this.getNb1() > t2.getNb1()) && (this.getNb1() > t2.getNb2())
                || ((this.getNb2() > t2.getNb1()) && (this.getNb2() > t2.getNb2()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * toString method.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + nb1 + "|" + nb2 + "]";
    }
}