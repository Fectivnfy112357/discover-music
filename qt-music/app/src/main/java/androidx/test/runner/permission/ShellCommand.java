package androidx.test.runner.permission;

/* loaded from: classes.dex */
public abstract class ShellCommand {
    private static final String SAFE_PUNCTUATION = "@%-_+:,./";

    abstract void execute() throws Exception;

    static String shellEscape(String word) {
        int length = word.length();
        if (length == 0) {
            return "''";
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = word.charAt(i);
            if (!Character.isLetterOrDigit(cCharAt) && SAFE_PUNCTUATION.indexOf(cCharAt) == -1) {
                String strReplace = word.replace("'", "'\\''");
                return new StringBuilder(String.valueOf(strReplace).length() + 2).append("'").append(strReplace).append("'").toString();
            }
        }
        return word;
    }
}
