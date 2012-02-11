package tribe.lost;

import java.net.DatagramPacket;

/**
 * Created by Tobias Ericsson
 */
public class AMain {

    public static void main(String[] args) {
        DatagramPacket datagramPacket, returnpacket;

        int port = AClientServerInterface.PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new AServer(port);
    }
}
