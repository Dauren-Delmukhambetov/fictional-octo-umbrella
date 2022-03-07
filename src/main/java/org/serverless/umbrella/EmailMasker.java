package org.serverless.umbrella;

import java.util.regex.Pattern;

public class EmailMasker {

    private static Pattern EMAIL_PATTERN = Pattern.compile("([0-9A-Za-z-\\._])+@([0-9A-Za-z])+[\\.]?([0-9A-Za-z]){1,5}");

    public static String mask(final String data) {
        return data.replaceAll(EMAIL_PATTERN.pattern(), "masked@email.com");
    }
}
