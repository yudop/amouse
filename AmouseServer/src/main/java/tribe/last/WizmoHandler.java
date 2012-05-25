package tribe.last;

/**
 * Created by Tobias Ericsson
 */

import static tribe.last.AClientServerInterface.*;

public class WizmoHandler {

    interface Wizmo {
        String EXE = "wizmo.exe";
        String SHUTDOWN = "shutdown";
        String RESTART = "reboot";
        String HIBERNATE = "hibernate";
        String SCREEN_SAVER = "blackout";
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
            case WIZMO.SHUTDOWN:
                AProcessExecuter.execute(Wizmo.EXE, Wizmo.SHUTDOWN);
                return Wizmo.SHUTDOWN;
            case WIZMO.HIBERNATE:
                AProcessExecuter.execute(Wizmo.EXE, Wizmo.HIBERNATE);
                return Wizmo.HIBERNATE;

            case WIZMO.RESTART:
                AProcessExecuter.execute(Wizmo.EXE, Wizmo.RESTART);
                return Wizmo.RESTART;

            case WIZMO.SCREEN_SAVER:
                String response = AProcessExecuter.execute(Wizmo.EXE, Wizmo.SCREEN_SAVER);
                return response;

            default:
                return "unknown command " + command;
        }
    }
}

