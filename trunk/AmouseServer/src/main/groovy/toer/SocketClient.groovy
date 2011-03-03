package toer

/**
 * Created by te - Date: 2/26/11
 */
class SocketClient {

    static void main(args) {
        Socket s = new Socket("localhost", Utils.port);

        s.withStreams { input, output ->
            println "saying hello"


            output << "mouseMove(20,20)\n"
             println input.newReader().readLine()
            output << "mouseMove(20,20)\n"
             println input.newReader().readLine()
            output << "quit\n"
             //println input.newReader().readLines()

            print "response:"

        }
        println "press button"
        int button = System.in.read()
        println button
    }
}
