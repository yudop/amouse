package tribe.lost;

import java.awt.*;
import java.awt.event.InputEvent;

import static tribe.lost.AClientServerInterface.*;


/**
 * Created by IntelliJ IDEA.
 * User: TOER
 * Date: 2012-04-30
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
public class MouseHandler {

    static Robot robot;
    static int currentX,currentY;

    public static void init() {
        try {
            robot = new Robot();
            Point point = MouseInfo.getPointerInfo().getLocation();
            currentX=point.x;
            currentY=point.y;
        } catch (AWTException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static String handleMove(String[] command) {
        try {
            int x = Integer.parseInt(command[1]);
            int y = Integer.parseInt(command[2]);
            mouseMove(x, y);
        } catch (NumberFormatException e) {
            return "NumberFormatException " + e.getMessage();
        }
        return "x,y: "+currentX+","+currentY;
    }

    public static String handleClick(String[] command) {
        int click = Integer.parseInt(command[1]);
        Point point = MouseInfo.getPointerInfo().getLocation();
        currentX=point.x;
        currentY=point.y;
        switch (click) {
            case MOUSE.BUTTON_DOWN_LEFT:
                //robot.mousePress(InputEvent.BUTTON1_MASK);
                break;
            case MOUSE.BUTTON_UP_LEFT:
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                return "left button";
            case MOUSE.BUTTON_DOWN_MIDDLE:
                //robot.mousePress(InputEvent.BUTTON2_MASK);
                break;
            case MOUSE.BUTTON_UP_MIDDLE:
                robot.mouseRelease(InputEvent.BUTTON2_MASK);
                return "middle button";
            case MOUSE.BUTTON_DOWN_RIGHT:
                //robot.mousePress(InputEvent.BUTTON3_MASK);
                break;
            case MOUSE.BUTTON_UP_RIGHT:
                robot.mousePress(InputEvent.BUTTON3_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_MASK);
                return "right button";
        }
        return "clicked "+click;
    }


    static void mouseMove(int dx, int dy) {
        //Point point = MouseInfo.getPointerInfo().getLocation();
        currentX = currentX + dx;
        currentY = currentY + dy;
        //System.out.println("x,y: " + currentX + "," + currentY);
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
}
