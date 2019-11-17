package com.pecora_elettrica.api.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "t_book_copy")
public class EBookCopy implements Serializable {

    @EmbeddedId
    private EBookCopy.PKey key;

    @Column(name = "is_available", nullable = false, updatable = true)
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("book")
    @JoinColumn(name = "book")
    private EBook book;

    /** getters */

    public EBookCopy.PKey getKey() {
        return key;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public EBook getBook() {
        return book;
    }

    /** static */

    private static final long serialVersionUID = 2L;

    @Embeddable
    public static class PKey implements Serializable {

        private static final long serialVersionUID = 12L;

        @Column(name = "book", nullable = false, updatable = false)
        private String book;

        @Column(name = "copy_no", nullable = false, updatable = false)
        private String copyNumber;

        /** getters */

        public String getBook() {
            return book;
        }

        public String getCopyNumber() {
            return copyNumber;
        }

        /** utils */

        PKey(String isbn, String no) {
            Utils.checkISBN(isbn);
            Utils.checkFixedLength(4, no);

            this.book = isbn;
            this.copyNumber = no;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof EBookCopy.PKey))
                return false;
            EBookCopy.PKey ok = (EBookCopy.PKey) o;

            return book.equals(ok.book) && copyNumber.equals(ok.copyNumber);
        }

        @Override
        public int hashCode() {
            return Objects.hash(book, copyNumber);
        }
    }
}