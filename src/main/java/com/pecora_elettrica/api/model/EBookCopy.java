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

    /** setters **/

    public void setKey(EBookCopy.PKey k) {
        key = k;
    }

    public void setIsAvailable(Boolean ia) {
        isAvailable = ia;
    }

    /** static */

    private static final long serialVersionUID = 2L;

    @Embeddable
    public static class PKey implements Serializable {

        private static final long serialVersionUID = 12L;

        @Column(name = "book", length = 17, nullable = false, updatable = false)
        private String book;

        @Column(name = "copy_no", length = 4, nullable = false, updatable = false)
        private String copyNumber;

        /** getters */

        public String getBook() {
            return book;
        }

        public String getCopyNumber() {
            return copyNumber;
        }

        /** setters **/

        public void setBook(String isbn) {
            book = Utils.checkISBN(isbn);
        }

        public void setCopyNumber(String no) {
            copyNumber = Utils.checkFixedLength(4, no);
        }

        /** utils */

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
