package toer.ss.client;

import android.app.Activity;
import android.util.Log;
import toer.ss.activity.HomeActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by te - Date: 2/27/11
 */
public class Client implements Runnable {
    private static Socket socket;
    private final static String TAG = Client.class.getName();
    private static DataOutputStream dataOutputStream;
    private static DataInputStream  dataInputStream;
    public BlockingQueue<String> commands = new LinkedBlockingQueue<String>();
    private Activity context;
    static public String result;
    public Client(Activity context) {
        this.context = context;
    }

    public static void connect() throws IOException {
        Log.d(TAG, "connecting");

        socket = new Socket("192.168.0.103", 5000);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());

    }

    private String sendCommand(String command) {
        Log.d(TAG, "command: " + command);
        try {
            dataOutputStream.writeUTF(command);
            String result = dataInputStream.readUTF();
            Log.d(TAG, "result: " + result);
            return result;
        }
        catch (IOException e) {
            Log.i(TAG, "IOException " + e.getMessage());
            return e.getMessage();
        }

    }

    public static void closeConnection() throws IOException {
        dataOutputStream.writeUTF(String.valueOf(Commands.QUIT));
        socket.close();
    }

    public void run() {
        try {
            connect();

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String command = commands.take();
                    Log.d(TAG, "sending command " + command);
                    result = sendCommand(command);
                    Log.d(TAG, "sending command done");
                    context.runOnUiThread(new Runnable() {
                        public void run() {
                            ((HomeActivity) context).updateStatus();
                        }
                    });

                }
            }
            catch (InterruptedException e) {
                Log.i(TAG, "InterruptedException" + e.getMessage());
            }

            closeConnection();
        }
        catch (IOException e) {
            Log.i(TAG, "IOException" + e.getMessage());
        }
    }

    public void cancel() {
        Thread.currentThread().interrupt();
    }

}
