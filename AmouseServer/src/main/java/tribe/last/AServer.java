package tribe.last;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Tobias Ericsson
 */
public class AServer {

    private DatagramSocket datagramSocket;
    public BlockingQueue<String> responses = new LinkedBlockingQueue<String>();
    private static InetAddress clientAddress;
    private static int clientPort;

    public AServer(int port) {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.println("Failed to start server on port "+port+": " + e.getMessage());
            System.exit(-1);
        }
        KeyPressHandler.init();
        MouseHandler.init();
        System.out.println("waiting for data on port " + port);

        Thread receiver = new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        String response = responses.take();
                        System.out.println("sending response " + response);
                        sendResponse(response);
                    }
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException "+e.getMessage());
                }
            }
        });
        receiver.start();
        receive();

    }


    public void receive() {
        while (true) {
            byte[] buffer = new byte[AClientServerInterface.PACKET_LENGTH];
            DatagramPacket datagramPacketIncoming = new DatagramPacket(buffer, buffer.length);
            try {
                datagramSocket.receive(datagramPacketIncoming);
            } catch (IOException e) {
                System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
            }

            String data = new String(datagramPacketIncoming.getData());
            String payload = data.substring(0, datagramPacketIncoming.getLength());
            System.out.println("payload received: " + payload);

            clientAddress = datagramPacketIncoming.getAddress();
            clientPort = datagramPacketIncoming.getPort();

            String response = executeCommands(payload);
            responses.offer(response);
        }
    }

    private String executeCommands(String payload) {
        String[] commands = payload.split(AClientServerInterface.TOP_COMMAND_DELIMITER);
        String response = "unknown command";
        for (String command : commands) {
            String[] subCommands = command.split(AClientServerInterface.SUB_COMMAND_DELIMITER);
            if (AClientServerInterface.STATE_KEY_PRESS.equals(subCommands[0])) {

                try {
                    char[] keys = subCommands[1].toCharArray();
                    response = KeyPressHandler.handle(keys);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("ArrayIndexOutOfBoundsException " + e.getMessage());
                }
            }

            if (AClientServerInterface.STATE_WINAMP.equals(subCommands[0])) {
                response = WinampHandler.handle(subCommands);
            }
            if (AClientServerInterface.STATE_VLC.equals(subCommands[0])) {


            }
            if (AClientServerInterface.STATE_WIZMO.equals(subCommands[0])) {
                response = WizmoHandler.handle(subCommands);

            }
            if (AClientServerInterface.STATE_MOUSE_CLICK.equals(subCommands[0])) {
                response = MouseHandler.handleClick(subCommands);

            }
            if (AClientServerInterface.STATE_MOUSE_MOVE.equals(subCommands[0])) {
                response = MouseHandler.handleMove(subCommands);

            }
            
            if (AClientServerInterface.STATE_HANDSHAKE.equals(subCommands[0])) {
                response = "connected";
            }

            if (AClientServerInterface.STATE_DELETE_PRESS.equals(subCommands[0])) {
                response = KeyPressHandler.delete();
            }


        }
        return response;
    }

    private void sendResponse(String response) {
        byte[] buffer = response.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramPacket.setAddress(clientAddress);
        datagramPacket.setPort(clientPort);
        try {
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        int port = AClientServerInterface.PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new AServer(port);
    }
}
