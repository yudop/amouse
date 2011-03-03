package toer.ss;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by te - Date: 3/2/11
 */
public class SocketServer {

    public static void main(String[] args) {

        int port = 5000;
        if (args.length>0) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        }
        catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
            System.exit(-1);
        }

        while (true) {
            System.out.println("waiting for client on port "+port);
            try {
                Socket socket = serverSocket.accept();

                System.out.println("client connected");

                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                String[] commandSplitted = new String[]{"0"};

                String result;
                MouseHandler.startRobot();

                while (Integer.parseInt(commandSplitted[0]) != Commands.QUIT) {
                    String command = dataInputStream.readUTF();
                    System.out.println("command: " + command);
                    commandSplitted = command.split(" ");
                    result = MouseHandler.evaluateCommand(commandSplitted);
                    dataOutputStream.writeUTF(result);
                }

                MouseHandler.stopRobot();

            }
            catch (IOException e) {
                System.out.println("IOException " + e.getMessage());
            }
        }

    }
}




