package tribe.lost;

/**
 * Created by Tobias Ericsson
 */

import static tribe.lost.AClientServerInterface.*;

public class WinampHandler {

    interface WINAMP {
        String EXE = "clever.exe";
        String PLAY = "play";
        String PAUSE = "pause";
        String NEXT = "next";
        String PREV = "prev";
        String VOL_UP = "volup";
        String VOL_DOWN = "voldn";
    }

    public static void handle(String[] commands) {
        for (int k = 1; k < commands.length; k++) {
            int command = Integer.valueOf(commands[k]);
            switch (command) {
                case Winamp.PLAY:
                    AProcessExecuter.execute(WINAMP.EXE, WINAMP.PLAY);
                    break;
                case Winamp.PAUSE:
                    AProcessExecuter.execute(WINAMP.EXE, WINAMP.PAUSE);
                    break;
                case Winamp.NEXT:
                    AProcessExecuter.execute(WINAMP.EXE, WINAMP.NEXT);
                    break;
                case Winamp.PREV:
                    AProcessExecuter.execute(WINAMP.EXE, WINAMP.PREV);
                    break;
                case Winamp.VOL_UP:
                    AProcessExecuter.execute(WINAMP.EXE, WINAMP.VOL_UP);
                    break;
                case Winamp.VOL_DOWN:
                    AProcessExecuter.execute(WINAMP.EXE, WINAMP.VOL_DOWN);
                    break;
            }
        }


    }

}
