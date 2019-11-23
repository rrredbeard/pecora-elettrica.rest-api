package com.pecora_elettrica.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "t_book")
public class EBook implements Serializable {

    @Id
    @Column(name = "isbn", length = 17)
    private String isbn;

    @Column(name = "title", length = 100, nullable = false, updatable = false)
    private String title;
    @Column(name = "author", length = 50, nullable = false, updatable = false)
    private String author;

    @Column(name = "pages_no", nullable = false, updatable = false)
    private Long pagesNumber;

    @Temporal(TemporalType.DATE)
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

    /** setters **/
    public void setISBN(String i) {
        isbn = Utils.checkISBN(i);
    }

    public void setTitle(String t) {
        title = t;
    }

    public void setAuthor(String a) {
        author = a;
    }

    public void setPagesNumber(Long no) {
        pagesNumber = Utils.checkUnsignedInteger(no);
    }

    public void setPreviousEdition(EBook book) {
        previousEdition = book;
    }

    /** getters **/
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

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof EBook))
            return false;

        EBook ob = (EBook) o;
        return isbn.equals(ob.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    /** static */
    private static final long serialVersionUID = 1L;

}
