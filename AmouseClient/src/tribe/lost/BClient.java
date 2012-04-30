package tribe.lost;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import toer.ss.activity.HomeActivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Tobias Ericsson
 */
public class BClient extends Thread {

    private Activity activity;
    private final static String TAG = BClient.class.getName();

    private static byte[] buffer = new byte[AClientServerInterface.PACKET_LENGTH];

    public BClient(Activity activity) {
        this.activity = activity;
    }

    public static String receiveResponse() {

        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        try {
            if (AClient.datagramSocket != null) {
                AClient.datagramSocket.receive(datagramPacket);
            }
        } catch (IOException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
        }

        String data = new String(datagramPacket.getData());
        String payload = data.substring(0, datagramPacket.getLength());
        System.out.println("payload received: " + payload);
        return payload;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Log.d(TAG, "waiting for resp... ");
            String response = receiveResponse();
            Log.d(TAG, "resp " + response);
            activity.runOnUiThread(new UpdateStatus(response));
        }
    }

    private class UpdateStatus implements Runnable {

        String status;

        public UpdateStatus(String status) {
            this.status = status;
        }

        public void run() {
            Log.d(TAG, "setting text " + status);
            final TextView textView = (TextView) activity.findViewById(R.id.statusText);
            if (textView != null) {
                textView.setText(status);
            }
        }
    }
}



   
