package toer.ss.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import toer.ss.client.DatagramClient;
import toer.ss.client.Commands;

public class HomeActivity extends Activity {

    private static String TAG = HomeActivity.class.getSimpleName();
    private static DatagramClient client;
    private static Thread thread;
    float oldX, oldY;
    TextView textView;
    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        takeKeyEvents(true);
        textView = (TextView) findViewById(R.id.statusText);
    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "Connecting");
        client = new DatagramClient();
        thread = new Thread(client);
        thread.start();
        //Client.sendCommand("mouseLeftClick()");
        //Client.sendCommand("mouseMove(30,50)");
        //Client.sendCommand("mouseMove(35,50)");

    }

    public void updateStatus() {
        textView.setText("");
    }

    public void onPause() {
        super.onPause();
        Log.d(TAG, "Closing connection");
        client.cancel();
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

    private void mouseMove(float x, float y) {

        float dx = x - oldX;
        float dy = y - oldY;
        putCommandOnQueue(Commands.MOUSE_MOVE+" " + addSensitivity(8, dx) + " " + addSensitivity(8, dy));
        oldX = x;
        oldY = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        //Log.d(TAG, "TOUCH!!");
        handleMotionEvent(motionEvent);
        return true;
    }

    public void clickHandler(View view) {
        //Log.d(TAG, "CLICK");
        int command = -1;
        switch (view.getId()) {
            case R.id.leftButton:
                //Log.d(TAG, "LEFT");
                command = Commands.MOUSE_LEFT_CLICK;
                break;
            case R.id.middleButton:
                //Log.d(TAG, "MIDDLE");
                command = Commands.MOUSE_MIDDLE_CLICK;
                break;
            case R.id.rightButton:
                //Log.d(TAG, "RIGHT");
                command = Commands.MOUSE_RIGHT_CLICK;
                break;

        }
        if (command>0) {
            putCommandOnQueue(String.valueOf(command));
        }
    }

    private void putCommandOnQueue(String command) {
        try {
            client.commands.put(command);
        }
        catch (InterruptedException e) {
            Log.e(TAG,"InterruptedException "+e.getMessage());
        }
    }

    @Override
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        handleMotionEvent(motionEvent);
        return true;
    }

    private void handleMotionEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "DOWN!!");
            oldX = motionEvent.getX();
            oldY = motionEvent.getY();

        }

        if (action == MotionEvent.ACTION_UP) {
            Log.d(TAG, "CLEAR!!");
            client.commands.clear();
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
}
