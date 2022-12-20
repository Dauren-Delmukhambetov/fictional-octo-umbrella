package org.serverless.umbrella;

import net.datafaker.Faker;

import java.util.regex.Pattern;

import static java.lang.String.format;

public class EmailMasker {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("([0-9A-Za-z-\\._])+@([0-9A-Za-z])+[\\.]?([0-9A-Za-z]){1,5}");

    private static final Faker faker = new Faker();

    public static String mask(final String data) {
        return data.replaceAll(EMAIL_PATTERN.pattern(), fakeEmail()) + " [from production]";
    }

    private static String fakeEmail() {
        return format("%s@%s", faker.name().username(), faker.internet().domainName());
    }
}
