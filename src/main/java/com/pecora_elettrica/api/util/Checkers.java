package com.pecora_elettrica.api.util;

import java.util.Objects;

public class Checkers {
    static String checkISBN(String isbn) {
        Objects.requireNonNull(isbn);

        return checkFixedLength(17, isbn);
    }

    static Long checkUnsignedInteger(Long i) {
        Objects.requireNonNull(i);

        if (i.longValue() < 0)
            throw new IllegalArgumentException("Invalid unsigned integer");

        return i;
    }

    static String checkFixedLength(int length, String s) {
        if (s.length() != length)
            throw new IllegalArgumentException("Invalid String length");
        return s;
    }
}
