package engine;

/**
 *
 * @author 2900735
 */
public class Game {

    Pioche bag;
    Plateau board;
    Player player1;
    Player player2;

    /**
     * Creates a full game for 2 players
     *
     * @param p Bag with tiles.
     * @param plat Board to play on.
     * @param p1 Player1.
     * @param p2 Player2.
     */
    public Game(Pioche p, Plateau plat, Player p1, Player p2) {
        bag = p;
        board = plat;
        player1 = p1;
        player2 = p2;
    }

    /**
     * Determines which player will start first.
     *
     * @param p1 Player 1
     * @param p2 Player 2
     * @return Returns the player that should start the game.
     */
    public Player whoStarts(Player p1, Player p2) {

        // Two identical doubles are not possible! 
        if (p1.getHighestDouble().sum() == p2.getHighestDouble().sum()) {

            // Handles on highest non double tile for 
            // both players to simplify reading. 
            Tile pl1tile = p1.getHighestNonDouble();
            Tile pl2tile = p2.getHighestNonDouble();

            if (pl1tile.isHigher(pl2tile)) {
                System.out.println("Player " + p1.getName()
                        + " will start with: "
                        + p1.getHighestNonDouble().toString());
                return p1;
            } else {
                System.out.println("Player " + p2.getName()
                        + " will start with: "
                        + p2.getHighestNonDouble().toString());
                return p2;
            }
        } else if (p1.getHighestDouble().sum() > p2.getHighestDouble().sum()) {
            System.out.println("Player " + p1.getName() + " will start with: "
                    + p1.getHighestDouble().toString());
            return p1;
        } else {
            System.out.println("Player " + p2.getName() + " will start with: "
                    + p2.getHighestDouble().toString());
            return p2;
        }
    }

    /**
     * Determines whether the game's over, prints results
     *
     * @return boolean true for over, false for GAME ON.
     */
    public boolean j_gameOver() {
        if (player1.canStillPLay(board, bag) == 3) {
            System.out.println(player1.getName() + " has won by KO.");
            return true;
        } else if (player2.canStillPLay(board, bag) == 3) {
            System.out.println(player2.getName() + " has won by KO.");
            return true;
        } else if ((player1.canStillPLay(board, bag) == 0)) {
            System.out.println("\t*** Scores ***\t\n" + player1.getName()
                    + ("\tscore:") + player1.getScore()
                    + ("\nscore:") + player2.getScore()
                    + " won. This is what the game board looks like:\n"
                    + board.toString());
            return true;
        } else if ((player2.canStillPLay(board, bag) == 0)) {
            System.out.println("\t*** Scores ***\t\n" + player1.getName()
                    + ("\tscore:") + player1.getScore() + player2.getName()
                    + ("\tscore:") + player2.getScore() + player1.getName()
                    + " won. This is what the game board looks like:\n"
                    + board.toString());
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Player 1 name : " + player1.getName()+ "\nHand: " + player1.printTiles()
                + "\n\nPlayer 2 name : " + player2.getName() + "\nHand: " + player2.printTiles()
                + "\n\n" + bag.toString() 
                + "\n\nBoard : " + board.toString();
    }
}