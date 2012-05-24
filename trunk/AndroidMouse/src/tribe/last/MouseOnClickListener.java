package tribe.last;

import android.util.Log;
import android.view.View;
/**
 * Created by Tobias Ericsson
 */
public class MouseOnClickListener implements View.OnClickListener {

    private final static String TAG = MouseOnTouchListener.class.getSimpleName();

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_left:
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_DOWN_LEFT);
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_UP_LEFT);
                break;
            case R.id.button_middle:
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_DOWN_MIDDLE);
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_UP_MIDDLE);
                break;
            case R.id.button_right:
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_DOWN_RIGHT);
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_UP_RIGHT);
                break;
            case R.id.button_delete:
                putCommandOnQueue(AClientServerInterface.STATE_DELETE_PRESS);
                break;
        }
    }

    private void putCommandOnQueue(String command) {
        try {
            AClient.commands.put(command);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException " + e.getMessage());
        }
    }
}
