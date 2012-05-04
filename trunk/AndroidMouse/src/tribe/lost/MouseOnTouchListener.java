package tribe.lost;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: TOER
 * Date: 2012-05-02
 * Time: 09:29
 * To change this template use File | Settings | File Templates.
 */
public class MouseOnTouchListener implements View.OnTouchListener {

    private final static String TAG = MouseOnTouchListener.class.getSimpleName();
    float oldX, oldY, startX, startY;
    private long startTime;

    MouseOnTouchListener() {
        oldX = 500;
        oldY = 200;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        handleMotionEvent(motionEvent);
        return true;
    }

    private void handleMotionEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        Log.d(TAG, "motion " + motionEvent.getAction());
        if (action == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "DOWN!!");
            oldX = motionEvent.getX();
            oldY = motionEvent.getY();
            startX = oldX;
            startY = oldY;
            startTime = System.currentTimeMillis();
            putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_DOWN_RIGHT);

        }

        if (action == MotionEvent.ACTION_UP) {
            Log.d(TAG, "CLEAR!!");
            //client.commands.clear();
            ifClickClick(motionEvent);

        }

        if (action == MotionEvent.ACTION_MOVE) {

            float x = 0;
            float y = 0;

            final int historySize = motionEvent.getHistorySize();
            final int pointerCount = motionEvent.getPointerCount();
            for (int h = 0; h < historySize; h++) {
                for (int p = 0; p < pointerCount; p++) {
                    //Log.d(TAG, "historical x,y: " + motionEvent.getHistoricalX(p, h) + "," + motionEvent.getHistoricalY(p, h));
                    x = motionEvent.getHistoricalX(p, h);
                    y = motionEvent.getHistoricalY(p, h);
                    mouseMove(x, y);
                }
            }
            Log.d(TAG, "At time %d:" + motionEvent.getEventTime());
            for (int p = 0; p < pointerCount; p++) {
                //Log.d(TAG, "x,y: " + motionEvent.getX(p) + ":" + motionEvent.getY(p));
                x = motionEvent.getX(p);
                y = motionEvent.getY(p);
                mouseMove(x, y);
            }

            //Log.d(TAG, "final x,y: " + x + "," + y);

        }
    }

    private void ifClickClick(MotionEvent motionEvent) {
        long upTime = System.currentTimeMillis();
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        float diffX = (startX - x) * (startX - x);
        float diffY = (startY - y) * (startY - y);
        Log.d(TAG, "diff " + diffX);
        if (diffX < 15 && diffY < 15) {
            if (upTime - startTime < 500) {
                Log.d(TAG, "clicked fast");
                //putCommandOnQueue("MOUSE_CLICK;;"+ AClientServerInterface.MOUSE.);
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_DOWN_LEFT);
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_UP_LEFT);
            } else if (upTime - startTime < 3000) {
                Log.d(TAG, "clicked slow");
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_DOWN_RIGHT);
                putCommandOnQueue("MOUSE_CLICK;;" + AClientServerInterface.MOUSE.BUTTON_UP_RIGHT);
            }
        }
    }

    private void mouseMove(float x, float y) {

        float dx = x - oldX;
        float dy = y - oldY;
        float diffX = (dx) * (dx);
        float diffY = (dy) * (dy);
        Log.d(TAG, "mouseMove diff: "+diffX);
        if (diffX >= 0.1 && diffY >= 0.1) {
            putCommandOnQueue("MOUSE_MOVE" + ";;" + addSensitivity(8, dx) + ";;" + addSensitivity(8, dy));
            oldX = x;
            oldY = y;
        }
    }

    private int addSensitivity(int sensitivity, float xy) {
        /*
        if (xy>sensitivity) {
            return 10;
        } else {
            return -10;
        } */
        return Math.round(xy * sensitivity);
    }

    private void putCommandOnQueue(String command) {
        try {
            AClient.commands.put(command);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException " + e.getMessage());
        }
    }
}
