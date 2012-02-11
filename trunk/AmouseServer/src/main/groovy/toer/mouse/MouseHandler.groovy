package toer.mouse

import java.awt.Robot
import java.awt.event.InputEvent
import toer.Utils

/**
 * Created by te - Date: 2/26/11
 */
class MouseHandler {

    static Robot robot
    static int currentX, currentY
    static GroovyShell groovyShell;


    static startRobot() {
        robot = new Robot()
        currentX = 300
        currentY = 300
        robot.mouseMove(currentX, currentY)
        groovyShell = new GroovyShell();
    }

    static stopRobot() {
        robot = null
        groovyShell = null
    }

    static void mouseMove(int dx, int dy) {
        currentX = currentX + dx
        currentY = currentY + dy
        robot.mouseMove(currentX, currentY)
    }

    static void mouseLeftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK)
        robot.mouseRelease(InputEvent.BUTTON1_MASK)
    }

    static void mouseMiddleClick() {
        robot.mousePress(InputEvent.BUTTON2_MASK)
        robot.mouseRelease(InputEvent.BUTTON2_MASK)
    }

    static void mouseRightClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK)
        robot.mouseRelease(InputEvent.BUTTON3_MASK)
    }

    static String evaluateCommand(String[] command) {
        try {
            //groovyShell.evaluate("toer.mouse.MouseHandler.${command}")
            switch (Integer.parseInt(command[0])) {
                case Utils.MOUSE_LEFT_CLICK:
                    mouseLeftClick();
                    return "LEFT"
                case Utils.MOUSE_MIDDLE_CLICK:
                    mouseMiddleClick();
                    return "MIDDLE"
                case Utils.MOUSE_RIGHT_CLICK:
                    mouseRightClick();
                    return "RIGHT"
                case Utils.MOUSE_MOVE:
                    mouseMove(Integer.parseInt(command[1]), Integer.parseInt(command[2]))
                    return "MOVE"
            }
            return "UNKNOWN"
        }
        catch (Exception e) {
            println e.class.getName() + " : " + e.message
            return "failed"
        }
    }

}
