package toer

import toer.mouse.MouseHandler

/**
 * Created by te - Date: 2/26/11
 */
class SocketServer {

    static void main(args) {
        ServerSocket serverSocket = new ServerSocket(Utils.port)
        println "waiting for clients"
        while (true) {
            serverSocket.accept() { socket ->
                println "client connected"
                socket.withStreams { input, output ->

                    DataOutputStream dataOutputStream = new DataOutputStream(output);
                    DataInputStream dataInputStream = new DataInputStream(input);

                    String[] commandSplitted = ["test"]

                    String result
                    MouseHandler.startRobot()

                    while (commandSplitted[0]!=Utils.QUIT) {
                        String command = dataInputStream.readUTF()
                        println "command: " + command
                        commandSplitted = command.split(" ")
                        result = executeCommand(commandSplitted)
                        dataOutputStream.writeUTF(command +" "+result)
                    }

                    MouseHandler.stopRobot()
                }

                println "waiting for clients"
            }
        }
    }

    private static String executeCommand(String... command) {


        //if (commandSplitted[0]>=10) {
            MouseHandler.evaluateCommand(command)

    }
}
