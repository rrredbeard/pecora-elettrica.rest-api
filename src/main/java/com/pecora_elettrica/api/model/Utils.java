package com.pecora_elettrica.api.model;

import java.util.Objects;

class Utils {
    static void checkISBN(String isbn){
        Objects.requireNonNull(isbn);

        checkFixedLength(17, isbn);
    }

    static void checkUnsignedInteger(Long i){
        Objects.requireNonNull(i);

        if( i.longValue() < 0 )
            throw new IllegalArgumentException("Invalid unsigned integer");
    }

    static void checkFixedLength(int length, String s){
        if( s.length() != length )
            throw new IllegalArgumentException("Invalid String length");
    }
}