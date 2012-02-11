package tribe.lost;

/**
 * Created by Tobias Ericsson
 */
public interface AClientServerInterface {

    int PORT = 8888;
    int PACKET_LENGTH = 128;
    String ERROR = "ERROR";
    String TOP_COMMAND_DELIMITER = "::";
    String SUB_COMMAND_DELIMITER = ";;";
    String STATE_WINAMP = "WINAMP";
    String STATE_VLC = "VLC";
    String STATE_WIZMO = "WIZMO";
    String STATE_KEY_PRESS = "KEY";

    //example payload: KEY;;a;;b;;c::WINAMP;;2;;3;;3::WIZMO;;0
    
    interface Winamp {
        int PLAY = 0;
        int PAUSE = 1;
        int NEXT = 2;
        int PREV = 3;
        int VOL_UP = 4;
        int VOL_DOWN = 5;
    }

    interface VLC {
        int PLAY = 0;
        int PAUSE = 1;
        int NEXT = 2;
        int VOL_UP = 3;
        int VOL_DOWN = 4;
    }

    interface WIZMO {
        int SHUTDOWN = 0;
        int RESTART = 1;
    }
}
