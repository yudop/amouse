package tribe.last;

/**
 * Created by Tobias Ericsson
 */
public interface AClientServerInterface {

    int PORT = 9090;
    int PACKET_LENGTH = 128;
    String ERROR = "ERROR";
    String TOP_COMMAND_DELIMITER = "::";
    String SUB_COMMAND_DELIMITER = ";;";
    String STATE_WINAMP = "WINAMP";
    String STATE_VLC = "VLC";
    String STATE_WIZMO = "WIZMO";
    String STATE_KEY_PRESS = "KEY";
    String STATE_DELETE_PRESS = "DELETE";
    String STATE_MOUSE_CLICK = "MOUSE_CLICK";
    String STATE_MOUSE_MOVE = "MOUSE_MOVE";
    String STATE_HANDSHAKE = "HAND_SHAKE";

    //example payload: KEY;;a;;b;;c::WINAMP;;2;;3;;3::WIZMO;;0
    // MOUSE_MOVE;;120;;33::MOUSE_CLICK;;BUTTON_UP_LEFT::
    interface Winamp {
        int PLAY = 0;
        int PAUSE = 1;
        int NEXT = 2;
        int PREV = 3;
        int VOL_UP = 4;
        int VOL_DOWN = 5;
        int START = 6;
        int STOP = 7;
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
        int HIBERNATE = 2;
        int SCREEN_SAVER = 3;
    }

    interface MOUSE {
        int BUTTON_DOWN_LEFT = 0;
        int BUTTON_UP_LEFT = 1;
        int BUTTON_DOWN_RIGHT = 2;
        int BUTTON_UP_RIGHT = 3;
        int BUTTON_DOWN_MIDDLE = 4;
        int BUTTON_UP_MIDDLE = 5;
        int MOVE = 6;
        int WHEEL = 7;
    }
}
