package domino.rudek;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import engine.Game;
import engine.Pioche;
import engine.Plateau;
import engine.Player;
import engine.Tile;
import java.util.ArrayList;

/**
 *
 * @author Michal Rudek 2900735
 */
public class GameActivity extends Activity {

    /**
     * Intent
     */
    public final static String SCORES_VIEW = "NULL";
    /**
     * Human player
     */
    public Player player;
    /**
     * CPU Player
     */
    public Player playercpu;
    /**
     * Bag they players will play with 
     */
    public Pioche bag;
    /**
     * Board to play on
     */
    public Plateau board;
    /**
     * Game class instance 
     */
    public Game game1;
    boolean cpuTurn = false;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.PL_NAME);
        bag = new Pioche();
        board = new Plateau();
        player = new Player(message, bag);
        playercpu = new Player("CPU", bag);
        game1 = new Game(bag, board, player, playercpu);



        TextView tv_Hand = (TextView) findViewById(R.textviews.gameview_AtHandText);
        TextView tv_board = (TextView) findViewById(R.textviews.gameview_board);

        tv_Hand.setMovementMethod(new ScrollingMovementMethod());
        tv_board.setMovementMethod(new ScrollingMovementMethod());

        Player startingPlayer = game1.whoStarts(player, playercpu);

        tv_board.setText("iDroid:Game root$ ./Game " + player.getName()+ "\n");
        
        if (startingPlayer.getName().equals("CPU")) {
            playercpu.playFirstTile(board);
            tv_board.append(playercpu.getName() + " first tile down!");
        } else {
            player.playFirstTile(board);
            tv_board.append(player.getName() + " first tile down!");
        }

        updateTexts();
        
    }


    /**
     * Button function for Scores
     * @param v View
     */
    public void btnClick_Scores(View v) {
        if (v.getId() == R.buttons.scoresButton) {
            Intent scoresIntent = new Intent(this, ScoresActivity.class);
            String message = game1.toString();
            scoresIntent.putExtra(SCORES_VIEW, message);
            startActivity(scoresIntent);
        }
    }

    /**
     * Button function for Draw
     * @param v View
     */
    public void btnClick_Draw(View v) {
        if (v.getId() == R.buttons.drawButton) {
            Tile t = bag.drawTile();
            String message = "Drawn " + t.toString();
            player.addTile(t);
            updateTexts();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Button function for Play
     * @param v View
     */
    public void btnClick_Play(View v) {
        if (v.getId() == R.buttons.playButton) {
            pickAndPlayDomino();
        }

    }

    /**
     * Will pop up an alert dialog if player has no more playable tiles left at hand
     */
    public void noTilesLeftAlert() {
        Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!");
        builder.setMessage("You have no playable tiles left.\nPlease draw.");

        // Draw
        builder.setPositiveButton("Draw", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv_board = (TextView) findViewById(R.textviews.gameview_board);

                Tile t = bag.drawTile();
                String message = "Drawn " + t.toString();
                player.addTile(t);

                tv_board.append("\nYou drew " + t.toString());
                updateTexts();

                // Toast
                Toast.makeText(GameActivity.this, message, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


        // Cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Will pop up an alert dialog with list of playable tiles to choose from
     */
    public void choiceAlert() {
        ArrayList<String> plbs = new ArrayList();
        final ArrayList<Integer> indexes = new ArrayList();
        plbs.clear();
        indexes.clear();

        for (int i = 0; i < player.getTilesAtHand().size(); i++) {
            Tile handle = player.getTilesAtHand().get(i);
            if (board.tileFits(handle)) {
                plbs.add(handle.toString());
                indexes.add(i);
            }
        }

        final CharSequence[] tiles = plbs.toArray(new CharSequence[plbs.size()]);
        final Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick tile:");
        builder.setItems(tiles, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Variables
                Tile handle = player.getTilesAtHand().get(indexes.get(which));
                TextView tv_board = (TextView) findViewById(R.textviews.gameview_board);
                tv_board.setMovementMethod(new ScrollingMovementMethod());


                // Actions
                player.playTile(handle, board);
                tv_board.append("\n" + player.getName() + " " + handle.toString() + " down!");
                updateTexts();

                // Toast
                Toast.makeText(GameActivity.this,
                        tiles[which] + " selected.", Toast.LENGTH_LONG)
                        .show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Take a pass",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateTexts();
                        dialog.dismiss();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * Main play method
     */
    public void pickAndPlayDomino() {
        if (gameOver() == true) {
            return;
        }
// Player's turn
        if (cpuTurn == false) {
// Case 1 : Players needs to draw, show drawer alert.
            if (player.canStillPLay(board, bag) == 2) {
                noTilesLeftAlert();
// Case 2 : Player has playable tiles at hand, show choice dialog
            } else {
                // Pick tile
                choiceAlert();
                cpuTurn = true;
            }
            // CPU turn
        } else {
            cpuPlayDomino();
            updateTexts();
            cpuTurn = false;
        }

        if (gameOver() == true) {
            return;
        }

        updateTexts();
    }

    /**
     * Updates gameview_AtHandText and gameview_Output (bottom of the screen)
     */
    public void updateTexts() {
        TextView tv_Hand = (TextView) findViewById(R.textviews.gameview_AtHandText);
        TextView tv_Output = (TextView) findViewById(R.textviews.gameview_output);

        tv_Output.setText("iDroid:~ root$ cat ~/Game/board.txt\n" + board.getSize() + " | Left edge: " + board.getLeft()
                + " | Right edge: " + board.getRight());
        tv_Hand.setText("iDroid:~ root$ cat ~/Game/hand.txt\n" + player.printTiles());
    }

    /**
     * Method used by CPU to autoplay
     */
    public void cpuPlayDomino() {
        int i = 0;
        boolean stopPlay = false;
        TextView tv_board = (TextView) findViewById(R.textviews.gameview_board);

        if (board.getTiles().isEmpty()) {
            tv_board.append("\nTable empty. Playing highest double.");
            playercpu.playFirstTile(board);
        }

        while (stopPlay == false) {
            switch (playercpu.canStillPLay(board, bag)) {
                //Case 0 : Game over
                //Case 1 : At least one playable Tile at hand
                //Case 2 : Draw from bag, redirect to case 1.
                //Case 3 : Winner.
                case 0:
                    tv_board.append("\n" + playercpu.getName() + " can no longer play.");
                    stopPlay = true;
                    break;
                case 1:
                    //Handle
                    Tile ti = playercpu.getTilesAtHand().get(i);
                    if (board.tileFits(ti)) {
                        tv_board.append("\nCPU " + ti.toString() + " down!");
                        playercpu.playTile(ti, board);
                        //tv_board.append("\n" + board.toString());
                        stopPlay = true;
                        break;
                    } else {
                        i++;
                    }
                    break;
                case 2:
                    tv_board.append("\n" + playercpu.getName() + " draws a tile");
                    playercpu.drawTile(bag);
                    break;
                case 3:
                    stopPlay = true;
                    break;
            }
        }

    }

    /**
     * Checks whether the game is over yet
     * @return true if over, else false
     */
    public boolean gameOver() {

        /*
         player.canStillPlay return codes: 
         0 : Game Over 
         1 : At least one playable Tile at hand 
         2 : Draw from bag redirect to case 1 
         3 : Winner
         */

        // Player 1 winner by KO
        if (player.canStillPLay(board, bag) == 3) {
            buttonsOff();
            Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You won, KO!");
            builder.setMessage("You have won!"
                    + "\n\t*** Scores ***\t\n" + player.getName()
                    + ("\tscore:") + player.getScore()
                    + ("\nCPU score:") + playercpu.getScore());

            // Start new game
            builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            // Show scores
            builder.setNegativeButton("Scores", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent scores2Intent = new Intent(GameActivity.this, ScoresActivity.class);
                    String message = game1.toString();
                    scores2Intent.putExtra(SCORES_VIEW, message);
                    startActivity(scores2Intent);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

            return true;


        } // Player CPU winner by KO
        else if (playercpu.canStillPLay(board, bag) == 3) {
            buttonsOff();
            Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You lost, KO!");
            builder.setMessage("CPU has won!"
                    + "\n\t*** Scores ***\t\n" + player.getName()
                    + ("\tscore:") + player.getScore()
                    + ("\nCPU score:") + playercpu.getScore());

            // Start new game
            builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            // Show scores
            builder.setNegativeButton("Scores", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent scores2Intent = new Intent(GameActivity.this, ScoresActivity.class);
                    String message = game1.toString();
                    scores2Intent.putExtra(SCORES_VIEW, message);
                    startActivity(scores2Intent);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

            return true;
        } // Player winner by score.
        else if ((player.canStillPLay(board, bag) == 0)) {
            buttonsOff();
            Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You won!");
            builder.setMessage("\n\t*** Scores ***\t\n" + player.getName()
                    + ("\tscore:") + player.getScore()
                    + ("\nCPU score:") + playercpu.getScore());

            // Start new game
            builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            // Show scores
            builder.setNegativeButton("Scores", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent scores2Intent = new Intent(GameActivity.this, ScoresActivity.class);
                    String message = game1.toString();
                    scores2Intent.putExtra(SCORES_VIEW, message);
                    startActivity(scores2Intent);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

            return true;


        } // CPU winner by score.
        else if ((playercpu.canStillPLay(board, bag) == 0)) {
            buttonsOff();
            Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You lost!");
            builder.setMessage("CPU has won!"
                    + "\n\t*** Scores ***\t\n" + player.getName()
                    + ("\tscore:") + player.getScore()
                    + ("\nCPU score:") + playercpu.getScore());

            // Start new game
            builder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(GameActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });

            // Show scores
            builder.setNegativeButton("Scores", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent scores2Intent = new Intent(GameActivity.this, ScoresActivity.class);
                    String message = game1.toString();
                    scores2Intent.putExtra(SCORES_VIEW, message);
                    startActivity(scores2Intent);
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

            return true;
        } // No winners, keep on playing.
        else {
            return false;
        }
    }

    /**
     * Turns off Play and Draw buttons
     */
    public void buttonsOff() {
        Button b1 = (Button) findViewById(R.buttons.drawButton);
        Button b2 = (Button) findViewById(R.buttons.playButton);
        b1.setEnabled(false);
        b2.setEnabled(false);
    }
}