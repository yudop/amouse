package toer.ss.client;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by te - Date: 3/3/11
 */
public class DatagramClient implements Runnable {
    private final static String TAG = Client.class.getName();
    public BlockingQueue<String> commands = new LinkedBlockingQueue<String>();
    private static DatagramSocket datagramSocket;
    private static InetAddress inetAddress;
























    public static void connect() throws IOException {
        Log.d(TAG, "connecting");

        inetAddress = InetAddress.getByName("192.168.0.103");
        datagramSocket =new DatagramSocket();


    }

    private static void sendCommand(String command) {
        Log.d(TAG, "command: " + command);

        byte[] buffer = command.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress,5000);
        try {
            datagramSocket.send(datagramPacket);
            /*
            byte[] rbuffer = new byte[64];

            DatagramPacket rPacket =new DatagramPacket(rbuffer, rbuffer.length);
            datagramSocket.receive(rPacket);
            String result =new String(rPacket.getData());
            Log.d(TAG,"result: "+result); */


        }
        catch (IOException e) {

            e.printStackTrace();

        }
    }

    public static void closeConnection() throws IOException {
        sendCommand(String.valueOf(Commands.QUIT));
        datagramSocket.close();
    }

    public void run() {
        try {
            connect();

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String command = commands.take();
                    //Log.d(TAG, "sending command " + command);
                    sendCommand(command);
                    //Log.d(TAG, "sending command done");
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
