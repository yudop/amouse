package tribe.lost;

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
    private DatagramPacket datagramPacket;
    private String state;

    public AServer(int port) {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.err.println(SERVER_ERROR + e.getMessage());
            System.exit(-1);
        }

        byte[] buf = new byte[AClientServerInterface.PACKET_LENGTH];
        datagramPacket = new DatagramPacket(buf, buf.length);
        System.out.println("waiting for data on port " + port);
        receive();
    }


    public void receive() {

        try {
            datagramSocket.receive(datagramPacket);
        } catch (IOException e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
        }

        String data = new String(datagramPacket.getData());
        String payload = data.substring(0, datagramPacket.getLength());
        System.out.println("payload received: " + payload);

        executeCommands(payload);

        String response = "executed " + payload;
        sendResponse(response);

        receive();
    }

    private void executeCommands(String payload) {
        String[] commands = payload.split(AClientServerInterface.TOP_COMMAND_DELIMITER);

        for (String command : commands) {
            String[] subCommands = command.split(AClientServerInterface.SUB_COMMAND_DELIMITER);
            if (AClientServerInterface.STATE_KEY_PRESS.equals(subCommands[0])) {


            }

            if (AClientServerInterface.STATE_WINAMP.equals(subCommands[0])) {
                WinampHandler.handle(subCommands);
            }
            if (AClientServerInterface.STATE_VLC.equals(subCommands[0])) {


            }
        }
    }

    private void sendResponse(String response) {
        byte[] buffer = response.getBytes();
        datagramPacket.setData(buffer);
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
