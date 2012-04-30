package tribe.lost;

import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import static tribe.lost.AClientServerInterface.*;


/**
 * Created by Tobias Ericsson
 */
public class KeyViewOnKeyListener implements View.OnKeyListener {

    private final static String TAG = KeyViewOnKeyListener.class.getSimpleName();

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        Log.d(TAG, "onKey " + i + " " + keyEvent);
        String command = STATE_KEY_PRESS + SUB_COMMAND_DELIMITER;
        if (keyEvent.getAction() == KeyEvent.ACTION_UP) {
            switch (i) {
                case 66:
                    String text = ((TextView) view).getText().toString();
                    ((TextView) view).setText("");
                    AClient.commands.add(command + text);
                    break;
                case 67:
                    AClient.commands.add(command + "ALT" + 127 + "ALT");
                    break;
            }
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
