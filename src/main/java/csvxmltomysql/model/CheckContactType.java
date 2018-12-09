package csvxmltomysql.model;

public class CheckContactType {

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isAlphabetic(String input) {
        if (input == null) {
            return false;
        }
        String[] inputsplit = input.split("");
        for (int i = 0; i < inputsplit.length; i++) {
            if (!Character.isLetter(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
