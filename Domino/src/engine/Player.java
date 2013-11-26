package engine;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Michal Rudek 2900735
 */
public class Player {

    private String name;
    private ArrayList<Tile> tilesAtHand = new ArrayList(7);
    private ArrayList<Tile> tilesPlayed = new ArrayList(14);
    private int sum;

    /**
     * Player constructor
     *
     * @param n player's name
     */
    public Player(String n) {
        name = n;
    }

    /**
     * Player constructor
     *
     * @param n name
     * @param p bag
     */
    public Player(String n, Pioche p) {
        this(n);
   
        for (int i = 0; i < 7; i++) {
            this.drawTile(p);
        }
    }

    /**
     * This is constructor made for jUnit tests purposes. It is not used in real
     * engine.
     *
     * @param name String - Player's name
     * @param t11 int - Tile1.Nb1
     * @param t12 int - Tile1.Nb2
     * @param t21 int - Tile2.Nb1
     * @param t22 int - Tile2.Nb2
     * @param t31 int - Tile3.Nb1
     * @param t32 int - Tile3.Nb2
     */
    public Player(String name, int t11, int t12, int t21, int t22, int t31, int t32) {
        this(name);
        Tile t1 = new Tile(t11, t12);
        Tile t2 = new Tile(t21, t22);
        Tile t3 = new Tile(t31, t32);
        tilesAtHand.add(t1);
        tilesAtHand.add(t2);
        tilesAtHand.add(t3);

    }

    /*  --- Getters --- */
    /**
     * Returns tiles player's got.
     *
     * @return tinesAtHand
     */
    public ArrayList<Tile> getTilesAtHand() {
        return tilesAtHand;
    }

    /**
     * Returns tiles played by player.
     *
     * @return tilesPlayed
     */
    public ArrayList<Tile> getTilesPlayed() {
        return tilesPlayed;
    }

    /**
     * Player's name
     *
     * @return player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Calculates the player's score.
     *
     * @return int score.
     */
    public int getScore() {
        int score = 0;
        for (Tile d : this.tilesPlayed) {
            score += d.sum();
        }
        return score;
    }

    /**
     * Returns the highest double for the player.
     *
     * @return Highest double tile.
     */
    public Tile getHighestDouble() {
        int highest = 0;
        int hiindex = 0;
        for (int i = 0; i < tilesAtHand.size(); i++) {
            if ((tilesAtHand.get(i).sum() > highest) && (tilesAtHand.get(i).isDouble())) {
                highest = tilesAtHand.get(i).sum();
                hiindex = i;
            }
        }
        return tilesAtHand.get(hiindex);
    }

    /**
     * Function will calculate the highest non-double tile in player's hand.
     *
     * @return Tile
     */
    public Tile getHighestNonDouble() {
        int highest = 0;
        int hiindex = 0;
        System.out.println("User: " + this.getName());
        for (int i = 0; i < this.tilesAtHand.size(); i++) {
            if (this.tilesAtHand.get(i).isDouble()) {
                System.out.println("Skipping iteration - double");
                continue;
            } else if (highest < this.tilesAtHand.get(i).sum()) {
                System.out.print("Current highest is: " + highest + " for tile "
                        + this.tilesAtHand.get(i).toString());
                highest = this.tilesAtHand.get(i).sum();
                hiindex = i;
                System.out.println(" new highest is: " + highest);
            } else {
                System.out.println("Skipping iteration "
                        + this.tilesAtHand.get(i).toString() + " - not higher");
                continue;
            }
        }
        return this.tilesAtHand.get(hiindex);
    }

    /**
     * Gets the starting tile for player.
     *
     * @return Tile that the player will start the game with.
     */
    public Tile getStartingTile() {
        if (this.hasDouble()) {
            return this.getHighestDouble();
        } else {
            return this.getHighestNonDouble();
        }
    }

