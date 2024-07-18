package sudapapa.java.codepack.extension;

public class StringExtension {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNullOrEmptyOrBlank(String s) {
        return s == null || s.isBlank() || s.isEmpty();
    }

    public static boolean isNullOrBlank(String s) {
        return s == null || s.isBlank();
    }
}
