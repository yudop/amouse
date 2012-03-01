package tribe.lost;

/**
 * Created by Tobias Ericsson
 */

import static tribe.lost.AClientServerInterface.*;

public class WinampHandler {

    interface WINAMP {
        String START = "C:\\home\\Program\\winamp\\winamp.exe";
        String EXE = "clever.exe";
        String PLAY = "play";
        String PAUSE = "pause";
        String NEXT = "next";
        String PREV = "prev";
        String VOL_UP = "volup";
        String VOL_DOWN = "voldn";
    }

    public static String handle(String[] commands) {

        int command = -1;
        try {
            command = Integer.valueOf(commands[1]);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException " + e.getMessage());
        }
        switch (command) {
            case Winamp.START:

                return AProcessExecuter.execute(WINAMP.START);
            case Winamp.PLAY:
                AProcessExecuter.execute(WINAMP.EXE, WINAMP.PLAY);
                return WINAMP.PLAY;

            case Winamp.STOP:
            case Winamp.PAUSE:
                AProcessExecuter.execute(WINAMP.EXE, WINAMP.PAUSE);
                return WINAMP.PAUSE;

            case Winamp.NEXT:
                AProcessExecuter.execute(WINAMP.EXE, WINAMP.NEXT);
                return WINAMP.NEXT;

            case Winamp.PREV:
                AProcessExecuter.execute(WINAMP.EXE, WINAMP.PREV);
                return WINAMP.PREV;

            case Winamp.VOL_UP:
                AProcessExecuter.execute(WINAMP.EXE, WINAMP.VOL_UP);
                return WINAMP.VOL_UP;

            case Winamp.VOL_DOWN:
                AProcessExecuter.execute(WINAMP.EXE, WINAMP.VOL_DOWN);
                return WINAMP.VOL_DOWN;
            default:
                return "unknown command " + command;
        }
    }
}
