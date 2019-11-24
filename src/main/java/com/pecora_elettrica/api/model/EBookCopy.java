package com.pecora_elettrica.api.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "t_book_copy")
public class EBookCopy implements Serializable {

    /** static */
    private static final long serialVersionUID = 80042067L;

    /** instance **/

    @EmbeddedId
    @Getter
    @Setter
    private EBookCopy.PKey key;

    @Column(name = "is_available", nullable = false, updatable = true)
    @Getter
    @Setter
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("book")
    @JoinColumn(name = "book")
    @Getter
    private EBook book;

    /** utils */

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof EBookCopy))
            return false;

        EBookCopy obc = (EBookCopy) o;

        return obc.key.equals(key);
    }

    /** PKEY **/
    @Getter
    @Setter
    @NoArgsConstructor
    @Embeddable
    public static class PKey implements Serializable {

        /** static **/
        private static final long serialVersionUID = 800420679437L;

        /** instance **/
        @Column(name = "book", length = 17, nullable = false, updatable = false)
        private String book;

        @Column(name = "copy_no", length = 4, nullable = false, updatable = false)
        private String copyNumber;

        /** utils */

        @Override
        public int hashCode() {
            return Objects.hash(book, copyNumber);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof EBookCopy.PKey))
                return false;

            EBookCopy.PKey opk = (PKey) o;

            return book.equals(opk.book) && copyNumber.equals(opk.copyNumber);
        }
    }
}
