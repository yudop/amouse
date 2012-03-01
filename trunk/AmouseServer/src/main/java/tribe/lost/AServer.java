package tribe.lost;

import sun.java2d.pipe.BufferedTextPipe;
import toer.ss.MouseHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import static tribe.lost.AServerCommandInterface.*;
import static tribe.lost.AClientServerInterface.*;

/**
 * Created by Tobias Ericsson
 */
public class AServer {


    private static String SERVER_ERROR = "SERVER ERROR: ";

    private DatagramSocket datagramSocket;
    private final DatagramPacket datagramPacketIncoming;

    private String state;

    public AServer(int port) {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.println(SERVER_ERROR + e.getMessage());
            System.exit(-1);
        }
        KeyPressHandler.init();

        byte[] buffer = new byte[AClientServerInterface.PACKET_LENGTH];
        datagramPacketIncoming = new DatagramPacket(buffer, buffer.length);
        System.out.println("waiting for data on port " + port);
        receive();
    }


    public void receive() {

        try {
            datagramSocket.receive(datagramPacketIncoming);
        } catch (IOException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
        }

        String data = new String(datagramPacketIncoming.getData());
        String payload = data.substring(0, datagramPacketIncoming.getLength());
        System.out.println("payload received: " + payload);

        String response = executeCommands(payload);
        System.out.println("executed " + response);
        sendResponse(response);

        receive();
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
        }
        return response;
    }

    private void sendResponse(String response) {
        byte[] buffer = response.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
        datagramPacket.setAddress(datagramPacketIncoming.getAddress());
        datagramPacket.setPort(datagramPacketIncoming.getPort());
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
