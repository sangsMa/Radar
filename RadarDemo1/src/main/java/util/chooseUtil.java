package util;

public class chooseUtil {
    public static boolean choose(String s) {
        int length = s.length();
        if (s.charAt(length-1) == 'x' && s.charAt(length-2) == 's' && s.charAt(length-3) == 'l' && s.charAt(length-4) == 'x') {
            return true;
        }else {
            return false;
        }
    }
}
