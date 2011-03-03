package toer.ss;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by te - Date: 3/2/11
 */
public class MouseHandler {

    static Robot robot;
    static int currentX, currentY;

    public static void startRobot() {
        try {
            robot = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        //Point point = currentMousePosition();
        //System.out.println("x,y: "+point.x+","+point.y);
        //currentX = point.x;
        //currentY = point.y;
        //robot.mouseMove(currentX, currentY);
    }

    public static void stopRobot() {
        robot = null;

    }

    public static Point currentMousePosition() {
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();

        return pointerInfo.getLocation();
    }

    static void mouseMove(int dx, int dy) {
        Point point = MouseInfo.getPointerInfo().getLocation();
        currentX = point.x + dx - 1280;
        currentY = point.y + dy;
        //currentX = currentX + dx;
        //currentY = currentY + dy;
        System.out.println("x,y: "+currentX+","+currentY);
        robot.mouseMove(currentX, currentY);
    }

    static void mouseLeftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    static void mouseMiddleClick() {
        robot.mousePress(InputEvent.BUTTON2_MASK);
        robot.mouseRelease(InputEvent.BUTTON2_MASK);
    }

    static void mouseRightClick() {
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);
    }

    static String evaluateCommand(String[] command) {
        try {
            switch (Integer.parseInt(command[0])) {
                case Commands.MOUSE_LEFT_CLICK:
                    mouseLeftClick();
                    return "LEFT";
                case Commands.MOUSE_MIDDLE_CLICK:
                    mouseMiddleClick();
                    return "MIDDLE";
                case Commands.MOUSE_RIGHT_CLICK:
                    mouseRightClick();
                    return "RIGHT";
                case Commands.MOUSE_MOVE:
                    mouseMove(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
                    return currentX+","+currentY;
            }
            return "UNKNOWN";
        }
        catch (Exception e) {
            return "failed";
        }
    }

}