    /**
     * Will determine whether the player has a double at hand or not.
     *
     * @return True if player holds a double, false otherwise.
     */
    public boolean hasDouble() {
        for (int i = 0; i < this.tilesAtHand.size(); i++) {
            Tile t = this.tilesAtHand.get(i);
            if (t.getNb1() == t.getNb2()) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

    /*  --- Play Methods ---    */
    /**
     * Checks whether the player can still play on, by verifying if he's got any
     * playable tiles at hand, if not, checking if Bag is empty.
     *
     * @param p Plateau
     * @param bag Pioche
     * @return 1 if there's a playable tile at hand, 2 if the player needs to
     * draw, 0 if the player can't play (lose) and 3 if the player has won.
     */
    public int canStillPLay(Plateau p, Pioche bag) {

        // Win.
        if (this.tilesAtHand.isEmpty()) {
            return 3;
        }

        // Can play at least 1 tile at hand.
        for (Tile t : this.tilesAtHand) {
            if (p.tileFits(t)) {
                return 1;
            }
        }

        // Getting here means there's no playable tiles at hand.
        if (bag.isEmpty() == false) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * Draws a tile from the bag p.
     *
     * @param p Bag to draw the tile from.
     */
    public void drawTile(Pioche p) {
        tilesAtHand.add(p.drawTile());
    }

    /**
     *
     * @param t
     */
    public void addTile(Tile t) {
        tilesAtHand.add(t);
    }

    /**
     * Plays the tile you chose to play with.
     *
     * @param t Tile to play with.
     * @param p Board to play it on.
     */
    public void playTile(Tile t, Plateau p) {
        p.playTile(t);
        tilesPlayed.add(t);
        tilesAtHand.remove(t);
    }

    /**
     * Plays the player's first tile according to rules (double -> highest
     * non-double)
     *
     * @param p Plateau the tile will be played on.
     */
    public void playFirstTile(Plateau p) {
        Tile t = this.getStartingTile();
        p.placeFirstTile(t);
        tilesPlayed.add(t);
        tilesAtHand.remove(t);
    }

    /**
     * Automatically picks the first possible tile to play with, plays it; is
     * used for CPU playing & testing.
     *
     * @param p Board to play it at.
     * @param bag Bag the players will draw from if needed
     */
    public void playTileAuto(Plateau p, Pioche bag) {
        int i = 0;
        boolean stopPlay = false;


        if (p.getTiles().isEmpty()) {
            System.out.println("Empty table, playing highest double...");
            this.playFirstTile(p);
            System.out.println(p.toString());
        }

        while (stopPlay == false) {
            switch (this.canStillPLay(p, bag)) {
                //Case 0 : Game over
                //Case 1 : At least one playable Tile at hand
                //Case 2 : Draw from bag, redirect to case 1.
                //Case 3 : Winner.
                case 0:
                    System.out.println(this.name + " can no longer play.");
                    stopPlay = true;
                    break;
                case 1:
                    if (p.tileFits(tilesAtHand.get(i))) {
                        System.out.println(tilesAtHand.get(i) + " goes on the board.   <--------------------------------------");
                        this.playTile((tilesAtHand.get(i)), p);
                        System.out.println(p.toString());
                        stopPlay = true;
                        break;
                    } else {
                        //System.out.println("This tile --(" + tilesAtHand.get(i) + ")-- doesn't fit, next...");
                        i++;
                    }
                    break;
                case 2:
                    System.out.println(this.getName() + " draws a tile");
                    this.drawTile(bag);
                    break;
                case 3:
                    stopPlay = true;
                    break;
            }
        }
    }

    /**
     * Shows tiles that can be played. Lets you pick which one you want to play
     * with.
     *
     * @param p Board
     * @param bag Pioche
     */
    public void playTilePick(Plateau p, Pioche bag) {
        int i = 0;
        boolean stopPlay = false;

        if (p.getTiles().isEmpty()) {
            System.out.println("Empty table, playing highest double...");
            this.playFirstTile(p);
            System.out.println(p.toString());
        }

        while (stopPlay == false) {
            switch (this.canStillPLay(p, bag)) {
                //Case 0 : Game over
                //Case 1 : At least one playable Tile at hand
                //Case 2 : Draw from bag, redirect to case 1.
                //Case 3 : Winner.
                case 0:
                    System.out.println(this.name + " can no longer play.");
                    stopPlay = true;
                    break;
                case 1:
                    int cpt = 0;
                    int playindex = -1;
                    Scanner input = new Scanner(System.in);
                    int[] indexes = new int[7];
                    indexes = new int[indexes.length];

                    // Get and print playabe tiles.
                    for (i = 0; i < this.getTilesAtHand().size(); i++) {
                        if (p.tileFits(this.getTilesAtHand().get(i))) {
                            cpt++;
                            indexes[cpt] = i;
                        }
                    }

                    while (playindex < 0 || playindex > cpt) {
                        System.out.println("You've " + cpt + " choice(s). Which tile would you like to play?");

                        for (i = 0; i < this.getTilesAtHand().size(); i++) {
                            if (p.tileFits(this.getTilesAtHand().get(i))) {
                                System.out.println(i + ":\t" + this.getTilesAtHand().get(i));
                                cpt = i;
                            }
                        }

                        playindex = input.nextInt();
                        if (playindex > cpt || playindex < 0) {
                            System.out.println("Wrong index, moron");
                            continue;
                        }
                        System.out.println("Playing tile: " + this.getTilesAtHand().get(playindex));
                        this.playTile(this.getTilesAtHand().get(playindex), p);
                        break;
                    }
                    break;

                case 2:
                    System.out.println(this.getName() + " draws a tile");
                    this.drawTile(bag);
                    break;
                case 3:
                    stopPlay = true;
                    break;
            }
        }
    }

    /*  --- String Methods  --- */
    /**
     * Returns player's name + tiles at hand
     *
     * @return String
     */
    @Override
    public String toString() {
        return (name + " " + this.printTiles());
    }

    /**
     * Prints tiles.
     *
     * @return String
     */
    public String printTiles() {
        String s = "";
        for (int i = 0; i < this.tilesAtHand.size(); i++) {
            s += this.tilesAtHand.get(i).toString() + " ";
        }
        return s;
    }

    /**
     *
     * @param board
     * @return
     */
    public String printPlayables(Plateau board) {

        int cpt = 0;
        String res = "";
        // Get and print playabe tiles.
        for (int i = 0; i < this.getTilesAtHand().size(); i++) {
            if (board.tileFits(this.getTilesAtHand().get(i))) {
                res = res + this.getTilesAtHand().get(i).toString() + " , ";
            }
        }
        return res;

    }

    /**
     *
     * @return
     */
    public String[] printTilesArray() {
        String[] res = new String[this.tilesAtHand.size()];
        for (int i = 0; i < this.tilesAtHand.size(); i++) {
            res[i] = this.tilesAtHand.get(i).toString();
        }
        return res;
    }
}
