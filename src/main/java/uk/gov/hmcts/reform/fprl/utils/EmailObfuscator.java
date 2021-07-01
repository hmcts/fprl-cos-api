package uk.gov.hmcts.reform.fprl.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailObfuscator {

    public static String obfuscate(String email) {
        if (!Optional.ofNullable(email).isPresent()) {
            throw new IllegalArgumentException("Email must not be empty");
        }

        String[] parts = email.split("@");

        if (parts.length != 2 || parts[0].length() < 1) {
            throw new IllegalArgumentException("Invalid email.");
        }

        return replaceAddressWithAsterisks(parts);
    }

    private static String replaceAddressWithAsterisks(String[] parts) {
        String address = parts[0];

        if (address.length() < 3) {
            return concatenateParts(address, 1, parts[1]);
        }

        return concatenateParts(address, 2, parts[1]);
    }

    private static String concatenateParts(String address, int i, String part) {
        return address.charAt(0) + StringUtils.repeat("*", address.length() - i) + "@" + part;
    }
}
