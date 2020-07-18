package uk.gsck.midi;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class KeyHandler {

    public static void sendKeys(Robot robot, String keys) {
        for (char c : keys.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            if (Character.isUpperCase(c)) {
                robot.keyPress(VK_SHIFT);
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            if (Character.isUpperCase(c)) {
                robot.keyRelease(VK_SHIFT);
            }
        }
    }

    public static void pressKey(char character, Robot robot) {
        switch (character) {
            case 'A':
                robot.keyPress(VK_Z);
                robot.keyRelease(VK_Z);
                break;
            case 'B':
                robot.keyPress(VK_X);
                robot.keyRelease(VK_X);
                break;
            case 'C':
                robot.keyPress(VK_C);
                robot.keyRelease(VK_C);
                break;
            case 'D':
                robot.keyPress(VK_V);
                robot.keyRelease(VK_V);
                break;
            case 'E':
                robot.keyPress(VK_B);
                robot.keyRelease(VK_B);
                break;
            case 'F':
                robot.keyPress(VK_N);
                robot.keyRelease(VK_N);
                break;

            case '1':
                robot.keyPress(VK_1);
                robot.keyRelease(VK_1);
                break;
            case '2':
                robot.keyPress(VK_2);
                robot.keyRelease(VK_2);
                break;
            case '3':
                robot.keyPress(VK_3);
                robot.keyRelease(VK_3);
                break;
            case '4':
                robot.keyPress(VK_4);
                robot.keyRelease(VK_4);
                break;
            case '5':
                robot.keyPress(VK_5);
                robot.keyRelease(VK_5);
                break;
            case '6':
                robot.keyPress(VK_6);
                robot.keyRelease(VK_6);
                break;
            case '7':
                robot.keyPress(VK_7);
                robot.keyRelease(VK_7);
                break;
            case '8':
                robot.keyPress(VK_8);
                robot.keyRelease(VK_8);
                break;
            case '9':
                robot.keyPress(VK_9);
                robot.keyRelease(VK_9);
                break;
            case '0':
                robot.keyPress(VK_0);
                robot.keyRelease(VK_0);
                break;

            case 'l':
                robot.keyPress(VK_ALT_GRAPH);
                robot.keyRelease(VK_ALT_GRAPH);
                break;
        }
    }
}
