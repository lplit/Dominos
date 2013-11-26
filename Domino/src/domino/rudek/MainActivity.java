package domino.rudek;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 * @author Michal Rudek 2900735
 */
public class MainActivity extends Activity {

    /**
     * Intent
     */
    public final static String PL_NAME = "Player";

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Button function for saving the name and starting GameActivity
     * @param view
     */
    public void btnClick_Name(View view) {
        if (view.getId() == R.buttons.B_EnterName) {
            EditText playersname = (EditText) findViewById(R.string.playerNameInput);
            Toast.makeText(this, "Your name: " + playersname.getText().toString(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, GameActivity.class);
            String message = playersname.getText().toString();
            intent.putExtra(PL_NAME, message);
 
            startActivity(intent);
        }
    }
}