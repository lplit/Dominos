package domino.rudek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

/**
 *
 * @author Michal Rudek 2900735
 */
public class ScoresActivity extends Activity {

    /**
     * Called when the activity is first created.
     * @param icicle 
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.scores);
        Intent intent = getIntent();
        String scores = intent.getStringExtra(GameActivity.SCORES_VIEW);
        TextView scoresview = (TextView) findViewById(R.textviews.scoreview_all);
        scoresview.setMovementMethod(new ScrollingMovementMethod());
        scoresview.setText(scores);

    }

    /**
     * Button function for New Game
     * @param view
     */
    public void btn_NewGame(View view) {
        if (view.getId() == R.buttons.newgameButton) {

            Intent intent = new Intent(ScoresActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    /**
     * Button function for Back (same as physical back button on device) 
     * @param view
     */
    public void btn_Back(View view) {
        if (view.getId() == R.buttons.backButton) {
            finish();
        }
    }
}
