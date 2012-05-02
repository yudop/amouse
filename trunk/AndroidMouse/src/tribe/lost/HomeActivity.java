package tribe.lost;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.io.IOException;

public class HomeActivity extends Activity {

    private static String TAG = HomeActivity.class.getSimpleName();
    private static Thread receiver, executer;
    boolean isConnected = false;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View touchArea = findViewById(R.id.touch_area);
        touchArea.setOnTouchListener(new MouseOnTouchListener());

        KeyViewOnKeyListener keyViewOnKeyListener = new KeyViewOnKeyListener();
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.key_input);
        autoCompleteTextView.setOnKeyListener(keyViewOnKeyListener);

    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        int port = AClientServerInterface.PORT;
        AClient.connectIfNotConnected("10.19.210.26", port);
        isConnected = true;
        startThreads();
    }

    private void startThreads() {
        receiver = new Thread(new Runnable() {
            public void run() {
                while (isConnected) {
                    System.out.println("waiting for response... ");

                    final String response;
                    try {
                        response = AClient.receiveResponse();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView view = (TextView) findViewById(R.id.response_field);
                                view.setText(response);
                            }
                        });
                    } catch (IOException e) {
                        Log.d(TAG, e.getClass().getSimpleName() + " " + e.getMessage());
                    }
                }
                Log.d(TAG, "receiver finished");
            }

        });
        receiver.start();

        executer = new Thread(new Runnable() {
            public void run() {
                while (isConnected) {
                    System.out.println("waiting for command... ");
                    try {
                        String command = AClient.commands.take();
                        AClient.sendPayload(command);
                    } catch (InterruptedException e) {
                        Log.d(TAG, "interrupted");
                    }
                }
                Log.d(TAG, "executer finished");
            }
        });
        executer.start();

    }

    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        stopThreads();
        AClient.closeConnection();
    }

    private void stopThreads() {
        isConnected = false;
        executer.interrupt();
    }
}
