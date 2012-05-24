package tribe.last;

import android.util.Log;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Tobias Ericsson
 */
public class AClient {
    private final static String TAG = AClient.class.getName();
    public static BlockingQueue<String> commands = new LinkedBlockingQueue<String>();
    private static DatagramSocket datagramSocket;
    //private static DatagramPacket datagramPacket;
    private static InetAddress inetAddress;
    private static int port;
    private static boolean connected = false;

    public static void connectIfNotConnected(String ipAddress, int port) {
        if (datagramSocket == null || !datagramSocket.isConnected()) {
            connect(ipAddress, port);
        }
    }

    public static boolean connect(String ipAddress, int port) {
        AClient.port = port;
        try {
            inetAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            Log.e(TAG,e.getClass().getSimpleName() + " " + e.getMessage());
            return false;
        }
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(inetAddress, port);


            connected = false;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.d(TAG, "waiting for handshake response... ");
                        receiveResponse();
                        Log.d(TAG, "got handshake = connected");
                        connected = true;
                    } catch (IOException e) {
                        Log.e(TAG,e.getClass().getSimpleName() + " " + e.getMessage());
                    }
                }
            });
            thread.start();

            int k = 0;
            while (k<3 && !connected) {
                sendPayload(AClientServerInterface.STATE_HANDSHAKE);
                try {
                    Thread.sleep(2000);
                    k++;
                } catch (InterruptedException e) {
                    Log.e(TAG,e.getClass().getSimpleName() + " " + e.getMessage());
                }
            }
        } catch (SocketException e) {
            Log.e(TAG,e.getClass().getSimpleName() + " " + e.getMessage());
            return false;
        }

        return connected;

        //byte[] buf = new byte[AClientServerInterface.PACKET_LENGTH];
        //datagramPacket = new DatagramPacket(buf, buf.length);
    }

    public static void putCommandOnQueue(String command) {
        try {
            AClient.commands.put(command);
        } catch (InterruptedException e) {
            Log.e(TAG, "InterruptedException " + e.getMessage());
        }
    }

    public static void sendPayload(String payload) {
        Log.d(TAG,"sending payload: " + payload);
        byte[] buffer = payload.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
        try {

                datagramSocket.send(datagramPacket);

        } catch (IOException e) {
            Log.i(TAG,e.getClass().getSimpleName() + " " + e.getMessage());
            //System.exit(-1);
        }
    }

    public static String receiveResponse() throws IOException {
        byte[] buf = new byte[AClientServerInterface.PACKET_LENGTH];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);



                datagramSocket.receive(datagramPacket);





        String data = new String(datagramPacket.getData());
        String payload = data.substring(0, datagramPacket.getLength());
        Log.d(TAG,"payload received: " + payload);
        return payload;
    }

    public static void closeConnection() {
        Log.d(TAG,"closing connection");
        commands.clear();

        if (datagramSocket != null) {
            AClient.sendPayload(AClientServerInterface.STATE_CLOSING);
            datagramSocket.close();
            datagramSocket.disconnect();
            //datagramSocket = null;
        }
    }

}

/*                                                                               abba.,♦ab
byte[] rbuffer = new byte[64];

DatagramPacket rPacket =new DatagramPacket(rbuffer, rbuffer.length);
datagramSocket.receive(rPacket);
String result =new String(rPacket.getData());
Log.d(TAG,"result: "+result); */

/*




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
}  */
