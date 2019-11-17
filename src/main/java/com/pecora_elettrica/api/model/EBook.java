package com.pecora_elettrica.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "t_book")
public class EBook implements Serializable {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title", nullable = false, updatable = false)
    private String title;
    @Column(name = "author", nullable = false, updatable = false)
    private String author;

    @Column(name = "pages_no", nullable = false, updatable = false)
    private Long pagesNumber;

    @Column(name = "publication", nullable = false, updatable = false)
    private Date publication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prev_edition", nullable = true)
    private EBook previousEdition;

    // NB: ovviamente non è fisicamente scritta nel db
    @OneToMany(mappedBy = "previousEdition", fetch = FetchType.LAZY)
    private Set<EBook> newestEditions;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Set<EBookCopy> bookCopies;

    /** getters */
    public String getISBN() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Long getPagesNumber() {
        return pagesNumber;
    }

    public Date getPublicationDate() {
        return publication;
    }

    public EBook getPreviousEdition() {
        return previousEdition;
    }

    public Set<EBook> getNewestEditions() {
        return newestEditions;
    }

    public Set<EBookCopy> getBookCopies() {
        return bookCopies;
    }

    /** utils */

    EBook(String isbn) {
        Utils.checkISBN(isbn);

        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EBook))
            return false;

        EBook ob = (EBook) o;
        return isbn.equals(ob.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    /** static */
    private static final long serialVersionUID = 1L;

}