package util;

/**
 * Class managing formatted console output.
 * Provides methods for colored text output,
 * error messages, and game state display.
 */

public class Outputhandler {
    public static void printlnInfo(String message, ConsoleColor backgroundcolor, ConsoleColor textcolor) {
        System.out.println(textcolor.format(backgroundcolor.format(message)));
    }

    public static void printlnInfo(String message, ConsoleColor color) {
        System.out.println(color.format(message));
    }

    public static void printInfo(String message, ConsoleColor color){
        System.out.print(color.format(message));
    }

    public static void printError(String message) {
        System.out.println(ConsoleColor.RED.format(message));
    }





}
