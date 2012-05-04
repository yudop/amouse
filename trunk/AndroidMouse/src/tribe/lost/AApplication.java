package tribe.lost;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

/**
 * Created by Tobias Ericsson
 */
public class AApplication extends Application {

    private final static String TAG = AApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");

    }

}
