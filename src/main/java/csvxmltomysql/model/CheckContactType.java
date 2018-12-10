package csvxmltomysql.model;

public class CheckContactType {

    public static boolean isTelNumber(String input) {
        if (input == null) {
            return false;
        }
        input = input.replaceAll("\\s", "");
        input = input.replace("-", "");
        input = input.replace("+", "");
        if (input.length() < 9 || input.length()>9) {
            return false;
        }
        try {
            double d = Double.parseDouble(input);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isJabber(String input) {
        if (input == null) {
            return false;
        }
        if (input.toLowerCase().startsWith("jbr:")) {
            return true;
        }
        return false;
    }

    public static boolean isEmail(String input) {
        if (input == null) {
            return false;
        }
        if (input.startsWith("@") || input.endsWith("@")) {
            return false;
        }
        if (input.contains("@")) {
            return true;
        }
        return false;
    }
}
