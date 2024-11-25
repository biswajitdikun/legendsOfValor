package util;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
/**
 * Generic class handling user input processing.
 * Supports type-safe input validation, parsing,
 * and error handling for different data types.
 */
public class Inputhandler<T> {
    private static final Scanner scanner = new Scanner(System.in);
    private final Function<String, T> parser;
    private final String typeName;

    @SuppressWarnings("unchecked")
    public Inputhandler(Class<T> type) {
        this.typeName = type.getSimpleName();

        if (type == Integer.class) {
            this.parser = str -> (T) Integer.valueOf(str);
        } else if (type == Double.class) {
            this.parser = str -> (T) Double.valueOf(str);
        } else if (type == Boolean.class) {
            // this.parser = str -> (T) Boolean.valueOf(str);
            this.parser = str -> {
                String input = str.trim().toLowerCase();
                if (input.equals("t") || input.equals("y") || input.equals("true") || input.equals("yes")) {
                    return (T) Boolean.TRUE;
                } else if (input.equals("f") || input.equals("n") || input.equals("false") || input.equals("no")) {
                    return (T) Boolean.FALSE;
                } else {
                    throw new IllegalArgumentException();
                }
            };
        } else if (type == String.class) {
            this.parser = str -> (T) str;
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type.getName());
        }
    }

    public Inputhandler(Function<String, T> customParser, String typeName) {
        this.parser = customParser;
        this.typeName = typeName;
    }

    public T getInput(String prompt, Predicate<T> validator, String validationMessage) {
        while (true) {
            // System.out.print(prompt);
            Outputhandler.printInfo(prompt, ConsoleColor.GREEN);
            String input = scanner.nextLine().trim();

            if (input.toLowerCase().equals("q")) {
                System.exit(0);
            }

            try {
                T parsedInput = parser.apply(input);
                if (validator.test(parsedInput)) {
                    return parsedInput;
                } else {
                    Outputhandler.printlnInfo(validationMessage, ConsoleColor.RED);
                }
            } catch (Exception e) {
                Outputhandler.printlnInfo("Invalid input. Please enter a valid " + typeName + "!", ConsoleColor.RED);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T getInput(String prompt, T min, T max) {
        if (!(min instanceof Comparable && max instanceof Comparable)) {
            throw new IllegalArgumentException("Min and max must be Comparable");
        }

        String validationMessage = String.format("Please enter a value between %s and %s!", min, max);

        return getInput(
                prompt,
                value -> {
                    Comparable<T> cMin = (Comparable<T>) min;
                    Comparable<T> cMax = (Comparable<T>) max;
                    return cMin.compareTo(value) <= 0 && cMax.compareTo(value) >= 0;
                },
                validationMessage
        );
    }

    public T getInput(String prompt, @SuppressWarnings("unchecked") T... validOptions) {
        Set<T> validSet = new HashSet<>(Arrays.asList(validOptions));
        String validationMessage = "Valid options are: " + Arrays.toString(validOptions);

        return getInput(
                prompt,
                validSet::contains,
                validationMessage
        );
    }

    public static int getIntInputWithBound(String prompt, int min, int max) {
        return new Inputhandler<>(Integer.class).getInput(prompt, min, max);
    }

    public static String getStringInput() {
        return new Inputhandler<>(String.class).getInput("Enter a string: ", s -> true, "");
    }

    public static boolean getBooleanInput() {
        return new Inputhandler<>(Boolean.class).getInput(
                "Enter T/Y for true, F/N for false: ",
                input -> true,
                "Invalid input. Please enter T/Y or F/N."
        );
    }

    public static int getIntInput() {
        return new Inputhandler<>(Integer.class).getInput("Enter a number: ", i -> true, "");
    }

    public static String getStringInputIgnoreCase(String prompt, String... validOptions) {
        Set<String> validSet = new HashSet<>();
        for (String option : validOptions) {
            validSet.add(option.toLowerCase());
        }

        return new Inputhandler<>(String.class).getInput(
                prompt,
                input -> validSet.contains(input.toLowerCase()),
                "Valid options are: " + Arrays.toString(validOptions)
        );
    }

    public static void close() {
        scanner.close();
    }
}