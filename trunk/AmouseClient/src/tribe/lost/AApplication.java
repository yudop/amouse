package tribe.lost;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Tobias Ericsson
 */
public class AApplication extends Application {

    private final static String TAG = AApplication.class.getSimpleName();

    private AClient aClient;
    private BClient bClient;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");

    }


    public void disconnectFromServer() {
        Log.d(TAG, "disconnectFromServer");
        aClient.cancel();
        aClient = null;
    }

    public void connectToServerIfNotConnected(Activity a) {
        Log.d(TAG, "connectToServerIfNotConnected");
        if (aClient == null || aClient.getState() == Thread.State.TERMINATED) {
            aClient = new AClient();
            aClient.start();
            bClient = new BClient(a);
            bClient.start();

        }
    }
}
