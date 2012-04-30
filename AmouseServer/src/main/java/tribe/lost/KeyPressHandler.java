package tribe.lost;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Tobias Ericsson
 */
public class KeyPressHandler {

    static Robot robot;

    public static void init() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static String handle(char[] keys) {
        try {
            for (char key : keys) {
                System.out.println("key " + key);
                switch (key) {
                    case '.':
                        robot.keyPress(KeyEvent.VK_PERIOD);
                        robot.keyRelease(KeyEvent.VK_PERIOD);
                        break;
                    case ',':
                        robot.keyPress(KeyEvent.VK_COMMA);
                        robot.keyRelease(KeyEvent.VK_COMMA);
                        break;
                    case '@':
                        robot.keyPress(KeyEvent.VK_ALT);
                        robot.keyPress(KeyEvent.VK_NUMPAD6);
                        robot.keyRelease(KeyEvent.VK_NUMPAD6);
                        robot.keyPress(KeyEvent.VK_NUMPAD4);
                        robot.keyRelease(KeyEvent.VK_NUMPAD4);
                        robot.keyRelease(KeyEvent.VK_ALT);
                        break;
                    case ':':
                        robot.keyPress(KeyEvent.VK_COLON);
                        robot.keyRelease(KeyEvent.VK_COLON);
                        break;
                    case ';':
                        robot.keyPress(KeyEvent.VK_SEMICOLON);
                        robot.keyRelease(KeyEvent.VK_SEMICOLON);
                        break;
                    case 'a':
                    case 'A':
                        robot.keyPress(KeyEvent.VK_A);
                        robot.keyRelease(KeyEvent.VK_A);
                        break;
                    case 'b':
                    case 'B':
                        robot.keyPress(KeyEvent.VK_B);
                        robot.keyRelease(KeyEvent.VK_B);
                        break;
                    case 'c':
                    case 'C':
                        robot.keyPress(KeyEvent.VK_C);
                        robot.keyRelease(KeyEvent.VK_C);
                        break;
                    case 'd':
                    case 'D':
                        robot.keyPress(KeyEvent.VK_D);
                        robot.keyRelease(KeyEvent.VK_D);
                        break;
                    case 'e':
                    case 'E':
                        robot.keyPress(KeyEvent.VK_E);
                        robot.keyRelease(KeyEvent.VK_E);
                        break;
                    case 'f':
                    case 'F':
                        robot.keyPress(KeyEvent.VK_F);
                        robot.keyRelease(KeyEvent.VK_F);
                        break;
                    case 'g':
                    case 'G':
                        robot.keyPress(KeyEvent.VK_G);
                        robot.keyRelease(KeyEvent.VK_G);
                        break;
                    case 'h':
                    case 'H':
                        robot.keyPress(KeyEvent.VK_H);
                        robot.keyRelease(KeyEvent.VK_H);
                        break;
                    case 'i':
                    case 'I':
                        robot.keyPress(KeyEvent.VK_I);
                        robot.keyRelease(KeyEvent.VK_I);
                        break;
                    case 'j':
                    case 'J':
                        robot.keyPress(KeyEvent.VK_J);
                        robot.keyRelease(KeyEvent.VK_J);
                        break;
                    case 'k':
                    case 'K':
                        robot.keyPress(KeyEvent.VK_K);
                        robot.keyRelease(KeyEvent.VK_K);
                        break;
                    case 'l':
                    case 'L':
                        robot.keyPress(KeyEvent.VK_L);
                        robot.keyRelease(KeyEvent.VK_L);
                        break;
                    case 'm':
                    case 'M':
                        robot.keyPress(KeyEvent.VK_M);
                        robot.keyRelease(KeyEvent.VK_M);
                        break;
                    case 'n':
                    case 'N':
                        robot.keyPress(KeyEvent.VK_N);
                        robot.keyRelease(KeyEvent.VK_N);
                        break;
                    case 'o':
                    case 'O':
                        robot.keyPress(KeyEvent.VK_O);
                        robot.keyRelease(KeyEvent.VK_O);
                        break;
                    case 'p':
                    case 'P':
                        robot.keyPress(KeyEvent.VK_P);
                        robot.keyRelease(KeyEvent.VK_P);
                        break;
                    case 'q':
                    case 'Q':
                        robot.keyPress(KeyEvent.VK_Q);
                        robot.keyRelease(KeyEvent.VK_Q);
                        break;
                    case 'r':
                    case 'R':
                        robot.keyPress(KeyEvent.VK_R);
                        robot.keyRelease(KeyEvent.VK_R);
                        break;
                    case 's':
                    case 'S':
                        robot.keyPress(KeyEvent.VK_S);
                        robot.keyRelease(KeyEvent.VK_S);
                        break;
                    case 't':
                    case 'T':
                        robot.keyPress(KeyEvent.VK_T);
                        robot.keyRelease(KeyEvent.VK_T);
                        break;
                    case 'u':
                    case 'U':
                        robot.keyPress(KeyEvent.VK_U);
                        robot.keyRelease(KeyEvent.VK_U);
                        break;
                    case 'v':
                    case 'V':
                        robot.keyPress(KeyEvent.VK_V);
                        robot.keyRelease(KeyEvent.VK_V);
                        break;
                    case 'w':
                    case 'W':
                        robot.keyPress(KeyEvent.VK_W);
                        robot.keyRelease(KeyEvent.VK_W);
                        break;
                    case 'x':
                    case 'X':
                        robot.keyPress(KeyEvent.VK_X);
                        robot.keyRelease(KeyEvent.VK_X);
                        break;
                    case 'y':
                    case 'Y':
                        robot.keyPress(KeyEvent.VK_Y);
                        robot.keyRelease(KeyEvent.VK_Y);
                        break;
                    case 'z':
                    case 'Z':
                        robot.keyPress(KeyEvent.VK_Z);
                        robot.keyRelease(KeyEvent.VK_Z);
                        break;
                    case 'å':
                    case 'Å':
                        robot.keyPress(KeyEvent.VK_ALT);
                        robot.keyPress(KeyEvent.VK_NUMPAD1);
                        robot.keyRelease(KeyEvent.VK_NUMPAD1);
                        robot.keyPress(KeyEvent.VK_NUMPAD4);
                        robot.keyRelease(KeyEvent.VK_NUMPAD4);
                        robot.keyPress(KeyEvent.VK_NUMPAD3);
                        robot.keyRelease(KeyEvent.VK_NUMPAD3);
                        robot.keyRelease(KeyEvent.VK_ALT);
                        break;

                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException" + e.getMessage() + e);
            e.printStackTrace();
            return "something wrong";
        }
        return "all good";
    }
}
