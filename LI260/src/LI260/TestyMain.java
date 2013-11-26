package LI260;

/**
 *
 * @author 2900735
 */
public class TestyMain {

    /**
     * This is here only because JavaDoc is bitching about it.
     * @param args It doesn't really take any.
     */
    public static void main(String[] args) {

        int playOn = 1;
        int play=1;


        Pioche pioche = new Pioche();
        //System.out.println(pioche.toString());

        Plateau plateau1 = new Plateau();
        //System.out.println(plateau1.toString());

        Player player1 = new Player("Hulk", pioche);
        System.out.println(player1.toString());
        //player1.getHighestNonDouble().toString();

        Player player2 = new Player("IS ME", pioche);
        System.out.println(player2.toString());
        //player2.getHighestNonDouble().toString();

        Game game1 = new Game(pioche, plateau1, player1, player2);
        
        
        // Simplified reading handles
        Player startingPlayer = game1.whoStarts(player1, player2);
                        
        if (startingPlayer.toString().equals(player1.toString())) {
            startingPlayer.playFirstTile(plateau1);
            play=2;
        } else {
            startingPlayer.playFirstTile(plateau1);
            play=1;
        }
        
        System.out.println(plateau1.toString());

        
        
        
        while (!game1.gameOver()) {
            
            switch (play) {
                // Player1 plays
                case 1:
                    play = 2;
                    System.out.println("Player " + player1.getName() +" playing");
                    player1.playTileAuto(plateau1, pioche);
                    break;

                case 2:
                    play = 1;
                    System.out.println("Player " + player2.getName() +" playing");
                    player2.playTilePick(plateau1, pioche);
                    
                    //player2.playTileAuto(plateau1, pioche);
                    break;
            }
        }
        
        System.out.println(plateau1.toString());
    }
}