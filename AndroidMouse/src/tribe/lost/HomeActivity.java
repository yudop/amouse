package tribe.lost;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class HomeActivity extends Activity {

    private static String TAG = HomeActivity.class.getSimpleName();
    private static Thread receiver, executer;
    boolean isConnected = false;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
                //break;
            /*
            case R.id.menu_search:
                Toast.makeText(this, "Tapped search", Toast.LENGTH_SHORT).show();
                break;  */
            case R.id.menu_settings:
                //Toast.makeText(this, "Tapped share", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,SettingsActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


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
        startThreads();
    }

    private void startThreads() {
        receiver = new Thread(new Runnable() {
            public void run() {
                //int port = AClientServerInterface.PORT;

                final SharedPreferences prefs = HomeActivity.this.getSharedPreferences("settings", MODE_PRIVATE);
                String ip = prefs.getString("ip",null);
                int port = prefs.getInt("port",AClientServerInterface.PORT);
                Log.d(TAG,"ip:port"+ip+":"+port);
                AClient.connectIfNotConnected(ip, port);
                isConnected = true;
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
                AClient.closeConnection();
            }
        });
        executer.start();

    }

    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        stopThreads();
    }

    private void stopThreads() {
        isConnected = false;
        executer.interrupt();
    }
}
