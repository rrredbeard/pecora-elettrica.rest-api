package com.pecora_elettrica.api.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "t_book")
public class EBook implements Serializable {

    /** static */

    private static final long serialVersionUID = 8004L;

    /** instance */

    @Id
    @Column(name = "isbn", length = 17)
    @Getter
    @Setter
    private String isbn;

    @Column(name = "title", length = 100, nullable = false, updatable = false)
    @Getter
    @Setter
    private String title;

    @Column(name = "author", length = 50, nullable = false, updatable = false)
    @Getter
    @Setter
    private String author;

    @Column(name = "pages_no", nullable = false, updatable = false)
    @Getter
    @Setter
    private Long pagesNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "publication", nullable = false, updatable = false)
    @Getter
    @Setter
    private Date publication;

    @Column(name = "prev_edition", length = 17, nullable = true, insertable = false, updatable = false)
    @Getter
    @Setter
    private String previous;

    /** joins **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prev_edition")
    @Getter
    private EBook previousEdition;

    // NB: ovviamente non è fisicamente scritta nel db
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "previousEdition")
    @Getter
    private Set<EBook> newestEditions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @Getter
    private Set<EBookCopy> bookCopies;

    /** utils **/

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof EBook))
            return false;

        EBook ob = (EBook) o;

        return isbn.equals(ob.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
