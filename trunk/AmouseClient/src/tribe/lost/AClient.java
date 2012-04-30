package tribe.lost;

import android.app.Activity;
import android.util.Log;
import toer.ss.activity.HomeActivity;
import toer.ss.client.Commands;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by te - Date: 2/27/11
 */
public class AClient extends Thread {

    private final static String TAG = AClient.class.getName();

    public static DatagramSocket datagramSocket;
    //private static byte[] buffer = new byte[AClientServerInterface.PACKET_LENGTH];
    public static BlockingQueue<String> commands = new LinkedBlockingQueue<String>();

    private static InetAddress inetAddress;
    private static int port;

    public static void connect(String ipAddress, int port) {
        System.out.println("connecting to port " + port);
        AClient.port = port;
        try {
            inetAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
            //System.exit(-1);
        }
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(inetAddress, port);
        } catch (SocketException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
            //System.exit(-1);
        }



    }

    public static void sendPayload(String payload) {
        System.out.println("sending payload: " + payload);
        byte[] buffer = payload.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
            System.exit(-1);
        }
    }

    public static void closeConnection() {
        System.out.println("closing connection");
        datagramSocket.close();
    }

    public void run() {

        connect("192.168.0.100", AClientServerInterface.PORT);

        try {
            while (!Thread.currentThread().isInterrupted()) {
                Log.d(TAG, "trying to sending command ");
                String command = commands.take();
                Log.d(TAG, "sending command " + command);
                sendPayload(command);
            }
        } catch (InterruptedException e) {
            Log.i(TAG, "InterruptedException" + e.getMessage());
        }

        closeConnection();
    }

    public void cancel() {
        Thread.currentThread().interrupt();
    }

}
