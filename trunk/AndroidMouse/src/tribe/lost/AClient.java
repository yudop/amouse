package tribe.lost;

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

    public static void connect(String ipAddress, int port) {
        System.out.println("connecting to port " + port);
        AClient.port = port;
        try {
            inetAddress = InetAddress.getByName(ipAddress);
        } catch (UnknownHostException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
            System.exit(-1);
        }
        try {
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(inetAddress, port);
        } catch (SocketException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
            System.exit(-1);
        }

        //byte[] buf = new byte[AClientServerInterface.PACKET_LENGTH];
        //datagramPacket = new DatagramPacket(buf, buf.length);
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

    public static String receiveResponse() {
        byte[] buf = new byte[AClientServerInterface.PACKET_LENGTH];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);

        try {
            datagramSocket.receive(datagramPacket);
        } catch (IOException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
        }

        String data = new String(datagramPacket.getData());
        String payload = data.substring(0, datagramPacket.getLength());
        System.out.println("payload received: " + payload);
        return payload;
    }

    public static void closeConnection() {
        System.out.println("closing connection");
        datagramSocket.close();
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
