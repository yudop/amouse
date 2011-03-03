package toer.ss;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by te - Date: 3/3/11
 */
public class DatagramSocketServer {

    public static void main(String[] args) {
        DatagramPacket datagramPacket, returnpacket;




        int port = 5000;
        if (args.length>0) {
            port = Integer.parseInt(args[0]);
        }

        int length = 64;
        try {
            DatagramSocket datagramSocket = new DatagramSocket(port);
            byte[] buf = new byte[length];
            datagramPacket = new DatagramPacket(buf, buf.length);
            MouseHandler.startRobot();

            while (true) {
                try {
                    System.out.println("waiting for data on port "+port);
                    datagramSocket.receive(datagramPacket);
                    //System.out.println("received data");
                    String data = new String(datagramPacket.getData());
                    String command = data.substring(0,datagramPacket.getLength());
                    System.out.println("command: "+command);

                    String[] commandSplitted = command.split(" ");
                    String result = MouseHandler.evaluateCommand(commandSplitted);
                    /*
                    byte[] buffer = result.getBytes();
                    returnpacket = new DatagramPacket(
                            buffer,
                            buffer.length,
                            datagramPacket.getAddress(),
                            datagramPacket.getPort());
                    datagramSocket.send(returnpacket);  */
                }
                catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
        catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
