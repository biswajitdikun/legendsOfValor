package util;

/**
 * Enum managing console color formatting.
 * Provides color codes for text and background,
 * supports formatted output for game visualization.
 */
public enum ConsoleColor {
    // Enum to define the colors for the console output.
    RESET("\033[0m"),
    BLACK("\033[0;30m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    BLUE("\033[0;34m"),
    PURPLE("\033[0;35m"),
    CYAN("\033[0;36m"),
    WHITE("\033[0;37m"),
    GRAY("\033[0;90m"),
    GOLD("\033[0;93m"),
    PINK("\033[0;95m"),

    // Background colors
    BG_RESET("\033[49m"),
    BG_BLACK("\033[40m"),
    BG_RED("\033[41m"),
    BG_GREEN("\033[42m"),
    BG_YELLOW("\033[43m"),
    BG_BLUE("\033[44m"),
    BG_PURPLE("\033[45m"),
    BG_CYAN("\033[46m"),
    BG_WHITE("\033[47m"),
    BG_GRAY("\033[100m"),
    BG_GOLD("\033[103m"),
    BG_PINK("\033[105m");
    
    private final String code;

    ConsoleColor(String code) {
        this.code = code;
    }

   public String getCode() {
        return code;
    }

    public String format(String text) {
        return code + text + RESET.code;
    }
}
