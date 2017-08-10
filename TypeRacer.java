package xyz.betanyan.typeracer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TypeRacer {

    public static void main(String[] args) {

        System.out.println("Enter the text to type");

        String message = new Scanner(System.in).nextLine();

        System.out.println("Got text: " + message);
        System.out.println("Typing in 3.5 seconds");

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Robot robot = new Robot();

            for (char c : message.toCharArray()) {

                if (Character.isUpperCase(c) || c == '?' || c == ':') {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }

                int code = 0;

                if (c == ' ') code = KeyEvent.VK_SPACE;
                else if (c == ',') code = KeyEvent.VK_COMMA;
                else if (c == '.') code = KeyEvent.VK_PERIOD;
                else if (c == ':') code = KeyEvent.VK_SEMICOLON;
                else if (c == '\'') code = KeyEvent.VK_QUOTE;
                else if (c == '-') code = KeyEvent.VK_MINUS;
                else if (c == ';') code = KeyEvent.VK_SEMICOLON;
                else if (c == '?') code = KeyEvent.VK_SLASH;
                else if (c == '!') code = KeyEvent.VK_EXCLAMATION_MARK;
                else try {
                    code = (int) KeyEvent.class.getField(("VK_" + c).toUpperCase()).get(null);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }

                robot.keyPress(code);
                robot.keyRelease(code);
                robot.keyRelease(KeyEvent.VK_SHIFT);

                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(15, 45));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

}
